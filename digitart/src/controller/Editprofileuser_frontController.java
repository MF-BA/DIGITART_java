/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.users_Services;
import static controller.DashboardController.conn;
import entity.Data;
import entity.users;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.Conn;

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
    private ComboBox<String> Rolebox_editprof;
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

    
    private users_Services user;
    private users user1;
     static Connection conn = Conn.getCon();
    private int idup;
    private File imageFile;
   
    private String imageUrl=null;
    
    PreparedStatement pst;
    @FXML
    private Button return_btn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Data.email = Data.user.getEmail();
        labelusername_edit.setText(Data.user.getFirstname());
//        if (Data.user.getImage()!=null){
//        Image image = new Image(Data.user.getImage());
//        circle_image_edit.setFill(new ImagePattern(image));
//        }
 try {
            if (Data.user.getImage() != null) {
                Image image = new Image(Data.user.getImage());
                circle_image_edit.setFill(new ImagePattern(image));
            } else {
                circle_image_edit.setFill(null);
            }
        } catch (Exception e) {
            // handle the exception
            System.out.println("An error occurred: " + e.getMessage());
        }

        comboboxedit();
        fname_editprof.setText(Data.user.getFirstname());
        lname_editprof.setText(Data.user.getLastname());
        //email_editprof.setText(Data.user.getEmail());
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
  public void comboboxedit()
{
    List<String> options = new ArrayList<>();
        options.add("Artist");
        options.add("Subscriber");
        Rolebox_editprof.getItems().addAll(options);
}
    @FXML
    private void editprof_btn(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        
        int Cin = 0;
        int phone_number = 0;
        LocalDate today = LocalDate.now();
        LocalDate BirthDate = birth_d_editprof.getValue();
        String firstname = fname_editprof.getText();
        String lastname = lname_editprof.getText();
        //String Email = email_editprof.getText();
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
     
//     if (!Email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
//    errormsgemail_edit.setText("Invalid email format!");
//    }
if (BirthDate.isBefore(today)) {
    errormsgbirthdate_edit.setText("Birth Date cannot be in the future!!");
    
         }
     if (firstname.isEmpty() || lastname.isEmpty() || Address.isEmpty() || phone_num_editprof.getText().isEmpty() || cin_editprof.getText().isEmpty() || BirthDate == null || (!male_gender_editprof.isSelected() && !female_gender_editprof.isSelected())) {
         
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
       if (Cin!=0 && phone_number!=0 ){
           
       if (imageFile!=null){
            imageUrl=imageFile.getName();
       String phpUrl = "http://localhost/images/upload.php";
       //String imageFilePath = "C:\xamppp\htdocs\piImg";

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
//         if (imageUrl!=null)
          
            //String pathimage = upload();
        users u = new users (Data.user.getId(),
             Cin,
             firstname,
                lastname,
                Data.email,
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
         Data.user = user.getgoogleuserdata(u.getEmail());
         Data.user.setImage(imageUrl);
         errormsgfiiledit.setText("your profile is successfully modified!!");  
         errormsggender_edit.setText(""); 
         errormsgfname_edit.setText(""); 
         errormsgcin_edit.setText(""); 
         errormsgaddress_edit.setText(""); 
         errormsgemail_edit.setText(""); 
         errormsgbirthdate_edit.setText(""); 
         errormsgphonenum_edit.setText(""); 
         
         gotohomepage(event);
       }
       else
       {
         users u = new users (Data.user.getId(),
             Cin,
             firstname,
                lastname,
                Data.email,
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
         Data.user = user.getgoogleuserdata(u.getEmail());
         
         errormsgfiiledit.setText("your profile is successfully modified!!");  
         errormsggender_edit.setText(""); 
         errormsgfname_edit.setText(""); 
         errormsgcin_edit.setText(""); 
         errormsgaddress_edit.setText(""); 
         errormsgemail_edit.setText(""); 
         errormsgbirthdate_edit.setText(""); 
         errormsgphonenum_edit.setText("");   
         gotohomepage(event);
       }
        
       }
        }
    }
   
    @FXML
    private void clear_fields_update_btn(ActionEvent event) {
        clearedit();
    }

    @FXML
    private void handleSelectImage(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Image");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    File selectedFile = fileChooser.showOpenDialog(circle_image_edit.getScene().getWindow());
    if (selectedFile != null) {
        imageFile = selectedFile;
        Image image = new Image(selectedFile.toURI().toString());
        
        
         // Set the clip of the ImageView to the circle shape
         //avatar_image.setClip(circle_image);
        
        circle_image_edit.setFill(new ImagePattern(image));
    }
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
                    System.out.println("modified successfully! ");
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
   public void gotohomepage(ActionEvent event){
        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/home_page.fxml"));

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
    private void return_btn_clicked(ActionEvent event) {
       gotohomepage(event);
    
    }
    
}
