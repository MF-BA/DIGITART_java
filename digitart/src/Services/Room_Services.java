/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import utils.Conn;
import java.sql.*;
import java.util.ArrayList;
import entity.Room;
/**
 *
 * @author mohamed
 */
public class Room_Services {
    static Connection conn = Conn.getCon();

    public static void add(Room room) {

        String add_to_room = "insert into room (name_room,area,state,description) values (?,?,?,?) ";
        try {
            PreparedStatement st = conn.prepareStatement(add_to_room);
           
            st.setString(1, room.getName_room());
            st.setInt(2, room.getArea());
            st.setString(3, room.getState());
            st.setString(4, room.getDescription());

            st.executeUpdate();
            System.out.println("success!! add room");
        } catch (SQLException ex) {
            System.err.println("Err Add room");
        }
    }
    
    
     public static ArrayList<Room> Display() {

        ArrayList<Room> list = new ArrayList<>();

        Statement statement;
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM room");
            

            while (resultSet.next()) {
              
                Room data = new Room(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                list.add(data);
            }
            System.out.println("success!! display room");
        } catch (SQLException ex) {
            System.err.println("Err Display room");
        }

        return list;

    }

    public static void delete(int ID) {

        Statement statement;

        try {
            statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM room WHERE id_room =" + ID);
            System.out.println("success!! delete room");
        } catch (SQLException ex) {
            System.err.println("Err delete room");
        }

    }
    
    
    
    
     public static void modify(Room room) {

        String Modify_room = "update room set name_room = ? ,area=?,state=? ,description=? where id_room=?  ";
        try {
            PreparedStatement st = conn.prepareStatement(Modify_room);
           
            st.setString(1, room.getName_room());
            st.setInt(2, room.getArea());
            st.setString(3, room.getState());
            st.setString(4, room.getDescription());
             st.setInt(5, room.getId_room());

            st.executeUpdate();
            System.out.println("success!! modify room");
        } catch (SQLException ex) {
            System.err.println("Err Modify room");
        }
    }
}
