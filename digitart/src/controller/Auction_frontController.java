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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
    private Button btn_Artworks_Auction;
    @FXML
    private Button btn_Add_Auction;
    @FXML
    private Button auction_btn;
    @FXML
    private Button disconnect;
    @FXML
    private Button editprof_btn;
    @FXML
    private Button home_btn;
    @FXML
    private Button artwork_btn;
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
    private Button auction_btn1;
    @FXML
    private ScrollPane scrollPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelusername.setText(Data.user.getFirstname());
        try {
    if (Data.user.getImage()!=null){
        Image image = new Image(Data.user.getImage());
        circle_image.setFill(new ImagePattern(image));
    } else {
        circle_image.setFill(null);
    }
} catch (Exception e) {
    // handle the exception
    System.out.println("An error occurred: " + e.getMessage());
}

        if(Data.user.getRole().equals("Subscriber") )
        {
            auction_btn1.setVisible(false);
            btn_Artworks_Auction.setVisible(false);
            btn_Add_Auction.setVisible(false);
        }
        auction_btn.setStyle("-fx-background-color: #bd2a2e");
        auction_btn1.setStyle("-fx-background-color: #bd2a2e");
        display_auction();
    }

    public void display_auction() {
        auction_array_detailed = Auction_Services.Display_auction_details(Auction_Services.Display_front_public(Data.user.getId()));
        int column = 0;
        int row = 1;
        if (auction_array_detailed.size() == 1) {
            scrollPane.setPrefSize(900,232
            );
        }
        else if(auction_array_detailed.size() == 0)
        {
            scrollPane.setVisible(false);
        }
        for (int i = 0; i < auction_array_detailed.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/artwork_front.fxml"));

            try {
                cardBox = fxmlLoader.load();
                Artwork_frontController cardController = fxmlLoader.getController();
                cardController.set_artwork(auction_array_detailed.get(i));
                //System.out.println(cardBox.getChildren());
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

}
