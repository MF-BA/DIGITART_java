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
        Data.user = new users(1, 12,"firstname", "lastname", "email", " pwd", "address",  45, LocalDate.of(2023, Month.FEBRUARY, 15), " gender", "role");
        launch(args);
        /*
         ArrayList<auction_display> AuctionList;
       AuctionList = Auction_Services.Display_auction_details();

        
        // TODO code application logic here
        
        Add auction to table 
         
        Auction auction1 = new Auction(100, 10, 1, LocalDate.of(2023, Month.MARCH, 10), "new edition");
        Auction_Services.add(auction1);
         
        //display table
        /*
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
         */
 /*
 
 
        ArrayList<Bid> dataBid;
        dataBid = Bid_Services.Display();
        System.out.println(dataBid);
         */
        // System.exit(0);
        //Amine---------------------------------------------------------------------------------------------------- 
        /*
        java.sql.Date ticketDate = java.sql.Date.valueOf("1990-03-12");
        Ticket ticket = new Ticket(123,ticketDate, "Teen", 100, 30, "kes7a");
      ServiceTicket.addTicket(ticket);

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
 /* 

       System.out.println(ServiceTicket.displayTicket());
        
       ServiceTicket.deleteTicket(1234);
        
        System.out.println(ServiceTicket.displayTicket());
        
        Ticket updatedTicket = new Ticket(123,ticketDate, "Adult", 200, 20, "dar");
        ServiceTicket.updateTicket(updatedTicket);
        
       System.out.println(ServiceTicket.displayTicket());
         */
        // Amine---------------------------------------------------------------------------------------------------- 
        System.exit(
                0);

    }

}
