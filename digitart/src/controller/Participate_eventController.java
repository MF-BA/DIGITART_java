/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Artwork_Services;
import Services.Event_Services;
import Services.ServiceTicket;
import entity.Data;
import entity.Event;
import entity.Participants;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import utils.Conn;
import static utils.Conn.conn;

/**
 * FXML Controller class
 *
 * @author kossay
 */
public class Participate_eventController implements Initializable {

 
    private Button deconnect;
    @FXML
    private AnchorPane default_anchor;
    @FXML
    private AnchorPane anc_scroll;
    @FXML
    private ScrollPane event_scroll;
    @FXML
    private GridPane event_container;
    ArrayList<Event> event_array;
    ArrayList<Event> event_array1;
    HBox cardBox;
    HBox cardBox1;
    @FXML
    private HBox event_view;
    private GridPane event_container2;
    @FXML
    private Label event_name_show;
    @FXML
    private Button btndelete;
    @FXML
    private Button disconnect;
    @FXML
    private Button editprof_btn;
    @FXML
    private Button home_btn;
    @FXML
    private Button artwork_btn;
    @FXML
    private Button auction_btn;
    @FXML
    private Button events_btn;
    @FXML
    private Circle circle_image;
    @FXML
    private ImageView avatar_image;
    @FXML
    private Label labelusername;
    @FXML
    private Button tickets_btn;
    @FXML
    private AnchorPane home;
    @FXML
    private AnchorPane homepage_anchorpane;

    /**
     * Initializes the controller class.
     */
    public void labelupdate()
    {
        String name_event = getname();
        if (name_event!=null){
            event_name_show.setText(getname());
        }
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelusername.setText(Data.user.getFirstname());
        if (Data.user.getImage()!=null){
        Image image = new Image(Data.user.getImage());
        circle_image.setFill(new ImagePattern(image));
        }
        else
        {
            circle_image.setFill(null);
        }
       
     
        labelupdate();
        anc_scroll.setVisible(true);
        event_array = Event_Services.displayEventFront();
        event_array1 = Event_Services.displayEventPart();
        int column = 0;
        int row = 1;
        int column1 = 0;
        int row1 = 1;
        for (int i = 0; i < event_array.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/event_display.fxml"));

            try {
                cardBox = fxmlLoader.load();
                
                Event_displayController cardController = fxmlLoader.getController();
             //   System.out.println(auction_array_detailed.get(0));
                cardController.show_event(event_array.get(i));
                VBox cardPane = (VBox) cardBox.getChildren().get(1);
                 cardPane.setOnMouseClicked(event -> {
                    // Pass the selected artwork to the detail view
                    getID(cardController.getEvent());
                });
            System.out.println(cardBox.getChildren());
                event_view.getChildren().add(cardBox);
                if (column == 1) {
                    column = 0;
                    row++;
                }
                event_container.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(10));//topRightBottomLeft: 10

            } catch (IOException ex) {
                Logger.getLogger("heeerrreeeee" + Auction_frontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }    

    
    
    public String getname()
    {
        String name=null;
        int id=0;
        PreparedStatement pst;
        String sql = "SELECT * FROM participants WHERE id_user=?";
        String sql1 = "SELECT * FROM event WHERE id=?";
        
        try {
            pst = conn.prepareStatement(sql);
             pst.setInt(1, Data.user.getId());
             


                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    id=rs.getInt("id_event");
                    
                }
                pst = conn.prepareStatement(sql1);
             pst.setInt(1, id);
              ResultSet rs1 = pst.executeQuery();
              if (rs1.next()) {
                    name=rs1.getString("event_name");
                    
                }
             
        } catch (SQLException ex) {
            Logger.getLogger(Participate_eventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }
    
    public void getID(Event event)
    {
        System.out.println(event.getEvent_id());
    }

   
    
     
   

    @FXML
    private void handleButtonAction(ActionEvent event) {
        
           
        
        if(event.getSource()==btndelete){
           EventDelete();
        }
    }
    
     
     public void EventDelete() {
        Alert alert;
         int id=0;
        PreparedStatement pst;
        String sql = "SELECT * FROM participants WHERE id_user=?";

        
        try {
            pst = conn.prepareStatement(sql);
             pst.setInt(1, Data.user.getId());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    id=rs.getInt("id_event");               
                }          
        } catch (SQLException ex) {
            Logger.getLogger(Participate_eventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            
        
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Your participation: " + id + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    Event_Services.deletePart(id);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    labelupdate();
                  
                } else {
                    return;
                }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    

//    private void deconnect_btn(ActionEvent event) {
//        deconnect.setStyle("-fx-background-color: #470011 ");
//        
//        
//        default_anchor.setVisible(false);
//
//        try {
//            Parent parent2 = FXMLLoader
//                    .load(getClass().getResource("/view/signin_page.fxml"));
//
//            Scene scene = new Scene(parent2);
//            Stage stage = (Stage) ((Node) event.getSource())
//                    .getScene().getWindow();
//            stage.setScene(scene);
//            stage.setTitle("DIGITART");
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

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
    private void home_btn(ActionEvent event) {try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/Home_page.fxml"));

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
    private void artwork_btn(ActionEvent event) {
        
         try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/display_artwork_user.fxml"));

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
    private void auction_btn(ActionEvent event) {
       try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/auction_front.fxml"));

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
    private void tickets_btn(ActionEvent event) {
            try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/add_ticket_user.fxml"));

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
        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/editprofileuser_front.fxml"));

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
    private void events_btn(ActionEvent event) {
        anc_scroll.setVisible(true);
        events_btn.setStyle("-fx-background-color: #bd2a2e "); 
    }

    

}
