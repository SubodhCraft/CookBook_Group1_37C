package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level; 

import Database.MySqlConnection;
import Model.Model;

public class UserDAO {
    MySqlConnection mysql = new MySqlConnection();
    Connection conn = mysql.openConnection();

    public void signUp(Model user){
        String sql = "Insert into users(username,email,password) values (?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){

        }catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
