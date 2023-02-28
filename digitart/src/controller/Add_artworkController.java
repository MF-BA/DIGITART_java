/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Artwork_Services;
import entity.Artwork;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class Add_artworkController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private ImageView imageView;
    private File selectedFile;
    private String imageUrl;
     private String nameRoom ;
    
    
    @FXML
    private Button btn_addimage;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_cancel;
    @FXML
    private TextField Input_name_artwork;
    @FXML
    private TextField input_name_artist;
    @FXML
    private ComboBox<Integer> input_id_artist;
    @FXML
    ComboBox<String> input_idroom;
    @FXML
    private DatePicker input_date;
    @FXML
    private TextArea input_desc;
    @FXML
    private Button btn_room;
    @FXML
    private Button btn_artwork;

    /**
     * Initializes the controller class.
     */
    
      private void go_room(ActionEvent event) {
        try {
             root = FXMLLoader.load(getClass().getResource("/view/display_room.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      private void go_Display(ActionEvent event) {
        try {
             root = FXMLLoader.load(getClass().getResource("/view/display_artwork.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       combobox();
       
      btn_artwork.setStyle("-fx-background-color: #470011 ");
      btn_room.setStyle("-fx-background-color: transparent ");
       
    }    
/*
     
       // Create a FileChooser dialog to let the user choose the file to upload
FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Select a file to upload");
File selectedFile = fileChooser.showOpenDialog(null);

// Call the uploadFile method with the selected file and the upload URL
if (selectedFile != null) {
    String uploadUrl = "http://localhost/images/upload";
    uploadFile(selectedFile, uploadUrl);
}
    */
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
        System.out.println(base64EncodedImage);

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
    TextInputControl nameArtistControl = this.input_name_artist;
  
    ComboBox<String> idRoomControl = this.input_idroom;
    TextInputControl descControl = this.input_desc;
    DatePicker dateControl = this.input_date;

    String name = nameControl.getText();
    String nameartist = nameArtistControl.getText();
    
    int id_room;
    String desc = descControl.getText();
    LocalDate date = dateControl.getValue();
    int id_artist;
      if(this.input_id_artist.getSelectionModel().getSelectedItem()== null){
            id_artist=-1;
        }else
        {id_artist = this.input_id_artist.getSelectionModel().getSelectedItem();}

    Alert alert;

    // CHECK IF THE FIELDS ARE EMPTY
    if (name.isEmpty() || nameartist.isEmpty() || desc.isEmpty() || date == null ||this.input_idroom.getSelectionModel().getSelectedItem()==null ) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all fields");
        alert.showAndWait();
    } else {
         nameRoom = idRoomControl.getSelectionModel().getSelectedItem();
        
        id_room = Artwork_Services.find_idroom(nameRoom) ;
        Artwork artwork = new Artwork(name, id_artist, nameartist, date, desc, imageUrl, id_room);
        Artwork_Services.add(artwork);
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Added!");
        alert.showAndWait();
        // UPDATE THE TABLE VIEW ONCE THE DATA IS SUCCESSFUL
        go_Display(event);
    }
}

    
    
        public void combobox() {
        ObservableList<Integer> myObservableList = FXCollections.observableArrayList(Artwork_Services.find_idartist());
        input_id_artist.setItems(myObservableList);
        
       
       ObservableList<String> myObservableList1 = FXCollections.observableArrayList(Artwork_Services.find_nameroom());
        input_idroom.setItems(myObservableList1);
        
          

      
        
    }
        


public void uploadFile(File file, String uploadUrl) {
    try {
        // Set up the HTTP connection
        URL url = new URL(uploadUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setDoOutput(true);
        httpConn.setRequestMethod("POST");

        // Set the request headers
        httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=boundary");

        // Create the multipart request body
        OutputStream outputStream = httpConn.getOutputStream();
        String boundary = "--boundary\r\n";
        outputStream.write(boundary.getBytes("UTF-8"));
        outputStream.write(("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n").getBytes("UTF-8"));
        outputStream.write(("Content-Type: " + HttpURLConnection.guessContentTypeFromName(file.getName()) + "\r\n\r\n").getBytes("UTF-8"));
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.write(("\r\n" + boundary + "--\r\n").getBytes("UTF-8"));
        fileInputStream.close();

        // Check the response status code
        int responseCode = httpConn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("File uploaded successfully.");
        } else {
            System.out.println("File upload failed. Error code: " + responseCode);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}



    @FXML
    private void btn_cancel_clicked(ActionEvent event) {
        go_Display(event);
    }


    @FXML
    private void btn_room_clicked(ActionEvent event) {
        go_room(event);
    }

    @FXML
    private void btn_artwork_clicked(ActionEvent event) {
        go_Display(event);
    }
    
}
