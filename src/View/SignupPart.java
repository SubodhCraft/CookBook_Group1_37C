/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import java.awt.event.ActionListener;
//import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class SignupPart extends javax.swing.JFrame {

    /**
     * Creates new form SignupPart
     */
//    private javax.swing.JLabel emailerrorLabel;
    
    public SignupPart() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
//    emailerrorLabel = new javax.swingJLabel();
//    emailerrorLabel.setForeground(Color.RED);
//    emailerrorLabel.setText("");
//    
//    email

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jEmail = new javax.swing.JTextField();
        BsignUp = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        BsignIn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtsetpass = new javax.swing.JTextField();
        TxtConfirm = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SignUp");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 201, 75));
        jPanel1.setToolTipText("SignUp");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/logo 2.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/WhatsApp_Image_2025-05-26_at_1.03.28_PM-removebg-preview.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 590));

        jPanel2.setBackground(new java.awt.Color(255, 231, 195));

        jLabel3.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel3.setText("Create Account ");

        jUsername.setText("Enter your name");
        jUsername.setAutoscrolls(false);
        jUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jUsernameFocusLost(evt);
            }
        });
        jUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUsernameActionPerformed(evt);
            }
        });

        jLabel4.setText("Username");

        jLabel5.setText("Email");

        jEmail.setText("Enter your email");
        jEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jEmailFocusLost(evt);
            }
        });
        jEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEmailActionPerformed(evt);
            }
        });

        BsignUp.setBackground(new java.awt.Color(127, 1, 31));
        BsignUp.setForeground(new java.awt.Color(255, 255, 255));
        BsignUp.setText("Sign Up");
        BsignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BsignUpActionPerformed(evt);
            }
        });

        jLabel6.setText("Already have an account? ");

        BsignIn.setBackground(new java.awt.Color(255, 231, 195));
        BsignIn.setText("Sign In");
        BsignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BsignInActionPerformed(evt);
            }
        });

        txtsetpass.setText("Create password");
        txtsetpass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtsetpassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtsetpassFocusLost(evt);
            }
        });
        txtsetpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsetpassActionPerformed(evt);
            }
        });

        TxtConfirm.setText("Confirm Password");
        TxtConfirm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtConfirmFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtConfirmFocusLost(evt);
            }
        });

        jLabel8.setText("Set Password");

        jLabel10.setText("Confirm Password ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(BsignIn))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(BsignUp))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtConfirm)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jEmail)
                                    .addComponent(txtsetpass)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel3)
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtsetpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(BsignUp)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(BsignIn))
                .addContainerGap(152, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 0, 330, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BsignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BsignUpActionPerformed
        // TODO add your handling code here:
//        JOptionPane.showMessageDialog(SignupPart.this,"Account created successfully!");
    }//GEN-LAST:event_BsignUpActionPerformed

    private void jEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jEmailActionPerformed

    private void jEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jEmailFocusLost
        // TODO add your handling code here:
        if (jEmail.getText().isEmpty()){
            jEmail.setText("Enter your email");
        }
    }//GEN-LAST:event_jEmailFocusLost

    private void jEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jEmailFocusGained
        // TODO add your handling code here:
        if (jEmail.getText().equals("Enter your email")){
            jEmail.setText("");
        }
    }//GEN-LAST:event_jEmailFocusGained

    private void jUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jUsernameActionPerformed

    private void jUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUsernameFocusLost
        // TODO add your handling code here:
        if (jUsername.getText().isEmpty()){
            jUsername.setText("Enter your name");
        }
    }//GEN-LAST:event_jUsernameFocusLost

    private void jUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUsernameFocusGained
        // TODO add your handling code here:
        if (jUsername.getText().equals("Enter your name")){
            jUsername.setText("");
        }
    }//GEN-LAST:event_jUsernameFocusGained

    private void txtsetpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsetpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsetpassActionPerformed

    private void txtsetpassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsetpassFocusGained
        // TODO add your handling code here:
        if (txtsetpass.getText().equals("Create password")){
            txtsetpass.setText("");
        }
    }//GEN-LAST:event_txtsetpassFocusGained

    private void BsignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BsignInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BsignInActionPerformed

    private void txtsetpassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsetpassFocusLost
        // TODO add your handling code here:
        if (txtsetpass.getText().isEmpty()){
            txtsetpass.setText("Create password");
        }
    }//GEN-LAST:event_txtsetpassFocusLost

    private void TxtConfirmFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtConfirmFocusGained
        // TODO add your handling code here:
        if (TxtConfirm.getText().equals("Confirm Password")){
            TxtConfirm.setText("");
        }
    }//GEN-LAST:event_TxtConfirmFocusGained

    private void TxtConfirmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtConfirmFocusLost
        // TODO add your handling code here:
        if (TxtConfirm.getText().isEmpty()){
            TxtConfirm.setText("Confirm Password");
        }
    }//GEN-LAST:event_TxtConfirmFocusLost

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(SignupPart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignupPart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignupPart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignupPart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignupPart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BsignIn;
    private javax.swing.JButton BsignUp;
    private javax.swing.JTextField TxtConfirm;
    private javax.swing.JTextField jEmail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jUsername;
    private javax.swing.JTextField txtsetpass;
    // End of variables declaration//GEN-END:variables

public void addAddUserListener(ActionListener listener) {
        BsignUp.addActionListener(listener);
    }
public void addLoginListener(ActionListener listener) {
        BsignIn.addActionListener(listener);
    }
    

    
    /**
     * @return the companynameField
     */
    public javax.swing.JTextField getUsernameField() {
        return jUsername;
        
    }
    
    
    /**
     * @return the emailField
     */
    public javax.swing.JTextField getEmailField() {
        return jEmail;
        
    }

    /**
     * @return the passwordField
     */
     public javax.swing.JTextField getSetPasswordField() {
         return txtsetpass;
     }

    /**
     * @return the usernameField
     */
    public javax.swing.JTextField getConfrimPassword() {
        return TxtConfirm;
    }


}
