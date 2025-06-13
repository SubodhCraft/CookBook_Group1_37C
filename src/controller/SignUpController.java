/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import View.SignupPart;

/**
 *
 * @author Acer
 */
public class SignUpController {
    private final SignupPart signup;
    public SignUpController(SignupPart signup){
        this.signup=signup;
        
        
    }
    public void open(){
        this.signup.setVisible(true);
        
    }
    public void close(){
        this.signup.dispose();
    }
}
