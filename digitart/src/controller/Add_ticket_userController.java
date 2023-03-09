/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import entity.Data;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import utils.Conn;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Add_ticket_userController implements Initializable {

    @FXML
    private AnchorPane main_anchor;
    @FXML
    private AnchorPane afterdate_anchor;
    @FXML
    private Button ticketbuy_reset_button;
    @FXML
    private Spinner<Integer> spinner_adult;
    @FXML
    private Label price_1;
    @FXML
    private Label price_2;
    @FXML
    private Label price_3;
    @FXML
    private Label price_4;
    @FXML
    private Spinner<Integer> spinner_teen;
    @FXML
    private Spinner<Integer> spinner_student;
    @FXML
    private Button ticketbuy_buy_button;
    @FXML
    private DatePicker payment_date;
    @FXML
    private Button disconnect;
    @FXML
    private Button editprof_btn;
    @FXML
    private Button home_btn;
    @FXML
    private Button artwork_btn;
    @FXML
    private Button auction_btn;
    @FXML
    private Button events_btn;
    @FXML
    private Circle circle_image;
    @FXML
    private ImageView avatar_image;
    @FXML
    private Label labelusername;
    @FXML
    private Button tickets_btn;
    @FXML
    private AnchorPane home;
    @FXML
    private AnchorPane homepage_anchorpane;
    @FXML
    private Label price_41;

    public void showSpinner(Spinner<Integer> spinner) {
        // Create a new spinner value factory with a range of 1 to 10, and an initial value of 1
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);

        // Set the spinner value factory
        spinner.setValueFactory(valueFactory);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tickets_btn.setStyle("-fx-background-color:#bd2a2e");
        showSpinner(spinner_adult);
        showSpinner(spinner_student);
        showSpinner(spinner_teen);

        checkDate();
    }

    
     public void SpinnerReset1() {
        price_1.setText("");
        price_2.setText("");
        price_3.setText("");
        price_4.setText("");
        spinner_student.getValueFactory().setValue(0);
        spinner_teen.getValueFactory().setValue(0);
        spinner_adult.getValueFactory().setValue(0);
    }

    @FXML
    public void SpinnerReset() {
        price_1.setText("");
        price_2.setText("");
        price_3.setText("");
        price_4.setText("");
        payment_date.setValue(null);
        spinner_student.getValueFactory().setValue(0);
        spinner_teen.getValueFactory().setValue(0);
        spinner_adult.getValueFactory().setValue(0);
    }

    public int getTicketPrice(String ticketType, LocalDate selectedDate) {
        String sql = "SELECT price FROM ticket WHERE ticket_type = ? AND ? BETWEEN ticket_date AND ticket_edate";
        int price = 0;
        try {
            PreparedStatement prepare = Conn.getCon().prepareStatement(sql);
            prepare.setString(1, ticketType);
            prepare.setDate(2, new java.sql.Date(selectedDate.toEpochDay() * 24 * 60 * 60 * 1000));
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                price = result.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If no price is found for the given ticketType and selectedDate,
        // set the price to a default value of 0
        return price;
    }

    private int qty1;
    private int qty2;
    private int qty3;
    private int total;

    @FXML
    public void getSpinner(MouseEvent event) {
        qty1 = spinner_adult.getValue();
        qty2 = spinner_teen.getValue();
        qty3 = spinner_student.getValue();

        LocalDate selectedDate = payment_date.getValue();
        /*
            spinner_adult.setDisable(false);
            spinner_teen.setDisable(false);
            spinner_student.setDisable(false);
         */

        int price1 = getTicketPrice("Adult", selectedDate) * qty1;
        int price2 = getTicketPrice("Teen", selectedDate) * qty2;
        int price3 = getTicketPrice("Student", selectedDate) * qty3;

        total = (price1 + price2 + price3);

        price_1.setText("$" + String.valueOf(price1));
        price_2.setText("$" + String.valueOf(price2));
        price_3.setText("$" + String.valueOf(price3));
        price_4.setText(String.valueOf(total));

    }

    @FXML
    public void checkDate() {

        // Disable all dates that are not between ticket_date and ticket_edate for each ticket
        String sql = "SELECT ticket_date, ticket_edate FROM ticket";
        try {
            PreparedStatement prepare = Conn.getCon().prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            List<LocalDate> enabledDates = new ArrayList<>();
            while (result.next()) {
                LocalDate ticketDate = result.getDate("ticket_date").toLocalDate();
                LocalDate ticketEdate = result.getDate("ticket_edate").toLocalDate();
                // Add all dates between ticket_date and ticket_edate to the enabledDates list
                for (LocalDate date = ticketDate; !date.isAfter(ticketEdate); date = date.plusDays(1)) {
                    enabledDates.add(date);
                }
            }
            enableDates(enabledDates);
            // Set the anchor to be visible only if a date is selected
            if (payment_date.getValue() != null) {
                afterdate_anchor.setVisible(true);
                // Disable spinners if the price is 0 for the respective ticket type

                if (getTicketPrice("Adult", payment_date.getValue()) == 0) {
                    spinner_adult.setDisable(true);
                } else {
                    spinner_adult.setDisable(false);
                }

                if (getTicketPrice("Teen", payment_date.getValue()) == 0) {
                    spinner_teen.setDisable(true);
                } else {
                    spinner_teen.setDisable(false);
                }

                if (getTicketPrice("Student", payment_date.getValue()) == 0) {
                    spinner_student.setDisable(true);
                } else {
                    spinner_student.setDisable(false);
                }

                // Reset spinner values to 0
                SpinnerReset1();

            } else {
                afterdate_anchor.setVisible(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void enableDates(List<LocalDate> enabledDates) {
        LocalDate yesterday = LocalDate.now();

        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || !enabledDates.contains(item) || item.isBefore(yesterday)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // Change the disabled date color
                }
            }
        };

        payment_date.setDayCellFactory(dayCellFactory);
    }

    @FXML
    private void displayPaymentStripe(ActionEvent event) throws WriterException {
        String total = price_4.getText();
        if (total.isEmpty() || total.equals("0")) {
            // Show a warning message that the user should choose a ticket before making a payment
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Please choose a ticket !!");
            alert.setContentText("You need to choose a ticket before you can make a payment.");
            alert.showAndWait();
            return;
        }
        try {
            Data.totalp = total;
            Data.purchaseDate = payment_date.getValue();
            Data.nbAdult = spinner_adult.getValue();
            Data.nbTeenager = spinner_teen.getValue();
            Data.nbStudent = spinner_student.getValue();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/payment.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL); // Set the modality to APPLICATION_MODAL to block other stages
            newStage.showAndWait(); // Use showAndWait() instead of show()

        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ticketbuy_buy_button(ActionEvent event) throws WriterException, DocumentException {
        // Check if price_4 label is empty or equals to 0
        if (price_4.getText().isEmpty() || price_4.getText().equals("0")) {
            // Show a warning message
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Please choose a ticket before proceeding with payment.");
            alert.showAndWait();
        } else {
            // Proceed with payment
            displayPaymentStripe(event);
        }
    }

    @FXML
    private void disconnect_btn(ActionEvent event) {
         try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/signin_page.fxml"));

            Scene scene = new Scene(parent2);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("DIGITART");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editprof_btn(ActionEvent event) {
        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/editprofileuser_front.fxml"));

            Scene scene = new Scene(parent2);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("DIGITART");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void home_btn(ActionEvent event) {
    }

    @FXML
    private void artwork_btn(ActionEvent event) {
    }

    @FXML
    private void auction_btn(ActionEvent event) {
    }

    @FXML
    private void events_btn(ActionEvent event) {
    }

}
