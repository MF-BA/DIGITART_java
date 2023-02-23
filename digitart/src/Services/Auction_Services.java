/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import utils.Conn;
import java.time.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Auction;
import entity.Auction_display;
import main.main;

/**
 *
 * @author fedi1
 *
 */
public class Auction_Services {

    static Connection conn = Conn.getCon();

    public static void add(Auction auction) {

        String add_to_auction = "insert into auction (starting_price,increment,ending_date,description, id_artwork ) values (?,?,?,?,?) ";
        try {
            PreparedStatement st = conn.prepareStatement(add_to_auction);
            st.setInt(1, auction.getStarting_price());
            st.setInt(2, auction.getIncrement());
            st.setDate(3, Date.valueOf(auction.getDate()));
            st.setString(4, auction.getDescription());
            st.setInt(5, auction.getId_artwork());

            st.executeUpdate();
            System.out.println("success!!");
        } catch (SQLException ex) {
            System.err.println("error!!");
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Auction> Display() {

        ArrayList<Auction> list = new ArrayList<>();

        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM auction");

            while (resultSet.next()) {
                LocalDate D = resultSet.getDate(4).toLocalDate();
                Auction data = new Auction(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(6),
                        D,
                        resultSet.getString(5)
                );
                list.add(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

    public static ArrayList<Auction_display> Display_auction_details() {
        ArrayList<Auction_display> displayList = new ArrayList<Auction_display>(); // initialize displayList
        for (Auction auction : Display()) {
            System.out.println(auction.getId_artwork());
            Auction_display display = new Auction_display(auction, Auction_Services.find_artwork_name(auction.getId_artwork()), Bid_Services.highest_offer(auction.getId_auction()));
            displayList.add(display);
        }
        return displayList;
    }
    

    public static void delete(int ID) {

        Statement statement;

        try {
            statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM auction WHERE id_auction =" + ID);

        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void modify(Auction auction) {
        String modify_auction = "UPDATE auction set starting_price = ?, increment = ?, ending_date = ?, description = ?, id_artwork = ? where id_auction = ? ";

        try {
            PreparedStatement st = conn.prepareStatement(modify_auction);
            st.setInt(1, auction.getStarting_price());
            st.setInt(2, auction.getIncrement());
            st.setDate(3, Date.valueOf(auction.getDate()));
            st.setString(4, auction.getDescription());
            st.setInt(5, auction.getId_artwork());
            st.setInt(6, auction.getId_auction());

            st.executeUpdate();
            System.out.println("success!!");
        } catch (SQLException ex) {
            System.err.println("error!!");
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<String> find_artworks(int ID_artist) {
        ArrayList<String> list = new ArrayList<>();

        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT artwork_name FROM artwork WHERE id_artist = " + ID_artist + " AND id_art NOT IN "
                    + "(SELECT id_artwork FROM auction WHERE id_artist =" + ID_artist + ")");

            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public static int find_artwork_id(String artwork_name) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT id_art FROM artwork WHERE artwork_name = ?");
            statement.setString(1, artwork_name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("No artwork found with name: " + artwork_name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public static String find_artwork_name(int id) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT artwork_name FROM artwork WHERE id_art= ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                throw new SQLException("No artwork found with id: " + id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static String get_img(int id) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT image_art FROM artwork WHERE id_art= ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                throw new SQLException("No image_art found with id: " + id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static int find_artist_id(String artwork_name) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT id_artist FROM artwork WHERE artwork_name = ?");
            statement.setString(1, artwork_name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException("No artwork found with name: " + artwork_name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
     public static String find_artist_name(int id) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT CONCAT(firstname,' ',lastname) AS name FROM users WHERE id= ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                throw new SQLException("No user found with id: " + id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
