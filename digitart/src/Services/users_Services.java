/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entity.users;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    
    public void adduser(users u){
       
            String sql = "insert into users (cin,firstname,lastname,email,password,address,phone_num,birth_date,gender,role,status, image) values (? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            pst.setString(11,"unblocked");
            pst.setString(12,"");
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
                        res.getString(11),
                        res.getString(12)
                        
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
       
            String sql = "update users set cin = ?, firstname = ?, lastname = ?, email = ?, password = ?, address=?, phone_num=?, birth_date=?, gender=?, role=?, status=?, image=?, secretcode=? where id = ?";
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
            pst.setString(11,u.getStatus());
            pst.setString(12,u.getImage());
            pst.setString(13,u.getSecretcode());
            pst.setInt(14,u.getId());
            
             pst.executeUpdate();
            System.out.println("success!!");
                
            } catch (SQLException ex) {
                System.err.println("error!!");
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
     public void modifyuserimage(users u, String path){
       
            String sql = "update users set cin = ?, firstname = ?, lastname = ?, email = ?, password = ?, address=?, phone_num=?, birth_date=?, gender=?, role=?, image= ? where id = ?";
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
            pst.setString(11,path);
            pst.setInt(12,u.getId());
            
             pst.executeUpdate();
            System.out.println("success!!");
                
            } catch (SQLException ex) {
                System.err.println("error!!");
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
    public void modifyuserImage(int id , String image){
       
            String sql = "update users set image= ? where id = ?";
             try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,image);
            pst.setInt(2,id);
            
             pst.executeUpdate();
            System.out.println("success!!");
                
            } catch (SQLException ex) {
                System.err.println("error!!");
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
     public void modifyusergoogle(users u){
       
            String sql = "update users set cin = ?, firstname = ?, lastname = ?, password = ?, address=?, phone_num=?, birth_date=?, gender=?, role=?, status=?, image=? where email = ?";
             try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,u.getCin());
            pst.setString(2,u.getFirstname());
            pst.setString(3,u.getLastname());
            pst.setString(4,u.getPwd());
            pst.setString(5,u.getAddress());
            pst.setInt(6,u.getPhone_number());
            pst.setDate(7,Date.valueOf(u.getBirth_date()));
            pst.setString(8,u.getGender());
            pst.setString(9,u.getRole());
            pst.setString(10,"unblocked");
            pst.setString(11,"");
            pst.setString(12,u.getEmail());
            
             pst.executeUpdate();
            System.out.println("success!!");
                
            } catch (SQLException ex) {
                System.err.println("error!!");
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
     public static void blockuser(int id){
       
             Statement statement;
             try {
            statement = conn.createStatement();
            statement.executeUpdate("update users set status='blocked' WHERE id =" + id);
            System.out.println("success!!");
                
            } catch (SQLException ex) {
                System.err.println("error!!");
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
    
    public users getuserdata(String email, String pwd) throws NoSuchAlgorithmException {
    users data = null;
    ResultSet res = null;
    String sql = "SELECT * FROM users WHERE email=? AND password=?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, email);
        stmt.setString(2, hashPassword(pwd));
        res = stmt.executeQuery();
        if (res.next()) {
            LocalDate D = res.getDate(9).toLocalDate();
            data = new users(
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
                res.getString(11),
                res.getString(12),
                res.getString(13),
                res.getString(14)
            );
        }
    } catch (SQLException ex) {
        Logger.getLogger(users_Services.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (res != null) {
                res.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(users_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return data;
}

    
   public users getgoogleuserdata(String email) throws NoSuchAlgorithmException {
    users data = null;
    ResultSet res = null;
    String sql = "SELECT * FROM users WHERE email=?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, email);
        
        res = stmt.executeQuery();
        if (res.next()) {
            
            data = new users(
                res.getInt(1),
                res.getInt(2),
                res.getString(3),
                res.getString(4),
                res.getString(5),
                res.getString(6),
                res.getString(7),
                res.getInt(8),
                //res.getDate(9).toLocalDate(),
                res.getString(10),
                res.getString(11),
                res.getString(12)
            );
        }
    } catch (SQLException ex) {
        Logger.getLogger(users_Services.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (res != null) {
                res.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(users_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return data;
} 
    
   public static void unblockuser(int id){
       
             Statement statement;
             try {
            statement = conn.createStatement();
            statement.executeUpdate("update users set status='unblocked' WHERE id =" + id);
            System.out.println("success!!");
                
            } catch (SQLException ex) {
                System.err.println("error!!");
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
    
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    } 
    
    
   public List<users> getUsersMatchingSearchQuery(String searchQuery) {
    
       List<users> matchingUsers = new ArrayList<>();
    
    try {
        boolean isInteger = false;
        try {
            int searchInt = Integer.parseInt(searchQuery);
            isInteger = true;
        } catch (NumberFormatException e) {
            // Ignore the exception and continue with the search
        }
        PreparedStatement stmt;
        if (isInteger){
           
        stmt = conn.prepareStatement("SELECT * FROM users WHERE phone_num = ? or cin = ?");
        int searchInt = Integer.parseInt(searchQuery);
        
        stmt.setInt(1, searchInt);
        stmt.setInt(2, searchInt);
        
        }
        else
        {
         stmt = conn.prepareStatement("SELECT * FROM users WHERE lower(firstname) LIKE ? or lower(lastname) LIKE ? or lower(email) like ? or lower(address) like ? or lower(gender) like ? or lower(role) like ?");
            String wildcardQuery = "%" + searchQuery + "%";
        stmt.setString(1, wildcardQuery);
        stmt.setString(2, wildcardQuery);
        stmt.setString(3, wildcardQuery);
        stmt.setString(4, wildcardQuery);
        stmt.setString(5, wildcardQuery);
        stmt.setString(6, wildcardQuery);
        }
        // Execute the prepared statement and retrieve the result set
         ResultSet res = stmt.executeQuery();

        // Iterate over the result set and create User objects for each row
        while (res.next()) {
            int cin = res.getInt(2);
            String fname = res.getString(3);
            String lname = res.getString(4);
            String email = res.getString(5);
            String password = res.getString(6);
            String address = res.getString(7);          
            int phone_num = res.getInt(8);
            LocalDate birth_d = res.getDate(9).toLocalDate();
            String gender = res.getString(10);
            String role = res.getString(11);
            String Status = res.getString(12);

            users user = new users(cin, fname, lname, email, password, address, phone_num, birth_d, gender, role, Status);
            matchingUsers.add(user);
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return matchingUsers;
}
    
}
