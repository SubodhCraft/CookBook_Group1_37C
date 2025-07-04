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
        String sql = "INSERT INTO users (username, Email, set_password, confirm_password, profile_picture_url, role) VALUES (?, ?, ?, ?, ?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getset_Password());
            pstmt.setString(4, user.getconfirm_Password());
            pstmt.setString(5,"View.Images/pp.jpg");
            pstmt.setString(6, user.getRole());

            pstmt.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
//        return false;
         
    }
 
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
    
    public boolean deleteAccount(String email){
        Connection conn = mysql.openConnection();
        if (email == null)return false;
        String query = "DELETE FROM users WHERE email = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, email);
            return pstmt.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public UserData getUserById(int userId){
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users WHERE id = ?";
        
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()){
                UserData user = new UserData(
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("set_password"),
                        rs.getString("confirm_password")
                );
                user.setId(rs.getInt("id"));
                user.setProfilePictureUrl(rs.getString("profile_picture_url"));
                return user;
            }
        }catch(SQLException e){
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }finally {
            mysql.closeConnection(conn);
        }
        return null;
    }
    
    public boolean updateProfilePictureUrl(int userId, String pictureUrl) {
    Connection conn = mysql.openConnection();
    String sql = "UPDATE users SET profile_picture_url = ? WHERE id = ?";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, pictureUrl);
        pstmt.setInt(2, userId);
        return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
    } finally {
        mysql.closeConnection(conn);
    }
    return false;
}

}

