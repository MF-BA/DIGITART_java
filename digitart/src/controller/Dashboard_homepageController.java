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
    @FXML
    private Button disconnect;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       labeladminname.setText(Data.user.getFirstname());
       System.out.println(Data.user.getImage());
        
       try {
            if (Data.user.getImage() != null) {
                Image image = new Image(Data.user.getImage());
                circle_image.setFill(new ImagePattern(image));
            } else {
                circle_image.setFill(null);
            }
        } catch (Exception e) {
            // handle the exception
            System.out.println("An error occurred: " + e.getMessage());
        }
       
    }    

    @FXML
    private void users_btn(ActionEvent event) {
        try {
             pt=FXMLLoader
                    .load(getClass().getResource("/view/Dashboard.fxml"));
            
             scene=new Scene(pt);
             stage=(Stage) ((Node) event.getSource())
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
       try {
             pt=FXMLLoader
                    .load(getClass().getResource("/view/display_artwork.fxml"));
            
             scene=new Scene(pt);
             stage=(Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void tickets_btn(ActionEvent event) {
        try {
             pt=FXMLLoader
                    .load(getClass().getResource("/view/add_ticket.fxml"));
            
             scene=new Scene(pt);
             stage=(Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void auction_btn(ActionEvent event) {
        try {
             pt=FXMLLoader
                    .load(getClass().getResource("/view/display_auction_back.fxml"));
            
             scene=new Scene(pt);
             stage=(Stage) ((Node) event.getSource())
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
        try {
             pt=FXMLLoader
                    .load(getClass().getResource("/view/add_event.fxml"));
            
             scene=new Scene(pt);
             stage=(Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deconnect_btn(ActionEvent event) {
        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/signin_page.fxml"));

            Scene scene = new Scene(parent2);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("DIGITART");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}