/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.UserLoginDAO;
import Model.LoginRequest;
import View.Sigininframe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class LoginController {
    private final UserLoginDAO userlogindao = new UserLoginDAO();
    private final Sigininframe userView;
    
    public LoginController(Signinframe userView){
        this.userView = userView;
        userView.addLoginUserListener(new AddUserListener());
        
    }
    public void open(){
        this.userView.setVisible(true);
        
    }
    public void close(){
        this.userView.dispose();
    }
    class AddUserListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                String email = userView.getEmailField().getText();
                String password = userView.getPasswordField().getText();
                LoginRequest user = new LoginRequest(email,password);
                UserSignup loginUser = UserLoginDAO.login(user);
                if(loginUser == null){
                    JOptionPane.showMessageDialog(userView,"Invalid Credentials");
                }else{
                    JOptionPane.showMessageDialog(userView,"Login Successful");
                    DashboardScreen dashboardView = new DashbpardScreen();
                    DashboardController dashboardcontroller = new DashboardController(dashboardview);
                    close();
                    dashboardController.open();
                    
                }
            }catch(Exception ex){
                System.out.println("Error adding user: " + ex.getMessage());
            }
        }
    }
}
