/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Model.Recipe;
import cookbook.Bookmark;
import cookbook.Home;
import cookbook.admin_dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AdminDashboardController {
    private final admin_dashboard dashboardView;
    private final Home homeView;
    private final Bookmark bookmarkView;

    public AdminDashboardController(admin_dashboard dashboardView, Home homeView, Bookmark bookmarkView) {
    this.dashboardView = dashboardView;
    this.homeView = homeView;
    this.bookmarkView = bookmarkView;

    dashboardView.addChooseImageListener(new ChooseImageListener());
    dashboardView.addRecipeListener(new AddRecipeListener());
}


    class ChooseImageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Recipe Image");
            int result = fileChooser.showOpenDialog(dashboardView);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                dashboardView.selectedImagePath = selectedFile.getAbsolutePath();
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

                JPanel recipeCard = new JPanel();
                recipeCard.setLayout(new BoxLayout(recipeCard, BoxLayout.Y_AXIS));
                recipeCard.setBorder(BorderFactory.createTitledBorder(name));
                recipeCard.setPreferredSize(new Dimension(200, 220));
                recipeCard.setMaximumSize(new Dimension(200, 220));
                recipeCard.setMinimumSize(new Dimension(200, 220));
                recipeCard.setBackground(Color.WHITE);


                if (recipe.getImagePath() != null && !recipe.getImagePath().isEmpty()) {
                    ImageIcon icon = new ImageIcon(recipe.getImagePath());
                    Image scaledImage = icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
                    JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
                    recipeCard.add(imageLabel);
                }

                JLabel durationLabel = new JLabel("Duration: " + duration + " mins");
                JTextArea processArea = new JTextArea(process);
                processArea.setLineWrap(true);
                processArea.setWrapStyleWord(true);
                processArea.setEditable(false);
                processArea.setOpaque(false);

                recipeCard.add(durationLabel);
                recipeCard.add(processArea);
                JButton bookmarkButton = new JButton("Bookmark");
                

                bookmarkButton.setPreferredSize(new Dimension(80, 25));
                recipeCard.add(bookmarkButton);

                // Add action for bookmark button
                bookmarkButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JPanel bookmarkCard = new JPanel();
                        bookmarkCard.setLayout(new BoxLayout(bookmarkCard, BoxLayout.Y_AXIS));
                        bookmarkCard.setBorder(BorderFactory.createTitledBorder(name));
                        bookmarkCard.setPreferredSize(new Dimension(200, 180));
                        bookmarkCard.setMaximumSize(new Dimension(200, 180));

                        if (recipe.getImagePath() != null && !recipe.getImagePath().isEmpty()) {
                            ImageIcon icon = new ImageIcon(recipe.getImagePath());
                            Image scaledImage = icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
                            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
                            bookmarkCard.add(imageLabel);
                        }

                        bookmarkCard.add(new JLabel("Duration: " + duration + " mins"));
                        JTextArea processArea = new JTextArea(process);
                        processArea.setLineWrap(true);
                        processArea.setWrapStyleWord(true);
                        processArea.setEditable(false);
                        processArea.setOpaque(false);
                        bookmarkCard.add(processArea);

                        bookmarkView.addBookmarkedRecipe(bookmarkCard);
                    }
                });

                homeView.getRecipeDisplayPanel().add(recipeCard);
                homeView.getRecipeDisplayPanel().revalidate();
                homeView.getRecipeDisplayPanel().repaint();

                JOptionPane.showMessageDialog(dashboardView, "Recipe added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dashboardView, "Error: " + ex.getMessage());
            }
        }
    }
}


