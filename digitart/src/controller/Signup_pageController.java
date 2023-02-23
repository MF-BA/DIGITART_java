/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.users_Services;
import entity.users;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    private users_Services user;
    private users user1;
    @FXML
    private Label errormsgelements;
    private Stage stage;
    private Scene scene;
    private Parent pt;
    
    @FXML
    void confirm_btn(ActionEvent event) {
       
        
        LocalDate BirthDate = birth_d.getValue();
        String firstname = fname.getText();
        String lastname = lname.getText();
        String Email = email.getText();
        String passwd = pwd.getText();
        String Address = address.getText();
        String gender = null;
        
    int Cin = 0;
    int phone_number = 0;
       
       if (!phone_num.getText().isEmpty()) {
    if (!phone_num.getText().matches("\\d+")) {
        errormsgphonenum.setText("Phone number should be a number!");
    } else {
        phone_number = Integer.parseInt(phone_num.getText().trim());
    }
}

if (!cin.getText().isEmpty()) {
    if (!cin.getText().matches("\\d+")) {
        errormsgcin.setText("CIN should be a number!");
    } else {
        Cin = Integer.parseInt(cin.getText().trim());  
    }
}

   if (firstname.isEmpty() || lastname.isEmpty() || Email.isEmpty() || passwd.isEmpty() || Address.isEmpty() || phone_num.getText().isEmpty() || cin.getText().isEmpty() || BirthDate == null) {
    errormsgelements.setText("Please fill all elements!!");  
    } else if (!male_gender.isSelected() && !female_gender.isSelected()) {
    errormsggender.setText("Please specify your gender!"); 
    }  else {
    if (male_gender.isSelected()) {
        gender = male_gender.getText();  
    } else if (female_gender.isSelected()) {
        gender = female_gender.getText();
    }
    user1 = new users(firstname, lastname, Email, passwd, Address, gender, Cin, phone_number, BirthDate);
    user = new users_Services();
    user.adduser(user1); 
    
        fname.setText("");
        lname.setText("");
        email.setText("");
        pwd.setText("");
        address.setText("");
        loginswitch(event);
    }
       
   
       
   
    }

    private void loginswitch(ActionEvent event) {
        try {
            pt = FXMLLoader.load(getClass().getResource("/view/signin_page.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(pt);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signup_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    void return_btn(ActionEvent event) {
     
    }
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /* confirm_sign.setOnAction(event->{
         /* String birth_date = birth_d.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date BDate = null;

        try {
            BDate = sdf.parse(birth_date); // deal with ParseException
        } catch (ParseException ex) {
            Logger.getLogger(Signup_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
       //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //java.sql.Date BirthDate = java.sql.Date.valueOf(sdf.format(birth_d));
        /*LocalDate BirthDate = birth_d.getValue();
        if( male_gender.isSelected() ){
        String gender = male_gender.getText();
        user1 = new users (fname.getText(),lname.getText(),email.getText(),pwd.getText(),address.getText(),gender,Integer.parseInt(cin.getText().trim()),Integer.parseInt(phone_num.getText().trim()),BirthDate);
    }
        if( female_gender.isSelected() ){
        String gender = female_gender.getText();
        user1 = new users (fname.getText(),lname.getText(),email.getText(),pwd.getText(),address.getText(),gender,Integer.parseInt(cin.getText().trim()),Integer.parseInt(phone_num.getText().trim()),BirthDate);
    }
       
      
      user.adduser(user1);
       try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/view/add_auction.fxml"));
                
                Scene scene=new Scene(parent2);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Interface 2");
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(Signup_pageController.class.getName()).log(Level.SEVERE, null, ex);
            }
     });*/
      /*fname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
      lname.
      email.
      pwd.
      address.
      gender.
      cin.
      phone_num.
      BirthDate.*/
        
        
    }    

    @FXML
    private void return_btn_clicked(ActionEvent event) {
        try {
             pt=FXMLLoader
                    .load(getClass().getResource("/view/signin_page.fxml"));
            
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
    
    
}
