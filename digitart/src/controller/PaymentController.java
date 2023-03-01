/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import entity.Data;
import entity.Payment;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private Label taxLabel;
    @FXML
    private Label subtotalLabel;
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
    private void handlePayButtonAction(ActionEvent event) {
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
            int totalPayment =Integer.parseInt(totalLabel.getText());
       
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
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        totalLabel.setText(Data.totalp);

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
    }

}
