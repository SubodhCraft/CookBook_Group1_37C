/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LEGION
 */
public class UserData {

    private int id ;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String username;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String email;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String set_password;
    
    public String getset_Password() {
        return set_password;
    }

    public void setPassword(String set_password) {
        this.set_password = set_password;
    }
    
    private String confirm_password;
    
    public String getconfirm_Password() {
        return confirm_password;
    }

    public void setconfirm_Password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public UserData(String username, String email, String set_password, String confirm_password){
        this.username= username;
        this.email = email;
        this.set_password = set_password;
        this.confirm_password=confirm_password;
        
      
    }
    

}
