/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Room_Services;
import entity.Room;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class Add_roomController implements Initializable {

       private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TextField INPUT_name;
    @FXML
    private ComboBox<String> INPUT_state;
    @FXML
    private TextArea INPUT_description;
    @FXML
    private Button BTN_cancel;
    @FXML
    private Button BTN_add;
    @FXML
    private TextField INPUT_area;
    @FXML
    private Button btn_room;
    @FXML
    private Button btn_artwork;

    /**
     * Initializes the controller class.
     */
      
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

    @FXML
    private void btn_cancel_clicked(ActionEvent event) {
        
        go_Display(event);
    }

    @FXML
    private void btn_add_clicked(ActionEvent event) {
        
        String name = this.INPUT_name.getText();
    String areaStr = this.INPUT_area.getText();
    String state = (String) this.INPUT_state.getSelectionModel().getSelectedItem();
    String desc = this.INPUT_description.getText();

    Alert alert;

    // CHECK IF THE FIELDS ARE EMPTY
    if (name.isEmpty() || desc.isEmpty() || state == null || areaStr.isEmpty()) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
    } else {
        // Parse the area string to an int
        int area = 0;
        try {
            area = Integer.parseInt(areaStr);
        } catch (NumberFormatException e) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Area must be a number");
            alert.showAndWait();
            return;
        }

        Room room = new Room(name, area, state, desc);
        Room_Services.add(room);
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Added!");
        alert.showAndWait();
        
        // UPDATE THE TABLE VIEW ONCE THE DATA IS SUCCESSFUL
        go_Display(event);
    }
    }
    
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       btn_room.setStyle("-fx-background-color: #470011 ");
       btn_artwork.setStyle("-fx-background-color: transparent ");
        
        
        INPUT_area.textProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue.matches("\\d*")) {
        INPUT_area.setText(newValue.replaceAll("[^\\d]", ""));
    }
});

        combobox();
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
