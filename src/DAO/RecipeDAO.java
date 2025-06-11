/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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

    // Insert recipe and set generated ID back to the Recipe object
    public boolean insertRecipe(Recipe recipe) {
        String query = "INSERT INTO recipes (name, duration, process, image_path) VALUES (?, ?, ?, ?)";
        try (Connection conn = db.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, recipe.getName());
            pstmt.setInt(2, recipe.getDuration());
            pstmt.setString(3, recipe.getProcess());
            pstmt.setString(4, recipe.getImagePath());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                return false; // no rows inserted
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    recipe.setId(generatedKeys.getInt(1)); // set the generated id in the Recipe object
                } else {
                    return false; // failed to get ID
                }
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all recipes, using constructor with ID for proper handling
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        String query = "SELECT * FROM recipes";
        try (Connection conn = db.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Recipe r = new Recipe(
                    rs.getInt("id"),  // <-- use ID here
                    rs.getString("name"),
                    rs.getInt("duration"),
                    rs.getString("process"),
                    rs.getString("image_path")
                );
                recipes.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }
}
