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
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
    @FXML
    private Label labeladminname;
    @FXML
    private Label labeladminname1;
    @FXML
    private Label labeladminname2;
    @FXML
    private Button btn_artwork;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private String imageUrl;
    private String nameRoom ;
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
        combobox();
    }    

    @FXML
    private void btn_addimage_clicked(ActionEvent event) {
        
        //        FileChooser fileChooser = new FileChooser();
//    fileChooser.setTitle("Select Image File");
//    fileChooser.getExtensionFilters().addAll(
//        new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
//    );
//   selectedFile = fileChooser.showOpenDialog(btn_addimage.getScene().getWindow());
//    if (selectedFile != null) {
//       
//        imageUrl = selectedFile.toURI().toString();
//        System.out.println(imageUrl);
//    }
//
 // Create a FileChooser to select the image file
    FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Select an image file");
File file = fileChooser.showOpenDialog(new Stage());
 imageUrl = file.toURI().toString();
  System.out.println(imageUrl);

if (file != null) {
    try {
        // Read the image file and encode it as Base64
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        fis.close();
        String base64EncodedImage = java.util.Base64.getEncoder().encodeToString(baos.toByteArray());
       

        // Set up the cURL request to send the encoded image data to the server
        String url = "http://localhost/images/upload.php"; // Replace with the URL of your server-side script
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setDoOutput(true);

        // Write the encoded image data to the request body
        String urlParameters = "imageData=" + base64EncodedImage;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        con.setDoOutput(true);
        con.setInstanceFollowRedirects(false);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("charset", "utf-8");
        con.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        con.setUseCaches(false);

        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.write(postData);
        }

        // Get the response from the server
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Print the response from the server
        System.out.println("Response code: " + responseCode);
        System.out.println("Response message: " + response.toString());
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    }

    @FXML
    private void btn_add_clicked(ActionEvent event) {
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
    private void btn_artwork_clicked(ActionEvent event) {
    }
    
    
    
}
