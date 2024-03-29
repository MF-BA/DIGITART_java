/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Event_Services;
import Services.ServiceTicket;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import entity.Data;
import entity.Event;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.Conn;

/**
 * FXML Controller class
 *
 * @author kossay
 */
public class Event_displayController implements Initializable {

    @FXML
    private HBox one_event;
    @FXML
    private Label txt_event_name;
    @FXML
    private Label txt_start_date;
    @FXML
    private Label txt_end_date;
    @FXML
    private Label txt_event_desc;
    @FXML
    private Label txt_start_time;
    @FXML
    private ImageView imagev;
    @FXML
    private Button btnparticipate;
    @FXML
    private Label countdown;
    @FXML
    private ImageView qrcode;
    @FXML
    private Button btn_qr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
    }   
    Event test;
    Image image1;
     public void show_event(Event event) throws MalformedURLException, IOException {
        test=event;
        URL url = new URL(event.getImage());
        System.out.println(event.getImage());
        image1 = new Image(event.getImage(), 350, 350, false, true);
        imagev.setImage(image1);
        txt_event_name.setText(event.getEvent_name().toUpperCase());
        txt_start_date.setText(event.getStart_date().toString());
        txt_end_date.setText(event.getEnd_date().toString());
        txt_event_desc.setText(event.getDetail());
       txt_start_time.setText(String.format("%02d:00", event.getStart_time()));
         ZoneId zoneId = ZoneId.systemDefault();
          
     Date date = event.getStart_date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        LocalDate localDate = LocalDate.of(calendar.get(Calendar.YEAR), 
                                            calendar.get(Calendar.MONTH) + 1, 
                                            calendar.get(Calendar.DAY_OF_MONTH));
         //System.out.println(event.getStart_date().toInstant());
        
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                String result = findDifference(zonedDateTime);
                Platform.runLater(() -> countdown.setText(result));
            }
        };

        // Schedule the TimerTask to run every 0.1 seconds
        timer.schedule(task, 0, 100); // 0 milliseconds initial delay, 5000 milliseconds (5 seconds) between subsequent executions
           
        
    }
     
     public static String findDifference(ZonedDateTime endDateTime) {
        // Calculate time difference in seconds
        long differenceInSeconds = ChronoUnit.SECONDS.between(ZonedDateTime.now(), endDateTime);

        long differenceInYears = differenceInSeconds / (365 * 24 * 60 * 60);
        long differenceInDays = (differenceInSeconds / (24 * 60 * 60)) % 365;
        long differenceInHours = (differenceInSeconds / (60 * 60)) % 24;
        long differenceInMinutes = (differenceInSeconds / 60) % 60;
        long differenceInSecondsOutput = differenceInSeconds % 60;

        // Build the result string
        String result = differenceInDays + " days, "
                + differenceInHours + " hours, "
                + differenceInMinutes + " minutes, "
                + differenceInSecondsOutput + " seconds.";

        // Return the result string
        return result;
    }
     public Event getEvent()
     {
         return test;
     }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource()==btnparticipate){
           UserAdd(test.getEvent_id());
           refresh(event);
        }
    }
   public void UserAdd(int id_event) {

    int id_user = Data.user.getId();
    String first_name = Data.user.getFirstname();
    String last_name = Data.user.getLastname();
    String address = Data.user.getAddress();
    String gender = Data.user.getGender();
    int event_id = id_event;

    Alert alert;

    try {
        // Check if the user has already participated in this event
        Connection connection = Conn.getCon();
        String check = "SELECT id_user FROM participants WHERE id_user = ? AND id_event = ?";
        PreparedStatement checkStatement = connection.prepareStatement(check);
        checkStatement.setInt(1, id_user);
        checkStatement.setInt(2, event_id);
        ResultSet result = checkStatement.executeQuery();
        
        if (result.next()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("User ID: " + id_user + " has already participated in this event!");
            alert.showAndWait();
        } else {
            // Insert the user's participation in the event
            Event_Services.insertuser(id_user, first_name, last_name, address, gender, event_id);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully added!");
            alert.showAndWait();
            // Update the table view once the data is successful
        }
    } catch (Exception e) {
        Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "Fatal error!", e);
    }
}

      public void refresh(ActionEvent event) {

        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/participate_event.fxml"));

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
public void generateQRCode() {
    String url = "https://artsandculture.google.com/search?q="+txt_event_name.getText().replaceAll("\\s+", "%20")+"%20"+ txt_start_date.getText().replaceAll("\\s+", "%20"+ txt_end_date.getText().replaceAll("\\s+", "%20")+ txt_event_desc.getText().replaceAll("\\s+", "%20"));

    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    ByteMatrix byteMatrix;
    try {
        byteMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200);
    } catch (WriterException e) {
        e.printStackTrace();
        return;
    }

    int width = byteMatrix.getWidth();
    int height = byteMatrix.getHeight();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
            int value = byteMatrix.get(x, y);
            image.setRGB(x, y, value == 0 ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
        }
    }

    Image fxImage = SwingFXUtils.toFXImage(image, null);
    qrcode.setImage(fxImage);
}
    @FXML
    private void btn_qr_clicked(ActionEvent event) {
           generateQRCode();
            System.out.println("qrcode generated");
    }
    
    
}