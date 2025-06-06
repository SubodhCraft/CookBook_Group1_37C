 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.UserData;
//import Model.LoginRequest;
/**
 *
 * @author LEGION
 */
public class UserDao {
    MySqlConnection mysql = new MySqlConnection();

    // SIGNUP method returns boolean now
    public void UserDao(UserData user) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO users (username, email, set_password, confirm_password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getset_Password());
            pstmt.setString(4, user.getconfirm_Password());

            pstmt.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
//        return false;
         
    }

    // LOGIN/VALIDATION method
//        public boolean Logincredentials(String email, String password) {
//            Connection conn = mysql.openConnection();
//            try {
//                String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
//                PreparedStatement ps = conn.prepareStatement(sql);
//                ps.setString(1, email);
//                ps.setString(2, password);
//
//                ResultSet rs = ps.executeQuery();
//                return rs.next(); // returns true if a record is found
//            } catch (Exception e) {
//                System.out.println("Sign in error: " + e);
//                return false;
//            } finally {
//                mysql.closeConnection(conn);
//            }
//        }
        

    
    
    
    
    
    
    
    
    
    
    
    // Check if email already exists
//    public boolean isEmailExists(String email) {
//        Connection conn = mysql.openConnection();
//        String sql = "SELECT 1 FROM users WHERE email = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, email);
//            ResultSet result = pstmt.executeQuery();
//            return result.next();  // returns true if email is found
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            mysql.closeConnection(conn);
//        }
//        return false;
//    }
//    
//    public UserData signIn(LoginRequest login){
//        Connection conn = mysql.openConnection();
//        String sql = "SELECT * FROM users where Email = ? and set_password = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, login.getEmail());
//            pstmt.setString(2, login.getPassword());
//            ResultSet result = pstmt.executeQuery();
//            if(result.next()){
//                UserData user  = new UserData(
//                    result.getString("username"),
//                    result.getString("email"),
//                    result.getString("set_ password"),
//                    result.getString("confirm_password")
//                );
//                user.setId(result.getInt("id"));
//                
//                return user;
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        } finally {
//            mysql.closeConnection(conn);
//        }
//        return null;
//    }
    
    public boolean CheckUser(UserData user){
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users WHERE email = ? AND set_password = ?";
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, user.getEmail());
            pstm.setString(2, user.getset_Password());
            ResultSet result = pstm.executeQuery();
            return result.next();
        }catch(Exception e){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
    }

