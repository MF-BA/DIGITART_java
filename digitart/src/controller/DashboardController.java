/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.users_Services;
import entity.users;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utils.Conn;

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
    private TableView<users> user_table;
    @FXML
    private Button edit_profile;
    @FXML
    private TextField search_userfield;
    @FXML
    private Button search_user_btn;
    @FXML
    private ComboBox<String> Rolebox;
    
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
    @FXML
    private AnchorPane adduser_dash_btn;
    @FXML
    private AnchorPane listusers_btn;
    @FXML
    private Label errormsgfname;
    @FXML
    private Label errormsglname;
    @FXML
    private Label errormsgemail;
    @FXML
    private Label errormsgpwd;
    @FXML
    private Label errormsgcin;
    @FXML
    private Label errormsgaddress;
    @FXML
    private Label errormsgphonenum;
    @FXML
    private Label errormsggender;
    @FXML
    private Label errormsgelements;
    @FXML
    private Label errormsgbirthdate;
    @FXML
    private Label errormsgrole;
    
    private users_Services user;
    private users user1;
     static Connection conn = Conn.getCon();
    PreparedStatement pst;
    @FXML
    private AnchorPane default_anchor;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listusers_btn.setVisible(false);
        adduser_dash_btn.setVisible(false);
        default_anchor.setVisible(true);
        
    }    

    
    
    @FXML
    private void add_user_btn(ActionEvent event) {
        listusers_btn.setVisible(false);
        adduser_dash_btn.setVisible(true);
        List<String> options = new ArrayList<>();
        options.add("Admin");
        options.add("Artist");
        options.add("Subscriber");
        Rolebox.getItems().addAll(options);
    }


    @FXML
    private void modify_user_btn(ActionEvent event) {
    }

    @FXML
    private void list_users_btn(ActionEvent event) {
        //listusers_btn.setVisible(true);
        //adduser_dash_btn.setVisible(false);
       /* list_users.setStyle("-fx-background-color: #E5BA73");
         add_user.setStyle("-fx-background-color:transparent");*/
         userslist = users_Services.Displayusers();
       id_tb.setCellValueFactory(new PropertyValueFactory<>("id"));
        cin_tb.setCellValueFactory(new PropertyValueFactory<>("cin"));
        fname_tb.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lname_tb.setCellValueFactory(new PropertyValueFactory<>("lastname"));
       email_tb.setCellValueFactory(new PropertyValueFactory<>("email"));
       pwd_tb.setCellValueFactory(new PropertyValueFactory<>("password"));
       address_tb.setCellValueFactory(new PropertyValueFactory<>("address"));
       phnum_tb.setCellValueFactory(new PropertyValueFactory<>("phone_num"));
       birth_d_tb.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
      gender_tb.setCellValueFactory(new PropertyValueFactory<>("gender"));
       role_tb.setCellValueFactory(new PropertyValueFactory<>("role"));
        if (user_table != null && user_table instanceof TableView) {
            // Cast user_table to TableView<users> and set its items
            ((TableView<users>) user_table).setItems(FXCollections.observableArrayList(userslist));
        }
      
    }

     
 
     @FXML
    private void add_dash_btn(ActionEvent event) {
        
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
        
    }
        
        
    }

    @FXML
    private void clear_fields_btn(ActionEvent event) {
    }

    @FXML
    private void edit_profile_btn(ActionEvent event) {
    }
    
}
