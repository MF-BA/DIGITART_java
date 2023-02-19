/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.*;

/**
 *
 * @author fedi1
 */
public class Conn {

    static Conn A;

    String URL = "jdbc:mysql://localhost/digitart?zeroDateTimeBehavior=convertToNull";
    String user = "root";
    String pwd = "";

    static public Connection conn;

    private Conn() {

        try {
            conn = DriverManager.getConnection(URL, user, pwd);
            System.out.println("Connection establised !!! ");
        } catch (SQLException ex) {
            System.err.println("Connection failed !!! ");
        }
    }

    public static Connection getCon() {
        if (A == null) {
            A = new Conn();
        }
        return conn;
    }

}
