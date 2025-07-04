package DAO;

import Model.Recipe;
import Database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {
    private final Database db;

    public RecipeDAO(Database db) {
        this.db = db;
    }

    //Insert recipe with qr_code_path
    public boolean insertRecipe(Recipe recipe) {
        String query = "INSERT INTO recipes (name, duration, process, image_path, category, qr_code_path) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = db.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, recipe.getName());
            pstmt.setInt(2, recipe.getDuration());
            pstmt.setString(3, recipe.getProcess());
            pstmt.setString(4, recipe.getImagePath());
            pstmt.setString(5, recipe.getCategory());
            pstmt.setString(6, recipe.getQrCodePath());
//            pstmt.setString(7, recipe.getLink());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    recipe.setId(generatedKeys.getInt(1));
                } else {
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all recipes with qr_code_path
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        String query = "SELECT * FROM recipes";
        try (Connection conn = db.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Recipe r = new Recipe(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("duration"),
                    rs.getString("process"),
                    rs.getString("image_path"),
                    rs.getString("category"),
                    rs.getString("qr_code_path")
                );
                r.setReward(rs.getDouble("reward"));
                r.setCompleted(r.getReward() >= 7);
                recipes.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    // Update recipe with qr_code_path
    public boolean updateRecipe(Recipe recipe) {
        String sql = "UPDATE recipes SET name = ?, duration = ?, process = ?, image_path = ?, category = ?, qr_code_path = ? WHERE id = ?";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, recipe.getName());
            stmt.setInt(2, recipe.getDuration());
            stmt.setString(3, recipe.getProcess());
            stmt.setString(4, recipe.getImagePath());
            stmt.setString(5, recipe.getCategory());
            stmt.setString(6, recipe.getQrCodePath());
            stmt.setInt(7, recipe.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRecipe(int id) {
        String sql = "DELETE FROM recipes WHERE id = ?";

        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateRecipeReward(int recipeId, double newReward) {
        String sql = "UPDATE recipes SET reward = ? WHERE id = ?";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newReward);
            stmt.setInt(2, recipeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getRecipeReward(int recipeId) {
        String sql = "SELECT reward FROM recipes WHERE id = ?";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recipeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("reward");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public List<Recipe> searchRecipesByTitle(String keyword) {
        List<Recipe> foundRecipes = new ArrayList<>();
        String query = "SELECT * FROM recipes WHERE name LIKE ?";
        try (Connection conn = db.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Recipe r = new Recipe(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("duration"),
                    rs.getString("process"),
                    rs.getString("image_path"),
                    rs.getString("category"),
                    rs.getString("qr_code_path")
                );
                foundRecipes.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundRecipes;
    }

    public Recipe getRecipeById(int id) {
        String sql = "SELECT * FROM recipes WHERE id= ?";
        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Recipe(
                    id,
                    rs.getString("name"),
                    rs.getInt("duration"),
                    rs.getString("process"),
                    rs.getString("image_path"),
                    rs.getString("category"),
                    rs.getString("qr_code_path")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Recipe> searchRecipesByCategory(String categoryKeyword) {
        List<Recipe> foundRecipes = new ArrayList<>();
        String query = "SELECT * FROM recipes WHERE category LIKE ?";
        try (Connection conn = db.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + categoryKeyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Recipe r = new Recipe(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("duration"),
                    rs.getString("process"),
                    rs.getString("image_path"),
                    rs.getString("category"),
                    rs.getString("qr_code_path")
                );
                foundRecipes.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundRecipes;
    }

    public List<Recipe> searchRecipesByTitleOrCategory(String keyword) {
        List<Recipe> results = new ArrayList<>();
        String query = "SELECT * FROM recipes WHERE name LIKE ? OR category LIKE ?";

        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Recipe r = new Recipe(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("duration"),
                    rs.getString("process"),
                    rs.getString("image_path"),
                    rs.getString("category"),
                    rs.getString("qr_code_path")
                );

                r.setReward(rs.getDouble("reward"));
                r.setCompleted(r.getReward() >= 7);
                results.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
}
