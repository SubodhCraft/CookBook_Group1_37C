package controller;

import DAO.BookmarkDAO;
import DAO.RecipeDAO;
import Model.Recipe;
import cookbook.Bookmark;
import cookbook.Home;
import cookbook.admin_dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminDashboardController {
    private final admin_dashboard dashboardView;
    private final Home homeView;
    private final Bookmark bookmarkView;
    private final RecipeDAO recipeDAO;
    private final BookmarkDAO bookmarkDAO;

    public AdminDashboardController(admin_dashboard dashboardView, Home homeView, Bookmark bookmarkView,
                                    RecipeDAO recipeDAO, BookmarkDAO bookmarkDAO) {
        this.dashboardView = dashboardView;
        this.homeView = homeView;
        this.bookmarkView = bookmarkView;
        this.recipeDAO = recipeDAO;
        this.bookmarkDAO = bookmarkDAO;

        dashboardView.addChooseImageListener(new ChooseImageListener());
        dashboardView.addRecipeListener(new AddRecipeListener());
    }

    // ✅ Modified to copy selected image into /images folder and store relative path
    class ChooseImageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Recipe Image");
            int result = fileChooser.showOpenDialog(dashboardView);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                File imagesDir = new File("images");
                if (!imagesDir.exists()) {
                    imagesDir.mkdir();  // Create images folder if missing
                }

                File destination = new File(imagesDir, selectedFile.getName());
                try {
                    Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    dashboardView.selectedImagePath = destination.getPath(); // Save relative path
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(dashboardView, "Failed to copy image.");
                    ex.printStackTrace();
                }
            }
        }
    }

    class AddRecipeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = dashboardView.getRecipeNameText().getText();
                int duration = Integer.parseInt(dashboardView.getRecipeDurationText().getText());
                String process = dashboardView.getRecipeProcessText().getText();

                Recipe recipe = new Recipe(name, duration, process, dashboardView.selectedImagePath);

                boolean inserted = recipeDAO.insertRecipe(recipe);
                if (!inserted) {
                    JOptionPane.showMessageDialog(dashboardView, "Failed to add recipe to database.");
                    return;
                }

                loadRecipesToHome();
                loadBookmarkedRecipes();

                JOptionPane.showMessageDialog(dashboardView, "Recipe added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dashboardView, "Error: " + ex.getMessage());
            }
        }
    }

    public void loadRecipesToHome() {
    homeView.getRecipeDisplayPanel().removeAll();

    List<Recipe> recipes = recipeDAO.getAllRecipes();

    for (Recipe recipe : recipes) {
        JPanel recipeCard = createRecipeCard(recipe);
        JButton bookmarkButton = new JButton();

        int recipeId = recipe.getId();
        boolean isBookmarked = bookmarkDAO.getBookmarkedRecipeIds().contains(recipeId);
        bookmarkButton.setText(isBookmarked ? "Bookmarked" : "Bookmark");
        bookmarkButton.setPreferredSize(new Dimension(100, 25));

        bookmarkButton.addActionListener(e -> {
            boolean toggled = bookmarkDAO.toggleBookmark(recipeId);

            if (toggled) {
                // Update the button text based on new state
                boolean nowBookmarked = bookmarkDAO.getBookmarkedRecipeIds().contains(recipeId);
                bookmarkButton.setText(nowBookmarked ? "Bookmarked" : "Bookmark");

                // Reload the bookmark panel to reflect the change
                loadBookmarkedRecipes();
            } else {
                JOptionPane.showMessageDialog(homeView, "Failed to toggle bookmark.");
            }
        });

        recipeCard.add(bookmarkButton);
        homeView.getRecipeDisplayPanel().add(recipeCard);
    }

    homeView.getRecipeDisplayPanel().revalidate();
    homeView.getRecipeDisplayPanel().repaint();
}


    private JPanel createRecipeCard(Recipe recipe) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createTitledBorder(recipe.getName()));
        card.setPreferredSize(new Dimension(200, 220));
        card.setMaximumSize(new Dimension(200, 220));
        card.setBackground(Color.WHITE);

        if (recipe.getImagePath() != null && !recipe.getImagePath().isEmpty()) {
            File imageFile = new File(recipe.getImagePath());
            if (imageFile.exists()) {
                ImageIcon icon = new ImageIcon(recipe.getImagePath());
                Image scaledImage = icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
                card.add(imageLabel);
            }
        }

        JLabel durationLabel = new JLabel("Duration: " + recipe.getDuration() + " mins");
        JTextArea processArea = new JTextArea(recipe.getProcess());
        processArea.setLineWrap(true);
        processArea.setWrapStyleWord(true);
        processArea.setEditable(false);
        processArea.setOpaque(false);

        card.add(durationLabel);
        card.add(processArea);

        return card;
    }

    public void loadBookmarkedRecipes() {
        bookmarkView.getRecipePanel().removeAll();

        Set<Integer> bookmarkedIds = new HashSet<>(bookmarkDAO.getBookmarkedRecipeIds());
        List<Recipe> allRecipes = recipeDAO.getAllRecipes();

        for (Recipe recipe : allRecipes) {
            if (bookmarkedIds.contains(recipe.getId())) {
                JPanel bookmarkCard = createBookmarkCard(recipe);
                bookmarkView.addBookmarkedRecipe(bookmarkCard);
            }
        }

        bookmarkView.getRecipePanel().revalidate();
        bookmarkView.getRecipePanel().repaint();
    }

    private JPanel createBookmarkCard(Recipe recipe) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createTitledBorder(recipe.getName()));
        card.setPreferredSize(new Dimension(200, 180));
        card.setMaximumSize(new Dimension(200, 180));

        if (recipe.getImagePath() != null && !recipe.getImagePath().isEmpty()) {
            File imageFile = new File(recipe.getImagePath());
            if (imageFile.exists()) {
                ImageIcon icon = new ImageIcon(recipe.getImagePath());
                Image scaledImage = icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
                card.add(imageLabel);
            }
        }

        JLabel durationLabel = new JLabel("Duration: " + recipe.getDuration() + " mins");
        JTextArea processArea = new JTextArea(recipe.getProcess());
        processArea.setLineWrap(true);
        processArea.setWrapStyleWord(true);
        processArea.setEditable(false);
        processArea.setOpaque(false);

        card.add(durationLabel);
        card.add(processArea);

        return card;
    }
}
