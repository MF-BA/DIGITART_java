/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class Signin_pageController implements Initializable {

    @FXML
    private Label loginerrormsg;
    @FXML
    private Label pwderrormsg;
    @FXML
    private Label emailerrormsg;
    @FXML
    private TextField emaillogin;
    @FXML
    private PasswordField pwdlogin;
    @FXML
    private Button return_btn_login;
    @FXML
    private Button register_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void return_btn_login(ActionEvent event) {
    }

    @FXML
    private void register_clicked(ActionEvent event) {
        
        try {
            Parent parent2=FXMLLoader
                    .load(getClass().getResource("/view/signup_page.fxml"));
            
            Scene scene=new Scene(parent2);
            Stage stage=(Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Register");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
