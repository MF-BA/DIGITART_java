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
import entity.Artwork;
import main.main;
/**
 *
 * @author mohamed
 */
public class Artwork_Services {
    
    static Connection conn = Conn.getCon();

    public static void add(Artwork artwork) {

        String add_to_artwork = "insert into artwork (artwork_name,id_artist,artist_name,date_art,description,image_art,id_room) values (?,?,?,?,?,?,?) ";
        try {
            PreparedStatement st = conn.prepareStatement(add_to_artwork);
           
            st.setString(1, artwork.getArtwork_name());
            st.setInt(2, artwork.getId_artist());
            st.setString(3, artwork.getArtist_name());
            st.setDate(4, Date.valueOf(artwork.getDate_art()));
            st.setString(5, artwork.getDescription());
            st.setString(6, artwork.getImage_art());
            st.setInt(7, artwork.getId_room());
            
            
            
            st.executeUpdate();
            System.out.println("success!!Add artwork");
        } catch (SQLException ex) {
            System.err.println("Err Add artwork");
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public static ArrayList<Artwork> Display() {

        ArrayList<Artwork> list = new ArrayList<>();

        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM artwork");
            

            while (resultSet.next()) {
                    LocalDate D = resultSet.getDate(5).toLocalDate();
                Artwork data = new Artwork(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        D,
                        resultSet.getString(6), 
                        resultSet.getString(7),
                        resultSet.getInt(8)
                );
                list.add(data);
                System.out.println("success!!display artwork");
            }
        } catch (SQLException ex) {
            System.err.println("Err Display artwork");
        }

        return list;

    }

    public static void delete(int ID) {

        Statement statement;

        try {
            statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM artwork WHERE id_art =" + ID);
            System.out.println("success!!delete artwork");
        } catch (SQLException ex) {
            System.err.println("Err delete artwork");
        }

    }
    
}
