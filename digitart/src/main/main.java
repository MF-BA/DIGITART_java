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
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import controller.Signup_pageController;
import entity.Bid;
import entity.Auction_display;
import entity.Data;
import entity.users;
import java.io.IOException;
import java.sql.Connection;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

        Parent DisplayROOM_Page = FXMLLoader.load(getClass().getResource("/view/add_ticket.fxml"));
        Scene scene = new Scene(DisplayROOM_Page);

        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws StripeException {
        Data.user = new users(1, 12, "firstname", "lastname", "email", " pwd", "address", 45, LocalDate.of(2023, Month.FEBRUARY, 15), " gender", "role");
        launch(args);

        // Create a new customer
        /* Customer customer;
        try {
            customer = Customer.create(
                    // Set the customer parameters
                    new CustomerCreateParams.Builder()
                            .setName("ali")
                            .setEmail("ali@example.com")
                            .build()
            );
   
        } catch (StripeException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } */
    

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
        System.exit(0);

    }

}
