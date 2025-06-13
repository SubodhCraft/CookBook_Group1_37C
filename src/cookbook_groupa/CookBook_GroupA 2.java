/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cookbook_groupa;

import View.LandingPage;
import View.Logout;
import controller.Langinpage;
import controller.LogoutController;

/**
 *
 * @author LEGION
 */
public class CookBook_GroupA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

//        LandingPage LandingpageForm = new LandingPage();
//        Langinpage controller = new Langinpage();
        Logout logout=new Logout();
        LogoutController controller=new LogoutController(logout);
        controller.open();
        
    }
    
}
