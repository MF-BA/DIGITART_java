/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import Services.Artwork_Services;
import entity.Artwork;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class Display_artworkController implements Initializable {

    
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_name;
    @FXML
    private TableColumn<?, ?> col_idartist;
    @FXML
    private TableColumn<?, ?> col_nameartist;
    @FXML
    private TableColumn<?, ?> col_date;
    @FXML
    private TableColumn<?, ?> col_desc;
    @FXML
    private Button BTN_add;
    @FXML
    private Button BTN_statistics;
    @FXML
    private Button BTN_modify;
    @FXML
    private Button BTN_delete;
    @FXML
    private Button btn_artworkM;
    
    private ArrayList<Artwork> ArtworkList;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button btn_roomM;
    @FXML
    private TableColumn<?, ?> col_idroom;
    @FXML
    private ImageView image;
    @FXML
    private TableView<?> table_Artwork;
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
        
           private void go_add_artwork(ActionEvent event) {
        try {
             root = FXMLLoader.load(getClass().getResource("/view/add_artwork.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
        
         public void ShowArtwork() {

        ArtworkList = Artwork_Services.Display();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id_art"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("artwork_name"));
        col_idartist.setCellValueFactory(new PropertyValueFactory<>("id_artist"));
        col_nameartist.setCellValueFactory(new PropertyValueFactory<>("artist_name"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_art"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_idroom.setCellValueFactory(new PropertyValueFactory<>("id_room"));

        if (table_Artwork != null && table_Artwork instanceof TableView) {
            // Cast room_tableview to TableView<Room> and set its items
            ((TableView<Artwork>) table_Artwork).setItems(FXCollections.observableArrayList(ArtworkList));
        }
    }
         
         
            public Artwork SelectRoom() {

    Artwork t = ((TableView<Artwork>) table_Artwork).getSelectionModel().getSelectedItem();
        return t;
 
    }
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ShowArtwork();
    }    

    @FXML
    private void btn_add_clicked(ActionEvent event) {
        
        go_add_artwork(event);
    }

    @FXML
    private void btn_modify_clicked(ActionEvent event) {
    }

    @FXML
    private void btn_delete_clicked(ActionEvent event) {
    }

   

    @FXML
    private void btn_artwork_clicked(ActionEvent event) {
    }

    @FXML
    private void btn_room_clicked(ActionEvent event) {
        go_room(event);
    }
    
}
