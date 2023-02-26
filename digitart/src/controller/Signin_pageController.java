/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.users_Services;
import entity.Data;
import entity.users;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.Conn;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
    private void login_btn(ActionEvent event) {

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
                pst.setString(2, pwdlogin.getText());

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    if(rs.getString("status").equals("unblocked"))
                    {
                    loginerrormsg.setText("Email and Password are correct!! ");
                    Data.user
                            = user.getuserdata(emaillogin.getText(), pwdlogin.getText());

                    System.out.println(Data.user);
                    role = rs.getString("role");    
                    }
                    else
                    {
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
}
