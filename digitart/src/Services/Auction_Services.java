/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static Services.users_Services.conn;
import utils.Conn;
import java.time.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Auction;
import entity.Auction_display;
import entity.users;
import javafx.scene.control.Alert;
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

    public static ArrayList<Auction> Display_back_archive_artist(int id_artist) {
        ArrayList<Auction> list = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id_auction,starting_price,increment,ending_date,auction.description,auction.id_artwork FROM auction INNER JOIN artwork ON auction.id_artwork  = artwork.id_art WHERE auction.ending_date <= CURDATE() and artwork.id_artist =" + id_artist);

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

    public static ArrayList<Auction> Display_back_artist(int id_artist) {
        ArrayList<Auction> list = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id_auction ,starting_price,increment,ending_date,auction.description,auction.id_artwork  FROM auction INNER JOIN artwork ON auction.id_artwork  = artwork.id_art WHERE auction.ending_date > CURDATE() and artwork.id_artist =" + id_artist);

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

    public static ArrayList<Auction> Display_back_archive() {
        ArrayList<Auction> list = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id_auction,starting_price,increment,ending_date,auction.description,auction.id_artwork FROM auction INNER JOIN artwork ON auction.id_artwork  = artwork.id_art WHERE auction.ending_date <= CURDATE()");

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

    public static ArrayList<Auction> Display_back() {
        ArrayList<Auction> list = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id_auction,starting_price,increment,ending_date,auction.description,auction.id_artwork FROM auction INNER JOIN artwork ON auction.id_artwork  = artwork.id_art WHERE auction.ending_date > CURDATE() ");

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

    public static ArrayList<Auction> Display_front(int id_artist) {
        ArrayList<Auction> list = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id_auction,starting_price,increment,ending_date,auction.description,auction.id_artwork FROM auction INNER JOIN artwork ON auction.id_artwork  = artwork.id_art WHERE ending_date > CURDATE() and artwork.id_artist =" + id_artist);

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

    public static ArrayList<Auction> Display_front_public(int id_artist) {
        ArrayList<Auction> list = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id_auction,starting_price,increment,ending_date,auction.description,auction.id_artwork  FROM auction INNER JOIN artwork ON auction.id_artwork  = artwork.id_art WHERE ending_date > CURDATE() and artwork.id_artist <>" + id_artist);
            //int id_auction, int starting_price, int increment, int id_artwork, LocalDate date, String description)
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

    public static ArrayList<Auction_display> Display_auction_details(ArrayList<Auction> Display) {
        ArrayList<Auction_display> displayList; // initialize displayList
        displayList = new ArrayList<>();
        for (Auction auction : Display) {
            Auction_display display = new Auction_display(auction, Auction_Services.find_artwork_name(auction.getId_artwork()), Bid_Services.highest_offer(auction.getId_auction()));
            displayList.add(display);
        }
        return displayList;
    }

    public static void delete(int ID) {
        Alert alert;
        Statement statement;
        ResultSet resultSet;

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) as cnt FROM bid WHERE id_auction =" + ID);
            resultSet.next();
            int count = resultSet.getInt("cnt");
            if (count > 0) {
                // There are bids for this auction, so exit the function
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("This auction has bids and cannot be deleted.");
                alert.showAndWait();
                return;
            }
            // No bids for this auction, so proceed with the deletion
            statement.executeUpdate("DELETE FROM auction WHERE id_auction =" + ID);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("The auction has been deleted successfully.");
            alert.showAndWait();

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
            resultSet = statement.executeQuery("SELECT artwork_name FROM artwork WHERE id_artist = " + ID_artist + " AND id_art NOT IN (SELECT id_artwork FROM auction WHERE id_artist =" + ID_artist + ")");

            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public static ArrayList<String> find_artists() {
        ArrayList<String> list = new ArrayList<>();

        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT CONCAT(firstname, ' ', lastname) AS fullname FROM users WHERE role = 'Artist'");

            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public static int find_id_artist(String name) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT id FROM users WHERE CONCAT(firstname, ' ', lastname) = ? AND role = 'Artist'");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
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

    public static String find_artwork_name_from_auctionID(int id_auction) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT artwork_name FROM artwork WHERE id_art = (select id_artwork from auction where id_auction= ?) ");
            statement.setInt(1, id_auction);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                throw new SQLException("No auction found with id: " + id_auction);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String find_artwork_name(int id_artwork) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT artwork_name FROM artwork WHERE id_art= ?");
            statement.setInt(1, id_artwork);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                throw new SQLException("No artwork found with id: " + id_artwork);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String get_img(int id) {
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT image_name FROM imageartwork WHERE id_art = ? LIMIT 1;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                return "";
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

    public static ArrayList<users> verif_winners(ArrayList<Integer> id_auction) {
        ArrayList<users> list = new ArrayList<>();

        PreparedStatement statement;
        ResultSet resultSet;
        try {

            statement = conn.prepareStatement("SELECT b.id_user,a.id_auction FROM bid b INNER JOIN auction a ON b.id_auction = a.id_auction WHERE b.offer = (SELECT MAX(b2.offer)"
                    + "  FROM bid b2 WHERE b2.id_auction = a.id_auction) AND a.ending_date <= CURDATE() AND a.state = ?  ");
            statement.setString(1, "sold");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id_auction.add(resultSet.getInt(2));

                ResultSet res;

                Statement statement2 = conn.createStatement();
                res = statement2.executeQuery("SELECT * FROM users where id=" + resultSet.getInt(1));

                if (res.next()) {
                    LocalDate D = res.getDate(9).toLocalDate();
                    users data = new users(
                            res.getInt(1),
                            res.getInt(2),
                            res.getString(3),
                            res.getString(4),
                            res.getString(5),
                            res.getString(6),
                            res.getString(7),
                            res.getInt(8),
                            D,
                            res.getString(10),
                            res.getString(11)
                    );
                    list.add(data);
                }
                Statement statement3 = conn.createStatement();
                statement3.executeUpdate("UPDATE auction SET state='informed' where id_auction =" + resultSet.getInt(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
