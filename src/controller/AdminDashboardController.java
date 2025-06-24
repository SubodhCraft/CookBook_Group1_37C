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
//    private final  Dashboard view;
    private final admin_dashboard dashboardView;
    private final Home homeView;
    private final  Bookmark bookmarkView;
    private final RecipeDAO recipeDAO;
    private final  BookmarkDAO bookmarkDAO;
    private final update updatePanel; 
private final javax.swing.JPanel mainPanel; 
    

//   public AdminDashboardController(admin_dashboard dashboardView, Home homeView, Bookmark bookmarkView, RecipeDAO recipeDAO, BookmarkDAO bookmarkDAO, JPanel mainPanel, update updatePanel) {
//    this.dashboardView = dashboardView;
//    this.homeView = homeView;
//    this.bookmarkView = bookmarkView;
//    this.recipeDAO = recipeDAO;
//    this.bookmarkDAO = bookmarkDAO;
//    this.mainPanel = mainPanel;     // now correctly references parameter 'mainPanel'
//    this.updatePanel = updatePanel; // now correctly references parameter 'updatePanel'
//
//    dashboardView.addChooseImageListener(new ChooseImageListener());
//    dashboardView.addRecipeListener(new AddRecipeListener());
//}
//     public AdminDashboardController(){}

    public AdminDashboardController(admin_dashboard dashboardView, Home homeView, Bookmark bookmarkView, RecipeDAO recipeDAO, BookmarkDAO bookmarkDAO, cookbook.update updatePanel, javax.swing.JPanel mainPanel) {
//       public AdminDashboardController(Dashboard view){
//        this.view=view;
//        view.addLogoutListener(new addLogoutListener(view));
//        dashboardController.setupLogoutListener(dashboardView);
        this.dashboardView = dashboardView;
        this.homeView = homeView;
        this.bookmarkView = bookmarkView;
        this.recipeDAO = recipeDAO;
        this.bookmarkDAO = bookmarkDAO;

        dashboardView.addChooseImageListener(new ChooseImageListener());
        dashboardView.addRecipeListener(new AddRecipeListener());
        this.updatePanel = updatePanel;
        this.mainPanel = mainPanel;
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
String category = dashboardView.getCategoryField().getText();
String imagePath = dashboardView.selectedImagePath;

Recipe recipe = new Recipe(name, duration, process, imagePath, category); // Without ID


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


        // Create Edit button
        JButton editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(80, 25));
        editButton.addActionListener(e -> {
            System.out.println("Edit clicked for recipe ID: " + recipe.getId());
            // TODO: add edit logic here
            
    edit editPanel = new edit(recipeDAO, this, mainPanel, updatePanel); // pass 4 args
editPanel.setRecipeId(recipe.getId());

                JDialog dialog = new JDialog();
    dialog.setTitle("Edit Recipe");
    dialog.setModal(true);
    dialog.getContentPane().add(editPanel);
    dialog.pack();
    dialog.setLocationRelativeTo(null);
    dialog.setVisible(true);

    
        });

        // Create Bookmark button
        JButton bookmarkButton = new JButton();

        


        int recipeId = recipe.getId();

int userId =LoggedInUser.getId();
        boolean isBookmarked = bookmarkDAO.getBookmarkedRecipeIds(userId).contains(recipeId);
        bookmarkButton.setText(isBookmarked ? "Bookmarked" : "Bookmark");
        bookmarkButton.setPreferredSize(new Dimension(100, 25));

        bookmarkButton.addActionListener(e -> {
            boolean toggled = bookmarkDAO.toggleBookmark(userId,recipeId);

            if (toggled) {
                // Update the button text based on new state
                boolean nowBookmarked = bookmarkDAO.getBookmarkedRecipeIds(userId).contains(recipeId);
                bookmarkButton.setText(nowBookmarked ? "Bookmarked" : "Bookmark");

                // Reload the bookmark panel to reflect the change
                loadBookmarkedRecipes();
            } else {
                JOptionPane.showMessageDialog(homeView, "Failed to toggle bookmark.");
            }
        });


        // Create panel to hold buttons side by side
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setMaximumSize(new Dimension(200, 30));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons to the panel
        buttonsPanel.add(editButton);
        buttonsPanel.add(Box.createHorizontalStrut(10)); // space between buttons
        buttonsPanel.add(bookmarkButton);

        // Add buttons panel to recipe card
        recipeCard.add(buttonsPanel);

        // Add the card to the home panel

       

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
        JTextArea processArea = new JTextArea(recipe.getProcess());
        processArea.setLineWrap(true);
        processArea.setWrapStyleWord(true);
        processArea.setEditable(false);
        processArea.setOpaque(false);

        card.add(durationLabel);
        card.add(processArea);

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
//    public void searchRecipes(String keyword){
//        List<Recipe> results = recipeDAO.searchRecipesByTitle(keyword);
////        homeView.displayRecipes(results);
//if(results.isEmpty()){
//    JOptionPane.showMessageDialog(null,"No matching recipes found.");
//}else{
//    homeView.displayRecipes(results);
//    CardLayout cl = (CardLayout) mainPanel.getLayout();
//    cl.show(mainPanel,"home");
//}
//    }
    
    public void searchRecipes(String keyword){
        List<Recipe> results = recipeDAO.searchRecipesByTitleOrCategory(keyword);
//        if(results.isEmpty()){
//    JOptionPane.showMessageDialog(null,"No matching recipes found.");
//}else{
    homeView.displayRecipes(results);
//    CardLayout cl = (CardLayout) mainPanel.getLayout();
//    cl.show(mainPanel,"home");
//    }


}
// public void setupLogoutListener(Dashboard view){
//     view.addLogoutListener(new addLogoutListener(view));
//}
// class addLogoutListener implements ActionListener{
//     private JFrame currentFrame;
//     
//     public addLogoutListener(JFrame frame){
//         this.currentFrame=frame;
//     }

//        @Override
//        public void actionPerformed(ActionEvent e) {
//           System.out.println("Logout button clicked"); 
//           int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?",
//                   "Logout Confirmation",
//                   JOptionPane.YES_NO_OPTION);
//           if(confirm==JOptionPane.YES_OPTION){
//               currentFrame.dispose();
//               new Sigininframe().setVisible(true);
//           }
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
    }