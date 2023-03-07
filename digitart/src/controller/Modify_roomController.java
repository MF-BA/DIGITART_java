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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class Modify_roomController implements Initializable {

    @FXML
    private TextField INPUT_name;
    @FXML
    private ComboBox<String> INPUT_state;
    @FXML
    private TextArea INPUT_description;
    @FXML
    private Button BTN_cancel;
    @FXML
    private TextField INPUT_area;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button BTN_modify;
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
        
        // controle de saisie
               INPUT_area.textProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue.matches("\\d*")) {
        INPUT_area.setText(newValue.replaceAll("[^\\d]", ""));
    }
});
           
      btn_artwork.setStyle("-fx-background-color: transparent ");
      btn_room.setStyle("-fx-background-color: #470011 ");
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

    @FXML
    private void btn_cancel_clicked(ActionEvent event) {
        go_Display(event);
    }

    
         private void go_Display(ActionEvent event) {
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
      
        public void combobox() {
        List<String> options = new ArrayList<>();
        options.add("Available");
        options.add("Unvailable");
        INPUT_state.getItems().addAll(options);
    }
        
           void showvalues(){
            INPUT_name.setText(String.valueOf(Data.room.getName_room()));
        INPUT_area.setText(Integer.toString(Data.room.getArea()));
        ((ComboBox<String>) INPUT_state).setValue(Data.room.getState());
        INPUT_description.setText(String.valueOf(Data.room.getDescription()));
    combobox();
    
    }

    @FXML
    private void btn_modify_clicked(ActionEvent event) {
        
        Alert alert;
        try {
            if (INPUT_name.getText().isEmpty() || INPUT_description.getText().isEmpty()
                    || INPUT_state.getSelectionModel().getSelectedItem() == null
                    || INPUT_area.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill the fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE the room Name: " + INPUT_name.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                     int area = Integer.parseInt(this.INPUT_area.getText());
                   Room room= new Room(Data.room.getId_room(),INPUT_name.getText(),area,INPUT_state.getSelectionModel().getSelectedItem(),INPUT_description.getText());
                   Room_Services.modify(room);
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
            go_Display(event);
    }

    @FXML
    private void btn_artwork_clicked(ActionEvent event) {
        
        go_artwork(event);
    }

    
}
