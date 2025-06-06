/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cookbook_groupa;
//import Controller.SignUpController;
import Database.*;
import View.LandingPage;
import View.SignupPart;
import controller.Langinpage;

public class CookBook_GroupA {



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       LandingPage landingForm = new LandingPage();
       Langinpage landingController = new Langinpage(landingForm);
       landingController.open();
           
       }   
}  

