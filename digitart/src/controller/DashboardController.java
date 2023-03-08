/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.users_Services;
import static controller.Signin_pageController.conn;
import entity.Data;
import entity.users;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javax.mail.MessagingException;
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
    private Label errormsggender_edit;
    @FXML
    private Label errormsgcin_edit;
    @FXML
    private Label errormsgbirthdate_edit;
    @FXML
    private Label errormsgphonenum_edit;
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
   
    private String imageUrl;
    
    @FXML
    private Pane avatar_icon;
    @FXML
    private ImageView avatar_image;
    @FXML
    private Circle circle_image;
    private CheckBox showpwd;
    @FXML
    private CheckBox showpwd_add;
    private CheckBox showpwd_edit;
    @FXML
    private AnchorPane userstats_dash;
    @FXML
    private PieChart chartgender;
    private HBox boxchart;
    @FXML
    private Button showstats_btn;
    @FXML
    private Button returnfromstats;
    @FXML
    private HBox chartbox;
    @FXML
    private Label errormsgfname_modif;
    @FXML
    private Label errormsglname_modif;
    @FXML
    private Label errormsgcin_modif;
    @FXML
    private Label errormsgaddress_modif;
    @FXML
    private Label errormsgphonenum_modif;
    @FXML
    private Label errormsggender_modif;
    @FXML
    private Label errormsgbirthdate_modif;
    @FXML
    private Label errormsgrole_modif;
    @FXML
    private Label msgsuccess_modif;
    @FXML
    private Button newpwd_btn;
    @FXML
    private Label errormsgaddress_edit;
    @FXML
    private Button return_dash_btn;
    @FXML
    private Button deconnect1;
    
    
    private Stage stage;
    private Scene scene;
    private Parent pt;
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
        userstats_dash.setVisible(false);
        labeladminname.setText(Data.user.getFirstname());
        
        /*if (Data.user.getImage()!=null){
            String imagePath = Data.user.getImage();
        Image image = new Image(new File(imagePath).toURI().toString());
        circle_image.setFill(new ImagePattern(image));
        }*/
        if (Data.user.getImage()!=null){
        Image image = new Image(Data.user.getImage());
        circle_image.setFill(new ImagePattern(image));
        }
        
        /*URL url = new URL(Data.user.getImage());
        Image image1 = new Image(url.openStream(), 245, 237, false, true);
        image.setImage(image1);*/
        
        
        
        comboboxup(); 
        combobox();
        comboboxedit();
        userstats();
        
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
        userstats_dash.setVisible(false);
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
        userstats_dash.setVisible(false);
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
        userstats_dash.setVisible(false);
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
   if (!phone_num.getText().isEmpty())
    {               
    if (!phone_num.getText().matches("\\d+")) {
        errormsgphonenum.setText("Phone number should be a number!");
    } else if (phone_num.getText().toString().length()<8 || phone_num.getText().toString().length()>8)
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
    } else if (cin.getText().toString().length()<8 || cin.getText().toString().length()>8)
    {
        errormsgcin.setText("CIN should contain 8 digits!");
    }
    else
    {
        Cin = Integer.parseInt(cin.getText().trim());  
    }   
    }
       if(Cin != 0 && phone_number != 0){
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
        userstats_dash.setVisible(false);
        editprofile_page.setVisible(true);
        
       fname_editprof.setText(Data.user.getFirstname());
        lname_editprof.setText(Data.user.getLastname());
        email_editprof.setText(Data.user.getEmail());
        //pwd_editprof.setText(user.hashPassword(Data.user.getPwd()));
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
        
        int Cin = 0;
        int phone_number = 0;
        LocalDate BirthDate = birth_d_up.getValue();
        String firstname = fname_up.getText();
        String lastname = lname_up.getText();
        String Address = address_up.getText();
        String gender = null;
     errormsgfname_modif.setText("");
     errormsglname_modif.setText("");
     errormsgcin_modif.setText("");
     errormsgaddress_modif.setText("");
     errormsgphonenum_modif.setText("");
     errormsggender_modif.setText("");
     errormsgbirthdate_modif.setText("");
   
        
        
     if (!male_gender_up.isSelected() && !female_gender_up.isSelected()) {
          
        errormsggender_modif.setText("Please specify your gender!"); 
        }  
 
if (firstname.isEmpty() || lastname.isEmpty() || Address.isEmpty() || phone_num_up.getText().isEmpty() || cin_up.getText().isEmpty() || BirthDate == null || (!male_gender_up.isSelected() && !female_gender_up.isSelected())) {
         
  
    if (Address.isEmpty())  {
     
     errormsgaddress_modif.setText("fill Address !!");   
    }
    
    if ( firstname.isEmpty())
    {
     errormsgfname_modif.setText("Fill first name !!");   
    }
    if ( lastname.isEmpty())
    {
       errormsglname_modif.setText("Fill last name !!");
    }
    if (phone_num_up.getText().isEmpty())
    {
     errormsgphonenum_modif.setText("Fill phone number !!");  
    }
    if (BirthDate == null)
    {
        errormsgbirthdate_modif.setText("Fill birth date !!"); 
    }
    if ( cin_up.getText().isEmpty() )
    {
     errormsgcin_modif.setText("Fill Cin !!");   
    }
    } 

   else if (male_gender_up.isSelected() && female_gender_up.isSelected())
    {
     errormsggender_modif.setText("Please specify your gender!");    
    }
    else {
    if (male_gender_up.isSelected()) {
        gender = male_gender_up.getText();  
    } 
    if (female_gender_up.isSelected()) {
        gender = female_gender_up.getText();
    }
    if (!phone_num_up.getText().isEmpty())
    {               
    if (!phone_num_up.getText().matches("\\d+")) {
        errormsgphonenum_modif.setText("Phone number should be a number!");
    } else if (phone_num_up.getText().toString().length()<8 || phone_num_up.getText().toString().length()>8)
    {
        errormsgphonenum_modif.setText("Phone number should contain 8 digits!");
    }
    else {
        phone_number = Integer.parseInt(phone_num_up.getText().trim());
    }
            }
       if (!cin_up.getText().isEmpty())
    {
     if (!cin_up.getText().matches("\\d+")) {
        errormsgcin_modif.setText("CIN should be a number!");
    } else if (cin_up.getText().toString().length()<8 || cin_up.getText().toString().length()>8)
    {
        errormsgcin_modif.setText("CIN should contain 8 digits!");
    }
    else
    {
        Cin = Integer.parseInt(cin_up.getText().trim());  
    }   
    }
       if(Cin != 0 && phone_number != 0){
      users newSelection = user_table.getSelectionModel().getSelectedItem();
      if (newSelection != null) {
        String role = (String) Rolebox_up.getSelectionModel().getSelectedItem();
        users u = new users (idup,
             Cin,
             firstname,
                lastname,
                newSelection.getEmail(),
                newSelection.getPwd(),
                Address,
             phone_number,   
             BirthDate,
                gender,
                role,
                newSelection.getStatus(),
                newSelection.getImage(),
                newSelection.getSecretcode()
        );
        users_Services user = new users_Services();
        user.modifyuser(u);
      }
        errormsgfname_modif.setText("");
    errormsglname_modif.setText("");
    errormsgcin_modif.setText("");
    errormsgaddress_modif.setText("");
     errormsgphonenum_modif.setText("");
   errormsggender_modif.setText("");
   errormsgbirthdate_modif.setText("");
        //clearupd();
        msgsuccess_modif.setText("User modified successfully !!");
       }
   }
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
        userstats_dash.setVisible(false);
        update_page.setVisible(true);
    }
    @FXML
    private void clear_fields_update_btn(ActionEvent event) {
       clearedit();
        
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
        //email_up.setText(newSelection.getEmail());
        //pwd_up.setText(newSelection.getPwd());
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
    private void editprof_btn(ActionEvent event) throws IOException {
        
       int Cin = 0;
        int phone_number = 0;
        LocalDate BirthDate = birth_d_editprof.getValue();
        String firstname = fname_editprof.getText();
        String lastname = lname_editprof.getText();
        String Email = email_editprof.getText();
        //String passwd = pwd_editprof.getText();
        String Address = address_editprof.getText();
        String gender = null;
        if (male_gender_editprof.isSelected()) {
        gender = male_gender_editprof.getText();  
    } else if (female_gender_editprof.isSelected()) {
        gender = female_gender_editprof.getText();
    }
        String role = (String) Rolebox_editprof.getSelectionModel().getSelectedItem();
         errormsggender_edit.setText(""); 
         errormsgfname_edit.setText(""); 
         errormsgcin_edit.setText(""); 
         errormsgaddress_edit.setText(""); 
         errormsgemail_edit.setText(""); 
         errormsgbirthdate_edit.setText(""); 
         errormsgphonenum_edit.setText(""); 
        
        
     if (!male_gender_editprof.isSelected() && !female_gender_editprof.isSelected()) {
          
        errormsggender_edit.setText("Please specify your gender!"); 
        }  
     
     if (!Email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
    errormsgemail_edit.setText("Invalid email format!");
    }
     if (firstname.isEmpty() || lastname.isEmpty() || Email.isEmpty() || Address.isEmpty() || phone_num_editprof.getText().isEmpty() || cin_editprof.getText().isEmpty() || BirthDate == null || (!male_gender_editprof.isSelected() && !female_gender_editprof.isSelected())) {
         
       errormsgfiiledit.setText("Please fill all elements!!");  
       
        }
        else
        {
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
       if (Cin!=0 && phone_number!=0 && Email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
           
       if (imageFile!=null){
       imageUrl="http://localhost/images/"+imageFile.getName();
       String phpUrl = "http://localhost/images/upload.php";
//        String imageFilePath = "C:\xamppp\htdocs\piImg";

        // Read the image file data
        byte[] imageData = Files.readAllBytes(imageFile.toPath());

        // Create the boundary string for the multipart request
        String boundary = "---------------------------12345";

        // Open the connection to the PHP script
        URL url = new URL(phpUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        // Write the image file data to the output stream of the connection
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(("--" + boundary + "\r\n").getBytes());
        outputStream.write(("Content-Disposition: form-data; name=\"file\"; filename=\"" + imageFile.getName() + "\"\r\n").getBytes());
        outputStream.write(("Content-Type: image/jpeg\r\n\r\n").getBytes());
        outputStream.write(imageData);
        outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
        outputStream.flush();
        outputStream.close();

        // Read the response from the PHP script
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        users u = new users (Data.user.getId(),
             Cin,
             firstname,
                lastname,
                Email,
                Data.user.getPwd(),
                Address,
             phone_number,   
             BirthDate,
                gender,
                role,
                Data.user.getStatus(),
                imageUrl,
                Data.user.getSecretcode()
        );
        
        users_Services user = new users_Services();
         // Upload the image file if one was selected
         // Debug statements
        System.out.println("Image url: " + imageUrl);
     
        user.modifyuser(u);
         showusers();
         errormsgfiiledit.setText("your profile is successfully modified!!");  
         errormsggender_edit.setText(""); 
         errormsgfname_edit.setText(""); 
         errormsgcin_edit.setText(""); 
         errormsgaddress_edit.setText(""); 
         errormsgemail_edit.setText(""); 
         errormsgbirthdate_edit.setText(""); 
         errormsgphonenum_edit.setText(""); 
       }
       else
       {
        users u = new users (Data.user.getId(),
             Cin,
             firstname,
                lastname,
                Email,
                Data.user.getPwd(),
                Address,
             phone_number,   
             BirthDate,
                gender,
                role,
                Data.user.getStatus(),
                Data.user.getImage(),
                Data.user.getSecretcode()
        );
        
        users_Services user = new users_Services();
         // Upload the image file if one was selected
         // Debug statements
        System.out.println("Image url: " + imageUrl);
     
        user.modifyuser(u);
         showusers();
         errormsgfiiledit.setText("your profile is successfully modified!!");  
         errormsggender_edit.setText(""); 
         errormsgfname_edit.setText(""); 
         errormsgcin_edit.setText(""); 
         errormsgaddress_edit.setText(""); 
         errormsgemail_edit.setText(""); 
         errormsgbirthdate_edit.setText(""); 
         errormsgphonenum_edit.setText("");    
       }
            //String pathimage = upload();
        
         
       }
        }
    }
    public void clearedit(){
        
        fname_editprof.setText("");
        lname_editprof.setText("");
        email_editprof.setText("");
       female_gender_editprof.setSelected(false);
       male_gender_editprof.setSelected(false);
       Rolebox_editprof.getSelectionModel().clearSelection(); 
       address_editprof.setText("");
       cin_editprof.setText("");
       birth_d_editprof.setValue(null); 
       phone_num_editprof.setText("");
    }

    @FXML
    private void deconnect_btn(ActionEvent event) throws SQLException {
        
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
        userstats_dash.setVisible(false);
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
       // conn.close();
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

    
public String upload() throws IOException {

       imageUrl="http://localhost/images/%22+selectedFile.getName()";
       String phpUrl = "http://localhost/images/upload.php";
//        String imageFilePath = "C:\xamppp\htdocs\piImg";

        // Read the image file data
        byte[] imageData = Files.readAllBytes(imageFile.toPath());

        // Create the boundary string for the multipart request
        String boundary = "---------------------------12345";

        // Open the connection to the PHP script
        URL url = new URL(phpUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        // Write the image file data to the output stream of the connection
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(("--" + boundary + "\r\n").getBytes());
        outputStream.write(("Content-Disposition: form-data; name=\"file\"; filename=\"" + imageFile.getName() + "\"\r\n").getBytes());
        outputStream.write(("Content-Type: image/jpeg\r\n\r\n").getBytes());
        outputStream.write(imageData);
        outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
        outputStream.flush();
        outputStream.close();

        // Read the response from the PHP script
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    return imageUrl;
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

    private void showpwd_edit(ActionEvent event) {
         if (showpwd_edit.isSelected()) {
            pwd_editprof.setPromptText(pwd_editprof.getText());
            pwd_editprof.setText("");
        } else {
            pwd_editprof.setText(pwd_editprof.getPromptText());
            pwd_editprof.setPromptText("");
        }
    }

 public void userstats() {
        
        try {
     String sql = "Select gender from users";
      pst = conn.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
     
     
     List<String> genders = new ArrayList<>();
while (rs.next()) {
    genders.add(rs.getString("gender"));
}
   int maleCount = 0;
int femaleCount = 0;
for (String gender : genders) {
    if (gender.equals("Male")) {
        maleCount++;
    } else if (gender.equals("Female")) {
        femaleCount++;
    }
}
     ObservableList<PieChart.Data> pieChartData =
    FXCollections.observableArrayList(
        new PieChart.Data("Male", maleCount),
        new PieChart.Data("Female", femaleCount)
    );
     chartgender = new PieChart(pieChartData);
chartgender.setTitle("User Gender Statistics");
     // Add percentage labels to chart
DecimalFormat decimalFormat = new DecimalFormat("#.##");
for (PieChart.Data data : chartgender.getData()) {
    double percentage = (data.getPieValue() / (maleCount + femaleCount)) * 100;
    String percentageString = decimalFormat.format(percentage) + "%";
    data.nameProperty().setValue(data.getName() + " (" + percentageString + ")");
}


//panechart.getChildren().add(chartgender);
//
//chartgender.setLegendVisible(true);
//chartgender.setLabelsVisible(true);
// Wrap the chart in an HBox container
HBox chartContainer = new HBox(chartgender);
chartContainer.setAlignment(Pos.CENTER);

// Set the size of the chart container
chartContainer.setPrefSize(1200, 1200);
// Set the layout properties of the chart to center it within its container
//chartgender.setLayoutX((chartContainer.getWidth() - chartgender.getPrefWidth()) / 2);
//chartgender.setLayoutY((chartContainer.getHeight() - chartgender.getPrefHeight()) / 2);
// Add the chart container to the user interface
chartbox.getChildren().add(chartContainer);
chartgender.setLegendVisible(true);
chartgender.setLabelsVisible(true);

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
  
     
     
 }

    @FXML
    private void showstats_btn(ActionEvent event) {
        
        default_anchor.setVisible(true);
        listusers_btn.setVisible(false);
        adduser_dash_btn.setVisible(false);
        update_page.setVisible(false);
        editprofile_page.setVisible(false);
        userstats_dash.setVisible(true);
        deconnect.setStyle("-fx-background-color: transparent ");
        edit_profile.setStyle("-fx-background-color: transparent ");
        add_user.setStyle("-fx-background-color: transparent ");
        list_users.setStyle("-fx-background-color:  #470011 ");
        modify_user.setStyle("-fx-background-color: transparent ");
    }

    @FXML
    private void returnfromstats_btn(ActionEvent event) {
        
        default_anchor.setVisible(false);
        listusers_btn.setVisible(true);
        adduser_dash_btn.setVisible(false);
        update_page.setVisible(false);
        editprofile_page.setVisible(false);
        userstats_dash.setVisible(false);
        deconnect.setStyle("-fx-background-color: transparent ");
        edit_profile.setStyle("-fx-background-color: transparent ");
        add_user.setStyle("-fx-background-color: transparent ");
        list_users.setStyle("-fx-background-color:  #470011 ");
        modify_user.setStyle("-fx-background-color: transparent ");
    }

    @FXML
    private void newpwd_btn(ActionEvent event) throws NoSuchAlgorithmException {
        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New password");
        dialog.setHeaderText("Enter your old password");
        dialog.setContentText("pwd :");
        Optional<String> result = dialog.showAndWait();
        
        String oldpwd = users_Services.hashPassword(result.get());
        try {
            String sql = "Select * from users where id = ? and password = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Data.user.getId());
            pst.setString(2, oldpwd);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if(rs.getString("password").equals(oldpwd)){
                TextInputDialog dialog1 = new TextInputDialog();
                dialog1.setTitle("New password");
                dialog1.setHeaderText("Enter your new password");
                dialog1.setContentText("pwd :");
                Optional<String> result1 = dialog1.showAndWait();
                
                String newpwd = result1.get();
                users u = new users (Data.user.getId(),
                Data.user.getCin(),
                Data.user.getFirstname(),
                Data.user.getLastname(),
                Data.user.getEmail(),
                users_Services.hashPassword(newpwd),
                Data.user.getAddress(),
                Data.user.getPhone_number(),   
                Data.user.getBirth_date(),
                Data.user.getGender(),
                Data.user.getRole(),
                Data.user.getStatus(),
                Data.user.getImage(),
                Data.user.getSecretcode()
        );
        
        users_Services user = new users_Services();
        user.modifyuser(u);
                /*try {
            String sql1 = "update users set password = ? where id = ?";
            pst = conn.prepareStatement(sql1);
            pst.setString(1, users_Services.hashPassword(newpwd));
            pst.setInt(2, Data.user.getId());
            pst.executeUpdate();
            
            System.out.println("success!!");   
            
                }catch (SQLException ex) {
            //Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("error!!");
            ex.printStackTrace();
             }*/
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Wrong password");
                        alert.setHeaderText(null);
                        alert.setContentText("the password you entered is incorrect!! ");
                        alert.showAndWait();
            }
            }
         catch (SQLException ex) {
            //Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
        
        
        
        
        
        
    }

    @FXML
    private void return_dash_btn(ActionEvent event) {
        try {
             pt=FXMLLoader
                    .load(getClass().getResource("/view/Dashboard_homepage.fxml"));
            
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
    
}
