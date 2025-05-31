package DAO;

import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Logger;
import java.util.logging.Level; 
import Model.UserSignup;

public class UserDAO {
    MySqlConnection mysql = new MySqlConnection();
    
    public boolean Sign(UserSignup user){
       Connection conn = mysql.openConnection();
       String sql = "INSERT INTO users ( username, email, set_password, confirm_password) VALUES ( ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getSet_password());
            pstmt.setString(4, user.getConfirm_password());
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        
    }
        return false;
    }
    

    public boolean checkUser(UserSignup user){
        Connection conn = mysql.openConnection();

        String sql = "SELECT * FROM users where email = ? or username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getUsername());
            ResultSet result = pstmt.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    
    public boolean isEmailExists(String email){
        Connection conn = mysql.openConnection();
        String sql = "SELECT 1 FROM users where email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,email);
            ResultSet result = pstmt.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
//    public static void main(String[] args){
//        UserDAO dao = new UserDAO();
//        UserSignup testUser = new UserSignup();
//        testUser.setEmail("cookbook@gmail.com");
//        testUser.setUsername("Recipe Finder");
//        
//        boolean exists = dao.checkUser(testUser);
//        System.out.println("User exists?" + exists);
//    }
    
    
}
