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

//        view.addAddNoteListener(new AddNoteButtonListener());
        loadNotes();
    }
    public void open(){
        view.setVisible(true);
        loadNotes();
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

}

//     public void loadNotes() {
//         try {
//             int userId = LoggedInUser.getId();
//             List<Notes> notes = dao.getNotesByUser(userId);
//             view.clearNotesContainer();
            
//             if(notes.isEmpty()){
//                 JOptionPane.showMessageDialog(null,"No notes yet! Click on the + sign to add new notes");
//             }else{
//                 for (Notes note : notes){
//                 JPanel notePanel = view.createNoteCard(note, e-> deleteNote(note.getId(), userId), e-> showEditDialog(note));
//                 view.addNoteCard(notePanel);
//             }
            
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//             JOptionPane.showMessageDialog(null,"Failed to load notes!"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
//         }
//     }
   

//     public void addNote(String title, String content) {
//         try {
//             int userId= LoggedInUser.getId();
// //            Notes note = new Notes (title, content);
// //            note.setUserId(userId);
//             dao.addNote(new Notes(title, content, userId));
//             loadNotes();
//         } catch (Exception e) {
//             e.printStackTrace();
//             JOptionPane.showMessageDialog(null,"Failed to add notes!"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
//         }
//     }

//     public void updateNote(Notes note) {
//         try {
//             note.setUserId(LoggedInUser.getId());
//             dao.updateNote(note);
//             loadNotes();
//         } catch (Exception e) {
//             e.printStackTrace();
//             JOptionPane.showMessageDialog(null,"Failed toupdate notes!"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
//         }
//     }

//     private void showEditDialog(Notes note){
//         Notes updatedNote = view.showEditNoteDialog(note);
//         if(updatedNote != null){
// //            updatedNote.setUserId(LoggedInUser.getId());
//             updateNote(updatedNote);
//         }
//     }
//     public void deleteNote(int id, int userId) {
//         try {
//             dao.deleteNote(id, userId);
//             loadNotes();
//         } catch (Exception e) {
//             e.printStackTrace();
//             JOptionPane.showMessageDialog(null,"Failed to delete notes!"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
//         }
//     }
//     private void showNoteEditor(){
//         Notes note = view.showNoteInputDialog();
//         if(note != null){
//             note.setUserId(LoggedInUser.getId());
//             dao.addNote(note);
//             loadNotes();
// //            addNote(note.getTitle(),note.getContent());
//         }
//     } 
// //    private void showNoteEditor() {
// //        JTextField titleField = new JTextField(20);
// //        JTextArea contentArea = new JTextArea(5, 20);
// //        contentArea.setLineWrap(true);
// //        contentArea.setWrapStyleWord(true);
// //        JScrollPane scrollPane = new JScrollPane(contentArea);
// //
// //        JPanel panel = new JPanel(new BorderLayout(5, 5));
// //        panel.add(new JLabel("Title:"), BorderLayout.NORTH);
// //        panel.add(titleField, BorderLayout.CENTER);
// //        panel.add(new JLabel("Content:"), BorderLayout.SOUTH);
// //
// //        JPanel contentPanel = new JPanel(new BorderLayout(5, 5));
// //        contentPanel.add(panel, BorderLayout.NORTH);
// //        contentPanel.add(scrollPane, BorderLayout.CENTER);
// //
// //        int result = JOptionPane.showConfirmDialog(null, contentPanel, "Add Note", JOptionPane.OK_CANCEL_OPTION);
// //
// //        if (result == JOptionPane.OK_OPTION) {
// //            String title = titleField.getText().trim();
// //            String content = contentArea.getText().trim();
// //            if (!title.isEmpty() || !content.isEmpty()) {
// //                addNote(title, content);
// //            } else {
// //                JOptionPane.showMessageDialog(null, "Note cannot be empty!");
// //            }
// //        }
// //    }
// //
// //    private JPanel createNoteCard(Notes note) {
// //        JPanel card = new JPanel();
// //        card.setLayout(new BorderLayout());
// //        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
// //        card.setBackground(new Color(245, 245, 245));
// //        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
// //
// //        JLabel title = new JLabel(note.getTitle());
// //        title.setFont(new Font("Serif", Font.BOLD, 16));
// //        JTextArea content = new JTextArea(note.getContent());
// //        content.setEditable(false);
// //        content.setLineWrap(true);
// //        content.setWrapStyleWord(true);
// //        content.setBackground(card.getBackground());
// //
// //        JPanel topPanel = new JPanel(new BorderLayout());
// //        topPanel.setOpaque(false);
// //        topPanel.add(title, BorderLayout.WEST);
// //
// //        JButton deleteBtn = new JButton("Delete");
// //        deleteBtn.addActionListener(deleteListener);
// //        topPanel.add(deleteBtn, BorderLayout.EAST);
// //
// //        card.add(topPanel, BorderLayout.NORTH);
// //        card.add(new JScrollPane(content), BorderLayout.CENTER);
// //
// //        return card;
// //    }
//     private class AddNoteButtonListener implements ActionListener{

//         @Override
//         public void actionPerformed (ActionEvent e) {
//             showNoteEditor();
//         }
        
//     }

