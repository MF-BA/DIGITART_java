/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Artwork_Services;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Conn;
import static utils.Conn.conn;

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
    @FXML
    private AnchorPane test_anc;
    @FXML
    private AnchorPane anc_scroll;
    @FXML
    private ScrollPane event_scroll;
    @FXML
    private GridPane event_container;
    ArrayList<Event> event_array;
    ArrayList<Event> event_array1;
    HBox cardBox;
    HBox cardBox1;
    @FXML
    private HBox event_view;
    private GridPane event_container2;
    @FXML
    private Label event_name_show;
    @FXML
    private Button btndelete;

    /**
     * Initializes the controller class.
     */
    public void labelupdate()
    {
        String name=null;
        event_name_show.setText(getname(name));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcome.setText(Data.user.getFirstname());
        showparticipants();
        combobox();
        labelupdate();
        
        event_array = Event_Services.displayEventFront();
        event_array1 = Event_Services.displayEventPart();
        int column = 0;
        int row = 1;
        int column1 = 0;
        int row1 = 1;
        for (int i = 0; i < event_array.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/event_display.fxml"));

            try {
                cardBox = fxmlLoader.load();
                
                Event_displayController cardController = fxmlLoader.getController();
             //   System.out.println(auction_array_detailed.get(0));
                cardController.show_event(event_array.get(i));
                VBox cardPane = (VBox) cardBox.getChildren().get(1);
                 cardPane.setOnMouseClicked(event -> {
                    // Pass the selected artwork to the detail view
                    getID(cardController.getEvent());
                });
            System.out.println(cardBox.getChildren());
                event_view.getChildren().add(cardBox);
                if (column == 1) {
                    column = 0;
                    row++;
                }
                event_container.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(10));//topRightBottomLeft: 10

            } catch (IOException ex) {
                Logger.getLogger("heeerrreeeee" + Auction_frontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }    

    
    
    public String getname(String name)
    {
        int id=0;
        PreparedStatement pst;
        String sql = "SELECT * FROM participants WHERE id_user=?";
        String sql1 = "SELECT * FROM event WHERE id=?";
        
        try {
            pst = conn.prepareStatement(sql);
             pst.setInt(1, Data.user.getId());
             


                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    id=rs.getInt("id_event");
                    
                }
                pst = conn.prepareStatement(sql1);
             pst.setInt(1, id);
              ResultSet rs1 = pst.executeQuery();
              if (rs1.next()) {
                    name=rs1.getString("event_name");
                    
                }
             
        } catch (SQLException ex) {
            Logger.getLogger(Participate_eventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }
    
    public void getID(Event event)
    {
        System.out.println(event.getEvent_id());
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
        if(event.getSource()==btndelete){
           EventDelete();
        }
    }
    
     public void combobox() {
        ObservableList<Integer> myObservableList = FXCollections.observableArrayList(Event_Services.find_idroom());
        txt_room.setItems(myObservableList);
        
        }
     public void EventDelete() {
        Alert alert;
         int id=0;
        PreparedStatement pst;
        String sql = "SELECT * FROM participants WHERE id_user=?";

        
        try {
            pst = conn.prepareStatement(sql);
             pst.setInt(1, Data.user.getId());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    id=rs.getInt("id_event");               
                }          
        } catch (SQLException ex) {
            Logger.getLogger(Participate_eventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            
        
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Your participation: " + id + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    Event_Services.deletePart(id);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    labelupdate();
                  
                } else {
                    return;
                }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
                    labelupdate();
                    
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