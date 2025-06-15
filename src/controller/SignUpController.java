package controller;
import DAO.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Model.UserData;
import View.Security_Questions;
import View.SignupPart;
import View.Sigininframe;
import controller.LoginController;

/**
 *
 * @author Acer
 */
public class SignUpController {
     private final UserDao user = new UserDao();
    private final SignupPart userView;
//    private final Sigininframe Userview;

    public SignUpController(SignupPart userView) {
        this.userView = userView;     
        userView.addAddUserListener(new AddUserListener());
            userView.addLoginListener(new LoginListener()); 
            
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { 
            UserDao userDao = new UserDao(); 
            try {
                String username = userView.getUsernameField().getText();
                String email = userView.getEmailField().getText();
                String set_password = userView.getSetPasswordField().getText();
                String confirm_password = userView.getConfrimPassword().getText();
                
                
                UserData user = new UserData(username,email,set_password,confirm_password);
                 
                 
                boolean check = userDao.CheckUser(user);
                if(check){
                    JOptionPane.showMessageDialog(userView,"User Already Exists");
                }else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
                    JOptionPane.showMessageDialog(userView,"Enter a Valid Email");
                } else if (!set_password.equals(confirm_password)){
                    JOptionPane.showMessageDialog(userView,"Password doesn't match");
            
                }else {
                    userDao.UserDao(user);
                    JOptionPane.showMessageDialog(null, "Registration successful Redirecting to Login...");
                    userView.dispose();
                    
                    Security_Questions reset=new Security_Questions("store",email);
                    reset.setVisible(true);
                    AuthController controller = new AuthController(reset);
                    controller.open();
                    
//                    Sigininframe signIn = new Sigininframe();
//                    signIn.setVisible(true);
//                    Sigininframe userloginform = new Sigininframe();
//                    LoginController controller = new LoginController(userloginform);
//                    controller.open();
                }
//                signUpp(username,email,set_password,confirm_password);
//                String UserData(username, email, set_password, confirm_password);
                

//                if (check) {
//                  JOptionPane.showMessageDialog(userView, "Duplicate user");
//               } else {
//                    userDao.Createsignup(user);
////
//                }
            } catch (Exception ex) {
                System.out.println("Error Adding user" + ex.getMessage());
//                ex.printStackTrace();
//            
//                JOptionPane.showMessageDialog(null,"Error adding user: " + ex.getMessage());
            }
//            Security_Questions reset=new Security_Questions("store",email);
//            reset.setVisible(true);
//            AuthController controller = new AuthController(reset);
//            controller.open();

        }
//        public boolean signUpp(String username, String email, String set_password, String confirm_password) {
////            UserDao userDao = new UserDao(); 
//           if (username == null || username.trim().isEmpty()) {
//                JOptionPane.showMessageDialog(null, "Username cannot be empty.");
//                return false;
//            }
////           
//
//            if (email == null || email.trim().isEmpty()) {
//                JOptionPane.showMessageDialog(userView, "Email cannot be empty.");
//                return false;
////                if (!email.contains("@")) {
////                JOptionPane.showMessageDialog(null, "Enter a valid email");
////                return false;
////            }
//            }
//
//            if (set_password == null || set_password.trim().isEmpty() || set_password.equals("Set Password")) {
//                JOptionPane.showMessageDialog(userView, "Please enter a valid password.");
//                return false;
//            }
//
//            if (!set_password.equals(confirm_password)) {
//                JOptionPane.showMessageDialog(userView, "Confirm password didn't match!");
//                return false;
//            }
//
//            if (userDao.isEmailExists(email)) {
//                JOptionPane.showMessageDialog(userView, "Email already in use!");
//                return false;
//            }
//
//            UserData user = new UserData(username, email, set_password, confirm_password);
//            boolean success = userDao.CheckUser(user);
//
//            if (success) {
//                JOptionPane.showMessageDialog(userView, "User already exists!");
//             
//            } else if (!email.contains("@")){
//                JOptionPane.showMessageDialog(userView,"Enter a Valid Email");
//            } else if (!set_password.equals(confirm_password)){
//                JOptionPane.showMessageDialog(userView,"Password doesn't Match");
//            
//            }else {
//                userDao.UserDao(user);
//                    JOptionPane.showMessageDialog(null, "Registration successful Redirecting to Login...");
//                    userView.dispose();
//                    
//                    Sigininframe signIn = new Sigininframe();
//                    signIn.setVisible(true);
//                    Sigininframe userloginform = new Sigininframe();
//                    LoginController controller = new LoginController(userloginform);
//                    controller.open();
////                    close();
//
//                }
//               return success;
//            }
        }          
//        class AddLoginListener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            Sigininframe loginView = new Sigininframe();
//            LoginController login = new LoginController(loginView);
//            close();
//            login.open();
//        }
    
//        } 
//class 
class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Sigininframe signin = new Sigininframe();
            signin.setVisible(true);  
            LoginController controller = new LoginController(signin);
            controller.open();
        }
        
    }    

    }
    
    

  
         

