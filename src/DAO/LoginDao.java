/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.MySqlConnection;
import Model.LoggedInUser;
import Model.LoginRequest;
import Model.UserData;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.*;
/**
 *
 * @author LEGION
 */
public class LoginDao {
    MySqlConnection mysql = new MySqlConnection();
    public boolean validateUser(LoginRequest userLogin ){
     Connection conn = mysql.openConnection();
     String sql = "SELECT * FROM users where Email = ? and set_password = ?";
     try (PreparedStatement pstmt = conn.prepareStatement(sql)){
//        System.out.println("Login email"+ userLogin.getEmail());
//        System.out.println("Login Password"+ userLogin.getPassword());
      
        pstmt.setString(1,userLogin.getEmail());
        pstmt.setString(2,userLogin.getPassword());
        ResultSet result = pstmt.executeQuery();
        return result.next();
     }catch (Exception ex){
         Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
     }finally{
         mysql.closeConnection(conn);
     }
     return false;
    }

    
    
    
    
     public UserData getUserByEmailAndPassword(String email, String password) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users WHERE Email = ? AND set_password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
//                LoggedInUser.setId(retrivedUserId);
                String username = rs.getString("username");
                return new UserData(id, username, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }
     
     public UserData validateAndFetchUser(LoginRequest user){
       Connection conn =mysql.openConnection();
       String sql = "SELECT * FROM users WHERE email=? AND set_password=?";
    UserData userData = null;
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
       
        stmt.setString(1, user.getEmail());
        stmt.setString(2, user.getPassword());
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            userData = new UserData();
            userData.setId(rs.getInt("Id")); 
            userData.setEmail(rs.getString("Email"));
            userData.setUsername(rs.getString("username"));
          
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
        mysql.closeConnection(conn);
    }
    return userData;
}

}

