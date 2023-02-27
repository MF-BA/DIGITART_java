/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.users_Services;
import entity.Data;
import entity.users;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    private AnchorPane update_page;
    @FXML
    private AnchorPane editprofile_page;
    @FXML
    private Button editprof_btn;
    @FXML
    private PasswordField pwd_editprof;
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
    private TextField phone_num_editprof;
    @FXML
    private RadioButton male_gender_editprof;
    @FXML
    private RadioButton female_gender_editprof;
    @FXML
    private Button clear_fields_editprof;
    @FXML
    private ComboBox<String> Rolebox_editprof;
    @FXML
    private Label errormsgfiiledit;
    @FXML
    private TextField address_editprof;
    @FXML
    private Label errormsglname_edit;
    @FXML
    private Label errormsgfname_edit;
    @FXML
    private Label errormsgemail_edit;
    @FXML
    private Label errormsglname_edit3;
    @FXML
    private Label errormsgpwd_edit;
    @FXML
    private Label errormsggender_edit;
    @FXML
    private Label errormsgcin_edit;
    @FXML
    private Label errormsglname_edit7;
    @FXML
    private Label errormsgbirthdate_edit;
    @FXML
    private Label errormsgphonenum_edit;
    @FXML
    private Label labeladminname21;
    @FXML
    private Label labeladminname22;
    @FXML
    private Label labeladminname23;
    @FXML
    private Label labeladminname24;
    @FXML
    private Label labeladminname25;
    @FXML
    private Button deconnect;
    private Button eye_on;
    private Button eye_off;
    @FXML
    private Button block_fromlist;
    @FXML
    private TableColumn<?, ?> status_tb;
    @FXML
    private Button unblock_fromlist;
    
    private File imageFile;
    @FXML
    private ImageView imageprof;
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
        editprofile_page.setVisible(false);
        labeladminname.setText(Data.user.getFirstname());
        comboboxup(); 
        combobox();
        comboboxedit();
        
        
     search_userfield.textProperty().addListener((observable, oldValue, newValue) -> {
    // Retrieve the search query from the text field
    String searchQuery = newValue.toLowerCase();
    users_Services u = new users_Services();
    // Query the database to retrieve the matching records
    List<users> matchingUsers = u.getUsersMatchingSearchQuery(searchQuery);

    // Clear the table view
    user_table.getItems().clear();

    // Update the table view to display the matching records
    user_table.getItems().addAll(matchingUsers);
    });
      
    }    

    
    
    
    @FXML
    private void add_user_btn(ActionEvent event) {
        default_anchor.setVisible(false);
        listusers_btn.setVisible(false);
        update_page.setVisible(false);
        editprofile_page.setVisible(false);
        adduser_dash_btn.setVisible(true);
        
         add_user.setStyle("-fx-background-color: #470011 ");
        list_users.setStyle("-fx-background-color:  transparent ");
        modify_user.setStyle("-fx-background-color: transparent ");
        edit_profile.setStyle("-fx-background-color: transparent ");
        
    }

public void combobox()
{
    List<String> options = new ArrayList<>();
        options.add("Admin");
        options.add("Artist");
        options.add("Subscriber");
        Rolebox.getItems().addAll(options);
}
public void comboboxedit()
{
    List<String> options = new ArrayList<>();
        options.add("Admin");
        options.add("Artist");
        options.add("Subscriber");
        Rolebox_editprof.getItems().addAll(options);
}
    @FXML
    private void modify_user_btn(ActionEvent event) {
      default_anchor.setVisible(false);
        listusers_btn.setVisible(false);
        adduser_dash_btn.setVisible(false);
        editprofile_page.setVisible(false);
        update_page.setVisible(true);
        
        list_users.setStyle("-fx-background-color:   transparent ");
        edit_profile.setStyle("-fx-background-color: transparent ");
        add_user.setStyle("-fx-background-color:  transparent ");
        modify_user.setStyle("-fx-background-color: #470011 ");  
        
    }

    @FXML
    private void list_users_btn(ActionEvent event) {
        default_anchor.setVisible(false);
        adduser_dash_btn.setVisible(false);
        update_page.setVisible(false);
        editprofile_page.setVisible(false);
        listusers_btn.setVisible(true);
        
       list_users.setStyle("-fx-background-color: #470011 ");
       add_user.setStyle("-fx-background-color: transparent ");
       modify_user.setStyle("-fx-background-color:  transparent ");
       edit_profile.setStyle("-fx-background-color: transparent ");
       
       showusers();
       
      
    }
    public void showusers()
    {
       userslist = users_Services.Displayusers();
     
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
       status_tb.setCellValueFactory(new PropertyValueFactory<>("status"));
       
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
    private void add_dash_btn(ActionEvent event) throws NoSuchAlgorithmException {
        
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
   
    String hashedPassword = users_Services.hashPassword(passwd);
        
    user1 = new users(Cin ,firstname, lastname, Email, hashedPassword, Address, phone_number, BirthDate, gender, role);
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
      edit_profile.setStyle("-fx-background-color: #470011 ");
        add_user.setStyle("-fx-background-color: transparent ");
        list_users.setStyle("-fx-background-color:  transparent ");
        modify_user.setStyle("-fx-background-color: transparent ");
        
        default_anchor.setVisible(false);
        listusers_btn.setVisible(false);
        adduser_dash_btn.setVisible(false);
        update_page.setVisible(false);  
        editprofile_page.setVisible(true);
        
        fname_editprof.setText(Data.user.getFirstname());
        lname_editprof.setText(Data.user.getLastname());
        email_editprof.setText(Data.user.getEmail());
        pwd_editprof.setText(Data.user.getPwd());
        cin_editprof.setText(Integer.toString(Data.user.getCin()));
        address_editprof.setText(Data.user.getAddress());
        birth_d_editprof.setValue(Data.user.getBirth_date());
        phone_num_editprof.setText(Integer.toString(Data.user.getPhone_number()));
        ((ComboBox<String>) Rolebox_editprof).setValue(Data.user.getRole());
        
        if (Data.user.getGender().equals("Male"))
        {
         male_gender_editprof.setSelected(true); 
        }
        else if (Data.user.getGender().equals("Female"))
        {
              female_gender_editprof.setSelected(true);
        }
      
    }

    @FXML
    private void update_btn_dash(ActionEvent event) {
        
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
        clearupd();
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
       edit_profile.setStyle("-fx-background-color: transparent ");
       
        default_anchor.setVisible(false);
        listusers_btn.setVisible(false);
        adduser_dash_btn.setVisible(false);
        editprofile_page.setVisible(false);
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

    /*private void delete_fromlist_btn(ActionEvent event) {
        
        /*users selectedUser = user_table.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
        // Remove the selected item from the table's data list
        
        users_Services.deleteuser(selectedUser.getId());
        user_table.getItems().remove(selectedUser);
        
    }
   
        
    }*/

    @FXML
    private void editprof_btn(ActionEvent event) {
        
       int Cin = Integer.parseInt(cin_editprof.getText().trim());
        int phone_number = Integer.parseInt(phone_num_editprof.getText().trim());
        LocalDate BirthDate = birth_d_editprof.getValue();
        String firstname = fname_editprof.getText();
        String lastname = lname_editprof.getText();
        String Email = email_editprof.getText();
        String passwd = pwd_editprof.getText();
        String Address = address_editprof.getText();
        String gender = null;
        if (male_gender_editprof.isSelected()) {
        gender = male_gender_editprof.getText();  
    } else if (female_gender_editprof.isSelected()) {
        gender = female_gender_editprof.getText();
    }
        String role = (String) Rolebox_editprof.getSelectionModel().getSelectedItem();
        
        
        if (!phone_num_editprof.getText().isEmpty())
    {               
    if (!phone_num_editprof.getText().matches("\\d+")) {
        errormsgphonenum_edit.setText("Phone number should be a number!");
    } else if (phone_num_editprof.getText().toString().length()<8)
    {
        errormsgphonenum_edit.setText("Phone number should contain 8 digits!");
    }
    else {
        phone_number = Integer.parseInt(phone_num_editprof.getText().trim());
    }
            }
       if (!cin_editprof.getText().isEmpty())
    {
     if (!cin_editprof.getText().matches("\\d+")) {
        errormsgcin_edit.setText("CIN should be a number!");
    } else if (cin_editprof.getText().toString().length()<8)
    {
        errormsgcin_edit.setText("CIN should contain 8 digits!");
    }
    else
    {
        Cin = Integer.parseInt(cin_editprof.getText().trim());  
    }   
    }
     if (!male_gender_editprof.isSelected() && !female_gender_editprof.isSelected()) {
          
        errormsggender_edit.setText("Please specify your gender!"); 
        }  
     
     if (!Email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
    errormsgemail_edit.setText("Invalid email format!");
    }
        if (firstname.isEmpty() || lastname.isEmpty() || Email.isEmpty() || passwd.isEmpty() || Address.isEmpty() || phone_num_editprof.getText().isEmpty() || cin_editprof.getText().isEmpty() || BirthDate == null || (!male_gender_editprof.isSelected() && !female_gender_editprof.isSelected())) {
         
       errormsgfiiledit.setText("Please fill all elements!!");  
       
        }
        else
        {
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
         // Upload the image file if one was selected
        if (imageFile != null) {
            uploadImage();
        }  
         showusers();
         errormsgfiiledit.setText("your profile is successfully modified!!");  
        }
    }

    @FXML
    private void deconnect_btn(ActionEvent event) {
        
        deconnect.setStyle("-fx-background-color: #470011 ");
        edit_profile.setStyle("-fx-background-color: transparent ");
        add_user.setStyle("-fx-background-color: transparent ");
        list_users.setStyle("-fx-background-color:  transparent ");
        modify_user.setStyle("-fx-background-color: transparent ");
        
        
        default_anchor.setVisible(false);
        listusers_btn.setVisible(false);
        adduser_dash_btn.setVisible(false);
        update_page.setVisible(false);  
        editprofile_page.setVisible(false);
        
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

    private void eye_on_btn(ActionEvent event) {
      
       eye_off.setVisible(false); 
        eye_on.setVisible(true); 
        
        
    }

    @FXML
    private void block_fromlist_btn(ActionEvent event) {
        
        users selectedUser = user_table.getSelectionModel().getSelectedItem();
         if (selectedUser != null) {
        // Remove the selected item from the table's data list
        
        
        users_Services.blockuser(selectedUser.getId());
        
        
    }
        
        
        
        showusers();
    }

    @FXML
    private void unblock_fromlist_btn(ActionEvent event) {
        
         users selectedUser = user_table.getSelectionModel().getSelectedItem();
         if (selectedUser != null) {
        // Remove the selected item from the table's data list
        
        
        users_Services.unblockuser(selectedUser.getId());
        
        
    }
        
        
        
        showusers(); 
        
    }

    @FXML
    private void handleSelectImage(ActionEvent event) {
     FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(imageprof.getScene().getWindow());
        if (selectedFile != null) {
            imageFile = selectedFile;
            Image image = new Image(selectedFile.toURI().toString());
            imageprof.setImage(image);
        }
        
    }

    private void uploadImage() {
        try {
            // Read the image file into a byte array
            byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
            
            // Encode the image bytes as a base64 string
            String imageData = Base64.getEncoder().encodeToString(imageBytes);
            
            // Create a HTTP connection to the servlet
            URL url = new URL("http://localhost:8080/uploadImage");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            // Construct the request body
            String userId = String.valueOf(Data.user.getId());
            String filename = imageFile.getName();
            String body = String.format("userId=%s&filename=%s&imageData=%s",
                                        userId, filename, imageData);
            
            // Write the request body to the connection output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            
            // Check the response code
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Failed to upload image: HTTP error code " + responseCode);
            }
            
            // Close the connection
            connection.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
}
