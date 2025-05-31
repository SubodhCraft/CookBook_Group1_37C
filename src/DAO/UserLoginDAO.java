/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import Model.LoginRequest;
import Model.UserSignup;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */


public class UserLoginDAO {
    MySqlConnection mysql = new MySqlConnection();
    
    public UserSignup login(LoginRequest login){
        Connection conn= mysql.openConnection();
        String sql = "SELECT * FROM Users where email = ? and password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,login.getEmail());
            pstmt.setString(2,login.getPassword());
            ResultSet result = pstmt.executeQuery();
            if(result.next()){
                UserSignup user = new UserSignup(
                        result.getString("email"),
                        result.getString("username"),
                        result.getString("password")
                );
                user.setId(result.getInt("Id"));
                
                return user;
                
            }
        
        }catch (SQLException ex){
            System.out.println(ex);
        }finally {
            mysql.closeConnection(conn);
        }
        return null;
    }
    
    public boolean checkUser(UserSignup user){
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM Users where email =? or username= ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,user.getEmail());
            pstmt.setString(2,user.getUsername());
            ResultSet result = pstmt.executeQuery();
            return result.next();
        
        }catch (SQLException ex){
            Logger.getLogger(UserLoginDAO.class.getName()).log(Level.SEVERE,null,ex);
        }finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    public void signup(UserSignup user){
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO Users (Username,email,password) VALUES (?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getEmail());
            pstmt.setString(3,user.getpassword());
            pstmt.executeUpdate();
        
        }catch (SQLException ex){
            Logger.getLogger(UserLoginDAO.class.getName()).log(Level.SEVERE,null,ex);
        }finally {
            mysql.closeConnection(conn);
        }
    }
    
    public ArrayList<UserSignup> getAllUserSignup(){
       Connection conn = mysql.openConnection();
       ArrayList<UserSignup> data = new ArrayList();
       String sql = "SELECT * FROM Users";
       try (PreparedStatement pstmt = conn.prepareStatement(sql)){
           ResultSet result = pstmt.executeQuery();
           while(result.next()){
               UserSignup user = new UserSignup(
                       result.getString("email"),
                       result.getString("username"),
                       result.getString("password")
               );
               user.setId(result.getInt("id"));
               
               data.add(user);
           }
           
       }catch (SQLException ex){
           Logger.getLogger(UserLoginDAO.class.getName().log(Level.SEVERE,null,ex));
       }finally {
           mysql.closeConnection(conn);
       }
       return data;
       
    }
    
    public boolean update(UserSignup user){
        Connection conn= mysql.openConnection();
        
        String sql = "UPDATE Users SET username = ?, email =? where id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getEmail());
            pstmt.setString(3,user.getId());
            pstmt.executeUpdate();
            return true;
        
        }catch (SQLException ex){
            System.out.println(ex);
            Logger.getLogger(UserLoginDAO.class.getName()).log(Level.SEVERE,null,ex);
        }finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    public boolean delete(UserSignup user){
        Connection conn= mysql.openConnection();
        
        String sql = "DELETE from Users where id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1,user.getId());
            pstmt.executeUpdate();
            return true;
        
        }catch (SQLException ex){
            System.out.println(ex);
            Logger.getLogger(UserLoginDAO.class.getName()).log(Level.SEVERE,null,ex);
        }finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    
}
