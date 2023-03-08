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
import doryan.windowsnotificationapi.fr.Notification;
import entity.Data;
import entity.Participants;
import entity.Ticket;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static javax.management.remote.JMXConnectorFactory.connect;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
//import org.apache.pdfbox.pdmodel.PDPageContentStream.MarkupException;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import static utils.Conn.conn;
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
    private Button add_event;
    @FXML
    private Button modify_event;
    @FXML
    private Button list_events;
    @FXML
    private AnchorPane event_add_anc;
    private AnchorPane list_of_users;
    @FXML
    private AnchorPane list_of_events;
    @FXML
    private Label add_event_txt_field;
    @FXML
    private Label modify_event_field;
    @FXML
    private Label errormsgeventid;
    @FXML
    private Label errormsgdetails;
    @FXML
    private Label errormsgeventname;
    @FXML
    private Label errormsgstarttime;
    @FXML
    private Label errormsgroomid;
    @FXML
    private Label errormsgstartdate;
    @FXML
    private Label errormsgenddate;
    @FXML
    private Label errormsgnbparticipants;
  
    @FXML
    private Label welcome;
    @FXML
    private TableView<Participants> tabpart;
    @FXML
    private TableColumn<Participants, Integer> colid_part;
    @FXML
    private TableColumn<Participants, String> colpart_fname;
    @FXML
    private TableColumn<Participants, String> colpart_lname;
    @FXML
    private TableColumn<Participants, String> colpart_adress;
    @FXML
    private TableColumn<Participants, String> colpart_gender;
    @FXML
    private ComboBox<Integer> id_event_combobox;
    @FXML
    private AnchorPane part_anc;
    @FXML
    private Button list_participants;
    @FXML
    private Button deconnect;
    @FXML
    private Label errormsgelements;
    @FXML
    private TextField ticket_search;
    @FXML
    private TextField ticket_search1;
    @FXML
    private Button pdf_print;
    @FXML
    private Button btn_addimage;
    @FXML
    
    private void searchTicket() {
        ObservableList<Event> ticketObservableList = FXCollections.observableList(eventList);
        FilteredList<Event> filteredData = new FilteredList<>(ticketObservableList, p -> true);
        ticket_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(event -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(event.getEvent_id()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches ticket id.
                } else if (event.getEvent_name().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches ticket type.
                } 
                return false; // Does not match.
            });
        });
        
        SortedList<Event> sortedData = new SortedList<>(filteredData);
        ((TableView<Event>) tabevent).setItems(sortedData);
    }
    
    @FXML
    private void searchpart() {
        
          ObservableList<Participants> ticketObservableList = FXCollections.observableList(partlist);
        FilteredList<Participants> filteredData = new FilteredList<>(ticketObservableList, p -> true);
        ticket_search1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(participants -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(participants.getId_event()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches ticket id.
                }  
                return false; // Does not match.
            });
        });
        
        SortedList<Participants> sortedData = new SortedList<>(filteredData);
        ((TableView<Participants>) tabpart).setItems(sortedData);
    }


  
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        event_add_anc.setVisible(true);
        list_of_events.setVisible(false);
        part_anc.setVisible(false);
        welcome.setText(Data.user.getFirstname());
         
     showevent();
     showparticipants();
     combobox();
     searchTicket();
    }    


    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me");
                String event_id = this.txt_event_id.getText();
        LocalDate start_date = this.txt_start_date.getValue();
        LocalDate end_date = this.txt_end_date.getValue();
        String event_name = this.txt_event_name.getText();
        
                LocalDate selectedDate = txt_start_date.getValue();
                        LocalDate today = LocalDate.now();


//        int nb_participants = Integer.parseInt(this.txt_nb_participants.getText());
        String event_desc = this.txt_desc.getText();
//        int start_time = Integer.parseInt(this.txt_start_time.getText());
       /* int id_room;
        id_room = this.txt_room.getSelectionModel().getSelectedItem(); */ 
        if(event.getSource()==btnadd){
            
            errormsgeventid.setText("");
            errormsgeventname.setText("");
            errormsgstarttime.setText("");
            errormsgroomid.setText("");
            errormsgstartdate.setText("");
            errormsgenddate.setText("");
            errormsgnbparticipants.setText("");
            errormsgdetails.setText("");
            
             if (!txt_start_time.getText().isEmpty())
    {               
    if (!txt_start_time.getText().matches("\\d+")) {
        errormsgstarttime.setText("Start Time should be a number!");
    } else if (txt_start_time.getText().toString().length()<2)
    {
        errormsgstarttime.setText("Time shouldn't pass 24!");
    }
 if (start_date == null) {
    errormsgstartdate.setText("Fill start date !!");
} else if (start_date.isBefore(today)) {
    errormsgstartdate.setText("Start date cannot be in the past!!");
}
   
            }
             try {
    int number = Integer.parseInt(txt_start_time.getText());
    if(number >= 24){
        errormsgstarttime.setText("Start Time shouldn't pass 24");
    }
} catch (NumberFormatException e) {
    // handle the case where the input is not a valid integer
    JOptionPane.showMessageDialog(null, "Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
    txt_start_time.setText(""); // clear the input field
    txt_start_time.requestFocus(); // set focus back to the input field
}
  
     
   if (event_name.isEmpty() || event_desc.isEmpty() || txt_room == null || txt_nb_participants.getText().isEmpty() || end_date == null  || start_date == null || txt_start_time.getText().isEmpty()) {
         
       errormsgelements.setText("Please fill all elements!!");  
    
    if (event_name.isEmpty())   {
     
     errormsgeventname.setText("fill event name !!");   
    }
    if(txt_room == null)
    {
        errormsgroomid.setText("Fill Room id");
    }
    if (event_desc.isEmpty())  {
     
     errormsgdetails.setText("fill event details !!");   
    }
    if ( start_date == null)
    {
     
     errormsgstartdate.setText("Fill start date !!");   
    }
    if ( end_date == null)
    {
     errormsgenddate.setText("Fill end date!!");   
    }
    if(txt_nb_participants.getText().isEmpty())
    {
        errormsgnbparticipants.setText("Fill the number of participants");
    }
    if(txt_start_time.getText().isEmpty())
    {
        errormsgstarttime.setText("Fill the start time");
    }
   
   
    } else {
                try {
                    Notification.sendNotification("Digitart","An Event was added",MessageType.INFO);
                } catch (AWTException ex) {
                    Logger.getLogger(Add_eventController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Add_eventController.class.getName()).log(Level.SEVERE, null, ex);
                }
       EventAdd();
       
   }
           
            
            
            
           
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
    
    
     private ArrayList<Participants> partlist;
    public void showparticipants() {

        partlist = Event_Services.displayPart();
        colid_part.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        colpart_adress.setCellValueFactory(new PropertyValueFactory<>("adress") );
        colpart_fname.setCellValueFactory(new PropertyValueFactory<>("first_name") );
        colpart_lname.setCellValueFactory(new PropertyValueFactory<>("last_name") );
        colpart_gender.setCellValueFactory(new PropertyValueFactory<>("gender") );
    

        if (tabpart != null && tabpart instanceof TableView) {
            // Cast ticket_tableview to TableView<Ticket> and set its items
            ((TableView<Participants>) tabpart).setItems(FXCollections.observableArrayList(partlist));
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
     public static void sendEmail(String recipient, String subject, String body) throws MessagingException {
        
        // Set the mail properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        // Create the session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("digitart.primes@gmail.com", "crpyprterxegkjes");
            }
        });
        
        // Create the message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("digitart.primes@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(body);
        
        // Send the message
        Transport.send(message);
    }
      
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
                 // String imagePath = file.getPath();
       imageUrl="http://localhost/images/"+selectedFile.getName();
       String phpUrl = "http://localhost/images/upload.php";
//        String imageFilePath = "C:\\xamppp\\htdocs\\piImg";

        // Read the image file data
        byte[] imageData = Files.readAllBytes(selectedFile.toPath());

        // Create the boundary string for the multipart request
        String boundary = "---------------------------12345";

        // Open the connection to the PHP script
        URL url = new URL(phpUrl);
        HttpURLConnection connectionn = (HttpURLConnection) url.openConnection();
        connectionn.setDoOutput(true);
        connectionn.setRequestMethod("POST");
        connectionn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        // Write the image file data to the output stream of the connection
        OutputStream outputStream = connectionn.getOutputStream();
        outputStream.write(("--" + boundary + "\r\n").getBytes());
        outputStream.write(("Content-Disposition: form-data; name=\"file\"; filename=\"" + selectedFile.getName() + "\"\r\n").getBytes());
        outputStream.write(("Content-Type: image/jpeg\r\n\r\n").getBytes());
        outputStream.write(imageData);
        outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
        outputStream.flush();
        outputStream.close();

        // Read the response from the PHP script
        InputStream inputStream = connectionn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Event ID: " + event_id + " already exists!");
                    alert.showAndWait();
                } else {
                    Event_Services.insertevent(event_name,start_date, end_date,nb_participants,start_time,event_desc,id_room,imageUrl);
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                  

                    // UPDATE THE TABLE VIEW ONCE THE DATA IS SUCCESSFUL
                    try {
        sendEmail("bedhiefkoussay2015@gmail.com", "Event Added", "Dear " +Data.user.getFirstname()+" "+Data.user.getLastname()+",\n Thank you for trusting our application, \n You have successfully added the event "+event_name);
    } catch (MessagingException e) {
        e.printStackTrace();
    }
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
        
        ObservableList<Integer> myObservableList1 = FXCollections.observableArrayList(Event_Services.find_idevent());
        id_event_combobox.setItems(myObservableList1);
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
    private void modify_event_btn(ActionEvent event) {
        btnadd.setVisible(false);
        btnupdate.setVisible(true);
        add_event_txt_field.setVisible(false);
        modify_event_field.setVisible(true);
         event_add_anc.setVisible(true);
        list_of_events.setVisible(false);
        part_anc.setVisible(false);
        list_participants.setStyle("-fx-background-color: transparent "); 
        add_event.setStyle("-fx-background-color:   transparent ");
        modify_event.setStyle("-fx-background-color:  #470011 ");
        list_events.setStyle("-fx-background-color: transparent "); 
        
    }

    @FXML
    private void add_event_btn(ActionEvent event) {
        btnadd.setVisible(true);
        btnupdate.setVisible(false);
        add_event_txt_field.setVisible(true);
        modify_event_field.setVisible(false);
        event_add_anc.setVisible(true);
        list_of_events.setVisible(false);
        part_anc.setVisible(false);
        add_event.setStyle("-fx-background-color:   #470011 ");
        modify_event.setStyle("-fx-background-color:  transparent ");
        list_events.setStyle("-fx-background-color: transparent "); 
        list_participants.setStyle("-fx-background-color: transparent "); 
        txt_event_id.setText("");
        txt_event_name.setText("");
        txt_start_time.setText("");
        txt_end_date.setValue(null);
        txt_start_date.setValue(null);
       txt_nb_participants.setText("");
        txt_room.getSelectionModel().clearSelection(); 
        txt_desc.setText("");
        
    }

    @FXML
    private void list_event_btn(ActionEvent event) {
        btnadd.setVisible(false);
        btnupdate.setVisible(false);
        add_event_txt_field.setVisible(false);
        modify_event_field.setVisible(false);
        event_add_anc.setVisible(false);
        list_of_events.setVisible(true);
        part_anc.setVisible(false);
        add_event.setStyle("-fx-background-color:   transparent ");
        modify_event.setStyle("-fx-background-color:  transparent ");
        list_events.setStyle("-fx-background-color: #470011 "); 
        list_participants.setStyle("-fx-background-color: transparent "); 
    }


    @FXML
    private void list_part_btn(ActionEvent event) {
        btnadd.setVisible(false);
        btnupdate.setVisible(false);
        add_event_txt_field.setVisible(false);
        modify_event_field.setVisible(false);
        part_anc.setVisible(true);
        event_add_anc.setVisible(false);
        list_of_events.setVisible(false);
        add_event.setStyle("-fx-background-color:   transparent ");
        modify_event.setStyle("-fx-background-color:  transparent ");
        list_events.setStyle("-fx-background-color: transparent "); 
        list_participants.setStyle("-fx-background-color: #470011 "); 
    }

    @FXML
    private void deconnect_btn(ActionEvent event) {
         deconnect.setStyle("-fx-background-color: #470011 ");
        
        
        event_add_anc.setVisible(false);
        list_of_events.setVisible(false);
        part_anc.setVisible(false);

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
    private void handleSaveButtonAction(ActionEvent event) {
    // Get the selected event from the database
    Event selectedEvent = tabevent.getSelectionModel().getSelectedItem();
    
    if (selectedEvent == null) {
        // If no event is selected, show an error message and return
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No event selected");
        alert.setContentText("Please select an event from the table.");
        alert.showAndWait();
        return;
    }
    
    // Get the selected file path using a file chooser dialog
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save PDF");
    fileChooser.setInitialFileName(selectedEvent.getEvent_name() + ".pdf");
    File selectedFile = fileChooser.showSaveDialog(pdf_print.getScene().getWindow());
    
    if (selectedFile == null) {
        // If no file is selected, show a message and return
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("No file selected");
        alert.setContentText("The PDF was not saved.");
        alert.showAndWait();
        return;
    }
    
    try (PDDocument document = new PDDocument()) {
        // Create a new page with A4 size and add it to the document
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        
        // Get the content stream for the page
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        
        // Set the font and font size for the text
        PDFont font = PDType1Font.HELVETICA_BOLD;
        float fontSize = 12;
         contentStream.setNonStrokingColor(Color.RED);
         contentStream.fillRect(0, page.getMediaBox().getHeight() - 50, page.getMediaBox().getWidth(), 50);
        // Write the event details to the PDF
        contentStream.setNonStrokingColor(Color.BLACK);
        contentStream.beginText();
   
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(50, 700);
        contentStream.showText("Event Name: " + selectedEvent.getEvent_name());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Event Start Date: " + selectedEvent.getStart_date());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Event End Date: " + selectedEvent.getEnd_date());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Event Location: " + selectedEvent.getDetail());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Event ID: " + selectedEvent.getEvent_id());
              contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Max number of participants: " + selectedEvent.getNb_participants());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("The Event details are: " + selectedEvent.getDetail());
        contentStream.endText();
        
        // Close the content stream
        contentStream.close();
        
        // Save the document to the selected file path
        document.save(selectedFile);
        
        // Show a success message
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("PDF saved");
        alert.setContentText("The PDF was saved to: " + selectedFile.getAbsolutePath());
        alert.showAndWait();
    } catch (IOException ex) {
        // If an error occurs during PDF generation or saving, show an error message
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("PDF generation or saving error");
        alert.setContentText("An error occurred while generating or saving the PDF: " + ex.getMessage());
        alert.showAndWait();
    }
}
    private File selectedFile;
    private String imageUrl;

    @FXML
    private void btn_addimage_clicked(ActionEvent event) throws IOException {
        
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        selectedFile = fileChooser.showOpenDialog(primaryStage);
        
        
        
        
        
     

    }
    
    
}
