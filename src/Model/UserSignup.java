/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Acer
 */
public class UserSignup {
    
    private int id;
    private String email;
    
    private String username;
    
    private String set_password;
    
    private String confirm_password;
  
    public UserSignup(String email,String username,String set_password,String confirm_password){
        this.email=email;
        this.username=username;
        this.set_password=set_password;
        this.confirm_password=confirm_password;
       
    }
    public UserSignup(){
        
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getSet_password() {
        return set_password;
    }
    public void setSet_password(String set_password) {
        this.set_password = set_password;
    }
    public String getConfirm_password() {
        return confirm_password;
    }
    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
    
    public int getID(){
        return id;
    }
     public void setID(int id){
         this.id = id;
     }
  
}
