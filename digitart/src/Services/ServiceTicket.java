package Services;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
import entity.Ticket;
import utils.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceTicket {

    public static void addTicket(LocalDate ticketDate, LocalDate ticketEDate, int price, String ticketType) {
        Connection connection;
        PreparedStatement statement;
        try {
            connection = Conn.getCon();
            statement = connection.prepareStatement("INSERT INTO ticket (ticket_date, ticket_edate, price, ticket_type) VALUES (?,?,?,?)");
            statement.setDate(1, java.sql.Date.valueOf(ticketDate));
            statement.setDate(2, java.sql.Date.valueOf(ticketEDate));
            statement.setInt(3, price);
            statement.setString(4, ticketType);
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
            resultSet = statement.executeQuery("SELECT ticket_id, ticket_date, ticket_edate, price, ticket_type FROM ticket");

            while (resultSet.next()) {
                Ticket data = new Ticket(resultSet.getInt("ticket_id"), resultSet.getDate("ticket_date"), resultSet.getDate("ticket_edate"), resultSet.getInt("price"), resultSet.getString("ticket_type"));
                list.add(data);
            }
        } catch (SQLException e) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in display!!", e);
        }

        return list;
    }

    public static void deleteTicket(int id) {
        Connection connection = Conn.getCon();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM ticket WHERE ticket_id =" + id);
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

    public static void updateTicket(Ticket t) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = Conn.getCon();
            preparedStatement = connection.prepareStatement("UPDATE ticket SET ticket_date = ?, ticket_edate= ?,  price = ?, ticket_type = ? WHERE ticket_id = ?");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ticketDate = dateFormat.format(t.getTicket_date());
            String ticketeDate = dateFormat.format(t.getTicket_edate());
            preparedStatement.setString(1, ticketDate);
            preparedStatement.setString(2, ticketeDate);
            preparedStatement.setInt(3, t.getPrice());
            preparedStatement.setString(4, t.getTicket_type());
            preparedStatement.setInt(5, t.getTicket_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ServiceTicket.class.getName()).log(Level.SEVERE, "error in update!!", e);
        }
    }

    
}
