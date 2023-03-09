/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import Services.Artwork_Services;
import entity.Artwork;
import entity.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class Display_artwork_userController implements Initializable {

    ArrayList<Artwork> artworkArray;
    @FXML
    private HBox auction_view;
    @FXML
    private GridPane auction_container;
    HBox cardBox;
    private Stage stage;
    private Scene scene;
    private Parent root;
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
    @FXML
    private Button add_artwork_btn;
    
    private void go_add_artwork(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/add_artwork_user.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            artwork_btn.setStyle("-fx-background-color: #bd2a2e ");
        if("Artist".equals(Data.user.getRole())){
            add_artwork_btn.setVisible(true);
        
        }else{add_artwork_btn.setVisible(false);}

        artworkArray = Artwork_Services.Display();
        int column = 0;
        int row = 1;
        for (int i = 0; i < artworkArray.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/artwork_userfront.fxml"));

            try {
                cardBox = fxmlLoader.load();
                Artwork_userfrontController cardController = fxmlLoader.getController();
                //System.out.println(auction_array_detailed.get(0));
                cardController.show_artwork(artworkArray.get(i));
                //ImageView cardPane = (ImageView) cardBox.getChildren().get(0);

            
                auction_view.getChildren().add(cardBox);
                if (column == 1) {
                    column = 0;
                    row++;
                }
                auction_container.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(10));//topRightBottomLeft: 10

            } catch (IOException ex) {
                Logger.getLogger("heeerrreeeee" + Auction_frontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void showArtworkDetails(Artwork artwork) {
        System.out.println(artwork);
    }

    @FXML
    private void view_clicked(MouseEvent event) {
    }

    private void btn_artwork_clicked(ActionEvent event) {
        go_add_artwork(event);
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
    private void events_btn(ActionEvent event) {
        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/participate_event.fxml"));

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
    private void add_artwork_btn_clicked(ActionEvent event) {
    }
}
