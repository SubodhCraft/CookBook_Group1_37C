/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.AuthDao;
import Model.SecAnswers;
import View.EmailCheck;
import View.NewPassword;
import View.ResetPass;
import View.Security_Questions;
import View.Sigininframe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author LEGION
 */
public class AuthController {
//    private final Security_Questions view;
    private final AuthDao authdao= new AuthDao();
//    private EmailCheck userView;
    private Security_Questions userView1;
    private ResetPass userView2;
    
//    public AuthController(EmailCheck userView){
//        this.userView = userView;
//        userView.addAddCheckListener(new AddCheckListener());
        
//        authdao = new AuthDao();
        
//    }
    public AuthController(Security_Questions userView1){
        this.userView1 = userView1;
        this.userView1.addSubmitListener(new SubmitListener());
    }
//    public void open(){
//        this.userView1.setVisible(true);
//    }
    
    public AuthController(ResetPass userView2){
        this.userView2 = userView2;
        userView2.addContinueListener(new ContinueListener());
    }
    
    public void open() {
    if (userView1 != null) {
        userView1.setVisible(true);
//    } else if (userView2 != null) {
//        userView2.setVisible(true);
    } 
     else {
        System.out.println("No view to open.");
    }
}

//    public void close(){
//        this.userView.dispose();
//    }
    public boolean checkSecurityAnswers(String email, String[] answers){
        return authdao.validateSecurityAnswers(email, answers);
    }
    public boolean updatePassword(String email, String newPassword){
        return authdao.updatePassword(email,newPassword);
    }
//    public boolean handleSecurityCheck(String email, String[] answers, JFrame currentFrame) {
//    boolean valid = checkSecurityAnswers(email, answers);
//    if (valid) {
//        JOptionPane.showMessageDialog(currentFrame, "Verified! Set your new password.");
//        new NewPassword(email).setVisible(true);
//        currentFrame.dispose();
//    } else {
//        JOptionPane.showMessageDialog(currentFrame, "Incorrect answers. Try again.");
//    }
//    return valid;
//    
//}
//    public void goToSecurityQuestions(String email, JFrame currentFrame){
//        new View.Security_Questions(email).setVisible(true);
//        currentFrame.dispose();
//    }
//    class AddCheckListener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
////             String email = userView.getEmailField().getText();
////            Security_Questions reset=new Security_Questions( );
////            reset.setVisible(true);
////            AuthController controller = new AuthController(reset);
////            controller.open();
////            
////             userView.dispose();
//        }
//      
//        
//    }
    class SubmitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("got it");
            String email = userView1.getEmailField().getText().trim();
                String answer1 = userView1.getanswer1().getText().trim();
                String answer2 = userView1.getanswer2().getText().trim();
                String answer3 = userView1.getanswer3().getText().trim();
//            AuthDao authdao = new AuthDao(); 
            try{
//                String email = userView1.getEmailField().getText();
//                String answer1 = userView1.getanswer1().getText();
//                String answer2 = userView1.getanswer2().getText();
//                String answer3 = userView1.getanswer3().getText();
                if (email.isEmpty() || answer1.isEmpty() || answer2.isEmpty() || answer3.isEmpty()){
                    JOptionPane.showMessageDialog(userView1,"Please fill in all fields.");
                    return;
                }
                if(userView1.getMode().equals("store")){
                    if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
                        JOptionPane.showMessageDialog(userView1,"Invalid email format. Must include'@' and be valid");
                        return;
                    }
                    if(!authdao.emailExists(email)){
                        JOptionPane.showMessageDialog(userView1,"This email is not registered!");
                        return;
                    }
//                    SecAnswers user = new SecAnswers(email,answer1,answer2,answer3)
                
                SecAnswers user = new SecAnswers(email,answer1,answer2,answer3);
                authdao.insertSecurityAnswers(user);
//                JOptionPane.showMessageDialog(user)
                
                JOptionPane.showMessageDialog(userView1,"Security questions stored successfully.");
//                userView1.dispose();
                
                Sigininframe signin = new Sigininframe();
                signin.setVisible(true);
                new LoginController(signin).open();
//                LoginController controller = new LoginController(signin);
//                controller.open();
                
                userView1.dispose();
                }else if (userView1.getMode().equals("verify")){
                    if(!authdao.emailExists(email)){
                        JOptionPane.showMessageDialog(userView1,"This email is not registered!");
                        return;
                    }
                  boolean valid = checkSecurityAnswers(email,new String[]{answer1,answer2,answer3});
                  if (valid){
                      JOptionPane.showMessageDialog(userView1,"Answers verified! Set your new password.");
                      ResetPass reset = new ResetPass(email);
                      reset.setVisible(true);
                      AuthController controller = new AuthController(reset);
                      controller.open();
                      userView1.dispose();
                  }else{
                      JOptionPane.showMessageDialog(userView1,"Incorrect answers. Try again!");
                  }
                }
            }catch (Exception ex) {
               JOptionPane.showMessageDialog(userView1,"Something went wrong:" + ex.getMessage());
                ex.printStackTrace();
            }
//            String email = userView1.getEmailField().getText();
//            Security_Questions reset=new Security_Questions("store",email );
//            reset.setVisible(true);
//            AuthController controller = new AuthController(reset);
//            controller.open();
//            
//            userView1.dispose();
                
            
//            Sigininframe signin = new Sigininframe();
//            signin.setVisible(true);
//            LoginController controller = new LoginController(signin);
//            controller.open();
                
//            ResetPass setnew= new ResetPass();
//            setnew.setVisible(true);
//            AuthController controller = new AuthController(setnew);
//            controller.open();
//            
        }
    
}
    class ContinueListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String newPassword = userView2.getNewPasswordField().trim();
            String confirmPassword = userView2.getConfirmPasswordField().trim();
             if (!newPassword.equals(confirmPassword)){
                 JOptionPane.showMessageDialog(userView2,"Passwords didn't match!");
                 return;
                 
                         
             }
             if(newPassword.length()<7){
                 JOptionPane.showMessageDialog(userView2,"Password must be atleast 7 characters.");
             }
             String email= userView2.getEmail();
             boolean success = updatePassword(email,newPassword);
             if(success){
                 JOptionPane.showMessageDialog(userView2,"Password updated successfully!");
//                 new Sigininframe().setVisible(true);
//                 userView2.dispose();
                 Sigininframe login = new Sigininframe();
                 login.setVisible(true);
                 LoginController controller = new LoginController(login);
                 controller.open();
             }else{
                 JOptionPane.showMessageDialog(userView2,"Failed to update password.");
             }
            
        }
        
    }
    
//        class addSubmitListener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            ResetPass setnew= new ResetPass();
//            setnew.setVisible(true);
//            AuthController controller = new AuthController(setnew);
//            controller.open();
//            System.out.println("got it");
//        }
    
}


 