/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.Auction;
import Services.Auction_Services;
import Services.Bid_Services;
import entity.Bid;
import java.io.IOException;
import java.time.*;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author fedi1
 */
public class JavaApplication6 extends Application {

    private Stage primaryStage;
    private Parent ADDAUCTION_Page;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hello World");

        ADDAUCTION_Page = FXMLLoader.load(getClass().getResource("/view/add_auction.fxml"));
        Scene scene = new Scene(ADDAUCTION_Page);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        // TODO code application logic here
        /*
        Add auction to table 
         
        Auction auction1 = new Auction(100, 10, 1, LocalDate.of(2023, Month.MARCH, 10), "new edition");
        Auction_Services.add(auction1);
         */
        //display table
        ArrayList<Auction> data;
        data = Auction_Services.Display();
        System.out.println(data);

        /*
        Delete and display table
         
        Auction auction2 = data.get(0);
        Auction_Services.delete(auction2.getId_auction());
        data = Auction_Services.Display();
        System.out.println(data);
         */

 /*
        Modify and display table
        
        Auction auction3 = new Auction(6, 500, 50, 1, LocalDate.of(2023, Month.FEBRUARY, 15), "new edition");
        Auction_Services.modify(auction3);

        data = Auction_Services.Display();
        System.out.println(data);
         */
/* 
        Bid bid1 = new Bid(2, 7, 110);
        Bid_Services.add(bid1);
*/
        ArrayList<Bid> dataBid;
        dataBid = Bid_Services.Display();
        System.out.println(dataBid);

        System.exit(0);

    }

}
