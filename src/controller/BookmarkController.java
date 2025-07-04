/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import cookbook.Bookmark;
import cookbook.Home;
import cookbook.admin_dashboard;
import DAO.RecipeDAO;
import DAO.BookmarkDAO;
import Model.BookmarkModel;
import Database.Database;
import Database.MySqlConnection;
import Model.LoggedInUser;
import Model.Recipe;
import cookbook.RecipeDetailPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookmarkController {
    private final BookmarkDAO bookmarkDAO;
    private final RecipeDAO recipeDAO;
    private admin_dashboard dashboardView;
    private Home homeView;
    private Bookmark bookmarkView;

    public BookmarkController(admin_dashboard dashboardView, Home homeView, Bookmark bookmarkView){
        this.dashboardView = dashboardView;
        this.homeView = homeView;
        this.bookmarkView = bookmarkView;
        
        Database db = new MySqlConnection();
        this.recipeDAO = new RecipeDAO(db);
        this.bookmarkDAO = new BookmarkDAO(db);
        
        loadBookmarkedRecipes();
    }

    public boolean toggleBookmark(int userId, int recipeId){
        return bookmarkDAO.toggleBookmark(userId, recipeId);
    }
    
    public List<Recipe> getBookmarkedRecipe(int userId){
        List<Recipe> recipes = new ArrayList<>();
        for(BookmarkModel bm : bookmarkDAO.getBookmarksByUser(userId)){
           Recipe recipe = recipeDAO.getRecipeById(bm.getRecipeId());
           if(recipe != null){
               recipes.add(recipe);
           }
        }
        return recipes;
    }
    
    public boolean isBookmarked(int userId, int recipeId){
        return bookmarkDAO.isBookmarked(userId, recipeId);
    }


    public void loadBookmarkedRecipes() {
        int userId = LoggedInUser.getId();
        List<Recipe> allRecipes = recipeDAO.getAllRecipes();
        Set<Integer> bookmarkedIds = bookmarkDAO.getBookmarkedRecipeIds(userId);

        // Clear current bookmarks (optional, in case you want to refresh)
        bookmarkView.getRecipePanel().removeAll();

        for (Recipe recipe : allRecipes) {
            if (bookmarkedIds.contains(recipe.getId())) {
                JPanel bookmarkCard = createBookmarkCard(recipe);
                bookmarkView.addBookmarkedRecipe(bookmarkCard);
            }
        }

        // Refresh the UI
        bookmarkView.getRecipePanel().revalidate();
        bookmarkView.getRecipePanel().repaint();
    }

  private JPanel createBookmarkCard(Recipe recipe) {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setPreferredSize(new Dimension(200, 150));
    panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    JLabel nameLabel = new JLabel(recipe.getName(), SwingConstants.CENTER);
    JLabel durationLabel = new JLabel("Duration: " + recipe.getDuration() + " mins", SwingConstants.CENTER);

    panel.add(nameLabel, BorderLayout.NORTH);
    panel.add(durationLabel, BorderLayout.CENTER);

    panel.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            System.out.println("Clicked recipe: " + recipe.getName());

            RecipeDetailPanel detailPanel = new RecipeDetailPanel();
            detailPanel.setRecipe(recipe);

            JDialog dialog = new JDialog();
            dialog.setTitle("Recipe Details");
            dialog.setModal(true);
            dialog.getContentPane().add(detailPanel);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    });

    return panel;
}


}



