/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import cookbook.Bookmark;
import cookbook.Home;
import cookbook.admin_dashboard;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookmarkController {
    private admin_dashboard dashboardView;
    private Home homeView;
    private Bookmark bookmarkView;

    public BookmarkController(admin_dashboard dashboardView, Home homeView, Bookmark bookmarkView) {
        this.dashboardView = dashboardView;
        this.homeView = homeView;
        this.bookmarkView = bookmarkView;

       
    }

}


