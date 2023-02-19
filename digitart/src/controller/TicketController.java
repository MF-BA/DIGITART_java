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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private AnchorPane main_anchor;

    @FXML
    private Button Close;

    @FXML
    private Button Minimize;

    @FXML
    private AnchorPane addticket_anchor;

    @FXML
    private Button btn_addticket;

    @FXML
    private AnchorPane dashboard_anchor;

    @FXML
    private Label dashboard_availabletickets;

    @FXML
    private LineChart<?, ?> dashboard_chart;

    @FXML
    private Button btn_dashboard;

    @FXML
    private Label dashboard_todayincome;

    @FXML
    private Label dashboard_totalincome;

    @FXML
    private Button ticket_add_button;

    @FXML
    private DatePicker ticket_date;

    @FXML
    private Button ticket_delete_button;

    @FXML
    private TextArea ticket_desc;

    @FXML
    private TextField ticket_id;

    @FXML
    private TextField ticket_price;

    @FXML
    private Button ticket_reset_button;

    @FXML
    private TextField ticket_search;

    @FXML
    private TextField ticket_stock;

    @FXML
    private TableView<?> ticket_tableview;

    @FXML
    private TableColumn<?, ?> ticket_tv_date;

    @FXML
    private TableColumn<?, ?> ticket_tv_description;

    @FXML
    private TableColumn<?, ?> ticket_tv_id;

    @FXML
    private TableColumn<?, ?> ticket_tv_price;

    @FXML
    private TableColumn<?, ?> ticket_tv_stock;

    @FXML
    private TableColumn<?, ?> ticket_tv_type;

    @FXML
    private ComboBox<String> ticket_type;

    @FXML
    private Button ticket_update_button;

    public void Close() {
        System.exit(0);
    }

    public void Minimize() {
        Stage stage = (Stage) main_anchor.getScene().getWindow();
        stage.setIconified(true);
    }

    public void defaultBtn() {
        btn_dashboard.setStyle("-fx-background-color:linear-gradient(to bottom right, #a73f4a, #3ea763)");
        btn_addticket.setStyle("-fx-background-color:transparent");

    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == btn_dashboard) {
            dashboard_anchor.setVisible(true);
            addticket_anchor.setVisible(false);
            btn_dashboard.setStyle("-fx-background-color:linear-gradient(to bottom right, #a73f4a, #3ea763)");
            btn_addticket.setStyle("-fx-background-color:transparent");
        } else if (event.getSource() == btn_addticket) {
            addticket_anchor.setVisible(true);
            dashboard_anchor.setVisible(false);
            btn_addticket.setStyle("-fx-background-color:linear-gradient(to bottom right, #a73f4a, #3ea763)");
            btn_dashboard.setStyle("-fx-background-color:transparent");
            ShowTicket();
        }
    }

    public void combobox() {
        List<String> options = new ArrayList<>();
        options.add("Teen");
        options.add("Adult");
        options.add("Student");
        ticket_type.getItems().addAll(options);
    }

    private ArrayList<Ticket> ticketList;

    public void ShowTicket() {

        ticketList = ServiceTicket.displayTicket();

        ticket_tv_id.setCellValueFactory(new PropertyValueFactory<>("ticket_id"));
        ticket_tv_date.setCellValueFactory(new PropertyValueFactory<>("ticket_date"));
        ticket_tv_type.setCellValueFactory(new PropertyValueFactory<>("ticket_type"));
        ticket_tv_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        ticket_tv_stock.setCellValueFactory(new PropertyValueFactory<>("ticket_nb"));

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
        ((ComboBox<String>) ticket_type).setValue(t.getTicket_type());
        ticket_price.setText(String.valueOf(t.getPrice()));
        ticket_stock.setText(String.valueOf(t.getTicket_nb()));
        ticket_desc.setText(String.valueOf(t.getTicket_desc()));

    }

    public void TicketAdd() {

        String ticket_id = this.ticket_id.getText();
        LocalDate ticket_date = this.ticket_date.getValue();
        String ticket_type = (String) this.ticket_type.getSelectionModel().getSelectedItem();
        int price = Integer.parseInt(this.ticket_price.getText());
        int ticket_nb = Integer.parseInt(this.ticket_stock.getText());
        String ticket_desc = this.ticket_desc.getText();

        Alert alert;

        // CHECK IF THE FIELDS ARE EMPTY
        if (ticket_id.isEmpty() || ticket_desc.isEmpty() || ticket_type == null || ticket_price.getText().isEmpty() || ticket_date == null || ticket_stock.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            try {
                // CHECK IF THE TICKET ID ALREADY EXISTS
                Connection connection;
                connection = Conn.getCon();
                String check = "SELECT ticket_id FROM ticket WHERE ticket_id = ?";
                PreparedStatement checkStatement = connection.prepareStatement(check);
                checkStatement.setString(1, ticket_id);
                ResultSet result = checkStatement.executeQuery();
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Ticket ID: " + ticket_id + " already exists!");
                    alert.showAndWait();
                } else {
                    ServiceTicket.addTicket(ticket_id, ticket_date, ticket_type, price, ticket_nb, ticket_desc);
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
            if (ticket_id.getText().isEmpty() || ticket_stock.getText().isEmpty()
                    || ticket_type.getSelectionModel().getSelectedItem() == null
                    || ticket_price.getText().isEmpty() || ticket_desc.getText().isEmpty()
                    || ticket_date.getValue() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the item first");
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
                    Ticket t = new Ticket(Integer.parseInt(ticket_id.getText()), date, ticket_type.getSelectionModel().getSelectedItem(),
                            Integer.parseInt(ticket_price.getText()), Integer.parseInt(ticket_stock.getText()), ticket_desc.getText());
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
            if (ticket_id.getText().isEmpty() || ticket_stock.getText().isEmpty() || ticket_type.getSelectionModel().getSelectedItem() == null
                    || ticket_price.getText().isEmpty() || ticket_desc.getText().isEmpty() || ticket_date.getValue() == null) {
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
        ticket_type.getSelectionModel().clearSelection();
        ticket_price.setText("");
        ticket_stock.setText("");
        ticket_desc.setText("");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        defaultBtn();
        ShowTicket();
        combobox();
    }

}
