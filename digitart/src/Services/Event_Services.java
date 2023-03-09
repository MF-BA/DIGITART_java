/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import controller.Add_eventController;
import entity.Data;
import static entity.Data.event;
import entity.Event;
import entity.Participants;
import entity.Ticket;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Conn;
import static utils.Conn.conn;

/**
 *
 * @author kossay
 */
public class Event_Services {
    
    
    public static void insertevent(String event_name, LocalDate start_date,LocalDate end_date, int nb_participants, int start_time, String desc,int id_room,String image) {
        Connection connection;
        PreparedStatement statement;
        try {
            connection = Conn.getCon();
            statement = connection.prepareStatement("INSERT INTO event (event_name, start_date, end_date, nb_participants, start_time, detail,id_room,image) VALUES (?,?,?,?,?,?,?,?)");
            statement.setString(1, event_name);
            statement.setDate(2, Date.valueOf(start_date));
            statement.setDate(3, Date.valueOf(end_date));
            statement.setInt(4, nb_participants);
            statement.setInt(5, start_time);
            statement.setString(6, desc);
            statement.setInt(7, id_room);
            statement.setString(8, image);
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in add!!", e);
        }
    }
    
    public static void insertuser(int id_user,String first_name,String last_name,String adress,String gender,int id_event) {
        Connection connection;
        PreparedStatement statement;
        
        
        
        
        
        try {
            connection = Conn.getCon();
            statement = connection.prepareStatement("INSERT INTO participants (id_user,id_event, first_name,last_name, adress, gender) VALUES (?,?,?,?,?,?)");
            statement.setInt(1, id_user);
            statement.setInt(2, id_event);
            statement.setString(3, first_name);
            statement.setString(4,last_name);
            statement.setString(5,adress);
            statement.setString(6, gender);

            statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in add!!", e);
        }
    }
    
       public static ArrayList<Event> displayEvent() {

        ArrayList<Event> list = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet result;
        try {
            connection = Conn.getCon();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM event");

            while (result.next()) {
                Event data = new Event(result.getInt("id"),result.getDate("start_date"),result.getDate("end_date"),result.getInt("start_time"),result.getString("event_name"),result.getString("detail"),result.getInt("nb_participants"),result.getInt("id_room"));
                list.add(data);
            }
        } catch (SQLException e) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in display!!", e);
        }

        return list;
    }
       
        public static ArrayList<Event> displayEventFront() {

        ArrayList<Event> list = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet result;
        try {
            connection = Conn.getCon();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM event where start_date > current_date");

            while (result.next()) {
                
               
                Event data = new Event(result.getInt("id"),result.getDate("start_date"),result.getDate("end_date"),result.getInt("start_time"),result.getString("event_name"),result.getString("detail"),result.getInt("nb_participants"),result.getInt("id_room"),result.getString("image"));
                list.add(data);
            }
        } catch (SQLException e) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in display!!", e);
        }

        return list;
    }
        
        public static ArrayList<Event> displayEventPart() {

    ArrayList<Event> list = new ArrayList<>();
    Connection connection;
    PreparedStatement statement;
    ResultSet result;
    int id_user_part=Data.user.getId();
    try {
        connection = Conn.getCon();
        statement = connection.prepareStatement("SELECT * FROM event INNER JOIN participants ON event.id = participants.id_event");

        result = statement.executeQuery();

        while (result.next()) {
            Event data = new Event(result.getInt("id"),result.getDate("start_date"),result.getDate("end_date"),result.getInt("start_time"),result.getString("event_name"),result.getString("detail"),result.getInt("nb_participants"),result.getInt("id_room"));
            list.add(data);
        }
    } catch (SQLException e) {
        Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in display!!", e);
    }

    return list;
}

         public static ArrayList<Event> displayEvents(int id) {

        ArrayList<Event> list = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet result;
        try {
            connection = Conn.getCon();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM event where event_id=id");

            while (result.next()) {
                Event data = new Event(result.getInt("id"),result.getDate("start_date"),result.getDate("end_date"),result.getInt("start_time"),result.getString("event_name"),result.getString("detail"),result.getInt("nb_participants"),result.getInt("id_room"));
                list.add(data);
            }
        } catch (SQLException e) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in display!!", e);
        }

        return list;
    }
       
        public static ArrayList<Participants> displayPart() {

        ArrayList<Participants> list = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet result;
        try {
            connection = Conn.getCon();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM participants");

            while (result.next()) {
                Participants data = new Participants(result.getString("first_name"),result.getString("last_name"),result.getInt("id_user"),result.getInt("id_event"),result.getString("adress"),result.getString("gender"));
                list.add(data);
            }
        } catch (SQLException e) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in display!!", e);
        }

        return list;
    }
       
        public static void deleteEvent(int id) {
        Connection connection = Conn.getCon();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM event WHERE id =" + id);
        } catch (SQLException e) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in delete!!", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
        
          public static void deletePart(int id) {
        Connection connection = Conn.getCon();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM participants WHERE id_event =" + id);
        } catch (SQLException e) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in delete!!", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
        
          public static void updateEvent(Event ev) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = Conn.getCon();
            preparedStatement = connection.prepareStatement("UPDATE event SET event_name = ?, start_date = ?, end_date = ?, nb_participants = ?, detail = ?, start_time = ?,id_room=?,image=? WHERE id = ?");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String sdate = dateFormat.format(ev.getStart_date());
            String edate = dateFormat.format(ev.getEnd_date());

            preparedStatement.setString(1, ev.getEvent_name());
            preparedStatement.setString(2,sdate);
            preparedStatement.setString(3, edate);
            preparedStatement.setInt(4,ev.getNb_participants());
            preparedStatement.setString(5, ev.getDetail());
            preparedStatement.setInt(6,ev.getStart_time() );
            preparedStatement.setInt(7,ev.getId_room() );
            preparedStatement.setString(8,ev.getImage());
            preparedStatement.setInt(9,ev.getEvent_id() );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in update!!", e);
        }
    }
   
 public static ArrayList<Integer> find_idroom() {
        ArrayList<Integer> list = new ArrayList<>();
        Connection connection = Conn.getCon();
        Statement statement;
        ResultSet resultSet;
        try {
              statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT id_room FROM room");

            while (resultSet.next()) {
                list.add(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
 
  public static ArrayList<Integer> find_idevent() {
        ArrayList<Integer> list = new ArrayList<>();
        Connection connection = Conn.getCon();
        Statement statement;
        ResultSet resultSet;
        try {
              statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT id FROM event");

            while (resultSet.next()) {
                list.add(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auction_Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
//  public Event getuserdata(String email, String pwd) throws NoSuchAlgorithmException {
//    Event data = null;
//    ResultSet res = null;
//    String sql = "SELECT * FROM event WHERE email=? AND password=?";
//    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//        stmt.setString(1, email);
//        stmt.setString(2, hashPassword(pwd));
//        res = stmt.executeQuery();
//        if (res.next()) {
//            LocalDate D = res.getDate(9).toLocalDate();
//            data = new users(
//                res.getInt(1),
//                res.getInt(2),
//                res.getString(3),
//                res.getString(4),
//                res.getString(5),
//                res.getString(6),
//                res.getString(7),
//                res.getInt(8),
//                D,
//                res.getString(10),
//                res.getString(11),
//                res.getString(12)
//            );
//        }
//    } catch (SQLException ex) {
//        Logger.getLogger(users_Services.class.getName()).log(Level.SEVERE, null, ex);
//    } finally {
//        try {
//            if (res != null) {
//                res.close();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(users_Services.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    return data;
//}
}
