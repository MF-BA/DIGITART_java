/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Auction;
import entity.Auction_display;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author fedi1
 */
public class Artwork_frontController implements Initializable {

    @FXML
    private ImageView photo;
    @FXML
    private Label artwork_name;
    @FXML
    private Label current_bid;
    @FXML
    private Label next_bid;
    @FXML
    private Label Date;
    @FXML
    private Label artist;
    ZonedDateTime endDateTime;
    Auction_display auction_display;
    @FXML
    private Button add_bid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        endDateTime = ZonedDateTime.of(2023, 2, 28, 12, 0, 0, 0, ZoneId.systemDefault());

        artwork_name.setTextFill(Color.BROWN);
        Date.setTextFill(Color.RED);
        Image image = new Image(getClass().getResourceAsStream("/view/pexels-sam-kolder-2387873.jpg"));
        //System.out.println(image.toString());
        photo.setImage(image);

    }

    public Auction_display getArtwork() {
        return this.auction_display;
    }

    public void set_artwork(Auction_display auction_display) {
        auction_display.setImg("/view/pexels-sam-kolder-2387873.jpg");
        this.auction_display = auction_display;
//photo.setImage(new Image(getClass().getResourceAsStream(auction_display.getImg())));

        artwork_name.setText(auction_display.getName().toUpperCase());
        artist.setText("Artist: " + auction_display.getName_artist());
        current_bid.setText("Current Bid:" + String.valueOf(auction_display.getBid()));
        int next_bid = auction_display.getBid() + auction_display.getIncrement();
        this.next_bid.setText("Next Bid:" + String.valueOf(next_bid));
        LocalDate localDate = auction_display.getDate();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                String result = findDifference(zonedDateTime);
                Platform.runLater(() -> Date.setText(result));
            }
        };

        // Schedule the TimerTask to run every 5 seconds
        timer.schedule(task, 0, 100); // 0 milliseconds initial delay, 5000 milliseconds (5 seconds) between subsequent executions
    }

    public static String findDifference(ZonedDateTime endDateTime) {
        // Calculate time difference in seconds
        long differenceInSeconds = ChronoUnit.SECONDS.between(ZonedDateTime.now(), endDateTime);

        long differenceInYears = differenceInSeconds / (365 * 24 * 60 * 60);
        long differenceInDays = (differenceInSeconds / (24 * 60 * 60)) % 365;
        long differenceInHours = (differenceInSeconds / (60 * 60)) % 24;
        long differenceInMinutes = (differenceInSeconds / 60) % 60;
        long differenceInSecondsOutput = differenceInSeconds % 60;

        // Build the result string
        String result = differenceInDays + " days, "
                + differenceInHours + " hours, "
                + differenceInMinutes + " minutes, "
                + differenceInSecondsOutput + " seconds.";

        // Return the result string
        return result;
    }

    @FXML
    private void add_bid_clicked(ActionEvent event) {
        System.out.println("add bid clicked");
    }

}
