/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.RecipeDAO;
import Model.Recipe;
import cookbook.Home;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author LEGION
 */
public class SearchController {
    private final RecipeDAO recipeDAO;
    private final Home homePanel;
    
    public SearchController(RecipeDAO recipeDAO, Home homePanel) {
        this.recipeDAO = recipeDAO;
        this.homePanel = homePanel;
    }

    public void searchRecipes(String keyword) {
        List<Recipe> results = recipeDAO.searchRecipesByTitle(keyword);

        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No recipes found for: " + keyword);
        } else {
            homePanel.displayRecipes(results);  // This assumes Home has a method to show recipes.
        }
    }
    public List<Recipe> searchByCategory(String categoryKeyword){
        return recipeDAO.searchRecipesByCategory(categoryKeyword);
    }
}


