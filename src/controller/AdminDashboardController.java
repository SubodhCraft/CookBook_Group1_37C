package controller;

import DAO.BookmarkDAO;
import DAO.RecipeDAO;
import Database.MySqlConnection;
import Model.LoggedInUser;
import Model.Recipe;
import View.Sigininframe;
import cookbook.Bookmark;
import cookbook.Dashboard;
import cookbook.Home;
import cookbook.RecipeDetailPanel;
import cookbook.admin_dashboard;
import cookbook.edit;
import cookbook.update;

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
//    MySqlConnection mysql = new MySqlConnection();
    private  Dashboard view;
    private  admin_dashboard dashboardView;
    private  Home homeView;
    private   Bookmark bookmarkView;
    private  RecipeDAO recipeDAO;
    private   BookmarkDAO bookmarkDAO;
    private  update updatePanel; 
private  javax.swing.JPanel mainPanel; 
    private admin_dashboard adminDash;
    private Home home;
    private Bookmark bookmark;
    private String selectedQRCodePath = null;

    
    
    MySqlConnection mysql = new MySqlConnection();

    
    public AdminDashboardController(admin_dashboard dashboardView, Home homeView, Bookmark bookmarkView, RecipeDAO recipeDAO, BookmarkDAO bookmarkDAO, cookbook.update updatePanel, javax.swing.JPanel mainPanel) {

        this.dashboardView = dashboardView;
        this.homeView = homeView;
        this.bookmarkView = bookmarkView;
        this.recipeDAO = recipeDAO;
        this.bookmarkDAO = bookmarkDAO;
        
        
        dashboardView.addChooseImageListener(new ChooseImageListener());
        dashboardView.getChooseQRButton().addActionListener(new ChooseQRListener());

        dashboardView.addRecipeListener(new AddRecipeListener());

        this.updatePanel = updatePanel;
        this.mainPanel = mainPanel;
    }
    public AdminDashboardController(admin_dashboard adminDash,Home home, Bookmark bookmark){
        this.adminDash = adminDash;
        this.home = home;
        this.bookmark = bookmark;
    }
    class ChooseQRListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select QR Code Image");
        int result = fileChooser.showOpenDialog(dashboardView);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            File imagesDir = new File("images");
            if (!imagesDir.exists()) {
                imagesDir.mkdir();
            }
            File destination = new File(imagesDir, selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                selectedQRCodePath = destination.getPath(); // save relative path
                // Optionally notify user of success or update UI element showing selected QR path
                JOptionPane.showMessageDialog(dashboardView, "QR code selected: " + selectedQRCodePath);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(dashboardView, "Failed to copy QR code image.");
                ex.printStackTrace();
            }
        }
    }
}






    // Modified to copy selected image into /images folder and store relative path
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
String category = dashboardView.getCategoryField().getText();
String imagePath = dashboardView.selectedImagePath;
String qrCodePath = selectedQRCodePath; 

Recipe recipe = new Recipe(name, duration, process, imagePath, category, qrCodePath); // Without ID


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
    JPanel displayPanel = homeView.getRecipeDisplayPanel();
    displayPanel.removeAll();

    // Set GridLayout: dynamic rows, 5 columns
    displayPanel.setLayout(new GridLayout(0, 5, 15, 15)); // 5 columns, with gaps

    List<Recipe> recipes = recipeDAO.getAllRecipes();

    for (Recipe recipe : recipes) {
        JPanel recipeCard = createRecipeCard(recipe);

        // Add click listener to open detail panel
        recipeCard.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RecipeDetailPanel detailPanel = new RecipeDetailPanel();
                detailPanel.setRecipe(recipe);
                mainPanel.add(detailPanel, "detail");

                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "detail");
            }
        });
        
        JButton editButton = null;
        
        if("admin".equalsIgnoreCase(LoggedInUser.getRole())){
            editButton = new JButton("Edit");
            editButton.setPreferredSize(new Dimension(80,25));
            editButton.addActionListener( e-> {
                edit editPanel = new edit (recipeDAO, this, mainPanel, updatePanel);
                editPanel.setRecipeId(recipe.getId());
                
                JDialog dialog = new JDialog();
                dialog.setTitle("Edit Recipe");
                dialog.setModal(true);
                dialog.getContentPane().add(editPanel);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            });
        }



        // Bookmark button
        JButton bookmarkButton = new JButton();
        int recipeId = recipe.getId();
        int userId = LoggedInUser.getId();
        boolean isBookmarked = bookmarkDAO.getBookmarkedRecipeIds(userId).contains(recipeId);
        bookmarkButton.setText(isBookmarked ? "Bookmarked" : "Bookmark");
        bookmarkButton.setPreferredSize(new Dimension(100, 25));

        bookmarkButton.addActionListener(e -> {
            boolean toggled = bookmarkDAO.toggleBookmark(userId, recipeId);
            if (toggled) {
                boolean nowBookmarked = bookmarkDAO.getBookmarkedRecipeIds(userId).contains(recipeId);
                bookmarkButton.setText(nowBookmarked ? "Bookmarked" : "Bookmark");
                loadBookmarkedRecipes();
            } else {
                JOptionPane.showMessageDialog(homeView, "Failed to toggle bookmark.");
            }
        });

        // Panel for buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        buttonsPanel.setBackground(Color.WHITE);
        
        if(editButton != null){
            buttonsPanel.add(editButton);
        }
//        buttonsPanel.add(editButton);
        buttonsPanel.add(bookmarkButton);

        recipeCard.add(buttonsPanel);

        // Add recipe card to display panel
        displayPanel.add(recipeCard);
    }

    displayPanel.revalidate();
    displayPanel.repaint();
}




    private JPanel createRecipeCard(Recipe recipe) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createTitledBorder(recipe.getName()));
        card.setPreferredSize(new Dimension(200, 180));
        card.setMaximumSize(new Dimension(200, 180));
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

        card.add(durationLabel);
//        card.add(processArea);

        return card;
    }

    public void loadBookmarkedRecipes() {
        bookmarkView.getRecipePanel().removeAll();

        int userId = LoggedInUser.getId();
        Set<Integer> bookmarkedIds = new HashSet<>(bookmarkDAO.getBookmarkedRecipeIds(userId));
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


        card.add(durationLabel);
//        card.add(processArea);

        return card;
    }

   public Recipe getRecipeById(int recipeId) {
    for (Recipe r : recipeDAO.getAllRecipes()) {
        if (r.getId() == recipeId) {
            return r;
        }
    }
    return null;
}
   public void showUpdatePanelById(int recipeId) {
    Recipe recipe = getRecipeById(recipeId);
    if (recipe != null) {
        updatePanel.loadRecipeData(recipe);
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "update");
    } else {
        JOptionPane.showMessageDialog(null, "Recipe data not found.");
    }
}

   public boolean searchRecipes(String keyword) {
    List<Recipe> results = recipeDAO.searchRecipesByTitleOrCategory(keyword);
    
    if (results.isEmpty()) {
        return false;
    }
    
    // Clear the current home panel
    homeView.getRecipeDisplayPanel().removeAll();
    
    for (Recipe recipe : results) {
        // Create a clickable recipe card like in your normal loading method
        JPanel recipeCard = createRecipeCard(recipe);
        
        recipeCard.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RecipeDetailPanel detailPanel = new RecipeDetailPanel();
                detailPanel.setRecipe(recipe);

                // Add detail panel to mainPanel and show it
                mainPanel.add(detailPanel, "detail");
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "detail");
            }
        });
        
        homeView.getRecipeDisplayPanel().add(recipeCard);
    }
    
    homeView.getRecipeDisplayPanel().revalidate();
    homeView.getRecipeDisplayPanel().repaint();
    
    // Show the home panel with the filtered clickable cards
    CardLayout cl = (CardLayout) mainPanel.getLayout();
    cl.show(mainPanel, "home");
    
    return true;
}

 public boolean searchRecipesWithFeedback(String keyword) {
    boolean found = searchRecipes(keyword);
    return found;
}
   

           private void logOutActionPerformed(java.awt.event.ActionEvent evt) {                                       
    // Show confirmation dialog
    int choice = JOptionPane.showConfirmDialog(
            null,
            "Do you want to logout?",
            "Logout Confirmation",
            JOptionPane.YES_NO_OPTION);

    if (choice == JOptionPane.YES_OPTION) {
        // Proceed with logout
        Sigininframe sigin = new Sigininframe();
        LoginController controller = new LoginController(sigin);
        controller.open();

        // Close current window (if this is a JFrame)
        this.dispose(); // optional: closes the current window
    } else {
        // Logout cancelled
        System.out.println("Logout cancelled by user.");
    }
}

        
        private void dispose(){
            
        }
        
        
    class SettingsListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Settings button clicked"); // Add this for debugging

        View.UserSettings settingsView = new View.UserSettings();
        settingsView.setVisible(true); // This MUST be here
         adminDash.dispose(); // Optional, only if you want to close the Dashboard
    }
}


}