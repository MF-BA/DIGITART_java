/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.users_Services;
import entity.users;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class DashboardController implements Initializable {

    @FXML
    private Label labeladminname;
    @FXML
    private Label labeladminname1;
    @FXML
    private Label labeladminname2;
    @FXML
    private Button add_user;
    @FXML
    private Button modify_user;
    @FXML
    private Button list_users;
    @FXML
    private Button add_dash;
    @FXML
    private PasswordField pwd;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField email;
    @FXML
    private DatePicker birth_d;
    @FXML
    private TextField cin;
    @FXML
    private TextField address;
    @FXML
    private TextField phone_num;
    @FXML
    private RadioButton male_gender;
    @FXML
    private RadioButton female_gender;
    
    @FXML
    private Button clear_fields;
    @FXML
    private TableView<?> user_table;
    @FXML
    private Button edit_profile;
    @FXML
    private TextField search_userfield;
    @FXML
    private Button search_user_btn;
    @FXML
    private ComboBox<?> Rolebox;
    
    private ArrayList<users> userslist;
    @FXML
    private TableColumn<?, ?> id_tb;
    @FXML
    private TableColumn<?, ?> cin_tb;
    @FXML
    private TableColumn<?, ?> fname_tb;
    @FXML
    private TableColumn<?, ?> lname_tb;
    @FXML
    private TableColumn<?, ?> email_tb;
    @FXML
    private TableColumn<?, ?> pwd_tb;
    @FXML
    private TableColumn<?, ?> address_tb;
    @FXML
    private TableColumn<?, ?> phnum_tb;
    @FXML
    private TableColumn<?, ?> birth_d_tb;
    @FXML
    private TableColumn<?, ?> gender_tb;
    @FXML
    private TableColumn<?, ?> role_tb;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    public void combobox() {
       /* List<String> options = new ArrayList<>();
        options.add("Admin");
        options.add("Artist");
        options.add("Subscriber");
        Rolebox.getItems().addAll(options);*/
    }
    @FXML
    private void add_user_btn(ActionEvent event) {
    }


    @FXML
    private void modify_user_btn(ActionEvent event) {
    }

    @FXML
    private void list_users_btn(ActionEvent event) {
        
        userslist = users_Services.Displayusers();
       
        if (user_table != null && user_table instanceof TableView) {
            // Cast user_table to TableView<users> and set its items
            ((TableView<users>) user_table).setItems(FXCollections.observableArrayList(userslist));
        }
    }

    @FXML
    private void add_dash_btn(ActionEvent event) {
    }

    @FXML
    private void clear_fields_btn(ActionEvent event) {
    }

    @FXML
    private void edit_profile_btn(ActionEvent event) {
    }
    
}
