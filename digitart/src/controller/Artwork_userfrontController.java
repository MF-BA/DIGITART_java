/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Artwork_Services;
import entity.Artwork;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class Artwork_userfrontController implements Initializable {

    @FXML
    private Label l_nameartwork;
    @FXML
    private Label l_nameartist;
    @FXML
    private Label l_date;
    @FXML
    private Label l_room;
    @FXML
    private Label l_desc;
    @FXML
    private ImageView imagev;
      private Image image1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void show_artwork(Artwork artwork) {
         image1 = new Image(artwork.getImage_art(), 350, 300, false, true);

        imagev.setImage(image1);
        l_nameartwork.setText(artwork.getArtwork_name().toUpperCase());
        l_nameartist.setText(artwork.getArtist_name());
        l_desc.setText(artwork.getDescription());
        l_date.setText(artwork.getDate_art().toString());
        l_room.setText(Artwork_Services.find_nameroom(artwork.getId_room()).toString());
        
    }
    /*
     public Artwork getArtwork() {
        return this.auction_display;
    }*/
       


        
        
    
        
        
    
}
