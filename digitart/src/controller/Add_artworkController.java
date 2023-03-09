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
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
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
    @FXML
    private Pane avatar_icon;
    @FXML
    private Circle circle_image;
    @FXML
    private ImageView avatar_image;
    @FXML
    private Label labeladminname2;
    @FXML
    private Label labeladminname3;
    @FXML
    private Button return_dash_btn;
    @FXML
    private Label labeladminname1;
    @FXML
    private Label labeladminname;
    @FXML
    private Button deconnect1;

    /**
     * Initializes the controller class.
     */
    
     private void go_signin(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/signin_page.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private void go_home(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/dashboard_homepage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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

    @FXML
private void btn_add_clicked(ActionEvent event) throws IOException {
    
       imageUrl="http://localhost/images/"+selectedFile.getName();
       String phpUrl = "http://localhost/images/upload.php";


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
//        
//                public void combobox() {
//        ObservableList<Integer> myObservableList = FXCollections.observableArrayList(Artwork_Services.find_idartist());
//        input_id_artist.setItems(myObservableList);
//        
//       
//       ObservableList<String> myObservableList1 = FXCollections.observableArrayList(Artwork_Services.find_nameroom());
//        input_idroom.setItems(myObservableList1);  
//        
//        
//
//        // Create a filtered list to update the ComboBox suggestions
//        FilteredList<String> filteredItems = new FilteredList<>(myObservableList1, p -> true);
//        input_idroom.setOnKeyReleased(event -> {
//            filteredItems.setPredicate(p -> p.toLowerCase().contains(input_idroom.getEditor().getText().toLowerCase().trim()));
//        });
//
//        // Update the ComboBox popup with the filtered list items
//        input_idroom.setItems(filteredItems);
//       
//    }

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

    @FXML
    private void return_dash_btn(ActionEvent event) {
        go_home(event);
    }

    @FXML
    private void deconnect_btn(ActionEvent event) {
        go_signin(event);
    }
    
}
