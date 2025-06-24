/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.Notes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author LEGION
 */
public class k extends javax.swing.JPanel {
    private JPanel notesContainer;
    private JScrollPane scrollPane;
    private List<JPanel> noteCards = new ArrayList<>();

    /**
     * Creates new form Self_Note
     */
    public k() {
//        setTitle("Self Notes");
//        setSize(500,400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        // Top-level panel with BorderLayout
//        mainPanel = new JPanel(new BorderLayout());
//        setContentPane(mainPanel);
//
//        // Header panel (with + button and labels)
//        headerPanel = new JPanel();
//        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        headerPanel.add(new JLabel("📝 Your Notes"));
//        addButton = new JButton("+");
//        headerPanel.add(addButton);
//
//        mainPanel.add(headerPanel, BorderLayout.NORTH);
//
//        // Notes container inside a scroll pane
//        notesContainer = new JPanel();
//        notesContainer.setLayout(new BoxLayout(notesContainer, BoxLayout.Y_AXIS));
//        scrollPane = new JScrollPane(notesContainer);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//
//        mainPanel.add(scrollPane, BorderLayout.CENTER);
//    
//    
        initComponents();
         notesContainer = new JPanel();
         notesContainer.setLayout(new BoxLayout(notesContainer, BoxLayout.Y_AXIS));
         notesContainer.setBackground(Color.WHITE);
         notesContainer.setAlignmentY(Component.TOP_ALIGNMENT);
         notesContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
      
         scrollPane = new JScrollPane(notesContainer);
         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         scrollPane.getVerticalScrollBar().setUnitIncrement(16);
         
         this.setLayout(new BorderLayout());
         this.add(scrollPane, BorderLayout.CENTER);
         
         jScrollPane2.setViewportView(notesContainer);
    }
    public Notes showNoteInputDialog() {
    JTextField titleField = new JTextField(20);
    JTextArea contentArea = new JTextArea(5, 20);
    contentArea.setLineWrap(true);
    contentArea.setWrapStyleWord(true);
    JScrollPane scrollPane = new JScrollPane(contentArea);

    JPanel panel = new JPanel(new BorderLayout(5, 5));
    panel.add(new JLabel("Title:"), BorderLayout.NORTH);
    panel.add(titleField, BorderLayout.CENTER);
    panel.add(new JLabel("Content:"), BorderLayout.SOUTH);

    JPanel contentPanel = new JPanel(new BorderLayout(5, 5));
    contentPanel.add(panel, BorderLayout.NORTH);
    contentPanel.add(scrollPane, BorderLayout.CENTER);

    int result = JOptionPane.showConfirmDialog(this, contentPanel, "Add Note", JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();
        if (!title.isEmpty() || !content.isEmpty()) {
            return new Notes(title, content);
        } else {
            JOptionPane.showMessageDialog(this, "Note cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    return null;
}
    
    public JPanel createNoteCard(Notes note, ActionListener deleteListener, ActionListener editListener) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.setBackground(new Color(245, 245, 245));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        JLabel title = new JLabel(note.getTitle());
        title.setFont(new Font("Serif", Font.BOLD, 16));
        
        JTextArea content = new JTextArea(note.getContent());
        content.setEditable(false);
        content.setLineWrap(true);
        content.setWrapStyleWord(true);
        content.setBackground(card.getBackground());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(title, BorderLayout.WEST);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton deleteBtn = new JButton("Delete");
        JButton editBtn = new JButton("Edit");
        
        deleteBtn.addActionListener(deleteListener);
        editBtn.addActionListener(editListener);
        
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        
        topPanel.add(buttonPanel, BorderLayout.EAST);

        card.add(topPanel, BorderLayout.NORTH);
        card.add(new JScrollPane(content), BorderLayout.CENTER);

        return card;
    }
    public void clearNotesContainer(){
        notesContainer.removeAll();
        notesContainer.revalidate();
        notesContainer.repaint();
    }
    
    public void addNoteCard(JPanel card){
        notesContainer.add(card);
        notesContainer.revalidate();
        notesContainer.repaint();
    }
    
// public void displayNotes(List<Notes> notes) {
//        notesContainer.removeAll();
//        for (Notes note : notes) {
//            JPanel noteCard = createNoteCard(note);
//            notesContainer.add(noteCard);
//        }
//        notesContainer.revalidate();
//        notesContainer.repaint();
// }
//    public JPanel createNoteCard(Notes note) {
//        JPanel card = new JPanel(new BorderLayout());
//        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//        card.setBackground(new Color(245, 245, 245));
//        card.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 100));
//
//        JLabel title = new JLabel(note.getTitle());
//        title.setFont(new Font("Serif", Font.BOLD, 16));
//        JTextArea content = new JTextArea(note.getContent());
//        content.setEditable(false);
//        content.setLineWrap(true);
//        content.setWrapStyleWord(true);
//        content.setBackground(card.getBackground());
//
//        JPanel topPanel = new JPanel(new BorderLayout());
//        topPanel.setOpaque(false);
//        topPanel.add(title, BorderLayout.WEST);
//
//        JButton deleteBtn = new JButton("Delete");
//        deleteBtn.addActionListener(deleteListener);
//        topPanel.add(deleteBtn, BorderLayout.EAST);
//
//        card.add(topPanel, BorderLayout.NORTH);
//        card.add(new JScrollPane(content), BorderLayout.CENTER);
//
//        return card;
//    }
public Notes showEditNoteDialog(Notes note) {
    JTextField titleField = new JTextField(note.getTitle());
    JTextArea contentArea = new JTextArea(note.getContent(), 5, 20);
    contentArea.setLineWrap(true);
    contentArea.setWrapStyleWord(true);
    JScrollPane scrollPane = new JScrollPane(contentArea);

    JPanel panel = new JPanel(new BorderLayout(5, 5));
    panel.add(new JLabel("Title:"), BorderLayout.NORTH);
    panel.add(titleField, BorderLayout.CENTER);
    panel.add(new JLabel("Content:"), BorderLayout.SOUTH);

    JPanel contentPanel = new JPanel(new BorderLayout(5, 5));
    contentPanel.add(panel, BorderLayout.NORTH);
    contentPanel.add(scrollPane, BorderLayout.CENTER);

    int result = JOptionPane.showConfirmDialog(null, contentPanel, "Edit Note", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
        String updatedTitle = titleField.getText().trim();
        String updatedContent = contentArea.getText().trim();

        if (!updatedTitle.isEmpty() || !updatedContent.isEmpty()) {
            note.setTitle(updatedTitle);
            note.setContent(updatedContent);
            return note;  // Return updated object
        } else {
            JOptionPane.showMessageDialog(null, "Note cannot be empty!");
        }
    }
    return null; // Cancelled or invalid
}

    public List<JButton> getAllDeleteButtons() {
        List<JButton> buttons = new ArrayList<>();
        for (java.awt.Component comp : notesContainer.getComponents()) {
            if (comp instanceof JPanel card) {
                JPanel topPanel = (JPanel) ((JPanel) card.getComponent(0));
                for (java.awt.Component c : topPanel.getComponents()) {
                    if (c instanceof JButton btn) {
                        buttons.add(btn);
                    }
                }
            }
        }
        return buttons;
    }

    /**
     * Creates new form k
     */
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SetNote = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLabel11.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(127, 1, 31));
        jLabel11.setText("Self Note");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/Screenshot 2025-06-15 193410.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel1.setText("You have not created any notes yet! ");

        jLabel2.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel2.setText("Click on the '+' button to add one.");

        javax.swing.GroupLayout SetNoteLayout = new javax.swing.GroupLayout(SetNote);
        SetNote.setLayout(SetNoteLayout);
        SetNoteLayout.setHorizontalGroup(
            SetNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SetNoteLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(SetNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SetNoteLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(SetNoteLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(SetNoteLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );
        SetNoteLayout.setVerticalGroup(
            SetNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SetNoteLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(SetNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(14, 14, 14)
                .addGroup(SetNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SetNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SetNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel SetNote;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
 public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Self_Note.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Self_Note.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Self_Note.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Self_Note.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Self_Note().setVisible(true);
            }
        });
    }
    
    
    public void addAddNoteListener (ActionListener listener){
    jButton1.addActionListener(listener);
}

public JPanel getNotesContainer(){
    return notesContainer;
}


}
