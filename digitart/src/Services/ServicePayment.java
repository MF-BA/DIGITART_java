package Services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entity.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utils.Conn;

/**
 *
 * @author User
 */
public class ServicePayment {

    public static ArrayList<Payment> displayPayment() {
        ArrayList<Payment> paymentList = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        try {
            connection = Conn.getCon();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * From payment");

            while (resultSet.next()) {
                Payment payment = new Payment(
                        resultSet.getInt("payment_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getDate("purchase_date").toLocalDate(),
                        resultSet.getInt("nb_adult"),
                        resultSet.getInt("nb_teenager"),
                        resultSet.getInt("nb_student"),
                        resultSet.getInt("total_payment")
                );
                paymentList.add(payment);
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving payments: " + ex.getMessage());
        }

        return paymentList;
    }

    public static boolean deletePayment(int paymentId) {
        Connection connection;
        PreparedStatement preparedStatement;
        boolean success = false;

        try {
            connection = Conn.getCon();
            preparedStatement = connection.prepareStatement("DELETE FROM payment WHERE payment_id=?");
            preparedStatement.setInt(1, paymentId);
            int rowsDeleted = preparedStatement.executeUpdate();
            success = (rowsDeleted > 0);
        } catch (SQLException ex) {
            System.out.println("Error while deleting payment: " + ex.getMessage());
        }

        return success;
    }
    
    

}
