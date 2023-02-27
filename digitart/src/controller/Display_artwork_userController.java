/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import Services.Artwork_Services;
import entity.Artwork;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    @FXML
    private Label labeladminname;
    @FXML
    private Label labeladminname1;
    @FXML
    private Label labeladminname2;
    @FXML
    private Button btn_artwork;
    private Stage stage;
    private Scene scene;
    private Parent root;
    
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

    @FXML
    private void btn_artwork_clicked(ActionEvent event) {
        go_add_artwork(event);
    }
}
