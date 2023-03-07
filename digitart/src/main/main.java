/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Services.Auction_Services;
import Services.Bid_Services;

import java.time.LocalDate;
import entity.Data;
import entity.users;
import java.time.Month;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import java.io.IOException;

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

        
        //Parent DisplayROOM_Page = FXMLLoader.load(getClass().getResource("/view/back_admin/display_auction_back.fxml"));
        Parent DisplayROOM_Page = FXMLLoader.load(getClass().getResource("/view/signin.fxml"));
        Scene scene = new Scene(DisplayROOM_Page);

        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    public void send_mail(users user, int id_auction) {
        //Session Creation
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Data.username, Data.password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Data.username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject("YOU WON!!!!!");
            message.setText("Dear " + user.getLastname() + ",\n"
                    + "Congratulations! We're excited to inform you that you've won the auction for " + Auction_Services.find_artwork_name_from_auctionID(id_auction).toUpperCase() + ". "
                    + "Your winning bid was " + Bid_Services.highest_offer(id_auction) + "."
                    + "\n"
                    + "We'll be in touch with you shortly to arrange payment and delivery of the artwork. If you have any questions or concerns, please don't hesitate to contact us."
                    + "\n"
                    + "Thank you for participating in our auction, and we hope you enjoy your new artwork!"
                    + "\n"
                    + "Best regards,\n"
                    + "DIGITART");
            Transport.send(message);
            System.out.println("Message_envoye");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    /* public static void main(String[] args) {
            EnvoyerEmail test = new EnvoyerEmail();
            test.envoyer();
        }*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        ArrayList<Integer> id_auction = new ArrayList<>();
        ArrayList<users> users = Auction_Services.verif_winners(id_auction);

        int i = 0;
        for (users user : users) {
            main test = new main();
            test.send_mail(user, id_auction.get(i));
            i++;

        }

        Data.user = new users(1, 12, "firstname", "lastname", "email", " pwd", "address", 45, LocalDate.of(2023, Month.FEBRUARY, 15), " gender", "role");

        /*String apiUrl = "http://data.fixer.io/api/latest?access_key=fBHGSQbaHC735dTQMppwn4QhzmuSeQcp";
        URL url = new URL(apiUrl);
        Scanner scanner = new Scanner(url.openStream());
        String jsonString = scanner.nextLine();
        System.out.println(jsonString);
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        double usdToEur = jsonObject.getAsJsonObject("rates").get("USD").getAsDouble();
        System.out.println("1 USD = " + usdToEur + " EUR");*/
        

        launch(args);

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
