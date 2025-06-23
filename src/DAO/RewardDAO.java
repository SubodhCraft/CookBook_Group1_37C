package DAO;

import Database.Database;
import Database.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RewardDAO {
    private Database db;
    private Connection conn;

    public RewardDAO() {
        try {
            db = new MySqlConnection();
            conn = db.openConnection();
        } catch (Exception e) {
            System.out.println("DAO Connection Error: " + e);
        }
    }

    // ✅ Get total reward points from the rewards_total table
    public int getTotalRewardPoints() {
        int points = 0;
        String sql = "SELECT reward_points FROM rewards_total WHERE id = 1";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                points = rs.getInt("reward_points");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Get Total Reward Error: " + e);
        }
        return points;
    }

    // ✅ Update the total reward points in the rewards_total table
   public void updateTotalRewardPoints(int newPoints) {
    String sql = "UPDATE rewards_total SET reward_points = ? WHERE id = 1";
    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, newPoints);
        int rows = stmt.executeUpdate();
        System.out.println("Rows updated: " + rows);
        stmt.close();
    } catch (Exception e) {
        System.out.println("Update Total Reward Error: " + e);
    }
}


    // ✅ Close DB connection
    public void close() {
        try {
            db.closeConnection(conn);
        } catch (Exception e) {
            System.out.println("DAO Close Error: " + e);
        }
    }
}
