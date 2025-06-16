/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.MySqlConnection;
import Model.LoginRequest;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
