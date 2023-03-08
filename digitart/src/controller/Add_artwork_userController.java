/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Artwork_Services;
import entity.Artwork;
import entity.Data;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.Properties;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class Add_artwork_userController implements Initializable {

    @FXML
    private TextField Input_name_artwork;
    @FXML
    private ComboBox<String> input_idroom;
    @FXML
    private DatePicker input_date;
    @FXML
    private TextArea input_desc;
    @FXML
    private Button btn_addimage;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_cancel;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private String imageUrl;
    private String nameRoom ;
    private File selectedFile;
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
    private Button add_artwork_btn;
    /**
     * Initializes the controller class.
     */
    
    
     private void go_Display(ActionEvent event) {
        try {
             root = FXMLLoader.load(getClass().getResource("/view/display_artwork_user.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
       public void combobox() {
      ObservableList<String> myObservableList1 = FXCollections.observableArrayList(Artwork_Services.find_nameroom());
        input_idroom.setItems(myObservableList1);

    }
       
       
     public void showNotification(String title, String message, String imageUrl, double imageWidth, double imageHeight) {
    Image image = new Image(imageUrl);
    
    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(imageWidth);
    imageView.setFitHeight(imageHeight);
    
    Notifications notificationsBuilder = Notifications.create()
            .title(title)
            .text(message)
            .position(Pos.TOP_RIGHT)
            .hideAfter(Duration.seconds(10))
            .graphic(imageView);
    
    notificationsBuilder.show();
    }
       
       
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
       
       
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        artwork_btn.setStyle("-fx-background-color: #bd2a2e ");
        add_artwork_btn.setStyle("-fx-background-color: #bd2a2e ");
        combobox();
    }    

    @FXML
    private void btn_addimage_clicked(ActionEvent event) {
        
         Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        selectedFile = fileChooser.showOpenDialog(primaryStage);
     
    }

    @FXML
    private void btn_add_clicked(ActionEvent event) throws IOException {
        
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
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        // Write the image file data to the output stream of the connection
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(("--" + boundary + "\r\n").getBytes());
        outputStream.write(("Content-Disposition: form-data; name=\"file\"; filename=\"" + selectedFile.getName() + "\"\r\n").getBytes());
        outputStream.write(("Content-Type: image/jpeg\r\n\r\n").getBytes());
        outputStream.write(imageData);
        outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
        outputStream.flush();
        outputStream.close();

        // Read the response from the PHP script
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    
        TextInputControl nameControl = this.Input_name_artwork;
   
  
    ComboBox<String> idRoomControl = this.input_idroom;
    TextInputControl descControl = this.input_desc;
    DatePicker dateControl = this.input_date;

    String name = nameControl.getText();
    String nameartist = Data.user.getLastname();
    
    int id_room;
    String desc = descControl.getText();
    LocalDate date = dateControl.getValue();
    int id_artist = Data.user.getId();
      

    Alert alert;

    // CHECK IF THE FIELDS ARE EMPTY
    if (name.isEmpty() || nameartist.isEmpty() || desc.isEmpty() || date == null ||this.input_idroom.getSelectionModel().getSelectedItem()==null ) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all fields");
        alert.showAndWait();
    } else {
       nameRoom = idRoomControl.getSelectionModel().getSelectedItem();
        
        id_room = Artwork_Services.find_idroom(nameRoom) ;
        Artwork artwork = new Artwork(name, id_artist, nameartist, date, desc, imageUrl, id_room);
        Artwork_Services.add(artwork);
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Added!");
        alert.showAndWait();
        
        try {
        sendEmail(Data.user.getEmail(), "ADD ARTWORK IN DIGITART", "Dear " +Data.user.getFirstname()+" "+Data.user.getLastname()+",\n Thank you for trusting our application, \n You have successfully added the artwork "+name);
    } catch (MessagingException e) {
        e.printStackTrace();
    }
        
        
        go_Display(event);
        String title="An Artwork was added successfully";
        String message="Dear " +Data.user.getFirstname()+" "+Data.user.getLastname()+",\n You have successfully added the artwork "+name;
        showNotification(title,message,imageUrl,300,300);
    }
        
    }

    @FXML
    private void btn_cancel_clicked(ActionEvent event) {
        go_Display(event);
    }

    @FXML
    private void disconnect_btn(ActionEvent event) {
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
            try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/home_page.fxml"));

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
    private void artwork_btn(ActionEvent event) {
       go_Display(event);
    }

    @FXML
    private void auction_btn(ActionEvent event) {
    }

    @FXML
    private void events_btn(ActionEvent event) {
    }

    @FXML
    private void tickets_btn(ActionEvent event) {
    }

    @FXML
    private void add_artwork_btn_clicked(ActionEvent event) {
    }
    
    
    
}
