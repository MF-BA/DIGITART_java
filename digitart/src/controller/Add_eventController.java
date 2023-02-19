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
    private TextField txt_desc;
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
 
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     showevent();
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me");
        if(event.getSource()==btnadd){
           EventAdd();
        }
        if(event.getSource()==btndelete){
           EventDelete();
        }
    }
    
  
     private ArrayList<Event> eventList;
    public void showevent() {

        eventList = Event_Services.displayEvent();
colid.setCellValueFactory(new PropertyValueFactory<Event,Integer>("event_id"));
        coleventname.setCellValueFactory(new PropertyValueFactory<Event,String>("event_name") );
        colstartdate.setCellValueFactory(new PropertyValueFactory<Event,Date>("start_date") );
        colenddate.setCellValueFactory(new PropertyValueFactory<Event,Date>("end_date") );
        colnbparticipants.setCellValueFactory(new PropertyValueFactory<Event,Integer>("nb_participants") );
        colstarttime.setCellValueFactory(new PropertyValueFactory<Event,Integer>("start_time") );
        coldesc.setCellValueFactory(new PropertyValueFactory<Event,String>("detail") );

        if (tabevent != null && tabevent instanceof TableView) {
            // Cast ticket_tableview to TableView<Ticket> and set its items
            ((TableView<Event>) tabevent).setItems(FXCollections.observableArrayList(eventList));
        }
    }
  
   
    
      
      public void EventAdd() {

        String event_id = this.txt_event_id.getText();
        LocalDate start_date = this.txt_start_date.getValue();
        LocalDate end_date = this.txt_end_date.getValue();
        String event_name = this.txt_event_name.getText();
        int nb_participants = Integer.parseInt(this.txt_nb_participants.getText());
        String event_desc = this.txt_desc.getText();
        int start_time = Integer.parseInt(this.txt_start_time.getText());
        

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
                    Event_Services.insertevent(event_name,start_date, end_date,nb_participants,start_time,event_desc);
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
        System.out.println("id" + eventt.getEvent_id());
        System.out.println("name" + eventt.getEvent_name());
        txt_event_id.setText(String.valueOf(eventt.getEvent_id()));
        txt_start_date.setValue(LocalDate.parse(String.valueOf(eventt.getStart_date())));
        txt_end_date.setValue(LocalDate.parse(String.valueOf(eventt.getEnd_date())));
        txt_nb_participants.setText(String.valueOf(eventt.getNb_participants()));
        txt_desc.setText(String.valueOf(eventt.getDetail()));
        txt_start_time.setText(String.valueOf(eventt.getStart_time()));
        txt_event_name.setText(String.valueOf(eventt.getEvent_name()));
        
        
            
    }
    
    /* public void TicketUpdate() {
        Alert alert;
        String event_id = this.txt_event_id.getText();
        LocalDate start_date = this.txt_start_date.getValue();
        LocalDate end_date = this.txt_end_date.getValue();
        String event_name = this.txt_event_name.getText();
        int nb_participants = Integer.parseInt(this.txt_nb_participants.getText());
        String event_desc = this.txt_desc.getText();
        int start_time = Integer.parseInt(this.txt_start_time.getText());
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
                    showevent();
                    
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    
}
