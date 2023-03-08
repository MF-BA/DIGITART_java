/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.users_Services;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class Home_pageController implements Initializable {

    @FXML
    private Label labelusername;
    @FXML
    private Button disconnect;
    @FXML
    private Pane avatar_icon;
    @FXML
    private Circle circle_image;
    @FXML
    private ImageView avatar_image;
    @FXML
    private AnchorPane home;
    @FXML
    private Button disconnect1;
    @FXML
    private Button editprof_btn;
    @FXML
    private TextField fname_editprof;
    @FXML
    private TextField lname_editprof;
    @FXML
    private TextField email_editprof;
    @FXML
    private DatePicker birth_d_editprof;
    @FXML
    private TextField cin_editprof;
    @FXML
    private TextField address_editprof;
    @FXML
    private TextField phone_num_editprof;
    @FXML
    private RadioButton male_gender_editprof;
    @FXML
    private RadioButton female_gender_editprof;
    @FXML
    private Button clear_fields_editprof;
    @FXML
    private ComboBox<?> Rolebox_editprof;
    @FXML
    private Label errormsglname_edit;
    @FXML
    private Label errormsgfname_edit;
    @FXML
    private Label errormsgemail_edit;
    @FXML
    private Label errormsggender_edit;
    @FXML
    private Label errormsgcin_edit;
    @FXML
    private Label errormsgaddress_edit;
    @FXML
    private Label errormsgbirthdate_edit;
    @FXML
    private Label errormsgphonenum_edit;
    @FXML
    private Button newpwd_btn;
    @FXML
    private Label errormsgfiiledit;
    @FXML
    private Circle circle_image_edit;
    @FXML
    private ImageView avatar_image1;
    @FXML
    private Label labelusername_edit;

    
    
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        labelusername.setText(Data.user.getFirstname());
        if (Data.user.getImage()!=null){
        Image image = new Image(Data.user.getImage());
        circle_image.setFill(new ImagePattern(image));
        }
     
        
    }    

    @FXML
    private void disconnect_btn(ActionEvent event) {
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

    @FXML
    private void editprof_btn(ActionEvent event) {
    }

    @FXML
    private void clear_fields_update_btn(ActionEvent event) {
    }

    @FXML
    private void handleSelectImage(ActionEvent event) {
    }

    @FXML
    private void newpwd_btn(ActionEvent event) {
    }
    
}
