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
    
    
    public static void insertevent(String event_name, LocalDate start_date,LocalDate end_date, int nb_participants, int start_time, String desc) {
        Connection connection;
        PreparedStatement statement;
        try {
            connection = Conn.getCon();
            statement = connection.prepareStatement("INSERT INTO event (event_name, start_date, end_date, nb_participants, start_time, detail) VALUES (?,?,?,?,?,?)");
            statement.setString(1, event_name);
            statement.setDate(2, Date.valueOf(start_date));
            statement.setDate(3, Date.valueOf(end_date));
            statement.setInt(4, nb_participants);
            statement.setInt(5, start_time);
            statement.setString(6, desc);
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
                Event data = new Event(result.getInt("id"),result.getDate("start_date"),result.getDate("end_date"),result.getInt("start_time"),result.getString("event_name"),result.getString("detail"),result.getInt("nb_participants"));
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
   

}
