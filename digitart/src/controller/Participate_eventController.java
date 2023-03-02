/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Event_Services;
import Services.ServiceTicket;
import entity.Data;
import entity.Event;
import entity.Participants;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Conn;

/**
 * FXML Controller class
 *
 * @author kossay
 */
public class Participate_eventController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private Label labeladminname;
    @FXML
    private Label labeladminname1;
    @FXML
    private Label labeladminname2;
    @FXML
    private TableView<Event> tabevent_user;
    @FXML
    private TableColumn<Event, String> colevent_name;
    @FXML
    private TableColumn<Event, Date> colstart_date;
    @FXML
    private TableColumn<Event, Date> colend_date;
    @FXML
    private TextField txt_event_id;
    @FXML
    private DatePicker txt_start_date;
    @FXML
    private TextField txt_nb_participants;
    @FXML
    private TextField txt_start_time;
    @FXML
    private TextArea txt_desc;
    @FXML
    private TextField txt_event_name;
    @FXML
    private DatePicker txt_end_date;
    @FXML
    private ComboBox<Integer> txt_room;
    @FXML
    private Button btnparticipate;
    @FXML
    private Label event_id_text;
    @FXML
    private Label nb_participants_text;
    @FXML
    private Button deconnect;
    @FXML
    private AnchorPane default_anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcome.setText(Data.user.getFirstname());
        showparticipants();
        combobox();
    }    



    @FXML
    private void handleMouseAction(MouseEvent event) {
        Event eventt = tabevent_user.getSelectionModel().getSelectedItem();
        txt_event_id.setText(String.valueOf(eventt.getEvent_id()));
        txt_start_date.setValue(LocalDate.parse(String.valueOf(eventt.getStart_date())));
        txt_end_date.setValue(LocalDate.parse(String.valueOf(eventt.getEnd_date())));
        txt_nb_participants.setText(String.valueOf(eventt.getNb_participants()));
        txt_desc.setText(String.valueOf(eventt.getDetail()));
        txt_start_time.setText(String.valueOf(eventt.getStart_time()));
        txt_event_name.setText(String.valueOf(eventt.getEvent_name()));
        ((ComboBox<Integer>) txt_room).setValue(eventt.getId_room());
    }
    
      private ArrayList<Event> eventlist;
    public void showparticipants() {

        eventlist = Event_Services.displayEvent();
        colevent_name.setCellValueFactory(new PropertyValueFactory<>("event_name"));
        colstart_date.setCellValueFactory(new PropertyValueFactory<>("start_date") );
        colend_date.setCellValueFactory(new PropertyValueFactory<>("end_date") );
    

        if (tabevent_user != null && tabevent_user instanceof TableView) {
            // Cast ticket_tableview to TableView<Ticket> and set its items
            ((TableView<Event>) tabevent_user).setItems(FXCollections.observableArrayList(eventlist));
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource()==btnparticipate){
           UserAdd();
        }
    }
    
     public void combobox() {
        ObservableList<Integer> myObservableList = FXCollections.observableArrayList(Event_Services.find_idroom());
        txt_room.setItems(myObservableList);
        
        }
    public void UserAdd() {

       int id_user=Data.user.getId();
        String first_name=Data.user.getFirstname();
        String last_name=Data.user.getLastname();
        String adress=Data.user.getAddress();
        String gender=Data.user.getGender();
        int event_id = Integer.parseInt(this.txt_event_id.getText());

        Alert alert;

        // CHECK IF THE FIELDS ARE EMPTY
      
            try {
                // CHECK IF THE TICKET ID ALREADY EXISTS
                Connection connection;
                connection = Conn.getCon();
                String check = "SELECT id_user FROM participants WHERE id_user = ?";
                PreparedStatement checkStatement = connection.prepareStatement(check);
                checkStatement.setInt(1, id_user);
                ResultSet result = checkStatement.executeQuery();
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("User ID: " + id_user + " can only participate in one event at a time!");
                    alert.showAndWait();
                } else {
                    Event_Services.insertuser(id_user,first_name, last_name,adress,gender,event_id);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    // UPDATE THE TABLE VIEW ONCE THE DATA IS SUCCESSFUL
                    showparticipants();
                    
                }
            } catch (Exception e) {
                Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "fatal error!!", e);
            }
        
    }

    @FXML
    private void deconnect_btn(ActionEvent event) {
        deconnect.setStyle("-fx-background-color: #470011 ");
        
        
        default_anchor.setVisible(false);

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
