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
                    int idroom = resultSet.getInt(8);
                Artwork data = new Artwork(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        D,
                        resultSet.getString(6), 
                        resultSet.getString(7),
                        idroom
                        
                );
                list.add(data);
         
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
    
    public static void modify(Artwork artwork) {

        String modify_artwork = "update artwork SET artwork_name = ?,id_artist = ? ,artist_name= ?,date_art=?,description=?,image_art=?,id_room=? where id_art= ? ";
        try {
            PreparedStatement st = conn.prepareStatement(modify_artwork);
           
            st.setString(1, artwork.getArtwork_name());
            st.setInt(2, artwork.getId_artist());
            st.setString(3, artwork.getArtist_name());
            st.setDate(4, Date.valueOf(artwork.getDate_art()));
            st.setString(5, artwork.getDescription());
            st.setString(6, artwork.getImage_art());
            st.setInt(7, artwork.getId_room());
            st.setInt(8, artwork.getId_art());
            
            
            
            st.executeUpdate();
            System.out.println("success!!modify artwork");
        } catch (SQLException ex) {
            System.err.println("Err modify artwork");
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public static ArrayList<Integer> find_idartist() {
        ArrayList<Integer> list = new ArrayList<>();

        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id FROM users WHERE role = 'artist'");

            while (resultSet.next()) {
                list.add(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
     public static ArrayList<String> find_nameroom() {
        ArrayList<String> list = new ArrayList<>();

        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT name_room FROM room");

            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
        public static String find_nameroom(int id) {
       String list=null ;

        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT name_room FROM room where id_room="+id+"");

            while (resultSet.next()) {
                list=(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
    public static ArrayList<Integer> find_idroom() {
        ArrayList<Integer> list = new ArrayList<>();

        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id_room FROM room");

            while (resultSet.next()) {
                list.add(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
       public static Integer find_idroom(String ID_artwork) {
    Integer result = null;

    PreparedStatement statement;
    ResultSet resultSet;
    try {
       
        String sql = "SELECT id_room FROM room WHERE name_room like  ? ";
         statement = conn.prepareStatement(sql);
         statement.setString(1, ID_artwork);
//        resultSet = statement.executeQuery("SELECT id_room FROM room WHERE name_room like " + ID_artwork);
            resultSet = statement.executeQuery();
        if (resultSet.next()) {
            result = resultSet.getInt(1);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
    }

    return result;
}
    
      public static String find_artwork(int ID_artwork) {
    String result = null;

    Statement statement;
    ResultSet resultSet;
    try {
        statement = conn.createStatement();
        resultSet = statement.executeQuery("SELECT image_art FROM artwork WHERE id_artist = " + ID_artwork);

        if (resultSet.next()) {
            result = resultSet.getString("image_art");
        }
    } catch (SQLException ex) {
        Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
    }

    return result;
}
      
      public static ArrayList<Artwork> search(String s) {

        ArrayList<Artwork> list = new ArrayList<>();

        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM artwork where artwork_name like '%"+s+"%' or artist_name like '%"+s+"%'");
            

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

            }
        } catch (SQLException ex) {
            System.err.println("Err search artwork");
        }

        return list;

    }
    
}
