package controller;

import cookbook.Bookmark;
import DAO.BookmarkDAO;
import DAO.RecipeDAO;
import Database.MySqlConnection;
import Database.Database;
import Model.BookmarkModel;
import Model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BookmarkUIController {
    private final Bookmark bookmarkPanel;
    private final BookmarkDAO bookmarkDAO;
//    private final BookmarkController bookmarkController;
    private final int userId; // CURRENT LOGGED-IN USER

    public BookmarkUIController(Bookmark bookmarkPanel, int userId) {
        this.bookmarkPanel = bookmarkPanel;
        this.userId = userId;
        this.bookmarkDAO = new BookmarkDAO(new MySqlConnection());

        loadBookmarkedRecipes();
    }

    public void loadBookmarkedRecipes() {
        List<BookmarkModel> bookmarkModels = bookmarkDAO.getBookmarksByUser(userId);
        List<Recipe> bookmarks = new ArrayList<>();
        RecipeDAO recipeDAO = new RecipeDAO(new MySqlConnection());
        
        for (BookmarkModel model : bookmarkModels){
            Recipe recipe = recipeDAO.getRecipeById(model.getRecipeId());
            if(recipe !=null){
                bookmarks.add(recipe);
            }
        }
        bookmarkPanel.displayBookmarks(bookmarks);

    }
    public void refreshBookmarks(){
        loadBookmarkedRecipes();
    }

    public JPanel createBookmarkCard(Recipe recipe) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(200, 150));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel nameLabel = new JLabel(recipe.getName(), SwingConstants.CENTER);
        JLabel durationLabel = new JLabel("Duration: " + recipe.getDuration() + " mins", SwingConstants.CENTER);

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(durationLabel, BorderLayout.CENTER);

        return panel;
    }

    // You can also add toggle or remove bookmark button handling here if needed
}
