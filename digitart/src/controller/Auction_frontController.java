/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Auction_Services;
import entity.Auction_display;
import entity.Data;
import javafx.geometry.Insets;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fedi1
 */
public class Auction_frontController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    
    ArrayList<Auction_display> auction_array_detailed;
    @FXML
    private HBox auction_view;
    @FXML
    private GridPane auction_container;
    HBox cardBox;
    @FXML
    private Label labeladminname;
    @FXML
    private Label labeladminname1;
    @FXML
    private Label labeladminname2;
    @FXML
    private Button btn_Artworks_Auction;
    @FXML
    private Button btn_Add_Auction;
    @FXML
    private Button auction_btn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_Add_Auction.setStyle("-fx-background-color: transparent");
        btn_Artworks_Auction.setStyle("-fx-background-color:transparent  ");
        auction_btn.setStyle("-fx-background-color: #470011");
        display_auction();
        
    }
    
    public void display_auction() {
        auction_array_detailed = Auction_Services.Display_auction_details(Auction_Services.Display_front(Data.user.getId()));
        int column = 0;
        int row = 1;
        for (int i = 0; i < auction_array_detailed.size(); i++) {
            
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/artwork_front.fxml"));
            
            try {
                cardBox = fxmlLoader.load();
                Artwork_frontController cardController = fxmlLoader.getController();
                //System.out.println(auction_array_detailed.get(0));
                cardController.set_artwork(auction_array_detailed.get(i));
                System.out.println(cardBox.getChildren());
                ImageView cardPane = (ImageView) cardBox.getChildren().get(0);
                
                cardPane.setOnMouseClicked(event -> {
                    // Pass the selected artwork to the detail view
                    showArtworkDetails(cardController.getArtwork());
                });
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
    
        private void showArtworkDetails(Auction_display artwork) {
        System.out.println(artwork);
    }
    
    @FXML
    private void view_clicked(MouseEvent event) {
    }
    
    @FXML
    private void auction_btn_clicked(ActionEvent event) {
    }
    
    @FXML
    private void btn_Add_Auction_click(ActionEvent event) {
        ArrayList<String> arr = Auction_Services.find_artworks(Data.user.getId());
        
        if (arr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING !!!!");
            alert.setContentText("You don't have any artwork to add!!!");
            alert.showAndWait();
        } else {
            try {
                root = FXMLLoader.load(getClass().getResource("/view/add_auction.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void go_Home(ActionEvent event) {
        try {
            // Get the current stage
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Load the FXML file for the home screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Display_auctions.fxml"));
            root = loader.load();

            // Create a new scene with the loaded FXML file
            scene = new Scene(root);

            // Set the new scene on the current stage
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void Artworks__Auction_cicked(ActionEvent event) {
        go_Home(event);
    }
    
}
