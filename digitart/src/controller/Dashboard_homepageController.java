/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author venom-1
 */
public class Dashboard_homepageController implements Initializable {

    @FXML
    private Circle circle_image;
    @FXML
    private ImageView avatar_image;
    @FXML
    private Label labeladminname;
    @FXML
    private Label labeladminname1;
    @FXML
    private Button users_btn;
    @FXML
    private Button gallery_btn;
    @FXML
    private Button tickets_btn;
    @FXML
    private Button auction_btn;
    @FXML
    private Button events_btn;
    @FXML
    private Label labeladminname11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void users_btn(ActionEvent event) {
    }

    @FXML
    private void gallery_btn(ActionEvent event) {
    }

    @FXML
    private void tickets_btn(ActionEvent event) {
    }

    @FXML
    private void auction_btn(ActionEvent event) {
    }

    @FXML
    private void events_btn(ActionEvent event) {
    }
    
}
