/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Auction_Services;
import entity.Auction_display;
import javafx.geometry.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author fedi1
 */
public class Auction_frontController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ArrayList<Auction_display> auction_array_detailed;
    @FXML
    private HBox auction_view;
    @FXML
    private GridPane auction_container;
    HBox cardBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        auction_array_detailed = Auction_Services.Display_auction_details();
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

}
