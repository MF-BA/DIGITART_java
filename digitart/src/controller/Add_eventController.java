/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import entity.Event;

import javafx.scene.layout.VBox;

import utils.Conn;
import Services.ServiceTicket;
import Services.Event_Services;
import static Services.Event_Services.displayEvent;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static javax.management.remote.JMXConnectorFactory.connect;
/**
 * FXML Controller class
 *
 * @author kossay
 */
public class Add_eventController implements Initializable {

    @FXML
    private Button btnadd;
    @FXML
    private TextField txt_nb_participants;
    @FXML
    private TextField txt_event_id;
    @FXML
    private DatePicker txt_start_date;
    @FXML
    private DatePicker txt_end_date;
    @FXML
    private TextField txt_start_time;
    @FXML
    private TextArea txt_desc;
    @FXML
    private TableView<Event> tabevent;
    @FXML
    private TableColumn<Event, Integer> colid;
    @FXML
    private TableColumn<Event, String> coleventname;
    @FXML
    private TableColumn<Event, Date> colstartdate;
    @FXML
    private TableColumn<Event, Date> colenddate;
    @FXML
    private TableColumn<Event, Integer> colnbparticipants;
    @FXML
    private TableColumn<Event, Integer> colstarttime;
    @FXML
    private TableColumn<Event, String> coldesc;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TextField txt_event_name;
    @FXML
    private ComboBox<Integer> txt_room;
    @FXML
    private TableColumn<Event, Integer> colroomid;
    @FXML
    private Label labeladminname;
    @FXML
    private Label labeladminname1;
    @FXML
    private Label labeladminname2;
    @FXML
    private Button add_user;
    @FXML
    private Button modify_user;
    @FXML
    private Button list_users;
    @FXML
    private TextField event_search;
 
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     showevent();
     combobox();
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me");
        if(event.getSource()==btnadd){
           EventAdd();
        }
        if(event.getSource()==btndelete){
           EventDelete();
        }if(event.getSource()==btnupdate)
        {
            EventUpdate();
        }
    }
    
  
     private ArrayList<Event> eventList;
    public void showevent() {

        eventList = Event_Services.displayEvent();
        colid.setCellValueFactory(new PropertyValueFactory<>("event_id"));
        coleventname.setCellValueFactory(new PropertyValueFactory<>("event_name") );
        colstartdate.setCellValueFactory(new PropertyValueFactory<>("start_date") );
        colenddate.setCellValueFactory(new PropertyValueFactory<>("end_date") );
        colnbparticipants.setCellValueFactory(new PropertyValueFactory<>("nb_participants") );
        colstarttime.setCellValueFactory(new PropertyValueFactory<>("start_time") );
        coldesc.setCellValueFactory(new PropertyValueFactory<>("detail") );
        colroomid.setCellValueFactory(new PropertyValueFactory<>("id_room") );

        if (tabevent != null && tabevent instanceof TableView) {
            // Cast ticket_tableview to TableView<Ticket> and set its items
            ((TableView<Event>) tabevent).setItems(FXCollections.observableArrayList(eventList));
        }
    }
    
  
   
    /*public void searchEvent() {
        ObservableList<Event> eventObservableList = FXCollections.observableList(eventList);
        FilteredList<Event> filteredData = new FilteredList<>(eventObservableList, p -> true);
        event_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(event -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(event.getEvent_id()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches ticket id.
                } else if (event.getEvent_name().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches ticket type.
                } else if (String.valueOf(event.getNb_participants()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches ticket price.
                } else if (event.getDetail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches ticket date.
                } 
                return false; // Does not match.
            });
        });

        SortedList<Event> sortedData = new SortedList<>(filteredData);
        ((TableView<Event>) tabevent).setItems(sortedData);
    }
    */
      
      public void EventAdd() {

        String event_id = this.txt_event_id.getText();
        LocalDate start_date = this.txt_start_date.getValue();
        LocalDate end_date = this.txt_end_date.getValue();
        String event_name = this.txt_event_name.getText();
        int nb_participants = Integer.parseInt(this.txt_nb_participants.getText());
        String event_desc = this.txt_desc.getText();
        int start_time = Integer.parseInt(this.txt_start_time.getText());
        int id_room;
        id_room = this.txt_room.getSelectionModel().getSelectedItem();
        
        

        Alert alert;

        // CHECK IF THE FIELDS ARE EMPTY
        if ( event_name.isEmpty() || start_date == null  || end_date == null ) {
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
                checkStatement.setString(1, event_id);
                ResultSet result = checkStatement.executeQuery();
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Event ID: " + event_id + " already exists!");
                    alert.showAndWait();
                } else {
                    Event_Services.insertevent(event_name,start_date, end_date,nb_participants,start_time,event_desc,id_room);
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    // UPDATE THE TABLE VIEW ONCE THE DATA IS SUCCESSFUL
                    showevent();
                    
                }
            } catch (Exception e) {
                Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "fatal error!!", e);
            }
        }
    }
       public void EventDelete() {
        Alert alert;
          String event_id = this.txt_event_id.getText();
        LocalDate start_date = this.txt_start_date.getValue();
        LocalDate end_date = this.txt_end_date.getValue();
        String event_name = this.txt_event_name.getText();
        int nb_participants = Integer.parseInt(this.txt_nb_participants.getText());
        String event_desc = this.txt_desc.getText();
        int start_time = Integer.parseInt(this.txt_start_time.getText());
        try {
            if ( event_name.isEmpty() || start_date == null  || end_date == null) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Event ID: " + event_id + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    int id = Integer.parseInt(event_id);
                    Event_Services.deleteEvent(id);
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    showevent();
                  
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Event eventt = tabevent.getSelectionModel().getSelectedItem();
        txt_event_id.setText(String.valueOf(eventt.getEvent_id()));
        txt_start_date.setValue(LocalDate.parse(String.valueOf(eventt.getStart_date())));
        txt_end_date.setValue(LocalDate.parse(String.valueOf(eventt.getEnd_date())));
        txt_nb_participants.setText(String.valueOf(eventt.getNb_participants()));
        txt_desc.setText(String.valueOf(eventt.getDetail()));
        txt_start_time.setText(String.valueOf(eventt.getStart_time()));
        txt_event_name.setText(String.valueOf(eventt.getEvent_name()));
        ((ComboBox<Integer>) txt_room).setValue(eventt.getId_room());
        
        
            
    }
      public void combobox() {
        ObservableList<Integer> myObservableList = FXCollections.observableArrayList(Event_Services.find_idroom());
        txt_room.setItems(myObservableList);
        }
      
    public void EventUpdate() {
        Alert alert;
        int event_id = Integer.parseInt(this.txt_event_id.getText());
        LocalDate start_date = this.txt_start_date.getValue();
        LocalDate end_date = this.txt_end_date.getValue();
        String event_name = this.txt_event_name.getText();
        int nb_participants = Integer.parseInt(this.txt_nb_participants.getText());
        String event_desc = this.txt_desc.getText();
        int start_time = Integer.parseInt(this.txt_start_time.getText());
        int id_room = this.txt_room.getSelectionModel().getSelectedItem();
        try {
            if (event_name.isEmpty() || start_date == null  || end_date == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the item first");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE ticket ID: " + event_id + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    LocalDate localDates = start_date;
                    LocalDate localDatee = end_date;
                    
                    Date dates = Date.from(localDates.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Date datee = Date.from(localDatee.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Event e = new Event (event_id,dates,datee, start_time, event_name,event_desc,nb_participants,id_room);
                    Event_Services.updateEvent(e);
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    showevent();
                    
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void add_user_btn(ActionEvent event) {
    }

    @FXML
    private void modify_user_btn(ActionEvent event) {
    }

    @FXML
    private void list_users_btn(ActionEvent event) {
    }

    
    
}
