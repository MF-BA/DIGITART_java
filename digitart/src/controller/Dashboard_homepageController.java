/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Data;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

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

    private Stage stage;
    private Scene scene;
    private Parent pt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labeladminname.setText(Data.user.getFirstname());
        if (Data.user.getImage() != null) {
            Image image = new Image(Data.user.getImage());
            circle_image.setFill(new ImagePattern(image));
        }
    }

    @FXML
    private void users_btn(ActionEvent event) {
        try {
            pt = FXMLLoader
                    .load(getClass().getResource("/view/Dashboard.fxml"));

            scene = new Scene(pt);
            stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gallery_btn(ActionEvent event) {

    }

    @FXML
    private void tickets_btn(ActionEvent event) {
    }

    @FXML
    private void auction_btn(ActionEvent event) {
        try {
            pt = FXMLLoader
                    .load(getClass().getResource("/view/display_auction_back.fxml"));

            scene = new Scene(pt);
            stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void events_btn(ActionEvent event) {
    }

}
