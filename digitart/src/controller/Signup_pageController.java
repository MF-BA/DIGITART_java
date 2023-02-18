/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import main.main;
/**
 * FXML Controller class
 *
 * @author Islem
 */
public class Signup_pageController implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private DatePicker birth_d;

    @FXML
    private TextField cin;

    @FXML
    private Button confirm_sign;

    @FXML
    private TextField email;

    @FXML
    private Label errormsgaddress;

    @FXML
    private Label errormsgbirthdate;

    @FXML
    private Label errormsgcin;

    @FXML
    private Label errormsgemail;

    @FXML
    private Label errormsgfname;

    @FXML
    private Label errormsggender;

    @FXML
    private Label errormsglname;

    @FXML
    private Label errormsgphonenum;

    @FXML
    private Label errormsgpwd;

    @FXML
    private RadioButton female_gender;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private RadioButton male_gender;

    @FXML
    private TextField phone_num;

    @FXML
    private PasswordField pwd;

    @FXML
    private Button return_btn;

    private main main;
    
    @FXML
    void confirm_btn(ActionEvent event) {

    }

    @FXML
    void return_btn(ActionEvent event) {

    }
    public void setMain(main main) {
        this.main = main; 
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
