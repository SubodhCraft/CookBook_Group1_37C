package controller;

import DAO.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Model.UserSignup;
import View.Sign;

public class SignUpController {
    private final UserDAO userDAO=new UserDAO();
    private final Sign userView;
    
    public SignUpController (Sign userView){
        this.userView = userView;
        userView.addAddUserListener(new AddUserListener());
    }
    public void open(){
        this.userView.setVisible(true);    
    }
    
    public void close(){
        this.userView.dispose();
        
    }
    class AddUserListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        try {
            String username=userView.getUsernameField().getText();
            String email = userView.getEmailField().getText();
            String set_password = userView.getpasswordField().getText();
            String confirm_password = userView.getConfirmPassword().getText();
            
            signUp(username, email, set_password, confirm_password);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occured:"+ex.getMessage());
        } 
    }
        public boolean signUp(String username, String email, String set_password,String confirm_password){ 
            if (username ==null || username.trim().isEmpty()){
                JOptionPane.showMessageDialog(null,"Username can't be empty");
                return false;
            }
            if (email == null || email.trim().isEmpty()){
                JOptionPane.showMessageDialog(null,"Email can't be empty");
                return false;
            }
            if (set_password == null || set_password.trim().isEmpty() || set_password.equals("Set Password")){
                JOptionPane.showMessageDialog(null,"Please enter a valid password");
                return false;
            }
            if (!set_password.equals(confirm_password)){
                JOptionPane.showMessageDialog(null,"Confirm password didn't match");
                return false;
            }
            if (userDAO.isEmailExists(email)){
                JOptionPane.showMessageDialog(null,"Email already exists!");
                return false;
            }
            UserSignup user = new UserSignup(username,email,set_password,confirm_password);
            boolean success = userDAO.Sign(user);
            
            if (success){
                JOptionPane.showMessageDialog(null,"User Signed up successfully!");
                
            }else{
                JOptionPane.showMessageDialog(null,"User Signed up failed!");
            }
            return success;
        }
    }
}
        
    


