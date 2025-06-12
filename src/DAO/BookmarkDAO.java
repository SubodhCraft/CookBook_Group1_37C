package DAO;

import Database.Database;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class BookmarkDAO {
    private final Database db;

    public BookmarkDAO(Database db) {
        this.db = db;
    }

    // Modified to return boolean
    public boolean bookmarkRecipe(int recipeId) {
    // Check if it's already bookmarked first
    if (getBookmarkedRecipeIds().contains(recipeId)) {
        return false; // Already bookmarked, do nothing
    }

    String query = "INSERT INTO bookmarks (recipe_id) VALUES (?)";
    try (Connection conn = db.openConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, recipeId);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    public Set<Integer> getBookmarkedRecipeIds() {
        Set<Integer> ids = new HashSet<>();
        String query = "SELECT recipe_id FROM bookmarks";

        try (Connection conn = db.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ids.add(rs.getInt("recipe_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }
    public boolean removeBookmark(int recipeId) {
    String query = "DELETE FROM bookmarks WHERE recipe_id = ?";
    try (Connection conn = db.openConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, recipeId);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
public boolean toggleBookmark(int recipeId) {
    if (getBookmarkedRecipeIds().contains(recipeId)) {
        return removeBookmark(recipeId);
    } else {
        return bookmarkRecipe(recipeId);
    }
}



}
