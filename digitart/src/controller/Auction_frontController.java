/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Auction_Services;
import entity.Auction_display;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        auction_array_detailed = Auction_Services.Display_auction_details();

        for (int i = 0; i < auction_array_detailed.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/artwork_front.fxml"));
            HBox cardBox;
            try {
                cardBox = fxmlLoader.load();
                Artwork_frontController cardController = fxmlLoader.getController();
                //System.out.println(auction_array_detailed.get(0));
                cardController.set_artwork(auction_array_detailed.get(0));
                auction_view.getChildren().add(cardBox);
            } catch (IOException ex) {
                Logger.getLogger("heeerrreeeee" + Auction_frontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
