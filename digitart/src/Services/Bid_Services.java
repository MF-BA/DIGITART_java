/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static Services.Auction_Services.conn;
import entity.Auction;
import entity.Bid;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.main;
import utils.Conn;

/**
 *
 * @author fedi1
 */
public class Bid_Services {

    static Connection conn = Conn.getCon();

    public static int next_offer(int ID_auction) {
        String latest_bid_sql = "SELECT MAX(offer) FROM bid WHERE id_auction = ?";
        String increment_sql = "SELECT increment FROM auction WHERE id_auction = ?";

        int latest_bid = 0;
        int increment = 0;

        PreparedStatement latest_bid_stmt;
        PreparedStatement increment_stmt;

        ResultSet resultLatest_bidt;
        ResultSet resultIncrement;
        try {
            latest_bid_stmt = conn.prepareStatement(latest_bid_sql);
            increment_stmt = conn.prepareStatement(increment_sql);

            latest_bid_stmt.setInt(1, ID_auction);
            increment_stmt.setInt(1, ID_auction);

            resultLatest_bidt = latest_bid_stmt.executeQuery();
            if (resultLatest_bidt.next()) {
                latest_bid = resultLatest_bidt.getInt(1);
            }

            resultIncrement = increment_stmt.executeQuery();
            if (resultIncrement.next()) {
                increment = resultIncrement.getInt(1);
            }

            if (latest_bid == 0) {
                String starting_price = "SELECT starting_price FROM auction WHERE id_auction = ?";
                PreparedStatement starting_price_stmt;
                ResultSet resultstarting_price;
                starting_price_stmt = conn.prepareStatement(starting_price);
                starting_price_stmt.setInt(1, ID_auction);
                
                resultstarting_price = starting_price_stmt.executeQuery();
                if (resultstarting_price.next()) {
                    return resultstarting_price.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bid_Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        return latest_bid + increment;
    }

    public static void add(Bid bid) {

        if (bid.getOffer() >= next_offer(bid.getId_auction())) {
            String add_to_auction = "insert into bid (date,offer,id_auction , id_user ) values (?,?,?,?) ";
            try {
                PreparedStatement st = conn.prepareStatement(add_to_auction);
                st.setDate(1, Date.valueOf(bid.getDate()));
                st.setInt(2, bid.getOffer());
                st.setInt(3, bid.getId_auction());
                st.setInt(4, bid.getId_user());

                st.executeUpdate();
                System.out.println("success!!");
            } catch (SQLException ex) {
                System.err.println("error!!");
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println("offer must be more than " + next_offer(bid.getId_auction()));
        }

    }

    public static ArrayList<Bid> Display() {

        ArrayList<Bid> list = new ArrayList<>();

        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM bid");

            while (resultSet.next()) {
                LocalDate D = resultSet.getDate(2).toLocalDate();
                Bid data = new Bid(resultSet.getInt(1), resultSet.getInt(4),
                        resultSet.getInt(5), resultSet.getInt(3), D);

                list.add(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bid_Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

    public static void delete(int ID) {

        Statement statement;

        try {
            statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM bid WHERE id_bid =" + ID);

        } catch (SQLException ex) {
            Logger.getLogger(Bid_Services.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void modify(Bid bid) {

        String modify_bid = "UPDATE bid set date = ?, offer = ?, id_auction = ?, id_user = ? where id_bid = ? ";

        try {
            PreparedStatement st = conn.prepareStatement(modify_bid);
            st.setDate(1, Date.valueOf(bid.getDate()));
            st.setInt(2, bid.getOffer());
            st.setInt(3, bid.getId_auction());
            st.setInt(4, bid.getId_user());

            st.executeUpdate();
            System.out.println("success!!");
        } catch (SQLException ex) {
            System.err.println("error!!");
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
