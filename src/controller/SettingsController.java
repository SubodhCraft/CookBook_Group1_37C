/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.SettingsDAO;
import Model.LoggedInUser;
import View.UserSettings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author LEGION
 */
public class SettingsController {
    private final UserSettings settingsView;
    
    public SettingsController(UserSettings View) {
        this.settingsView = View;
        loadUserInfo();
        
//        settingsView.add
    }
    
    private void loadUserInfo(){
        String username = LoggedInUser.getUsername();
        settingsView.setUsername(username);
    }
//    private final SettingsDAO settingsDao = new SettingsDAO();
//    private final UserSettings settingsView;
//    
//    public SettingsController(UserSettings view){
//        this. settingsView = view;
//        this.settingsView.addSettingsListener(new SettingsListener());
//    }
//    
//    void open(){
//        
//    }
//    void close(){
//        
//    }
//    
//    public void setupSettingsListener(UserSettings view){
//        view.addSettingsListener(new SettingsListener());
//    }
//    
//    class SettingsListener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Settings Button clicked!");
//            UserSettings userSettings = new UserSettings();
//            new S
//        }
//        
//    }
}
