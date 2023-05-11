/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Artwork_Services;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.IOException;
import entity.Artwork;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    @FXML
    private ImageView qrcode;

    public void qrcode() throws IOException, WriterException {

    }

    public void generateQRCode() {
        String url = "https://artsandculture.google.com/search?q=" + l_nameartwork.getText().replaceAll("\\s+", "%20") + "%20";

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        ByteMatrix byteMatrix;
        try {
            byteMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200);
        } catch (WriterException e) {
            e.printStackTrace();
            return;
        }

        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int value = byteMatrix.get(x, y);
                image.setRGB(x, y, value == 0 ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
            }
        }

        Image fxImage = SwingFXUtils.toFXImage(image, null);
        qrcode.setImage(fxImage);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void show_artwork(Artwork artwork) {

        if (!artwork.getImage_art().isEmpty()) {
            image1 = new Image("http://127.0.0.1:8000/uploads/" + artwork.getImage_art(), 350, 300, false, true);
        } else {
            image1 = new Image("http://127.0.0.1:8000/uploads/empty.jpeg", 350, 300, false, true);
        }

        imagev.setImage(image1);
        l_nameartwork.setText(artwork.getArtwork_name().toUpperCase());
        l_nameartist.setText(artwork.getArtist_name());
        l_desc.setText(artwork.getDescription());
        l_date.setText(artwork.getDate_art().toString());
        l_room.setText(Artwork_Services.find_nameroom(artwork.getId_room()).toString());
        generateQRCode();

    }
    /*
     public Artwork getArtwork() {
        return this.auction_display;
    }*/

}
