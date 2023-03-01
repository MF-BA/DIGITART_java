/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.zxing.WriterException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Source;
import com.stripe.model.Token;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerUpdateParams;
import com.stripe.param.SourceCreateParams;
import entity.Code128BarcodeGenerator;
import entity.Data;
import entity.Payment;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.main;
import utils.Conn;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PaymentController implements Initializable {

    @FXML
    private TextField cardNumberTextField;
    @FXML
    private TextField cvcTextField;
    @FXML
    private Button pay_button;
    @FXML
    private Label totalLabel;
    @FXML
    private TextField expirymTextField;
    @FXML
    private TextField expiryyTextField;
    @FXML
    private Label totalLabel1;
    @FXML
    private Button ticketbuy_qr_code;
    @FXML
    private Button ticketbuy_pdf_button;
    @FXML
    private AnchorPane anchor_2;
    @FXML
    private AnchorPane anchor_1;
    @FXML
    private TextField payment_email;
    @FXML
    private TextField payment_name;

    @FXML
    private void handlePayButtonAction(ActionEvent event) {
        if (payment_email.getText().isEmpty() || cardNumberTextField.getText().isEmpty() || expirymTextField.getText().isEmpty() || expiryyTextField.getText().isEmpty() || cvcTextField.getText().isEmpty() || payment_name.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Payment Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the required fields.");
            alert.showAndWait();
        }
        else{
        // Retrieve the card details from the text fields
        String cardNumber = cardNumberTextField.getText();
        String monthexpiry = expirymTextField.getText();
        String yearexpiry = expiryyTextField.getText();
        String cvc = cvcTextField.getText();

        // Create a token using the card details
        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", cardNumber);
        cardParams.put("exp_month", monthexpiry);
        cardParams.put("exp_year", yearexpiry);
        cardParams.put("cvc", cvc);

        Map<String, Object> tokenParams = new HashMap<>();
        tokenParams.put("card", cardParams);

        Token token;
        try {

            token = Token.create(tokenParams);
        } catch (StripeException ex) {
            // Handle the exception
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Payment Error");
            alert.setHeaderText(null);
            alert.setContentText("There was an error processing your payment. Please check your payment information and try again.");
            alert.showAndWait();
            return;
        }

        // Confirm the payment with the user
        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Payment");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to make this payment?");
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Charge the user's card for the total amount
            int amount = Integer.parseInt(totalLabel.getText()) * 100;
            String currency = "usd";

            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", amount);
            chargeParams.put("currency", currency);
            chargeParams.put("source", token.getId());

            try {
                Charge.create(chargeParams);
            } catch (StripeException ex) {
                // Handle the exception
                Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Payment Error");
                alert.setHeaderText(null);
                alert.setContentText("There was an error processing your payment. Please check your payment information and try again.");
                alert.showAndWait();
                return;
            }

            // Get the user ID (assuming the user ID is 1)
            int userId = 1;

            // Get the selected date from the date picker
            LocalDate purchaseDate = Data.purchaseDate;

            // Get the quantities from the spinners
            int nbAdult = Data.nbAdult;
            int nbTeenager = Data.nbTeenager;
            int nbStudent = Data.nbStudent;
            int totalPayment = Integer.parseInt(totalLabel.getText());

            // Create a new Payment object with the collected data
            Payment payment = new Payment(userId, purchaseDate, nbAdult, nbTeenager, nbStudent, totalPayment);

            try {
                Connection conn = Conn.getCon();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO payment (purchase_date, nb_adult, nb_teenager, nb_student, total_payment) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setDate(1, java.sql.Date.valueOf(payment.getPurchaseDate()));
                stmt.setInt(2, payment.getNbAdult());
                stmt.setInt(3, payment.getNbTeenager());
                stmt.setInt(4, payment.getNbStudent());
                stmt.setInt(5, payment.getTotalPayment());
                //data.user.getUserId();
                int affectedRows = stmt.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating payment failed, no rows affected.");
                }

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        payment.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating payment failed, no ID obtained.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Display a message to the user indicating that the payment was successful
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Payment Successful");
            alert.setHeaderText(null);
            alert.setContentText("Your payment was successful.");
            alert.showAndWait();

            anchor_2.setVisible(true);
            anchor_1.setVisible(false);
        }
        }
    }

    @FXML
    public void generateTicketPDF() throws FileNotFoundException, DocumentException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Ticket");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "test.pdf"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            PdfContentByte canvas = writer.getDirectContent();
            canvas.saveState();
            canvas.setColorFill(new BaseColor(239, 34, 40)); // #FFC8C8
            canvas.rectangle(0, 800, 700, 40);
            canvas.fill();
            canvas.restoreState();

            document.add(new Paragraph("\n")); // add a line break

            String Name = payment_name.getText();
            int qtyAdult = Data.nbAdult;
            int qtyTeen = Data.nbTeenager;
            int qtyStudent = Data.nbStudent;

            // Create greeting paragraph
            // Add a title
            Paragraph title = new Paragraph();
            title.add(new Chunk("Museum Ticket Receipt", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("\n"));
            Paragraph greeting = new Paragraph();
            greeting.add(new Chunk("Hi ", FontFactory.getFont(FontFactory.HELVETICA, 14)));
            greeting.add(new Chunk("Mr. " + Name + ", ", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD)));
            greeting.add(new Chunk("thanks for your reservation, enjoy your tour!", FontFactory.getFont(FontFactory.HELVETICA, 14)));
            greeting.setAlignment(Element.ALIGN_CENTER);
            PdfPCell greetingCell = new PdfPCell(greeting);
            greetingCell.setBackgroundColor(new BaseColor(229, 231, 230));
            greetingCell.setPadding(10);
            greetingCell.setBorder(Rectangle.NO_BORDER);
            greetingCell.setHorizontalAlignment(Element.ALIGN_CENTER);

            // Create a table with two columns
            PdfPTable table1 = new PdfPTable(2);
            // Add the greeting cell to the first row
            table1.addCell(greetingCell);
            // Add the reservation details cell to the second row
            // Add the date picker cell to the third row
            PdfPCell datePickerCell = new PdfPCell();
            datePickerCell.addElement(new Paragraph("Ticket's Date: " + Data.purchaseDate));
            table1.addCell(datePickerCell);
            // Add the table to the document
            document.add(table1);

            // Create reservation details table
            PdfPTable reservationDetails = new PdfPTable(2);
            reservationDetails.addCell(new PdfPCell(new Phrase("Number of Adult Tickets:")));
            reservationDetails.addCell(new PdfPCell(new Phrase(Integer.toString(qtyAdult))));
            reservationDetails.addCell(new PdfPCell(new Phrase("Number of Teenager Tickets:")));
            reservationDetails.addCell(new PdfPCell(new Phrase(Integer.toString(qtyTeen))));
            reservationDetails.addCell(new PdfPCell(new Phrase("Number of Student Tickets:")));
            reservationDetails.addCell(new PdfPCell(new Phrase(Integer.toString(qtyStudent))));

            PdfPCell reservationCell = new PdfPCell(reservationDetails);
            reservationCell.setPadding(10);
            reservationCell.setBackgroundColor(new BaseColor(229, 231, 230)); // #E5E7E6
            reservationCell.setBorder(Rectangle.NO_BORDER);

            // Create details table
            PdfPTable detailsTable = new PdfPTable(1);
            //detailsTable.addCell(greetingCell);
            detailsTable.addCell(reservationCell);
            document.add(detailsTable);

            // Create a cell for the date picker and add it to the second row
            // Create total payment paragraph
            PdfPTable table = new PdfPTable(1);
            Paragraph totalPayment = new Paragraph();
            totalPayment.add(new Chunk("Total Payment: ", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD)));
            totalPayment.add(new Chunk("$" + totalLabel.getText(), FontFactory.getFont(FontFactory.HELVETICA, 14)));
            totalPayment.setAlignment(Element.ALIGN_RIGHT);
            PdfPCell totalPaymentCell = new PdfPCell(totalPayment);
            totalPaymentCell.setBorder(Rectangle.NO_BORDER);
            document.add(new Paragraph("\n"));
            table.addCell(totalPaymentCell);
            document.add(table);

            // Add a date
            Paragraph date = new Paragraph();
            date.add(new Chunk("Date of the payment: " + LocalDate.now().toString(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
            date.setAlignment(Element.ALIGN_RIGHT);
            document.add(date);
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));

            // Add a thank you message
            Paragraph thankYou = new Paragraph();
            thankYou.add(new Chunk("Thank you for your purchase!", FontFactory.getFont(FontFactory.HELVETICA, 14)));
            thankYou.setAlignment(Element.ALIGN_CENTER);
            document.add(thankYou);
            document.add(new Paragraph("\n"));

            // Create a new instance of the Code128BarcodeGenerator class
            Code128BarcodeGenerator gen = new Code128BarcodeGenerator();

            // Generate the barcode image
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            gen.generateBarcode("1234567890", out); // Replace "1234567890" with your actual barcode data

            // Add the barcode image to the PDF
            Image img = Image.getInstance(out.toByteArray());
            img.scalePercent(50); // Adjust this value to change the scale of the barcode image
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            img.setAlignment(Element.ALIGN_CENTER);
            document.add(img);

            document.close();
        }
    }

    @FXML
    private void ticketbuy_qr_code(ActionEvent event) throws WriterException, DocumentException {

        displayQRCode(event);
    }

    private void displayQRCode(ActionEvent event) throws WriterException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/displayQr.fxml"));
            Parent root = loader.load();

            DisplayQrController controller = loader.getController();
            // Generate the content for the QR code
            String qrContent = "Payment Date: " + Data.purchaseDate + "\n"
                    + "Number of Adult Tickets: " + Data.nbAdult + "\n"
                    + "Number of Teen Tickets: " + Data.nbTeenager + "\n"
                    + "Number of Student Tickets: " + Data.nbStudent + "\n"
                    + "Total Ticket Price: " + totalLabel.getText();
            controller.setQrCodeContent(qrContent);

            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();

        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        totalLabel.setText(Data.totalp);
        anchor_2.setVisible(false);

        Stripe.apiKey = "sk_test_51MfRIsHcaMLPP7A1X3INIItKLbEljzGYdpTujtvwb4mrggNEJtwS1SG2C6MyxYdz8T2uPVh219jsg7LBZRWSh2Ye00QEgBJZmW";
        // Retrieve the customer
        /*Customer customer = Customer.retrieve("cus_NRSxsv14a22Ich");
        
        Map<String, Object> cardParam = new HashMap<String, Object>(); //add card details
        cardParam.put("number", "4111111111111111");
        cardParam.put("exp_month", "11");
        cardParam.put("exp_year", "2026");
        cardParam.put("cvc", "123");

        Map<String, Object> tokenParam = new HashMap<String, Object>();
        tokenParam.put("card", cardParam);

        Token token = Token.create(tokenParam); // create a token

        Map<String, Object> source = new HashMap<String, Object>();
        source.put("source", token.getId()); //add token as source

        Card card = (Card) customer.getSources().create(source); // add the customer details to which card is need to link
        String cardDetails = card.toJson();
        System.out.println("Card Details : " + cardDetails);*/
 /* Map<String, Object> params = new HashMap<>();
        params.put("amount", 3000);
        params.put("currency", "usd");
        params.put("customer", "cus_NRSxsv14a22Ich");
 
        Charge charge;
        try {
            charge = Charge.create(params);
            System.out.println(charge);
        } catch (StripeException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         */

        // Set up formatter for email field to accept any text
        TextFormatter<String> emailFormatter = new TextFormatter<>(change
                -> change.getControlNewText().length() <= 32 ? change : null);
        payment_email.setTextFormatter(emailFormatter);

        // Set up formatter for card number field to only accept numbers and limit to 16 digits
        TextFormatter<String> cardNumberFormatter = new TextFormatter<>(change
                -> change.getControlNewText().matches("\\d{0,16}") ? change : null);
        cardNumberTextField.setTextFormatter(cardNumberFormatter);

        // Set up formatter for expiry month field to only accept numbers and limit to 2 digits
        TextFormatter<String> expiryMFormatter = new TextFormatter<>(change
                -> change.getControlNewText().matches("\\d{0,2}") ? change : null);
        expirymTextField.setTextFormatter(expiryMFormatter);

        // Set up formatter for expiry year field to only accept numbers and limit to 2 digits
        TextFormatter<String> expiryYFormatter = new TextFormatter<>(change
                -> change.getControlNewText().matches("\\d{0,2}") ? change : null);
        expiryyTextField.setTextFormatter(expiryYFormatter);

        // Set up formatter for CVC field to only accept numbers and limit to 3 digits
        TextFormatter<String> cvcFormatter = new TextFormatter<>(change
                -> change.getControlNewText().matches("\\d{0,3}") ? change : null);
        cvcTextField.setTextFormatter(cvcFormatter);

    }

}
