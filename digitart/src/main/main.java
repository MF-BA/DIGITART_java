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
public class main extends Application {

    private Stage primaryStage;
    private Parent ADDAUCTION_Page;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hello World");

        ADDAUCTION_Page = FXMLLoader.load(getClass().getResource("/view/add_room.fxml"));
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
         
        //display table
        ArrayList<Auction> data;
        data = Auction_Services.Display();
        System.out.println(data);
*/
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

        ArrayList<Bid> dataBid;
        dataBid = Bid_Services.Display();
        System.out.println(dataBid);

        System.exit(0);
        
   */     
        
        
        
        /* MOHAMED -----------------------------------------------------------
        
        
          ArrayList<Room> data ;
        ArrayList<Artwork> data2 ;
        
        
        
        /**add and display room 
         * 
             Room room1 = new Room("Carthage",260,"available","qdqzdqzd");
             Room_Services.add(room1);
        
    
             data = Room_Services.Display() ;
             System.out.println(data);
        */
         
        /* delete and display room 
        
            Room room2 = data.get(0);
            Room_Services.delete(room2.getId_room());
        
            data = Room_Services.Display() ;
            System.out.println(data);
        */
        
       
        /* add  and display artwork
        
          Artwork artwork1 = new Artwork("La Jaconde",1,"Leonard de Vinci",LocalDate.of(1517, Month.MARCH, 10),"qdqzdqzd","url",2);
             Artwork_Services.add(artwork1);
        
        
             data2 = Artwork_Services.Display() ;
             System.out.println(data2);
             
        */
        
      
        
        /* delete and display artwork 
        
            Artwork artwork2 = data2.get(0);
            Artwork_Services.delete(artwork2.getId_art());
        
            data2 = Artwork_Services.Display() ;
            System.out.println(data2);
   
        */
        
        
        
       

    }

}
