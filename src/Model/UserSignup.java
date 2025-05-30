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
    
    private String full_name;
    
    private String create_password;
    
    private String confirm_password;
  
    public UserSignup(String email,String full_name,String create_password,String confirm_password){
        this.email=email;
        this.full_name=full_name;
        this.create_password=create_password;
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
    public String getFull_name() {
        return full_name;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public String getCreate_password() {
        return create_password;
    }
    public void setCreate_password(String create_password) {
        this.create_password = create_password;
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
