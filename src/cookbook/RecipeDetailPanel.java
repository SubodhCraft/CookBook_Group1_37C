/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package cookbook;

import DAO.RecipeDAO;
import Database.Database;
import Database.MySqlConnection;
import Model.Recipe;
import java.awt.CardLayout;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import cookbook.Dashboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DAO.CommentDAO;
import Model.Comment;
import java.awt.Font;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Model.Comment;


/**
 *
 * @author Jay pradhan
 */
public class RecipeDetailPanel extends javax.swing.JPanel {

    /**
     * Creates new form RecipeDetailPanel
     */
    Database db = new MySqlConnection();
    private CommentDAO commentDAO = new CommentDAO(db);

    
    private Recipe currentRecipe;
    private java.awt.event.ActionListener backButtonListener;
    private boolean isCompleted = false;  // or fetch actual state from DB
 


private int currentUserId = 1; // You can replace this with actual login user later


public void setBackButtonListener(java.awt.event.ActionListener listener) {
    this.backButtonListener = listener;
}


public void setRecipe(Recipe recipe) {
    this.currentRecipe = recipe;
    loadRecipeDetails(recipe);
    loadComments();
    
    
}

public Recipe getRecipe() {
    return currentRecipe;
}


    public RecipeDetailPanel() {
        initComponents();
    }
   public void loadRecipeDetails(Recipe recipe) {
    // Existing code for title, duration, process, imageLabel...
    titleLabel.setText(recipe.getName());
    durationLabel.setText("Duration: " + recipe.getDuration() + " mins");
    processArea.setText(recipe.getProcess());

    // Load recipe image
    if (recipe.getImagePath() != null && !recipe.getImagePath().isEmpty()) {
        File imageFile = new File(recipe.getImagePath());
        if (imageFile.exists()) {
            ImageIcon icon = new ImageIcon(recipe.getImagePath());
            Image scaledImage = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            imageLabel.setIcon(null);
        }
    } else {
        imageLabel.setIcon(null);
    }

    // Load QR code image similarly
    if (recipe.getQrCodePath() != null && !recipe.getQrCodePath().isEmpty()) {
        File qrFile = new File(recipe.getQrCodePath());
        if (qrFile.exists()) {
            ImageIcon qrIcon = new ImageIcon(recipe.getQrCodePath());
            Image scaledQr = qrIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Adjust size as needed
            QR.setIcon(new ImageIcon(scaledQr));
        } else {
            QR.setIcon(null);
        }
    } else {
        QR.setIcon(null);
    }

    // Existing mark complete button text logic...
    if (recipe.isCompleted()) {
        MarkasComplete.setText("Marked as Complete");
        isCompleted = true;
    } else {
        MarkasComplete.setText("Mark as Complete");
        isCompleted = false;
    }
}

   private void toggleCompletion() {
    Database db = new MySqlConnection(); 
    RecipeDAO recipeDAO = new RecipeDAO(db);
    int recipeId = currentRecipe.getId();

    if (!isCompleted) {
        // Mark as complete: set reward to 2.5 (fixed)
        currentRecipe.setReward(2.5);
        MarkasComplete.setText("Marked as Complete");
    } else {
        // Unmark complete: set reward back to 0
        currentRecipe.setReward(0);
        MarkasComplete.setText("Mark as Complete");
    }

    isCompleted = !isCompleted;

    // Update reward in database
    recipeDAO.updateRecipeReward(recipeId, currentRecipe.getReward());
}
   private void loadComments() {
    commentListPanel.removeAll();

    // Fetch list of Comment objects (not just strings)
    List<Comment> comments = commentDAO.getCommentsByRecipeId(currentRecipe.getId());

    commentListPanel.setLayout(new BoxLayout(commentListPanel, BoxLayout.Y_AXIS)); // Ensure vertical layout

    for (Comment comment : comments) {
        JPanel commentPanel = new JPanel();
        commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
        commentPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Username label
        JLabel userLabel = new JLabel(comment.getUsername() + ":");
        userLabel.setFont(new Font("Arial", Font.BOLD, 12));

        // Comment content label with HTML for line-wrapping
        JLabel commentLabel = new JLabel("<html><p style=\"width:400px\">" + comment.getContent() + "</p></html>");
        commentLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Add both to the comment panel
        commentPanel.add(userLabel);
        commentPanel.add(commentLabel);

        // Add comment panel to list panel
        commentListPanel.add(commentPanel);
    }

    commentListPanel.revalidate();
    commentListPanel.repaint();
}




    



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        submitCommentButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        MarkasComplete = new javax.swing.JButton();
        imageLabel = new javax.swing.JLabel();
        durationLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        commentInputArea = new javax.swing.JTextArea();
        commentScrollPane = new javax.swing.JScrollPane();
        commentListPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        processArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        QR = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        submitCommentButton.setText("Submit");
        submitCommentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCommentButtonActionPerformed(evt);
            }
        });

        titleLabel.setText("jLabel1");

        MarkasComplete.setText("Mark as Complete");
        MarkasComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MarkasCompleteActionPerformed(evt);
            }
        });

        imageLabel.setText("jLabel1");

        durationLabel.setText("jLabel1");

        backButton.setText("Back");
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });

        commentInputArea.setColumns(20);
        commentInputArea.setRows(5);
        jScrollPane3.setViewportView(commentInputArea);

        commentListPanel.setLayout(new javax.swing.BoxLayout(commentListPanel, javax.swing.BoxLayout.LINE_AXIS));
        commentScrollPane.setViewportView(commentListPanel);

        processArea.setColumns(20);
        processArea.setRows(5);
        jScrollPane1.setViewportView(processArea);

        jButton1.setText("Pair with drinks");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("flavour balancing");

        jLabel1.setText("Add a comment:");

        QR.setText("QR");

        jLabel2.setText("Comments:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(durationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(317, 317, 317))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(commentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(submitCommentButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(MarkasComplete, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(QR)
                            .addComponent(jLabel2))))
                .addGap(0, 329, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(imageLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(durationLabel)
                            .addComponent(titleLabel))
                        .addGap(62, 62, 62)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(QR)
                .addGap(87, 87, 87)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(submitCommentButton)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(commentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)))
                .addComponent(MarkasComplete)
                .addContainerGap(618, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void submitCommentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCommentButtonActionPerformed
        // TODO add your handling code here:
        String userComment = commentInputArea.getText().trim();
        if (!userComment.isEmpty()) {
            commentDAO.addComment(currentRecipe.getId(), currentUserId, userComment);
            commentInputArea.setText("");  // clear input box
            loadComments();  // reload comments after submitting
        }
    }//GEN-LAST:event_submitCommentButtonActionPerformed

    private void MarkasCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MarkasCompleteActionPerformed
        // TODO add your handling code here:
        MarkasComplete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleCompletion();
            }
        });
    }//GEN-LAST:event_MarkasCompleteActionPerformed

    private void backButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseClicked
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) Dashboard.Main_panel.getLayout();
        cl.show(Dashboard.Main_panel, "home");

        System.out.println("Back pressed");
    }//GEN-LAST:event_backButtonMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton MarkasComplete;
    private javax.swing.JLabel QR;
    private javax.swing.JButton backButton;
    private javax.swing.JTextArea commentInputArea;
    private javax.swing.JPanel commentListPanel;
    private javax.swing.JScrollPane commentScrollPane;
    private javax.swing.JLabel durationLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea processArea;
    private javax.swing.JButton submitCommentButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}

