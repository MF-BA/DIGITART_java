/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import Services.Artwork_Services;
import entity.Artwork;
import entity.Data;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    private ComboBox<Integer> input_idroom;
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
    
    @FXML
    private Button btn_addimage;
    @FXML
    private Button btn_room;
    @FXML
    private Button btn_artwork;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showvalues();
        
       btn_room.setStyle("-fx-background-color: transparent ");
       btn_artwork.setStyle("-fx-background-color: #470011 ");
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
       
        imageUrl = selectedFile.toURI().toString();
    }
    }


    @FXML
    private void btn_cancel_clicked(ActionEvent event) {
        
        go_Display(event);
        
    }
    
           public void combobox() {
        ObservableList<Integer> myObservableList = FXCollections.observableArrayList(Artwork_Services.find_idartist());
        input_id_artist.setItems(myObservableList);
        
       
      ObservableList<Integer> myObservableList1 = FXCollections.observableArrayList(Artwork_Services.find_idroom());
        input_idroom.setItems(myObservableList1);
    
    }
    
    void showvalues(){
            Input_name_artwork.setText(String.valueOf(Data.getArtwork().getArtwork_name()));
        input_date.setValue(LocalDate.parse(String.valueOf(Data.getArtwork().getDate_art())));
        ((ComboBox<Integer>) input_id_artist).setValue(Data.getArtwork().getId_artist());
        ((ComboBox<Integer>) input_idroom).setValue(Data.getArtwork().getId_room());
        input_name_artist.setText(String.valueOf(Data.getArtwork().getArtist_name()));
        input_desc.setText(String.valueOf(Data.getArtwork().getDescription()));
        imageUrl=Data.getArtwork().getImage_art();
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
                    || input_desc.getText().isEmpty()|| input_date.getValue() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill the fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE the artwork Name: " + Input_name_artwork.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    LocalDate localDate = input_date.getValue();
                   Artwork artwork= new Artwork(Data.getArtwork().getId_art(),Input_name_artwork.getText(),input_id_artist.getSelectionModel().getSelectedItem(),input_name_artist.getText(),localDate,input_desc.getText(),imageUrl,input_idroom.getSelectionModel().getSelectedItem());
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
    
}
