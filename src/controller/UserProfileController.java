/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.UserDao;
import javax.swing.JOptionPane;

import Model.LoggedInUser;
import Model.UserData;
import View.UserMyProfile;
import View.UserMyProfile2nd;

import javax.swing.JFileChooser;

/**
 *
 * @author LEGION
 */
public class UserProfileController {
    private final UserMyProfile view;
    private final UserDao userDao;
    
    public UserProfileController (UserMyProfile view, UserDao userDAO) {
        this.view = view;
        this.userDao = userDAO;

//        view.addRecipeStatusActionListener(e -> openUserProfile2ndView());
        loadUserData();
//        initListeners();
    }

    private void loadUserData() {
        int userId = LoggedInUser.getId();
        UserData user = userDao.getUserById(userId);
        if (user != null) {
            view.setUserInfo(user);
        } else {
            JOptionPane.showMessageDialog(view, "Failed to load user info.");
        }
    }
//    public void openUserProfile2ndView() {
//    UserData loggedUser = LoggedInUser.getUser();
//
//    if (loggedUser != null) {
//        String imagePath = loggedUser.getProfilePictureUrl();
//
//        UserMyProfile2nd second = new UserMyProfile2nd();
//        second.setUserProfilePicture(imagePath);
//        second.setVisible(true);
//
//        view.dispose();  // Assuming 'view' is a reference to UserMyProfile
//    } else {
//        JOptionPane.showMessageDialog(view, "User not logged in. Please log in again.");
//    }
}

    
    
//    private void initListeners(){
//        view.addProfileListener( e -> handleProfilePictureChange());
//    }
//    
//    private void handleProfilePictureChange(){
//        JFileChooser fileChooser = new JFileChooser();
//        int result = fileChooser.showOpenDialog(null);
//        
//        if(result ==JFileChooser.APPROVE_OPTION){
//            File result = fileChooser.getSelectedFile();
//            
//            try{
//                File dir = new File("profile_pictures");
//                if (!dir.exists()) dir.mkdir
//            }
//        }
//    }
//}
