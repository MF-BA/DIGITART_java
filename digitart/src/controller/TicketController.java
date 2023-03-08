/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.ServicePayment;
import Services.ServiceTicket;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import entity.Data;
import utils.Conn;
import entity.Ticket;
import entity.Payment;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TicketController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane addticket_anchor;

    @FXML
    private Button btn_addticket;

    @FXML
    private Button btn_dashboard;

    @FXML
    private AnchorPane dashboard_anchor;

    @FXML
    private Label dashboard_availabletickets;

    @FXML
    private LineChart<String, Number> dashboard_chart;

    @FXML
    private Label dashboard_todayincome;

    @FXML
    private Label dashboard_totalincome;

    @FXML
    private AnchorPane main_anchor;


    @FXML
    private DatePicker ticket_date;


    @FXML
    private DatePicker ticket_edate;

    @FXML
    private TextField ticket_price;

    @FXML
    private TextField ticket_id;


    @FXML
    private TextField ticket_search;

    @FXML
    private TableView<?> ticket_tableview;

    @FXML
    private TableColumn<?, ?> ticket_tv_date;

    @FXML
    private TableColumn<?, ?> ticket_tv_edate;

    private TableColumn<?, ?> ticket_tv_id;

    @FXML
    private TableColumn<?, ?> ticket_tv_price;

    @FXML
    private TableColumn<?, ?> ticket_tv_type;

    @FXML
    private ComboBox<String> ticket_type;

    @FXML
    private AnchorPane payment_anchor;
    @FXML
    private TableView<Payment> payment_tableview;
    @FXML
    private TableColumn<?, ?> payment_tv_purchaseD;
    @FXML
    private TableColumn<?, ?> payment_tv_adult;
    @FXML
    private TableColumn<?, ?> payment_tv_teen;
    @FXML
    private TableColumn<?, ?> payment_tv_student;
    @FXML
    private TableColumn<?, ?> payment_tv_total;
    @FXML
    private PieChart dashboard_pie;
    @FXML
    private Button ticket_add_button;
    @FXML
    private Button ticket_update_button;
    @FXML
    private Button ticket_reset_button;
    @FXML
    private Button ticket_delete_button;
    @FXML
    private Button check_payment;
    @FXML
    private Button payment_delete_button;
    @FXML
    private Pane avatar_icon;
    @FXML
    private Circle circle_image;
    @FXML
    private ImageView avatar_image;
    @FXML
    private Button return_dash_btn;
    @FXML
    private Button deconnect1;
    
     private Parent pt;
    @FXML
    private Label name;

    public void combobox() {
        List<String> options = new ArrayList<>();
        options.add("Adult");
        options.add("Teen");
        options.add("Student");
        ticket_type.getItems().addAll(options);
    }

//////// STOP  //////////////////////////////////////////////// STOP /////////////////////////////////////////////////////
    public void ShowPayment() {
        ArrayList<Payment> paymentList = ServicePayment.displayPayment();

        //payment_tv_id.setCellValueFactory(new PropertyValueFactory<>("paymentid"));
        //payment_tv_idUser.setCellValueFactory(new PropertyValueFactory<>("id"));
        payment_tv_purchaseD.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        payment_tv_adult.setCellValueFactory(new PropertyValueFactory<>("nbAdult"));
        payment_tv_teen.setCellValueFactory(new PropertyValueFactory<>("nbTeenager"));
        payment_tv_student.setCellValueFactory(new PropertyValueFactory<>("nbStudent"));
        payment_tv_total.setCellValueFactory(new PropertyValueFactory<>("totalPayment"));

        if (payment_tableview != null && payment_tableview instanceof TableView) {
            ((TableView<Payment>) payment_tableview).setItems(FXCollections.observableArrayList(paymentList)); // Cast payment_tableview to TableView<Payment> and set its items
        }
    }

    @FXML
    public void PaymentDelete() {
        // Get the selected payment from the tableview
        Payment selectedPayment = payment_tableview.getSelectionModel().getSelectedItem();

        // Check if a payment is selected
        if (selectedPayment == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Payment Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a payment to delete.");
            alert.showAndWait();
            return;
        }

        // Show confirmation dialog before deleting payment
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this payment?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // Delete payment from database

            if (ServicePayment.deletePayment(selectedPayment.getPaymentid())) {
                // Remove payment from tableview
                payment_tableview.getItems().remove(selectedPayment);

                // Show success message
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Payment Deleted");
                successAlert.setHeaderText(null);
                successAlert.setContentText("The selected payment has been deleted.");
                successAlert.showAndWait();
            } else {
                // Show error message
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Delete Failed");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("An error occurred while deleting the payment.");
                errorAlert.showAndWait();
            }

        }
    }

    //////// TICKET  //////////////////////////////////////////////// TICKET /////////////////////////////////////////////////////
    private ArrayList<Ticket> ticketList;

    public void ShowTicket() {

        ticketList = ServiceTicket.displayTicket();

        //ticket_tv_id.setCellValueFactory(new PropertyValueFactory<>("ticket_id"));
        ticket_tv_date.setCellValueFactory(new PropertyValueFactory<>("ticket_date"));
        ticket_tv_edate.setCellValueFactory(new PropertyValueFactory<>("ticket_edate"));
        ticket_tv_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        ticket_tv_type.setCellValueFactory(new PropertyValueFactory<>("ticket_type"));

        if (ticket_tableview != null && ticket_tableview instanceof TableView) {
            // Cast ticket_tableview to TableView<Ticket> and set its items
            ((TableView<Ticket>) ticket_tableview).setItems(FXCollections.observableArrayList(ticketList));
        }
    }

    @FXML
    public void SelectTicket() {

        Ticket t = ((TableView<Ticket>) ticket_tableview).getSelectionModel().getSelectedItem();
        int num = ticket_tableview.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        ticket_id.setText(String.valueOf(t.getTicket_id()));
        ticket_date.setValue(LocalDate.parse(String.valueOf(t.getTicket_date())));
        ticket_edate.setValue(LocalDate.parse(String.valueOf(t.getTicket_edate())));
        ticket_price.setText(String.valueOf(t.getPrice()));
        ((ComboBox<String>) ticket_type).setValue(t.getTicket_type());

    }

    @FXML
    public void TicketAdd() {
        LocalDate ticketDate = this.ticket_date.getValue();
        LocalDate ticketEDate = this.ticket_edate.getValue();
        int price = Integer.parseInt(this.ticket_price.getText());
        String ticketType = (String) this.ticket_type.getSelectionModel().getSelectedItem();

        Alert alert;

        // CHECK IF THE FIELDS ARE EMPTY
        if (ticketType == null || ticketDate == null || ticketEDate == null) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } // CHECK IF THE TICKET DATE IS A FUTURE DATE
        else if (ticketDate.isBefore(LocalDate.now())) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select a future date");
            alert.showAndWait();
        } // CHECK IF THE PRICE IS GREATER THAN 0
        else if (price <= 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Price should be greater than 0");
            alert.showAndWait();
        } // CHECK IF THE END DATE IS AFTER THE TICKET DATE
        else if (ticketEDate.isBefore(ticketDate)) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("End date should be after ticket date");
            alert.showAndWait();
        } else {
            try {
                // CHECK IF THE TICKET ALREADY EXISTS
                Connection connection = Conn.getCon();
                String check = "SELECT * FROM ticket WHERE ticket_type=? AND ((ticket_date >= ? AND ticket_date <= ?) OR (ticket_edate >= ? AND ticket_edate <= ?))";
                PreparedStatement checkStatement = connection.prepareStatement(check);
                checkStatement.setString(1, ticketType);
                checkStatement.setDate(2, java.sql.Date.valueOf(ticketDate));
                checkStatement.setDate(3, java.sql.Date.valueOf(ticketEDate));
                checkStatement.setDate(4, java.sql.Date.valueOf(ticketDate));
                checkStatement.setDate(5, java.sql.Date.valueOf(ticketEDate));
                ResultSet result = checkStatement.executeQuery();
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Ticket already exists!");
                    alert.showAndWait();
                } else {
                    ServiceTicket.addTicket(ticketDate, ticketEDate, price, ticketType);
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    // UPDATE THE TABLE VIEW ONCE THE DATA IS SUCCESSFUL
                    ShowTicket();
                    TicketReset();
                    //checkDate();

                }
            } catch (Exception e) {
                Logger.getLogger(ServiceTicket.class
                        .getName()).log(Level.SEVERE, "fatal error!!", e);
            }

        }
    }

    @FXML
    public void TicketUpdate() {
        Alert alert;
        try {
            if (!ticket_price.getText().matches("\\d+") || Integer.parseInt(ticket_price.getText()) == 0) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid ticket price");
                alert.showAndWait();
            } else if (ticket_date.getValue() == null || ticket_edate.getValue() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please choose a date");
                alert.showAndWait();
            } else if (ticket_date.getValue().isBefore(LocalDate.now())) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid ticket date (today or later)");
                alert.showAndWait();
            } else if (ticket_edate.getValue().isBefore(ticket_date.getValue())) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid end date (after start date)");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE ticket ID: " + ticket_id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    LocalDate localDate = ticket_date.getValue();
                    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    LocalDate localDate1 = ticket_edate.getValue();
                    Date date1 = Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Ticket t = new Ticket(Integer.parseInt(ticket_id.getText()), date, date1,
                            Integer.parseInt(ticket_price.getText()), ticket_type.getSelectionModel().getSelectedItem());
                    ServiceTicket.updateTicket(t);
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    ShowTicket();
                    TicketReset();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void TicketDelete() {
        Alert alert;
        try {
            if (ticket_type.getSelectionModel().getSelectedItem() == null
                    || ticket_price.getText().isEmpty() || ticket_edate.getValue() == null || ticket_date.getValue() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the item first");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Ticket ID: " + ticket_id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    int id = Integer.parseInt(ticket_id.getText());
                    ServiceTicket.deleteTicket(id);
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    ShowTicket();
                    TicketReset();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void searchTicket() {
        ObservableList<Ticket> ticketObservableList = FXCollections.observableList(ticketList);
        FilteredList<Ticket> filteredData = new FilteredList<>(ticketObservableList, p -> true);
        ticket_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(ticket -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(ticket.getTicket_id()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches ticket id.
                } else if (ticket.getTicket_type().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches ticket type.
                } else if (String.valueOf(ticket.getPrice()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches ticket price.
                } else if (ticket.getTicket_date().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches ticket date.
                } else if (ticket.getTicket_edate().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches ticket edate.
                }
                return false; // Does not match.
            });
        });

        SortedList<Ticket> sortedData = new SortedList<>(filteredData);
        ((TableView<Ticket>) ticket_tableview).setItems(sortedData);
    }
//////// DASHBOARD  //////////////////////////////////////////////// DASHBOARD /////////////////////////////////////////////////////
    private int count = 0;

    public void dashboardDisplayAvailableTickets() {
        String sql = "SELECT COUNT(*) FROM ticket";
        Connection connection;
        PreparedStatement prepare;
        ResultSet result;

        try {
            connection = Conn.getCon();
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                count = result.getInt(1);
            }

            dashboard_availabletickets.setText(String.valueOf(count));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dashboardDisplayTodayIncome() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        // Build the SQL query to sum the total_payment column for the current day
        String sql = "SELECT SUM(total_payment) FROM payment WHERE purchase_date = ?";
        // Initialize the total payment to 0
        double totalPayment = 0;
        try {
            PreparedStatement stmt = Conn.getCon().prepareStatement(sql);
            // Set the query parameter to the current date
            stmt.setDate(1, java.sql.Date.valueOf(currentDate));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Get the sum of total_payment for the current day
                totalPayment = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Set the text of the dashboard_todayincome TextField to the total payment
        dashboard_todayincome.setText(String.format("$%.1f", totalPayment).replace(',', '.'));
    }

    public void dashboardDisplayTotalIncome() {
        String sql = "SELECT SUM(total_payment) FROM payment";
        double totalIncome = 0;
        try {
            Statement stmt = Conn.getCon().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                totalIncome = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dashboard_totalincome.setText(String.format("$%.1f", totalIncome).replace(',', '.'));
    }

    public void displayStatistics(LineChart<String, Number> dashboard_chart) {
        Connection conn = Conn.getCon();
        String sql = "SELECT purchase_date, SUM(total_payment) as total_payment FROM payment GROUP BY purchase_date";

        try {
            PreparedStatement prepare = conn.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            List<XYChart.Data<String, Number>> data = new ArrayList<>();
            while (result.next()) {
                LocalDate date = result.getDate("purchase_date").toLocalDate();
                double totalPayment = result.getDouble("total_payment");
                data.add(new XYChart.Data<>(date.toString(), totalPayment));
            }
            XYChart.Series<String, Number> series = new XYChart.Series<>("Total Payments", FXCollections.observableArrayList(data));
            dashboard_chart.getData().setAll(series);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayPieChart(PieChart dashboard_pie) {
        Connection conn = Conn.getCon();
        String sql = "SELECT SUM(nb_adult) as total_adult, SUM(nb_teenager) as total_teenager, SUM(nb_student) as total_student FROM payment";

        try {
            PreparedStatement prepare = conn.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                int totalAdult = result.getInt("total_adult");
                int totalTeenager = result.getInt("total_teenager");
                int totalStudent = result.getInt("total_student");

                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("Adults", totalAdult),
                        new PieChart.Data("Teenagers", totalTeenager),
                        new PieChart.Data("Students", totalStudent)
                );
                dashboard_pie.setPrefWidth(400);
                dashboard_pie.setPrefHeight(400);
                dashboard_pie.setData(pieChartData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//////// DISPLAY  //////////////////////////////////////////////// DISPLAY /////////////////////////////////////////////////////
    @FXML
    public void TicketReset() {
        ticket_id.setText("");
        ticket_date.setValue(null);
        ticket_edate.setValue(null);
        ticket_type.getSelectionModel().clearSelection();
        ticket_price.setText("0");

    }

    @FXML
    public void CheckPayment() {
        addticket_anchor.setVisible(false);
        payment_anchor.setVisible(true);
        ShowPayment();
    }

    @FXML
    public void switchForm(ActionEvent event) {
        if (event.getSource() == btn_dashboard) {
            dashboard_anchor.setVisible(true);
            addticket_anchor.setVisible(false);
            payment_anchor.setVisible(false);
            btn_dashboard.setStyle("-fx-background-color:#470011");
            btn_addticket.setStyle("-fx-background-color:transparent");
            dashboardDisplayAvailableTickets();
            dashboardDisplayTodayIncome();
            dashboardDisplayTotalIncome();
            displayStatistics(dashboard_chart);
            displayPieChart(dashboard_pie);
            dashboard_pie.setAnimated(true);
        } else if (event.getSource() == btn_addticket) {
            addticket_anchor.setVisible(true);
            dashboard_anchor.setVisible(false);
            payment_anchor.setVisible(false);
            btn_addticket.setStyle("-fx-background-color:#470011");
            btn_dashboard.setStyle("-fx-background-color:transparent");
            ShowTicket();

            Connection connection;
            Statement statement;
            ResultSet resultSet;
            try {
                connection = Conn.getCon();
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM ticket WHERE ticket_date < CURDATE()");

                if (resultSet.next()) {
                    String title = "Warning!";
                    String message = "Please check ticket's date! Tickets needs updates!";
                    NotificationType notificationType = NotificationType.WARNING;
                    String imagePath = "/view/warning.jpg";
                    AnimationType animationType = AnimationType.POPUP;
                    TrayNotification notification = new TrayNotification();
                    notification.setTitle(title);
                    notification.setMessage(message);
                    notification.setNotificationType(notificationType);
                    notification.setAnimationType(animationType);
                    notification.setRectangleFill(Paint.valueOf("#BD2A2E"));
                    notification.setImage(new Image(imagePath));
                    notification.showAndWait();
                }
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } 
        /*else if (event.getSource() == btn_buyticket) {
            addticket_anchor.setVisible(false);
            buyticket_anchor.setVisible(true);
            dashboard_anchor.setVisible(false);
            payment_anchor.setVisible(false);
            SpinnerReset();
            TicketReset();
            btn_buyticket.setStyle("-fx-background-color:#470011");
            btn_dashboard.setStyle("-fx-background-color:transparent");
            btn_addticket.setStyle("-fx-background-color:transparent");
        } */
    }

    public void defaultBtn() {
        btn_dashboard.setStyle("-fx-background-color:#470011");
        btn_addticket.setStyle("-fx-background-color:transparent");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        name.setText(Data.user.getFirstname());
        
        dashboard_anchor.setVisible(true);
        addticket_anchor.setVisible(false);
        payment_anchor.setVisible(false);
        dashboardDisplayAvailableTickets();
        dashboardDisplayTodayIncome();
        dashboardDisplayTotalIncome();
        defaultBtn();
        ShowTicket();
        combobox();
        //checkDate();
        displayStatistics(dashboard_chart);
        displayPieChart(dashboard_pie);
        dashboard_pie.setAnimated(true);

        ticket_price.setText("0");
        TextFormatter<String> ticketPriceFormatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("[0-9]{0,4}")) {
                return change;
            } else {
                return null;
            }
        });
        ticket_price.setTextFormatter(ticketPriceFormatter);

        

    }

    private Element generateRedRectangle(Document document, Paragraph contact) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Element generateGreyRectangle(Document document, Paragraph instructions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Element generateGreyRectangle(Document document, PdfPTable ticketTable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void return_dash_btn(ActionEvent event) {
         try {
             pt=FXMLLoader
                    .load(getClass().getResource("/view/Dashboard_homepage.fxml"));
            
             scene=new Scene(pt);
             stage=(Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deconnect_btn(ActionEvent event) {
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

}
