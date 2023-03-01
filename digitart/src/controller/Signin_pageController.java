/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.users_Services;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
//import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import entity.Data;
import entity.users;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
//import sun.rmi.transport.Transport;
import utils.Conn;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.swing.JOptionPane;

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
    private Button register_btn;

    private users_Services user;
    private users user1;
    static Connection conn = Conn.getCon();

    PreparedStatement pst;
    @FXML
    private Button login_btn;
    @FXML
    private CheckBox showpwd;
    @FXML
    private AnchorPane loginpage;
    @FXML
    private Button confirm_code_google_btn;
    @FXML
    private TextField codegoogle;
    @FXML
    private Button return_fromcode_login_btn;
    @FXML
    private AnchorPane codepage;
    @FXML
    private Label codeerrormsg;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        codepage.setVisible(false);
        loginpage.setVisible(true);
    }

    @FXML
    private void register_clicked(ActionEvent event) {

        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/signup_page.fxml"));

            Scene scene = new Scene(parent2);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Register");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void login_btn(ActionEvent event) throws NoSuchAlgorithmException {

        users_Services user = new users_Services();
        String role = null;

        if (emaillogin.getText().trim().isEmpty() && pwdlogin.getText().trim().isEmpty()) {
            emailerrormsg.setText("Email is Empty !! ");
            pwderrormsg.setText("Password is Empty !! ");
        } else if (emaillogin.getText().trim().isEmpty()) {
            emailerrormsg.setText("Email is Empty !! ");
        } else if (pwdlogin.getText().trim().isEmpty()) {
            pwderrormsg.setText("Password is Empty !! ");
        } else {
            try {
                String sql = "Select * from users where email=? and password=?";

                pst = conn.prepareStatement(sql);
                pst.setString(1, emaillogin.getText());
                pst.setString(2, users_Services.hashPassword(pwdlogin.getText()));

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    if (rs.getString("status").equals("unblocked")) {
                        loginerrormsg.setText("Email and Password are correct!! ");
                        Data.user
                                = user.getuserdata(emaillogin.getText(), pwdlogin.getText());

                        System.out.println(Data.user);
                        role = rs.getString("role");
                    } else {
                        loginerrormsg.setText("Your account is blocked !! ");
                        emaillogin.setText("");
                        pwdlogin.setText("");
                    }

                } else {
                    loginerrormsg.setText("Email or Password incorrect !! ");
                    emaillogin.setText("");
                    pwdlogin.setText("");

                }
                //conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (role != null) {
                if (role.equals("Admin")) {
                    gotoDash(event);
                } else {
                    gotoHome(event);
                }
            }

        }

    }

    public void gotoHome(ActionEvent event) {

        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/home_page.fxml"));

            Scene scene = new Scene(parent2);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoDash(ActionEvent event) {
        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/Dashboard.fxml"));

            Scene scene = new Scene(parent2);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showpwd(ActionEvent event) {

        if (showpwd.isSelected()) {
            pwdlogin.setPromptText(pwdlogin.getText());
            pwdlogin.setText("");
        } else {
            pwdlogin.setText(pwdlogin.getPromptText());
            pwdlogin.setPromptText("");
        }

    }

    @FXML
    private void forgotPasswordButton(ActionEvent event) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Forgot Password");
        dialog.setHeaderText("Enter your email address");
        dialog.setContentText("Email :");

        Optional<String> result = dialog.showAndWait();
        Properties props = new Properties();
        //props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Data.username, Data.password);
            }
        });
        String recipientEmail = result.get();
        System.out.println(recipientEmail);
        try {
            String sql = "Select * from users where email = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, recipientEmail);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                try {
                    Random random = new Random();
                    int resetCode = random.nextInt(1000000); // Generate a random 6-digit code
                    String emailContent = "Your reset code is: " + resetCode;

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(Data.username));

                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(recipientEmail));
                    message.setSubject("Reset Password");
                    message.setText(emailContent);
                    Transport.send(message);
                    /* Transport t = session.getTransport("smtp");
                    t.connect(Data.username, Data.password);
                    t.sendMessage(message, message.getAllRecipients());
                    t.close();
                     */
                    System.out.println("Email sent successfully!");

                    TextInputDialog dialog1 = new TextInputDialog();
                    dialog1.setTitle("Forgot Password");
                    dialog1.setHeaderText("Enter the code sent by mail");
                    dialog1.setContentText("CODE :");
                    Optional<String> result1 = dialog1.showAndWait();
                    final int code = Integer.parseInt(result1.get());
                    if (resetCode == code) {
                        TextInputDialog dialog2 = new TextInputDialog();
                        dialog2.setTitle("Forgot Password");
                        dialog2.setHeaderText("Enter your new password");
                        dialog2.setContentText("New password :");
                        Optional<String> result2 = dialog2.showAndWait();
                        String sql1 = "update users set password = ? where id = ?";
                        System.out.println(result2.get());
                        System.out.println(id);

                        pst = conn.prepareStatement(sql1);
                        pst.setString(1, result2.get());
                        pst.setInt(2, id);
                        pst.executeUpdate();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Forgot Password");
                        alert.setHeaderText(null);
                        alert.setContentText("the code you entered is incorrect!! ");
                        alert.showAndWait();
                    }

                } catch (MessagingException e) {
                    //throw new RuntimeException(e);
                    e.printStackTrace();
                }

            }

            

        } catch (SQLException ex) {
            //Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    @FXML
    private void google_signin_btn(ActionEvent event) throws IOException {
        
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        new NetHttpTransport(),
        new JacksonFactory(),
        "120437690388-3v4hej9ne4v073q4o5ij9hpqkenih37v.apps.googleusercontent.com",
        "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",
        Arrays.asList("email", "profile"))
        .setAccessType("offline")
        .build();

    String authorizationUrl = flow.newAuthorizationUrl()
        .setRedirectUri("urn:ietf:wg:oauth:2.0:oob")
        .build();

    try {
        Desktop.getDesktop().browse(new URI(authorizationUrl));
    } catch (URISyntaxException ex) {
        Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
    }

    codepage.setVisible(true);
    loginpage.setVisible(false);
     
    }

    @FXML
    private void confirm_code_google_btn(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        String code = codegoogle.getText();
        users_Services user = new users_Services();
    if (!code.isEmpty()) {
    
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            new NetHttpTransport(),
            new JacksonFactory(),
            "120437690388-3v4hej9ne4v073q4o5ij9hpqkenih37v.apps.googleusercontent.com",
            "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",
            Arrays.asList("email", "profile"))
            .setAccessType("offline")
            .build();
        try {
         
             GoogleTokenResponse tokenResponse = flow.newTokenRequest(code)
                     .setRedirectUri("urn:ietf:wg:oauth:2.0:oob")
                     .execute();
             
             GoogleCredential credential = new GoogleCredential.Builder()
                     .setTransport(new NetHttpTransport())
                     .setJsonFactory(new JacksonFactory())
                     .setClientSecrets("120437690388-3v4hej9ne4v073q4o5ij9hpqkenih37v.apps.googleusercontent.com", "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")
                     .build()
                     .setFromTokenResponse(tokenResponse);
             
             GoogleIdToken idToken = tokenResponse.parseIdToken();
             GoogleIdToken.Payload payload = idToken.getPayload();
             String email = payload.getEmail();
             String password = UUID.randomUUID().toString();
             
             String sql = "SELECT * FROM users WHERE email=?";
             
             String query = "INSERT INTO users (email, password) VALUES (?, ?)";
             PreparedStatement stmt;
             PreparedStatement st;
             ResultSet res = null;
             try {
                st = conn.prepareStatement(sql);
                st.setString(1, email);
                res = st.executeQuery();
                
        if (res.next()) {
           
          Data.user = user.getgoogleuserdata(email);

        }
          else
        {
            stmt = conn.prepareStatement(query);
                 stmt.setString(1, email);
                 stmt.setString(2, password);
                 stmt.executeUpdate();
                    
        }
           
             } catch (SQLException ex) {
                 Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
             }
             Data.user = user.getgoogleuserdata(email);
             gotoHome(event);
             
         
        } catch (TokenResponseException ex) {
        // Display an error message to the user
        codeerrormsg.setText("the code is wrong !! ");
    }
            
                }
    }

    @FXML
    private void return_fromcode_login_btn(ActionEvent event) {

      codepage.setVisible(false);
        loginpage.setVisible(true);
    }

}
