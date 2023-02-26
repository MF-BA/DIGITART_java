/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Room_Services;
import entity.Data;
import entity.Room;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class Display_roomController implements Initializable {
    
       private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button BTN_add;
    @FXML
    private Button BTN_statistics;
    @FXML
    private TableView<?> table_room;
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_name;
    @FXML
    private TableColumn<?, ?> col_area;
    @FXML
    private TableColumn<?, ?> col_state;
    @FXML
    private TableColumn<?, ?> col_desc;
    @FXML
    private Button BTN_modify;
    @FXML
    private Button BTN_delete;

     private ArrayList<Room> RoomList;
    @FXML
    private TextField room_search;
    @FXML
    private Button btn_room;
    @FXML
    private Button btn_artwork;
    @FXML
    private Label labeladminname;
    @FXML
    private Label labeladminname1;
    @FXML
    private Label labeladminname2;

    public void ShowRoom() {

       RoomList = Room_Services.Display();

       
        col_name.setCellValueFactory(new PropertyValueFactory<>("name_room"));
        col_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        col_state.setCellValueFactory(new PropertyValueFactory<>("state"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));

        if (table_room != null && table_room instanceof TableView) {
            // Cast room_tableview to TableView<Room> and set its items
            ((TableView<Room>) table_room).setItems(FXCollections.observableArrayList(RoomList));
        }
    }
    
     public Room SelectRoom() {

    Room t = ((TableView<Room>) table_room).getSelectionModel().getSelectedItem();
        return t;

       
 
    }
    
    
    
    

    @FXML
    private void btn_add_clicked(ActionEvent event) {
        
         go_ADD(event);
    }
    
    
    
    
    private void go_ADD(ActionEvent event) {
        try {
             root = FXMLLoader.load(getClass().getResource("/view/add_room.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     private void go_artwork(ActionEvent event) {
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
     
       private void go_modify_room(ActionEvent event) {
        try {  
            Alert alert;
         Room r= SelectRoom();
            if (table_room.getSelectionModel().getSelectedIndex()== -1) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the item first");
                alert.showAndWait();
            }else{
             root = FXMLLoader.load(getClass().getResource("/view/modify_room.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();}
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

    @FXML
    private void btn_modify_clicked(ActionEvent event) {
         Data.setRoom(SelectRoom());
        go_modify_room(event);
    }

    @FXML
    private void btn_delete_clicked(ActionEvent event) {
         Alert alert;
         Room r= SelectRoom();
       
        try {
            if (table_room.getSelectionModel().getSelectedIndex()== -1) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the item first");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete the room with ID: " + r.getId_room()+ "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    
                    Room_Services.delete(r.getId_room());
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    ShowRoom();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ShowRoom();
        
       btn_room.setStyle("-fx-background-color: #470011 ");
       btn_artwork.setStyle("-fx-background-color: transparent ");
    }   

   

    @FXML
    private void btn_artwork_clicked(ActionEvent event) {
        
          
          go_artwork(event);
        
    }


  
    @FXML
    private void search_room_Keytyped(KeyEvent event) {
               RoomList = Room_Services.search(room_search.getText());

        col_id.setCellValueFactory(new PropertyValueFactory<>("id_room"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name_room"));
        col_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        col_state.setCellValueFactory(new PropertyValueFactory<>("state"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));

        if (table_room != null && table_room instanceof TableView) {
            // Cast room_tableview to TableView<Room> and set its items
            ((TableView<Room>) table_room).setItems(FXCollections.observableArrayList(RoomList));
        }
    }


    @FXML
    private void btn_room_clicked(ActionEvent event) {
    }
    
}
  