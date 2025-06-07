 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.LoginDao;
import View.Sigininframe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Model.LoginRequest;
import Model.UserData;
import View.EmailCheck;
import View.Security_Questions;
import View.SignupPart;
//import controller.LoginController.AddLoginListener.RegisterListener;
//import controller.Langinpage.RegisterListener;

/**
 *
 * @author ACER
 */
public class LoginController {
    private final LoginDao loginDao = new LoginDao();
    private final Sigininframe userView;
    
    public LoginController(Sigininframe userView){
        this.userView = userView;
        userView.addLoginUserListener(new AddLoginListener());
        userView.addRegisterListener(new RegisterListener());
        userView.addForgetPassListener(new ForgetPassListener());
    }
    public void open(){
        this.userView.setVisible(true);
        
    }
    public void close(){
        this.userView.dispose();
    }
    class AddLoginListener implements ActionListener{
        
        
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                String email = userView.getEmailField().getText();
                String password = new String(userView.getPasswordField().getPassword());
                LoginRequest user = new LoginRequest(email,password);
                
                boolean check = loginDao.validateUser(user);
                if (check){
                    JOptionPane.showMessageDialog(userView,"Login successful");
                    
                }else{
                    JOptionPane.showMessageDialog(userView,"Invalid Credentials");
                }
                
//                Sigininframe login = new Sigininframe();
//                login.setVisible(true);
//                LoginController controller = new LoginController(login);
//                controller.open();
                
                
//                UserData signInUser  = userDao.signIn(user);
////                new Sigininframe().setVisible(true);
//                
//                if(signInUser == null){
//                    JOptionPane.showMessageDialog(userView,"Login Successful");
//
//                }else{
//                    JOptionPane.showMessageDialog(userView,"Invalid Credentials");
//                    
//                    
//                    
//                }
            }catch(Exception ex){
                System.out.println("Error adding user: " + ex.getMessage());
            }
//            new Sigininframe().setVisible(true);
        }
        
//      
    }
    class RegisterListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            SignupPart signup = new SignupPart();
            signup.setVisible(true);
            SignUpController controller = new SignUpController(signup);
            controller.open();
        }
    }
    class ForgetPassListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String email = userView.getEmailField().getText();
            if (email.isEmpty()){
                JOptionPane.showMessageDialog(userView,"Please enter your email first:");
                return;
            }
            
           Security_Questions secure = new Security_Questions("verify",email);
           secure.setVisible(true);
           AuthController controller = new AuthController(secure);
           controller.open();
        }
        
    }
}

