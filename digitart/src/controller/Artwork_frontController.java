/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Auction_display;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        artwork_name.setTextFill(Color.BROWN);
        Date.setTextFill(Color.RED);
        Image image = new Image(getClass().getResourceAsStream("/view/pexels-sam-kolder-2387873.jpg"));
        System.out.println(image.toString());
        photo.setImage(image);

    }

    public void set_artwork(Auction_display auction_display) {
        //photo.setImage(new Image(getClass().getResourceAsStream(auction_display.getImg())));

        artwork_name.setText(auction_display.getName().toUpperCase());
        artist.setText("Artist: "+auction_display.getName_artist());
        current_bid.setText("Current Bid:"+String.valueOf(auction_display.getBid()));
        int next_bid = auction_display.getBid() + auction_display.getIncrement();
        this.next_bid.setText("Next Bid:"+String.valueOf(next_bid));
        Date.setText(findDifference(auction_display.getDate()));

    }

    String findDifference(LocalDate end_date) {

        // parse method is used to parse
        // the text from a string to
        // produce the date
        // Calculate time difference
        // in milliseconds
        long difference_In_Time = ChronoUnit.DAYS.between(end_date, LocalDate.now());
        // Calculate time difference in
        // seconds, minutes, hours, years,
        // and days
        long difference_In_Seconds
                = (difference_In_Time
                / 1000)
                % 60;
        long difference_In_Minutes
                = (difference_In_Time
                / (1000 * 60))
                % 60;
        long difference_In_Hours
                = (difference_In_Time
                / (1000 * 60 * 60))
                % 24;
        long difference_In_Years
                = (difference_In_Time
                / (1000l * 60 * 60 * 24 * 365));
        long difference_In_Days
                = (difference_In_Time
                / (1000 * 60 * 60 * 24))
                % 365;
        // Print the date difference in
        // years, in days, in hours, in
        // minutes, and in seconds
        System.out.print(
                "Difference "
                + "between two dates is: ");
        return (difference_In_Years
                + " years, "
                + difference_In_Days
                + " days, "
                + difference_In_Hours
                + " hours, "
                + difference_In_Minutes
                + " minutes, "
                + difference_In_Seconds
                + " seconds");
    }

}
