/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitart;

import java.sql.*;

/**
 *
 * @author mohamed
 */
public class Connec {

    static Connec A;

    String URL = "jdbc:mysql://localhost/digitart";
    String user = "root";
    String pwd = "";
    static public Connection conn;

    private Connec() {

        try {
            conn = DriverManager.getConnection(URL, user, pwd);
            System.out.println("Connection establised !!! ");
        } catch (SQLException ex) {
            System.err.println("Connection failed !!! ");
        }
    }

    public static Connection getConnec() {
        if (A == null) {
            A = new Connec();
        }
        return conn;
    }

}
