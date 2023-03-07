/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import Services.users_Services;
import static controller.Signin_pageController.conn;
import entity.Data;
import entity.users;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
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
    private RadioButton yesartist;
    @FXML
    private RadioButton noartist;
    @FXML
    private Label errorquestionartist;
    @FXML
    private CheckBox showpwd;
    
    @FXML
    void confirm_btn(ActionEvent event) throws NoSuchAlgorithmException, AddressException, MessagingException {
       
        
        LocalDate BirthDate = birth_d.getValue();
        String firstname = fname.getText();
        String lastname = lname.getText();
        String Email = email.getText();
        String passwd = pwd.getText();
        String Address = address.getText();
        String gender = null;
        String role = null;
    int Cin = 0;
    int phone_number = 0;
       errormsgfname.setText("");
    errormsglname.setText("");
    errormsgemail.setText("");
    errormsgpwd.setText("");
    errormsgcin.setText("");
    errormsgaddress.setText("");
     errormsgphonenum.setText("");
   errormsggender.setText("");
   errormsgelements.setText("");
   errormsgbirthdate.setText("");
   errorquestionartist.setText("");
   
   
   if (!phone_num.getText().isEmpty())
    {               
    if (!phone_num.getText().matches("\\d+")) {
        errormsgphonenum.setText("Phone number should be a number!");
    } else if (phone_num.getText().toString().length()<8)
    {
        errormsgphonenum.setText("Phone number should contain 8 digits!");
    }
    else {
        phone_number = Integer.parseInt(phone_num.getText().trim());
    }
            }
       if (!cin.getText().isEmpty())
    {
     if (!cin.getText().matches("\\d+")) {
        errormsgcin.setText("CIN should be a number!");
    } else if (cin.getText().toString().length()<8)
    {
        errormsgcin.setText("CIN should contain 8 digits!");
    }
    else
    {
        Cin = Integer.parseInt(cin.getText().trim());  
    }   
    }
     if (!male_gender.isSelected() && !female_gender.isSelected()) {
          
        errormsggender.setText("Please specify your gender!"); 
        }  
     if (!yesartist.isSelected() && !noartist.isSelected()) {
          
        errorquestionartist.setText("Please answer the question!"); 
        }
   
     if (!Email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
    errormsgemail.setText("Invalid email format!");
    }
if (firstname.isEmpty() || lastname.isEmpty() || Email.isEmpty() || passwd.isEmpty() || Address.isEmpty() || phone_num.getText().isEmpty() || cin.getText().isEmpty() || BirthDate == null || (!male_gender.isSelected() && !female_gender.isSelected())|| (!yesartist.isSelected() && !noartist.isSelected())) {
         
       errormsgelements.setText("Please fill all elements!!");  
    
    if (passwd.isEmpty())   {
     
     errormsgpwd.setText("fill paswword !!");   
    }
    if (Address.isEmpty())  {
     
     errormsgaddress.setText("fill Address !!");   
    }
    if ( Email.isEmpty())
    {
     
     errormsgemail.setText("Fill Email !!");   
    }
    if ( firstname.isEmpty())
    {
     errormsgfname.setText("Fill first name !!");   
    }
    if ( lastname.isEmpty())
    {
       errormsglname.setText("Fill last name !!");
    }
    if (phone_num.getText().isEmpty())
    {
     errormsgphonenum.setText("Fill phone number !!");  
    }
    if (BirthDate == null)
    {
        errormsgbirthdate.setText("Fill birth date !!"); 
    }
    if ( cin.getText().isEmpty() )
    {
     errormsgcin.setText("Fill Cin !!");   
    }
    } 
else if (yesartist.isSelected() && noartist.isSelected())
    {
     errorquestionartist.setText("Please select only one answer!");    
    }
   else if (male_gender.isSelected() && female_gender.isSelected())
    {
     errormsggender.setText("Please specify your gender!");    
    }
    else {
    if (male_gender.isSelected()) {
        gender = male_gender.getText();  
    } 
    if (female_gender.isSelected()) {
        gender = female_gender.getText();
    }
   if (yesartist.isSelected()) {
        role = "Artist";
    }
   if (noartist.isSelected()) {
        role = "Subscriber";
    }
   
   boolean isValid = isValidEmail(email.getText());
     if (isValid) {
    // email is valid
    String sql = "SELECT * FROM users WHERE email=?";
   PreparedStatement st;
   ResultSet res = null;
     try{
         st = conn.prepareStatement(sql);
                st.setString(1, Email);
                res = st.executeQuery();
   
    if (res.next())
    {
       errormsgemail.setText(" this email is already used !!");    
    }
    else
    {
        
    Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");    
    Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Data.username, Data.password);
            }
        });
    Random random = new Random();
    int resetCode = random.nextInt(1000000); // Generate a random 6-digit code
    String emailContent = "Your verification code is: " + resetCode;
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(Data.username));
    message.setRecipients(Message.RecipientType.TO,
    InternetAddress.parse(email.getText()));
    message.setSubject("Email Verification");
    message.setText(emailContent);
    Transport.send(message);
    TextInputDialog dialog1 = new TextInputDialog();
                    dialog1.setTitle("Email Verification");
                    dialog1.setHeaderText("Enter the code sent by mail");
                    dialog1.setContentText("CODE :");
                    Optional<String> result1 = dialog1.showAndWait();
                    final int code = Integer.parseInt(result1.get());
                    if (resetCode == code) {
                      String hashedPassword = users_Services.hashPassword(passwd);
                      user1 = new users(Cin ,firstname, lastname, Email, hashedPassword, Address, phone_number, BirthDate, gender, role,"unblocked");
                      user = new users_Services();
                      user.adduser(user1); 
                      errormsgfname.setText("");
                      errormsglname.setText("");
                      errormsgemail.setText("");
                      errormsgpwd.setText("");
                      errormsgcin.setText("");
                      errormsgaddress.setText("");
                      errormsgphonenum.setText("");
                      errormsggender.setText("");
                      errormsgelements.setText("");
                      errormsgbirthdate.setText("");
                      errorquestionartist.setText("");
                      clear();
                      loginswitch(event);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Email Verification");
                        alert.setHeaderText(null);
                        alert.setContentText("the code you entered is incorrect!! ");
                        alert.showAndWait();
                    }
    
    }
   }catch (SQLException ex) {
                 Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
             }
         } else {
         // email is not valid
         errormsgemail.setText(" this email is not valid !!");  
      }
        
   
        
    }

  }
    public static boolean isValidEmail(String email) {
        boolean isValid = false;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
            isValid = true;
        } catch (AddressException ex) {
            // email is not valid
        }
        return isValid;
    }
 public void clear(){
     
        fname.setText("");
        lname.setText("");
        email.setText("");
        pwd.setText("");
        address.setText("");
        cin.setText("");
        phone_num.setText("");
        birth_d.setValue(null);
        female_gender.setSelected(false);
        male_gender.setSelected(false);
        
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

    @FXML
    private void showpwd(ActionEvent event) {
         if (showpwd.isSelected()) {
            pwd.setPromptText(pwd.getText());
            pwd.setText("");
        } else {
            pwd.setText(pwd.getPromptText());
            pwd.setPromptText("");
        }
    }
    
    
}
