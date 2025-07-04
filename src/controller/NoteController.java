/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import java.awt.*;
import javax.swing.*;

import DAO.NoteDao;
import Model.LoggedInUser;
import Model.Notes;
import View.SelfNote;
import View.UserMyProfile;
import View.UserSettings;
//import View.Self_Note;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author LEGION
 */
public class NoteController {
     private final  NoteDao dao;
    private final SelfNote view;

    public NoteController(SelfNote view) {
        this.dao = new NoteDao();
        this.view = view;

        view.addSettingsListener(new SettingsListener());
        view.addUserMyProfileListener(new MyProfileListener());
//        view.addBookmarkListener(new BookmarkListener());
        loadNotes();
    }
    public void open(){
        view.setVisible(true);
        loadNotes();
    }
    public void dispose(){
        view.setVisible(true);
    }

    public void loadNotes() {
        view.clearNotePanel();
        List<Notes> notes = dao.getNotesByUserId(LoggedInUser.getId());
        if (notes.isEmpty()) {
            view.showEmptyMessage();
        } else {
            for (Notes note : notes) {
                view.addNoteToPanel(note, this);
            }
        }
    }

    public void saveNote(Notes note) {
        int userId = LoggedInUser.getUser() != null ? LoggedInUser.getUser().getId() : LoggedInUser.getId();
        note.setUserId(userId);
        
        System.out.println("DEBUG: saving note with user_id: "+ userId);
        System.out.println("Note Title: "+note.getTitle());
        System.out.println("Note Content: "+note.getContent());
        System.out.println("Trying to save note for user_id: "+note.getUserId());
        
        boolean success = dao.addNote(note);
        if(success){
            loadNotes();
        }else{
            JOptionPane.showMessageDialog(view, "Failed to save note.");
        }
//        if (dao.addNote(note)) loadNotes();
    }

    public List<Notes> getNotesByUserId(int userId) {
    return dao.getNotesByUserId(userId);
}

public void updateNote(Notes note) {
    
    note.setUserId(LoggedInUser.getId());
    boolean success = dao.updateNote(note);
    if (success) {
        view.refreshNotes();
    } else {
        JOptionPane.showMessageDialog(view, "Failed to update note.");
    }
}

public void deleteNote(int noteId) {
    boolean success = dao.deleteNote(noteId);
    if (success) {
        view.refreshNotes();
    } else {
        JOptionPane.showMessageDialog(view, "Failed to delete note.");
    }
}

class SettingsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            UserSettings set = new UserSettings();
            AuthController control = new AuthController(set);
            set.setVisible(true);
            control.open();
            view.dispose();
        }

}

class MyProfileListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            UserMyProfile profile = new UserMyProfile();
//            AuthController control = new AuthController(profile);
            profile.setVisible(true);
//            control.open();
        }

}
}


class AdminListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}

