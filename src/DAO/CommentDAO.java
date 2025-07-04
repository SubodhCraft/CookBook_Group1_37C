package DAO;

import Database.Database;
import Model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private final Database db;

    public CommentDAO(Database db) {
        this.db = db;
    }

    // Get all comments for a given recipe (with username)
    public List<Comment> getCommentsByRecipeId(int recipeId) {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT rc.userComment AS content, u.username " +
                     "FROM recipe_comment rc " +
                     "JOIN users u ON rc.user_id = u.Id " +
                     "WHERE rc.recipe_id = ? " +
                     "ORDER BY rc.comment_id DESC";

        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recipeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String content = rs.getString("content");
                String username = rs.getString("username");
                comments.add(new Comment(username, content));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comments;
    }

    // Add a new comment to a recipe
    public boolean addComment(int recipeId, int userId, String comment) {
        String sql = "INSERT INTO recipe_comment (recipe_id, user_id, userComment) VALUES (?, ?, ?)";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recipeId);
            stmt.setInt(2, userId);
            stmt.setString(3, comment);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
