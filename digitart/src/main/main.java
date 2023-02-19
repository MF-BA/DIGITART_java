/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.Ticket;
import Services.ServiceTicket;
import entity.Auction;
import Services.Auction_Services;
import Services.Bid_Services;
import entity.Bid;
import java.io.IOException;
import java.sql.Connection;
import java.time.*;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

/**
 *
 * @author fedi1
 */
public class main extends Application {

    private Stage primaryStage;
    //private Parent ADDAUCTION_Page
    private double  x=0;
    private double  y=0;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hello World");

       Parent ADDEVENT_Page = FXMLLoader.load(getClass().getResource("/view/add_event.fxml"));
       // Scene scene = new Scene(ADDAUCTION_Page);
       //Scene scene = new Scene(ADDATICKET_Page);
       Scene scene = new Scene(ADDEVENT_Page);
       
       
       /* ADDATICKET_Page.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        ADDATICKET_Page.setOnMouseDragged((MouseEvent event) -> {

            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);

            primaryStage.setOpacity(.8);
        });
        ADDATICKET_Page.setOnMouseReleased((MouseEvent event) -> {
            primaryStage.setOpacity(1);
        });*/

        primaryStage.initStyle(StageStyle.TRANSPARENT);
       
       
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

       System.out.println(ServiceTicket.displayTicket());
        
       ServiceTicket.deleteTicket(1234);
        
        System.out.println(ServiceTicket.displayTicket());
        
        Ticket updatedTicket = new Ticket(123,ticketDate, "Adult", 200, 20, "dar");
        ServiceTicket.updateTicket(updatedTicket);
        
       System.out.println(ServiceTicket.displayTicket());
        */
       // Amine---------------------------------------------------------------------------------------------------- 
    }

}
