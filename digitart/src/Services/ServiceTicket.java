package Services;
import entity.Ticket;
import utils.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceTicket {
    
    public static void addTicket(Ticket ticket) {
        Connection connection;
        PreparedStatement statement;
        try {
                connection = Conn.getCon();
            statement = connection.prepareStatement("INSERT INTO ticket (ticket_id, ticket_date, ticket_type, price, ticket_nb, ticket_desc) VALUES (?,?,?,?,?,?)");
            statement.setInt(1, ticket.getTicket_id());
            statement.setDate(2, (Date) ticket.getTicket_date());
            statement.setString(3, ticket.getTicket_type());
            statement.setInt(4, ticket.getPrice());
            statement.setInt(5, ticket.getTicket_nb());
            statement.setString(6, ticket.getTicket_desc());
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in add!!", e);
        } 
    }

    public static ArrayList<Ticket> displayTicket() {

        ArrayList<Ticket> list = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = Conn.getCon();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM ticket");
            
            while (resultSet.next()) {
                Ticket data = new Ticket(resultSet.getInt(1),resultSet.getDate(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getString(6));
                list.add(data);
            }
        } catch (SQLException e) {
           Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in display!!", e);
        }

        return list;
    }
 
    public  static void deleteTicket(int id) {
        Connection connection;
        Statement statement;
        try {
         connection = Conn.getCon();
         statement = connection.createStatement();
         statement.executeUpdate("DELETE FROM ticket WHERE ticket_id =" +id);
        }catch (SQLException e) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in delete!!", e);
        }
    }
     
    
    public static void updateTicket(Ticket t) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = Conn.getCon();
            preparedStatement = connection.prepareStatement("UPDATE ticket SET ticket_date = ?, ticket_type = ?, price = ?, ticket_nb = ?, ticket_desc = ? WHERE ticket_id = ?");
           
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ticketDate = dateFormat.format(t.getTicket_date());
            
            preparedStatement.setString(1, ticketDate);
            preparedStatement.setString(2, t.getTicket_type());
            preparedStatement.setInt(3, t.getPrice());
            preparedStatement.setInt(4, t.getTicket_nb());
            preparedStatement.setString(5, t.getTicket_desc());
            preparedStatement.setInt(6, t.getTicket_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in update!!", e);
        }
    }

}
