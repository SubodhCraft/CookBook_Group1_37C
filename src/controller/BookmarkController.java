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
import Database.Database;
import Database.MySqlConnection;
import Model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Set;

public class BookmarkController {
    private admin_dashboard dashboardView;
    private Home homeView;
    private Bookmark bookmarkView;

    private RecipeDAO recipeDAO;
    private BookmarkDAO bookmarkDAO;

    public BookmarkController(admin_dashboard dashboardView, Home homeView, Bookmark bookmarkView) {
        this.dashboardView = dashboardView;
        this.homeView = homeView;
        this.bookmarkView = bookmarkView;

        Database db = new MySqlConnection();
        this.recipeDAO = new RecipeDAO(db);
        this.bookmarkDAO = new BookmarkDAO(db);

        loadBookmarkedRecipes();
    }

    private void loadBookmarkedRecipes() {
        List<Recipe> allRecipes = recipeDAO.getAllRecipes();
        Set<Integer> bookmarkedIds = bookmarkDAO.getBookmarkedRecipeIds();

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

        JLabel nameLabel = new JLabel(recipe.getName());
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(nameLabel, BorderLayout.NORTH);

        JLabel durationLabel = new JLabel("Duration: " + recipe.getDuration() + " mins");
        durationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(durationLabel, BorderLayout.CENTER);

        // You can add image loading here if you want:
        // ImageIcon icon = new ImageIcon(recipe.getImagePath());
        // JLabel imageLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(180, 100, Image.SCALE_SMOOTH)));
        // panel.add(imageLabel, BorderLayout.SOUTH);

        return panel;
    }
}



