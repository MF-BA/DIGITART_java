/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author venom-1
 */
public class Editprofileuser_frontController implements Initializable {

    @FXML
    private AnchorPane editprofile_page;
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
