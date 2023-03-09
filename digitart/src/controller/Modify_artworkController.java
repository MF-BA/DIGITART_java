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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class Modify_artworkController implements Initializable {

    @FXML
    private TextField Input_name_artwork;
    @FXML
    private TextField input_name_artist;
    @FXML
    private ComboBox<Integer> input_id_artist;
    @FXML
    private ComboBox<String> input_idroom;
    @FXML
    private DatePicker input_date;
    @FXML
    private TextArea input_desc;
    @FXML
    private Button btn_cancel;
    @FXML
    private Button btn_modify;
    
    private File selectedFile;
    private String imageUrl;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String nameRoom ;
    private int id_room ;
    
    @FXML
    private Button btn_addimage;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           return_dash_btn.setVisible(true);
        if (!Data.user.getRole().equals("Admin")) {
            return_dash_btn.setVisible(false);
        }
        labeladminname3.setText(Data.user.getFirstname());

        /*if (Data.user.getImage()!=null){
            String imagePath = Data.user.getImage();
        Image image = new Image(new File(imagePath).toURI().toString());
        circle_image.setFill(new ImagePattern(image));
        }*/
        if (Data.user.getImage() != null) {
            Image image = new Image(Data.user.getImage());
            circle_image.setFill(new ImagePattern(image));
        }
        showvalues();
        
       btn_room.setStyle("-fx-background-color: transparent ");
       btn_artwork.setStyle("-fx-background-color: #470011 ");
    }   
    
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

    @FXML
    private void btn_addimage_clicked(ActionEvent event) {
        
                FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Image File");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
    );
   selectedFile = fileChooser.showOpenDialog(btn_addimage.getScene().getWindow());
    if (selectedFile != null) {
       
        imageUrl = "http://localhost/images/"+selectedFile.getName();
        
    }
    }


    @FXML
    private void btn_cancel_clicked(ActionEvent event) {
        
        go_Display(event);
        
    }
    
           public void combobox() {
        ObservableList<Integer> myObservableList = FXCollections.observableArrayList(Artwork_Services.find_idartist());
        input_id_artist.setItems(myObservableList);
        
       
      ObservableList<String> myObservableList1 = FXCollections.observableArrayList(Artwork_Services.find_nameroom());
        input_idroom.setItems(myObservableList1);
    
    }
    
    void showvalues(){
        Input_name_artwork.setText(String.valueOf(Data.artwork.getArtwork_name()));
        input_date.setValue(LocalDate.parse(String.valueOf(Data.artwork.getDate_art())));
        ((ComboBox<Integer>) input_id_artist).setValue(Data.artwork.getId_artist());
        ((ComboBox<String>) input_idroom).setValue(Artwork_Services.find_nameroom(Data.artwork.getId_room()));
        input_name_artist.setText(String.valueOf(Data.artwork.getArtist_name()));
        input_desc.setText(String.valueOf(Data.artwork.getDescription()));
        imageUrl=Data.artwork.getImage_art();
    combobox();
    
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
    

    @FXML
    private void btn_modify_clicked(ActionEvent event) {
        
         Alert alert;
        try {
    
     
            if (Input_name_artwork.getText().isEmpty() || input_name_artist.getText().isEmpty()
                    || input_idroom.getSelectionModel().getSelectedItem() == null
                    || input_desc.getText().isEmpty()|| input_date.getValue() == null ) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the fields ");
                alert.showAndWait();
            } else {
                if(selectedFile!=null)  {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE the artwork Name: " + Input_name_artwork.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    
                    
                         
       
        imageUrl = "http://localhost/images/"+selectedFile.getName();
        
       
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
                    
                    LocalDate localDate = input_date.getValue();
                    nameRoom = input_idroom.getSelectionModel().getSelectedItem();
        
                    id_room = Artwork_Services.find_idroom(nameRoom) ;
                   Artwork artwork= new Artwork(Data.artwork.getId_art(),Input_name_artwork.getText(),input_id_artist.getSelectionModel().getSelectedItem(),input_name_artist.getText(),localDate,input_desc.getText(),imageUrl,id_room);
                    Artwork_Services.modify(artwork);
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    go_Display(event);
                  
                } else {
                    return;
                }
             } else{
                    LocalDate localDate = input_date.getValue();
                    nameRoom = input_idroom.getSelectionModel().getSelectedItem();
        
                    id_room = Artwork_Services.find_idroom(nameRoom) ;
                   Artwork artwork= new Artwork(Data.artwork.getId_art(),Input_name_artwork.getText(),input_id_artist.getSelectionModel().getSelectedItem(),input_name_artist.getText(),localDate,input_desc.getText(),Data.artwork.getImage_art(),id_room);
                    Artwork_Services.modify(artwork);
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    go_Display(event);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
