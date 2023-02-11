/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.time.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fedi1
 *
 */
public class Auction_Services {

    static Connection conn = Con.getCon();

    public static void add(Auction auction) {

        String add_to_auction = "insert into auction (starting_price,increment,ending_date,description, id_artwork ) values (?,?,?,?,?) ";
        try {
            PreparedStatement st = conn.prepareStatement(add_to_auction);
            st.setInt(1, auction.getStarting_price());
            st.setInt(2, auction.getIncrement());
            st.setInt(5, auction.getId_artwork());
            st.setDate(3, Date.valueOf(auction.getDate()));
            st.setString(4, auction.getDescription());

            st.executeUpdate();
            System.out.println("success!!");
        } catch (SQLException ex) {
            System.err.println("error!!");
            Logger.getLogger(JavaApplication6.class.getName()).log(Level.SEVERE, null, ex);
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
                LocalDate D = resultSet.getDate(4).toLocalDate() ;
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
}
