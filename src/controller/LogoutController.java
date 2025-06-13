/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import View.Logout;
import View.SignupPart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author chandrabahadurkc
 */
public class LogoutController {
    public final Logout logout;
    public LogoutController(Logout logout){
        this.logout=logout;
        logout.addConfirmListener(new ConfirmListener());
        logout.addCancelListener(new CancelListener());
    } 
    public void open(){
        this.logout.setVisible(true);
        
    }
    public void close(){
        this.logout.dispose();
    }
    class ConfirmListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           SignupPart signup=new SignupPart();
           SignUpController controller=new SignUpController(signup); 
           controller.open();
           close();
        }
        
        
    }
    class CancelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        close();
        }
        
    }
}
