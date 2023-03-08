/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import entity.QrCodeGenerator;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DisplayQrController implements Initializable {

    @FXML
    private ImageView qrCode;
    @FXML
    private Button save_qr_code;

    private Image qrImage;

    public void setQrCodeContent(String content) throws IOException, WriterException {
        // Generate the QR code image
        byte[] qrCodeImage = QrCodeGenerator.generateQrCode(content);
        // Load the QR code image into an ImageView control
        Image image = new Image(new ByteArrayInputStream(qrCodeImage));
        qrImage = image;
        qrCode.setImage(image);
    }

    @FXML
    private void saveQrCode(ActionEvent event) {
        // Check if there is a QR code image to save
        if (qrImage == null) {
            return;
        }

        // Show a file chooser dialog to let the user choose a file to save the image to
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save QR Code");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG Image", "*.png"));
        File file = fileChooser.showSaveDialog(save_qr_code.getScene().getWindow());
        if (file != null) {
            try {
                // Save the QR code image to the chosen file
                ImageIO.write(SwingFXUtils.fromFXImage(qrImage, null), "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String data = "Hello, world!"; // replace with your ticket data

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);

            BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < 200; x++) {
                for (int y = 0; y < 200; y++) {
                    int grayValue = (bitMatrix.get(x, y) ? 0 : 1) & 0xff;
                    bufferedImage.setRGB(x, y, (grayValue << 16) | (grayValue << 8) | grayValue);
                }
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            byte[] imageBytes = baos.toByteArray();
            baos.close();

            Image image = new Image(new ByteArrayInputStream(imageBytes));
            qrImage = image;
            qrCode.setImage(image);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}
