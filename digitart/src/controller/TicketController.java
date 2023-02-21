/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import utils.Conn;
import Services.ServiceTicket;
import utils.Conn;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Ticket;
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
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static javax.management.remote.JMXConnectorFactory.connect;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TicketController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button Close;

    @FXML
    private Button Minimize;

    @FXML
    private AnchorPane addticket_anchor;

    @FXML
    private Button btn_addticket;

    @FXML
    private Button btn_buyticket;

    @FXML
    private Button btn_dashboard;

    @FXML
    private AnchorPane buyticket_anchor;

    @FXML
    private AnchorPane dashboard_anchor;

    @FXML
    private Label dashboard_availabletickets;

    @FXML
    private LineChart<?, ?> dashboard_chart;

    @FXML
    private Label dashboard_todayincome;

    @FXML
    private Label dashboard_totalincome;

    @FXML
    private AnchorPane main_anchor;

    @FXML
    private Button ticket_add_button;

    @FXML
    private DatePicker ticket_date;

    @FXML
    private Button ticket_delete_button;

    @FXML
    private DatePicker ticket_edate;

    @FXML
    private TextField ticket_price;

    @FXML
    private TextField ticket_id;

    @FXML
    private Button ticket_reset_button;

    @FXML
    private TextField ticket_search;

    @FXML
    private TableView<?> ticket_tableview;

    @FXML
    private TableColumn<?, ?> ticket_tv_date;

    @FXML
    private TableColumn<?, ?> ticket_tv_edate;

    @FXML
    private TableColumn<?, ?> ticket_tv_id;

    @FXML
    private TableColumn<?, ?> ticket_tv_price;

    @FXML
    private TableColumn<?, ?> ticket_tv_type;

    @FXML
    private ComboBox<String> ticket_type;

    @FXML
    private Spinner<Integer> spinner_adult;

    @FXML
    private Spinner<Integer> spinner_student;

    @FXML
    private Spinner<Integer> spinner_teen;

    @FXML
    private Button ticket_update_button;

    @FXML
    private Button ticketbuy_add_button;

    @FXML
    private DatePicker ticketbuy_date;

    @FXML
    private Button ticketbuy_reset_button;

    @FXML
    private Button ticketbuy_update_button;

    @FXML
    private Label price_1;

    @FXML
    private Label price_2;

    @FXML
    private Label price_3;

    @FXML
    private Label price_4;

    @FXML
    private DatePicker payment_date;

    @FXML
    private AnchorPane afterdate_anchor;

    @FXML
    private TextField date_warning;

    public void Close() {
        System.exit(0);
    }

    public void Minimize() {
        Stage stage = (Stage) main_anchor.getScene().getWindow();
        stage.setIconified(true);
    }

    public void defaultBtn() {
        btn_dashboard.setStyle("-fx-background-color:linear-gradient(to bottom right, #a73f4a, #9c8585)");
        btn_addticket.setStyle("-fx-background-color:transparent");
        btn_buyticket.setStyle("-fx-background-color:transparent");

    }

    public void checkDate() {
        int i = 0;
        LocalDate selectedDate = payment_date.getValue();
        LocalDate today = LocalDate.now();

        if (selectedDate == null) {
            // If no date is selected, do nothing
            return;
        }

        String sql = "SELECT COUNT(*) FROM ticket WHERE ? BETWEEN ticket_date AND ticket_edate";
        try {
            PreparedStatement prepare = Conn.getCon().prepareStatement(sql);
            prepare.setDate(1, new java.sql.Date(selectedDate.toEpochDay() * 24 * 60 * 60 * 1000));
            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                int count = result.getInt(1);
                if (count > 0) {
                    // If the selected date is between ticket_date and ticket_edate
                    afterdate_anchor.setVisible(true);
                    date_warning.setVisible(false);
                    i = 1;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (selectedDate.isBefore(today)) {
            // If selected date is before today's date
            date_warning.setVisible(true);
            date_warning.setText("Date already passed!!!");
            afterdate_anchor.setVisible(false);
            payment_date.valueProperty().addListener((observable, oldValue, newValue) -> {
                checkDate();
            });
        } else if (i == 0) {
            // If no message has been set yet
            date_warning.setVisible(true);
            date_warning.setText("Please select another date!");
            afterdate_anchor.setVisible(false);
            payment_date.valueProperty().addListener((observable, oldValue, newValue) -> {
                checkDate();
            });
        }

    }

    public void showSpinner(Spinner<Integer> spinner) {
        // Create a new spinner value factory with a range of 1 to 10, and an initial value of 1
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);

        // Set the spinner value factory
        spinner.setValueFactory(valueFactory);
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
        return price;
    }

    private int qty1;
    private int qty2;
    private int qty3;
    private int total;

    public void getSpinner(MouseEvent event) {
        qty1 = spinner_adult.getValue();
        qty2 = spinner_teen.getValue();
        qty3 = spinner_student.getValue();

        LocalDate selectedDate = payment_date.getValue();

        int price1 = getTicketPrice("Adult", selectedDate) * qty1;
        int price2 = getTicketPrice("Teen", selectedDate) * qty2;
        int price3 = getTicketPrice("Student", selectedDate) * qty3;

        total = (price1 + price2 + price3);

        price_1.setText("$" + String.valueOf(price1));
        price_2.setText("$" + String.valueOf(price2));
        price_3.setText("$" + String.valueOf(price3));

        price_4.setText("$" + String.valueOf(total));
    }

    public void combobox() {
        List<String> options = new ArrayList<>();
        options.add("Adult");
        options.add("Teen");
        options.add("Student");
        ticket_type.getItems().addAll(options);
    }

    private ArrayList<Ticket> ticketList;

    public void ShowTicket() {

        ticketList = ServiceTicket.displayTicket();

        ticket_tv_id.setCellValueFactory(new PropertyValueFactory<>("ticket_id"));
        ticket_tv_date.setCellValueFactory(new PropertyValueFactory<>("ticket_date"));
        ticket_tv_edate.setCellValueFactory(new PropertyValueFactory<>("ticket_edate"));
        ticket_tv_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        ticket_tv_type.setCellValueFactory(new PropertyValueFactory<>("ticket_type"));

        if (ticket_tableview != null && ticket_tableview instanceof TableView) {
            // Cast ticket_tableview to TableView<Ticket> and set its items
            ((TableView<Ticket>) ticket_tableview).setItems(FXCollections.observableArrayList(ticketList));
        }
    }

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

    public void TicketAdd() {
        LocalDate ticketDate = this.ticket_date.getValue();
        LocalDate ticketEDate = this.ticket_edate.getValue();
        int price = Integer.parseInt(this.ticket_price.getText());
        String ticketType = (String) this.ticket_type.getSelectionModel().getSelectedItem();

        Alert alert;

        // CHECK IF THE FIELDS ARE EMPTY
        if (ticket_type == null || ticket_date == null || ticket_edate == null) {
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
                Connection connection;
                connection = Conn.getCon();
                String check = "SELECT * FROM ticket WHERE ticket_date=? AND ticket_edate=? AND price=? AND ticket_type=?";
                PreparedStatement checkStatement = connection.prepareStatement(check);
                checkStatement.setDate(1, java.sql.Date.valueOf(ticketDate));
                checkStatement.setDate(2, java.sql.Date.valueOf(ticketEDate));
                checkStatement.setInt(3, price);
                checkStatement.setString(4, ticketType);
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

                }
            } catch (Exception e) {
                Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "fatal error!!", e);
            }
        }
    }

    public void TicketUpdate() {
        Alert alert;
        try {
            if (!ticket_price.getText().matches("\\d+") || Integer.parseInt(ticket_price.getText()) == 0) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid ticket price");
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

    public void TicketReset() {
        ticket_id.setText("");
        ticket_date.setValue(null);
        ticket_edate.setValue(null);
        ticket_type.getSelectionModel().clearSelection();
        ticket_price.setText("");

    }

    public void SpinnerReset() {
        price_1.setText("");
        price_2.setText("");
        price_3.setText("");
        price_4.setText("");
        spinner_student.getValueFactory().setValue(0);
        spinner_teen.getValueFactory().setValue(0);
        spinner_adult.getValueFactory().setValue(0);
    }

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

    public void switchForm(ActionEvent event) {
        if (event.getSource() == btn_dashboard) {
            dashboard_anchor.setVisible(true);
            addticket_anchor.setVisible(false);
            buyticket_anchor.setVisible(false);
            btn_dashboard.setStyle("-fx-background-color:linear-gradient(to bottom right, #a73f4a, #9c8585)");
            btn_addticket.setStyle("-fx-background-color:transparent");
            btn_buyticket.setStyle("-fx-background-color:transparent");
            dashboardDisplayAvailableTickets();
        } else if (event.getSource() == btn_addticket) {
            addticket_anchor.setVisible(true);
            buyticket_anchor.setVisible(false);
            dashboard_anchor.setVisible(false);
            btn_addticket.setStyle("-fx-background-color:linear-gradient(to bottom right, #a73f4a, #9c8585)");
            btn_dashboard.setStyle("-fx-background-color:transparent");
            btn_buyticket.setStyle("-fx-background-color:transparent");
            ShowTicket();
        } else if (event.getSource() == btn_buyticket) {
            addticket_anchor.setVisible(false);
            buyticket_anchor.setVisible(true);
            dashboard_anchor.setVisible(false);
            btn_buyticket.setStyle("-fx-background-color:linear-gradient(to bottom right, #a73f4a, #9c8585)");
            btn_dashboard.setStyle("-fx-background-color:transparent");
            btn_addticket.setStyle("-fx-background-color:transparent");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        dashboard_anchor.setVisible(true);
        addticket_anchor.setVisible(false);
        buyticket_anchor.setVisible(false);
        ticket_id.setVisible(false);
        afterdate_anchor.setVisible(false);
        dashboardDisplayAvailableTickets();
        defaultBtn();
        ShowTicket();
        combobox();

        ticket_price.setText("0");
        TextFormatter<String> ticketPriceFormatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("[0-9]{0,4}")) {
                return change;
            } else {
                return null;
            }
        });
        ticket_price.setTextFormatter(ticketPriceFormatter);

        showSpinner(spinner_adult);
        showSpinner(spinner_student);
        showSpinner(spinner_teen);

    }

}
