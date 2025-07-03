package DAO;

import Database.Database;
import java.sql.*;

public class UserRecipeCompletionDAO {
    private final Database db;

    public UserRecipeCompletionDAO(Database db) {
        this.db = db;
    }

    public boolean isRecipeCompletedByUser(int userId, int recipeId) {
        String sql = "SELECT is_completed FROM user_recipe_completion WHERE user_id = ? AND recipe_id = ?";
        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, recipeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("is_completed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public double getRewardByUser(int userId, int recipeId) {
        String sql = "SELECT reward FROM user_recipe_completion WHERE user_id = ? AND recipe_id = ?";
        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, recipeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("reward");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public void setRecipeCompletion(int userId, int recipeId, boolean isCompleted, double reward) {
        String selectSql = "SELECT id FROM user_recipe_completion WHERE user_id = ? AND recipe_id = ?";
        String insertSql = "INSERT INTO user_recipe_completion (user_id, recipe_id, is_completed, reward) VALUES (?, ?, ?, ?)";
        String updateSql = "UPDATE user_recipe_completion SET is_completed = ?, reward = ? WHERE user_id = ? AND recipe_id = ?";
        try (Connection conn = db.openConnection()) {
            PreparedStatement selectPs = conn.prepareStatement(selectSql);
            selectPs.setInt(1, userId);
            selectPs.setInt(2, recipeId);
            ResultSet rs = selectPs.executeQuery();
            if (rs.next()) {
                PreparedStatement updatePs = conn.prepareStatement(updateSql);
                updatePs.setBoolean(1, isCompleted);
                updatePs.setDouble(2, reward);
                updatePs.setInt(3, userId);
                updatePs.setInt(4, recipeId);
                updatePs.executeUpdate();
            } else {
                PreparedStatement insertPs = conn.prepareStatement(insertSql);
                insertPs.setInt(1, userId);
                insertPs.setInt(2, recipeId);
                insertPs.setBoolean(3, isCompleted);
                insertPs.setDouble(4, reward);
                insertPs.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
