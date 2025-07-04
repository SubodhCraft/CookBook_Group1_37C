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

}
