/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import controller.Add_eventController;
import entity.Event;
import entity.Ticket;
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

/**
 *
 * @author kossay
 */
public class Event_Services {
    
    
    public static void insertevent(String event_name, LocalDate start_date,LocalDate end_date, int nb_participants, int start_time, String desc,int id_room) {
        Connection connection;
        PreparedStatement statement;
        try {
            connection = Conn.getCon();
            statement = connection.prepareStatement("INSERT INTO event (event_name, start_date, end_date, nb_participants, start_time, detail,id_room) VALUES (?,?,?,?,?,?,?)");
            statement.setString(1, event_name);
            statement.setDate(2, Date.valueOf(start_date));
            statement.setDate(3, Date.valueOf(end_date));
            statement.setInt(4, nb_participants);
            statement.setInt(5, start_time);
            statement.setString(6, desc);
            statement.setInt(7, id_room);
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
        
          public static void updateEvent(Event ev) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = Conn.getCon();
            preparedStatement = connection.prepareStatement("UPDATE event SET event_name = ?, start_date = ?, end_date = ?, nb_participants = ?, detail = ?, start_time = ?,id_room=? WHERE id = ?");

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
            preparedStatement.setInt(8,ev.getEvent_id());
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
}
