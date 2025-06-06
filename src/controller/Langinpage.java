package controller;



import View.LandingPage;
import View.Sigininframe;
import View.SignupPart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Langinpage {
    private final LandingPage userview;
    
    public Langinpage(LandingPage userView){
        this.userview = userView;
        userview.addRegisterListener(new RegisterListener());
        userview.addLoginListener(new LoginListener());
    }
    
    public void open(){
        this.userview.setVisible(true);
    }
    
    public void close(){
        this.userview.dispose();
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
