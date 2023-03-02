/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.time.LocalDate;
import entity.Ticket;
import Services.ServiceTicket;
import entity.Auction;
import Services.Auction_Services;
import Services.Bid_Services;
import controller.Signup_pageController;
import entity.Bid;
import entity.Auction_display;
import entity.Data;
import entity.users;
import java.io.IOException;
import java.sql.Connection;
import java.time.Month;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

/**
 *
 * @author fedi1
 */
public class main extends Application {

    private Stage primaryStage;
    private Parent root;
    //private Parent ADDAUCTION_Page
    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("DIGITART");

        Parent DisplayROOM_Page = FXMLLoader.load(getClass().getResource("/view/display_artwork_user.fxml"));
        Scene scene = new Scene(DisplayROOM_Page);

        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Data.user = new users(1, 12,"Edvard", " Munch", "laatarmomo@gmail.com", " pwd", "address",  45, LocalDate.of(2023, Month.FEBRUARY, 15), " gender", "role");
        launch(args);
        
        System.exit(
                0);

    }

}
