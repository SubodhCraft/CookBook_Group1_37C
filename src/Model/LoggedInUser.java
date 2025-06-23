/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LEGION
 */
public class LoggedInUser {
    private static int id;
    private static String username;
    private static String email;
    
    public static void setUser(UserData user){
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
    }
    
    public static void setId(int userId){
        id = userId;
    }
    public static int getId(){
        return id;
    }
    public static String getUsername(){
        return username;
    }
    public static String getEmail(){
        return email;
    }
    public static void clear(){
        id = 0;
        username = null;
        email = null;
    }
}
