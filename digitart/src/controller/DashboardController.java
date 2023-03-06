/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.users_Services;
import entity.Data;
import entity.users;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType;
import main.main;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.control.CheckBox;
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
    private PasswordField pwd_up;
    @FXML
    private TextField fname_up;
    @FXML
    private TextField lname_up;
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
    private Pane avatar_icon;
    @FXML
    private ImageView avatar_image;
    @FXML
    private Circle circle_image;
    private CheckBox showpwd;
    @FXML
    private CheckBox showpwd_add;
    @FXML
    private CheckBox showpwd_edit;
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
        
        if (Data.user.getImage()!=null){
            String imagePath = Data.user.getImage();
        Image image = new Image(new File(imagePath).toURI().toString());
        circle_image.setFill(new ImagePattern(image));
        }
        
        
        
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
    private void edit_profile_btn(ActionEvent event) throws NoSuchAlgorithmException {
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
        pwd_editprof.setText(user.hashPassword(Data.user.getPwd()));
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
        //String Email = email_up.getText();
        //String passwd = pwd_up.getText();
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
                email_up.getText(),
                pwd_up.getText(),
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
    private void modify_fromlist_btn(ActionEvent event) throws NoSuchAlgorithmException {
       
       
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
         // Upload the image file if one was selected
         // Debug statements
        System.out.println("Image file: " + imageFile);
        if (imageFile != null) {
            String pathimage = uploadImage();
            
            if (pathimage != null) {
               user.modifyuserimage(u,pathimage);
//            String sql = "update users set image= ? where id = ?";
//            try {
//                pst = conn.prepareStatement(sql);
//                pst.setString(1, pathimage);
//                pst.setInt(2, idup);
//
//                pst.executeUpdate();
//                System.out.println("success!!");
//
//            } catch (SQLException ex) {
//                System.err.println("error!!");
//                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } else {
            System.err.println("error: failed to upload image");
        }
        }  
        
        user.modifyuser(u);
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
    File selectedFile = fileChooser.showOpenDialog(circle_image.getScene().getWindow());
    if (selectedFile != null) {
        imageFile = selectedFile;
        Image image = new Image(selectedFile.toURI().toString());
        
        
         // Set the clip of the ImageView to the circle shape
         //avatar_image.setClip(circle_image);
        
        circle_image.setFill(new ImagePattern(image));
    }
        
    }

    private String uploadImage() {
      
        try {
        // Read the image file into a byte array
        byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
        
        // Encode the image bytes as a base64 string
        String imageData = Base64.getEncoder().encodeToString(imageBytes);
        
        // Create a HTTP connection to the server
        //URL url = new URL("http://localhost:8080/upload");
        URL url = new URL("http://localhost:80/images");
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
        String pathlocal = "localhost/images/" + filename;
        // Save the image file to the specified directory
        String uploadDir = "D:/xamp/htdocs/images/";
        Path imagePath = Paths.get(uploadDir, filename);
        Files.write(imagePath, imageBytes);
            System.out.println(imagePath);
        // Close the connection
        System.out.println(filename);
        /*String imageUrl = String.format("http://localhost/images/", filename);
        System.out.println("aziz");
        System.out.println(imageUrl);*/
        //return imageUrl;
        //connection.disconnect();
        //String imageUrl = String.format("http://localhost/images/%s", filename);
        
        // Close the connection
        //connection.disconnect();
        //System.out.println(imageUrl);
        connection.disconnect();
        // Return the URL for the uploaded image
         //return imageUrl;
        // Return the path to the saved image file
        //return imagePath.toString();
        return pathlocal;
    } catch (IOException ex) {
        ex.printStackTrace();
        return null;
    }
    }
private String upload() {

    try {
        // Read the image file and encode it as Base64
        FileInputStream fis = new FileInputStream(imageFile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        fis.close();
        String base64EncodedImage = java.util.Base64.getEncoder().encodeToString(baos.toByteArray());
       

        // Set up the cURL request to send the encoded image data to the server
        String url = "http://localhost/images/upload.php"; // Replace with the URL of your server-side script
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setDoOutput(true);

        // Write the encoded image data to the request body
        String urlParameters = "imageData=" + base64EncodedImage;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        con.setDoOutput(true);
        con.setInstanceFollowRedirects(false);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("charset", "utf-8");
        con.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        con.setUseCaches(false);

        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.write(postData);
        }

        // Get the response from the server
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        
        // Print the response from the server
        System.out.println("Response code: " + responseCode);
        System.out.println("Response message: " + response.toString());
        System.out.println("Response message: " + inputLine);
        return inputLine;
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }

    
    /*try {
        // Read the image file into a byte array
        byte[] imageBytes = Files.readAllBytes(imageFile.toPath());

        // Create a MultipartEntityBuilder to build the HTTP request body
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        // Add the user ID and file name as text fields in the request body
        builder.addTextBody("userId", String.valueOf(Data.user.getId()));
        builder.addTextBody("filename", imageFile.getName());

        // Add the image data as a binary field in the request body
        builder.addBinaryBody("imageData", imageBytes, ContentType.DEFAULT_BINARY, imageFile.getName());

        // Create a HTTP client and a HTTP post request to the server
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost/images");
        httpPost.setEntity(builder.build());

        // Execute the HTTP post request and get the response
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // Check the response code
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new RuntimeException("Failed to upload image: HTTP error code " + statusCode);
        }

        // Save the image file to the specified directory
        String uploadDir = "http://localhost/images";
        Path imagePath = Paths.get(uploadDir, imageFile.getName());
        Files.write(imagePath, imageBytes);

        // Close the response and the HTTP client
        response.close();
        httpClient.close();

        // Return the path to the saved image file
        return imagePath.toString();

    } catch (IOException ex) {
        ex.printStackTrace();
        return null;
    }*/

}

    private void showpwd(ActionEvent event) {
         if (showpwd.isSelected()) {
            pwd_up.setPromptText(pwd_up.getText());
            pwd_up.setText("");
        } else {
            pwd_up.setText(pwd_up.getPromptText());
            pwd_up.setPromptText("");
        }
    }

    @FXML
    private void showpwd_add(ActionEvent event) {
         if (showpwd_add.isSelected()) {
            pwd.setPromptText(pwd.getText());
            pwd.setText("");
        } else {
            pwd.setText(pwd.getPromptText());
            pwd.setPromptText("");
        }
    }

    @FXML
    private void showpwd_edit(ActionEvent event) {
         if (showpwd_edit.isSelected()) {
            pwd_editprof.setPromptText(pwd_editprof.getText());
            pwd_editprof.setText("");
        } else {
            pwd_editprof.setText(pwd_editprof.getPromptText());
            pwd_editprof.setPromptText("");
        }
    }


    
}
