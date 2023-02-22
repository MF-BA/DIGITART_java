/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.users_Services;
import entity.users;
import java.awt.Color;
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
    private int idup;
    
    PreparedStatement pst;
    @FXML
    private AnchorPane default_anchor;
    @FXML
    private Button update_btn;
    @FXML
    private PasswordField pwd_up;
    @FXML
    private TextField fname_up;
    @FXML
    private TextField lname_up;
    @FXML
    private TextField email_up;
    @FXML
    private DatePicker birth_d_up;
    @FXML
    private TextField cin_up;
    @FXML
    private TextField address_up;
    @FXML
    private TextField phone_num_up;
    @FXML
    private RadioButton male_gender_up;
    @FXML
    private RadioButton female_gender_up;
    @FXML
    private Button clear_fields_update;
    @FXML
    private ComboBox<String> Rolebox_up;
    @FXML
    private Button modify_fromlist;
    @FXML
    private Button delete_fromlist;
    @FXML
    private AnchorPane update_page;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        default_anchor.setVisible(true);
        listusers_btn.setVisible(false);
        adduser_dash_btn.setVisible(false);
        update_page.setVisible(false);
        
        comboboxup(); 
        combobox();
    }    

    
    
    @FXML
    private void add_user_btn(ActionEvent event) {
        default_anchor.setVisible(false);
        listusers_btn.setVisible(false);
        update_page.setVisible(false);
        adduser_dash_btn.setVisible(true);
         add_user.setStyle("-fx-background-color: #470011 ");
        list_users.setStyle("-fx-background-color:  transparent ");
        modify_user.setStyle("-fx-background-color: transparent ");
        
        
    }

public void combobox()
{
    List<String> options = new ArrayList<>();
        options.add("Admin");
        options.add("Artist");
        options.add("Subscriber");
        Rolebox.getItems().addAll(options);
}
    @FXML
    private void modify_user_btn(ActionEvent event) {
      default_anchor.setVisible(false);
        listusers_btn.setVisible(false);
        adduser_dash_btn.setVisible(false);
        update_page.setVisible(true);
        list_users.setStyle("-fx-background-color:   transparent ");
        add_user.setStyle("-fx-background-color:  transparent ");
        modify_user.setStyle("-fx-background-color: #470011 ");  
        
    }

    @FXML
    private void list_users_btn(ActionEvent event) {
        default_anchor.setVisible(false);
        adduser_dash_btn.setVisible(false);
        update_page.setVisible(false);
        listusers_btn.setVisible(true);
        
       list_users.setStyle("-fx-background-color: #470011 ");
       add_user.setStyle("-fx-background-color: transparent ");
       modify_user.setStyle("-fx-background-color:  transparent ");
       showusers();
       
      
    }
    public void showusers()
    {
       userslist = users_Services.Displayusers();
       id_tb.setCellValueFactory(new PropertyValueFactory<>("id"));
        cin_tb.setCellValueFactory(new PropertyValueFactory<>("cin"));
        fname_tb.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lname_tb.setCellValueFactory(new PropertyValueFactory<>("lastname"));
       email_tb.setCellValueFactory(new PropertyValueFactory<>("email"));
       pwd_tb.setCellValueFactory(new PropertyValueFactory<>("pwd"));
       address_tb.setCellValueFactory(new PropertyValueFactory<>("address"));
       phnum_tb.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
       birth_d_tb.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
      gender_tb.setCellValueFactory(new PropertyValueFactory<>("gender"));
       role_tb.setCellValueFactory(new PropertyValueFactory<>("role"));
        if (user_table != null && user_table instanceof TableView) {
            // Cast user_table to TableView<users> and set its items
            ((TableView<users>) user_table).setItems(FXCollections.observableArrayList(userslist));
        }   
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
        Rolebox.getSelectionModel().clearSelection();  
    }
    public void clearupd(){
      fname_up.setText("");
        lname_up.setText("");
        email_up.setText("");
        pwd_up.setText("");
        address_up.setText("");
        cin_up.setText("");
        phone_num_up.setText("");
        female_gender_up.setSelected(false);
        male_gender_up.setSelected(false);
        Rolebox_up.getSelectionModel().clearSelection(); 
        birth_d_up.setValue(null);  
    }
 
     @FXML
    private void add_dash_btn(ActionEvent event) {
        
        LocalDate BirthDate = birth_d.getValue();
        String firstname = fname.getText();
        String lastname = lname.getText();
        String Email = email.getText();
        String passwd = pwd.getText();
        String Address = address.getText();
        String gender=null;
        String role = (String) Rolebox.getSelectionModel().getSelectedItem();
        
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
   errormsgrole.setText("");
       
    if (!phone_num.getText().isEmpty())
    {               
    if (!phone_num.getText().matches("\\d+")) {
        errormsgphonenum.setText("Phone number should be a number!");
    } else {
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
     
     if (!Email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
    errormsgemail.setText("Invalid email format!");
    }
     
   if (firstname.isEmpty() || lastname.isEmpty() || Email.isEmpty() || passwd.isEmpty() || Address.isEmpty() || phone_num.getText().isEmpty() || cin.getText().isEmpty() || BirthDate == null || (!male_gender.isSelected() && !female_gender.isSelected())) {
         
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
   
    user1 = new users(Cin ,firstname, lastname, Email, passwd, Address, phone_number, BirthDate, gender, role);
    user = new users_Services();
    user.adminadduser(user1); 
     
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
   errormsgrole.setText("");
   clear();
        
    }
        
        
    }

    @FXML
    private void clear_fields_btn(ActionEvent event) {
        clear();
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
   errormsgrole.setText("");
    }

    @FXML
    private void edit_profile_btn(ActionEvent event) {
        
    }

    @FXML
    private void update_btn_dash(ActionEvent event) {
        
        //int id = ;
        int Cin = Integer.parseInt(cin_up.getText().trim());
        int phone_number = Integer.parseInt(phone_num_up.getText().trim());
        LocalDate BirthDate = birth_d_up.getValue();
        String firstname = fname_up.getText();
        String lastname = lname_up.getText();
        String Email = email_up.getText();
        String passwd = pwd_up.getText();
        String Address = address_up.getText();
        String gender = null;
        if (male_gender_up.isSelected()) {
        gender = male_gender_up.getText();  
    } else if (female_gender_up.isSelected()) {
        gender = female_gender_up.getText();
    }
        String role = (String) Rolebox_up.getSelectionModel().getSelectedItem();
        users u = new users (idup,
             Cin,
             firstname,
                lastname,
                Email,
                passwd,
                Address,
             phone_number,   
             BirthDate,
                gender,
                role
        );
        users_Services user = new users_Services();
        user.modifyuser(u);
    }

    public int getidupdate(int id){
      idup = id;  
      return idup;
    }
    public void showmodif()
    {
        modify_user.setStyle("-fx-background-color: #470011 ");
       list_users.setStyle("-fx-background-color: transparent ");
       add_user.setStyle("-fx-background-color: transparent ");
       
        default_anchor.setVisible(false);
        listusers_btn.setVisible(false);
        adduser_dash_btn.setVisible(false);
        update_page.setVisible(true);
    }
    @FXML
    private void clear_fields_update_btn(ActionEvent event) {
        clearupd();
        
    }
 public void comboboxup()
 {
     List<String> options = new ArrayList<>();
        options.add("Admin");
        options.add("Artist");
        options.add("Subscriber");
        Rolebox_up.getItems().addAll(options);
 }
    @FXML
    private void modify_fromlist_btn(ActionEvent event) {
       
       
      users newSelection = user_table.getSelectionModel().getSelectedItem();
    if (newSelection != null) {
        // Set up the update page with text fields filled with the data of the selected user
        
        getidupdate(newSelection.getId());
        fname_up.setText(newSelection.getFirstname());
        lname_up.setText(newSelection.getLastname());
        email_up.setText(newSelection.getEmail());
        pwd_up.setText(newSelection.getPwd());
        address_up.setText(newSelection.getAddress());
        if (newSelection.getGender().equals("Male")) {
            male_gender_up.setSelected(true);
        } else {
            female_gender_up.setSelected(true);
        }
        
        ((ComboBox<String>) Rolebox_up).setValue(newSelection.getRole());
        
        cin_up.setText(Integer.toString(newSelection.getCin()));
        phone_num_up.setText(Integer.toString(newSelection.getPhone_number()));
        birth_d_up.setValue(newSelection.getBirth_date());
        

      
    }
    
      showmodif(); 
        
    }

    @FXML
    private void delete_fromlist_btn(ActionEvent event) {
        
        users selectedUser = user_table.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
        // Remove the selected item from the table's data list
        
        users_Services.deleteuser(selectedUser.getId());
        user_table.getItems().remove(selectedUser);
        
    }
   
        showusers();
    }
    
}
