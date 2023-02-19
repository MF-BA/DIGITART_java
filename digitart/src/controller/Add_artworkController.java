/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Artwork_Services;
import entity.Artwork;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

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
    private ComboBox<Integer> input_idroom;
    @FXML
    private DatePicker input_date;
    @FXML
    private TextArea input_desc;

    /**
     * Initializes the controller class.
     */
    
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
    }    

    @FXML
    private void btn_addimage_clicked(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Image File");
    fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
    );
   selectedFile = fileChooser.showOpenDialog(btn_addimage.getScene().getWindow());
    if (selectedFile != null) {
       
        imageUrl = selectedFile.toURI().toString();
        System.out.println(imageUrl);
    }
    }

    @FXML
    private void btn_add_clicked(ActionEvent event) {
          String name = this.Input_name_artwork.getText();
        String nameartist = this.input_name_artist.getText(); 
        int id_artist = this.input_id_artist.getSelectionModel().getSelectedItem();
        int id_room = this.input_idroom.getSelectionModel().getSelectedItem();
        String desc = this.input_desc.getText();
         LocalDate date = this.input_date.getValue();
         
        

        Alert alert;

        // CHECK IF THE FIELDS ARE EMPTY
        if (name.isEmpty() ||nameartist.isEmpty() || desc.isEmpty() || date == null ) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
           Artwork artwork= new Artwork(name,id_artist,nameartist,date,desc,imageUrl,id_room);
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
        List<Integer> options = new ArrayList<>();
        options.add(1);
        options.add(2);
        input_id_artist.getItems().addAll(options);
      
          List<Integer> options1 = new ArrayList<>();
        options.add(1);
       
        input_idroom.getItems().addAll(options1);
        
        
        
    }

    @FXML
    private void btn_cancel_clicked(ActionEvent event) {
        go_Display(event);
    }
    
}
