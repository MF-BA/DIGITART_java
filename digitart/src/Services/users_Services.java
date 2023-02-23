/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entity.users;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.main;
import utils.Conn;

/**
 *
 * @author Islem
 */
public class users_Services {
    
    static Connection conn = Conn.getCon();
    PreparedStatement pst;

    public users_Services() {
    }
    
    public void adminadduser(users u){
       
            String sql = "insert into users (cin,firstname,lastname,email,password,address,phone_num,birth_date,gender,role) values (? ,?, ?, ?, ?, ?, ?, ?, ?, ?)";
             try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,u.getCin());
            pst.setString(2,u.getFirstname());
            pst.setString(3,u.getLastname());
            pst.setString(4,u.getEmail());
            pst.setString(5,u.getPwd());
            pst.setString(6,u.getAddress());
            pst.setInt(7,u.getPhone_number());
            pst.setDate(8,Date.valueOf(u.getBirth_date()));
            pst.setString(9,u.getGender());
            pst.setString(10,u.getRole());
            
             pst.executeUpdate();
            System.out.println("success!!");
                
            } catch (SQLException ex) {
                System.err.println("error!!");
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
     public void adduser(users u){
       
            String sql = "insert into users (cin,firstname,lastname,email,password,address,phone_num,birth_date,gender) values (? ,?, ?, ?, ?, ?, ?, ?, ?)";
             try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,u.getCin());
            pst.setString(2,u.getFirstname());
            pst.setString(3,u.getLastname());
            pst.setString(4,u.getEmail());
            pst.setString(5,u.getPwd());
            pst.setString(6,u.getAddress());
            pst.setInt(7,u.getPhone_number());
            pst.setDate(8,Date.valueOf(u.getBirth_date()));
            pst.setString(9,u.getGender());
           
            
             pst.executeUpdate();
            System.out.println("success!!");
                
            } catch (SQLException ex) {
                System.err.println("error!!");
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
     public static ArrayList<users> Displayusers() {

        ArrayList<users> list = new ArrayList<>();

        
        ResultSet res;
        try {
            Statement statement= conn.createStatement();
            res= statement.executeQuery("SELECT * FROM users");

            while (res.next()) {
                LocalDate D = res.getDate(9).toLocalDate();
                users data = new users(
                        res.getInt(1),
                        res.getInt(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        res.getString(7),
                        res.getInt(8),
                           D,
                        res.getString(10),
                        res.getString(11)
                        
                );
                list.add(data);
            }
            
        } catch (SQLException ex) {
          Logger.getLogger(users_Services.class.getName()).log(Level.SEVERE, null, ex);
            
        }

        return list;

    }
     
      public static void deleteuser(int id) {

        Statement statement;

        try {
            statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM users WHERE id =" + id);

        } catch (SQLException ex) {
            Logger.getLogger(users_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void modifyuser(users u){
       
            String sql = "update users set cin = ?, firstname = ?, lastname = ?, email = ?, password = ?, address=?, phone_num=?, birth_date=?, gender=?, role=? where id = ?";
             try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,u.getCin());
            pst.setString(2,u.getFirstname());
            pst.setString(3,u.getLastname());
            pst.setString(4,u.getEmail());
            pst.setString(5,u.getPwd());
            pst.setString(6,u.getAddress());
            pst.setInt(7,u.getPhone_number());
            pst.setDate(8,Date.valueOf(u.getBirth_date()));
            pst.setString(9,u.getGender());
            pst.setString(10,u.getRole());
            pst.setInt(11,u.getId());
            
             pst.executeUpdate();
            System.out.println("success!!");
                
            } catch (SQLException ex) {
                System.err.println("error!!");
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
