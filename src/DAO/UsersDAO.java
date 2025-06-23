package DAO;

import Database.Database;
import Database.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersDAO {
    private Database db;
    private Connection conn;

    public UsersDAO() {
        try {
            db = new MySqlConnection();          // use your implemented connection class
            conn = db.openConnection();          // open connection
        } catch (Exception e) {
            System.out.println("DAO Connection Error: " + e);
        }
    }

    // ✅ Fetch reward points for a given username
    public int getRewardPoints(String username) {
        int points = 0;
        String sql = "SELECT reward_points FROM users WHERE username = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                points = rs.getInt("reward_points");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Get Reward Error: " + e);
        }
        return points;
    }

    // ✅ Update reward points for a user
    public void updateRewardPoints(String username, int newPoints) {
        String sql = "UPDATE users SET reward_points = ? WHERE username = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, newPoints);
            stmt.setString(2, username);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Update Reward Error: " + e);
        }
    }

    // ✅ Close database connection
    public void close() {
        try {
            db.closeConnection(conn);
        } catch (Exception e) {
            System.out.println("DAO Close Error: " + e);
        }
    }
}
