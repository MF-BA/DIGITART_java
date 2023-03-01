/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Artwork_Services;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import entity.Artwork;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.glxn.qrgen.QRCode;

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
    @FXML
    private Button btn_qr;
    
//    public void qrcode() throws IOException, WriterException {
//    // Créer une nouvelle fenêtre
//    Stage qrCodeStage = new Stage();
//    qrCodeStage.setTitle("QR Code");
//
//    // Créer un ImageView pour afficher le QR code
//    ImageView qrCodeImageView = new ImageView();
//    qrCodeStage.setScene(new Scene(new StackPane(qrCodeImageView), 300, 300));
//
//    // Générer le QR code avec la bibliothèque ZXing
//    String text = "https://www.google.com/search?q=" + l_nameartwork.getText() + "+" + l_nameartist.getText();
//    int size = 200;
//    String fileType = "png";
//    Map<EncodeHintType, Object> hintMap = new HashMap<>();
//    hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//
//    QRCodeWriter qrCodeWriter = new QRCodeWriter();
//    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size, size, hintMap);
//
//    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//    MatrixToImageWriter.writeToStream(bitMatrix, fileType, byteArrayOutputStream);
//
//    byte[] byteArray = byteArrayOutputStream.toByteArray();
//
//    // Convertir le tableau de bytes en BufferedImage
//    BufferedImage qrCodeImage = ImageIO.read(new ByteArrayInputStream(byteArray));
//
//    // Convertir le BufferedImage en Image
//    Image fxImage = SwingFXUtils.toFXImage(qrCodeImage, null);
//
//    // Afficher le QR code dans l'ImageView
//    qrCodeImageView.setImage(fxImage);
//
//    // Afficher la nouvelle fenêtre
//    qrCodeStage.show();
//}


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

//    @FXML
//    private void btn_qr_clicked(ActionEvent event) {
//        try {
//            qrcode();
//        } catch (IOException ex) {
//            Logger.getLogger(Artwork_userfrontController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (WriterException ex) {
//            Logger.getLogger(Artwork_userfrontController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
       


        
        
    
        
        
    
}
