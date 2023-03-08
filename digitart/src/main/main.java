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

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("DIGITART");

        //Parent DisplayROOM_Page = FXMLLoader.load(getClass().getResource("/view/back_admin/display_auction_back.fxml"));
        Parent DisplayROOM_Page = FXMLLoader.load(getClass().getResource("/view/signin_page.fxml"));
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
        Data.user = new users(35, 12, "firstname", "lastname", "laatarmomo@gmail.com", " pwd", "address", 45, LocalDate.of(2023, Month.FEBRUARY, 15), " gender", "Admin");
        launch(args);
        System.exit(0);
    }

}
