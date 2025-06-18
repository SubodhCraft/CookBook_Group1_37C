/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import java.awt.*;
import javax.swing.*;

import DAO.NoteDao;
import Model.Notes;
import View.Self_Note;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author LEGION
 */
public class NoteController {
     private NoteDao dao;
    private Self_Note view;

    public NoteController(NoteDao dao, Self_Note view) {
        this.dao = dao;
        this.view = view;

        view.addAddNoteListener(new AddNoteButtonListener());
//        loadNotes();
    }
    public void open(){
        view.setVisible(true);
        loadNotes();
    }

    public void loadNotes() {
        try {
            List<Notes> notes = dao.getAllNotes();
            view.clearNotesContainer();
            
            if(notes.isEmpty()){
                JOptionPane.showMessageDialog(null,"No notes yet! Click on the + sign to add new notes");
            }else{
                for (Notes note : notes){
                JPanel notePanel = view.createNoteCard(note, e-> deleteNote(note.getId()), e-> showEditDialog(note));
                view.addNoteCard(notePanel);
            }
            
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Failed to load notes!"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
   

    public void addNote(String title, String content) {
        try {
            dao.addNote(new Notes(title, content));
            loadNotes();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Failed to add notes!"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateNote(Notes note) {
        try {
            dao.updateNote(note);
            loadNotes();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Failed toupdate notes!"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showEditDialog(Notes note){
        Notes updatedNote = view.showEditNoteDialog(note);
        if(updatedNote != null){
            updateNote(updatedNote);
        }
    }
    public void deleteNote(int id) {
        try {
            dao.deleteNote(id);
            loadNotes();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Failed to delete notes!"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void showNoteEditor(){
        Notes note = view.showNoteInputDialog();
        if(note != null){
            addNote(note.getTitle(),note.getContent());
        }
    } 
//    private void showNoteEditor() {
//        JTextField titleField = new JTextField(20);
//        JTextArea contentArea = new JTextArea(5, 20);
//        contentArea.setLineWrap(true);
//        contentArea.setWrapStyleWord(true);
//        JScrollPane scrollPane = new JScrollPane(contentArea);
//
//        JPanel panel = new JPanel(new BorderLayout(5, 5));
//        panel.add(new JLabel("Title:"), BorderLayout.NORTH);
//        panel.add(titleField, BorderLayout.CENTER);
//        panel.add(new JLabel("Content:"), BorderLayout.SOUTH);
//
//        JPanel contentPanel = new JPanel(new BorderLayout(5, 5));
//        contentPanel.add(panel, BorderLayout.NORTH);
//        contentPanel.add(scrollPane, BorderLayout.CENTER);
//
//        int result = JOptionPane.showConfirmDialog(null, contentPanel, "Add Note", JOptionPane.OK_CANCEL_OPTION);
//
//        if (result == JOptionPane.OK_OPTION) {
//            String title = titleField.getText().trim();
//            String content = contentArea.getText().trim();
//            if (!title.isEmpty() || !content.isEmpty()) {
//                addNote(title, content);
//            } else {
//                JOptionPane.showMessageDialog(null, "Note cannot be empty!");
//            }
//        }
//    }
//
//    private JPanel createNoteCard(Notes note) {
//        JPanel card = new JPanel();
//        card.setLayout(new BorderLayout());
//        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//        card.setBackground(new Color(245, 245, 245));
//        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
//
//        JLabel title = new JLabel(note.getTitle());
//        title.setFont(new Font("Serif", Font.BOLD, 16));
//        JTextArea content = new JTextArea(note.getContent());
//        content.setEditable(false);
//        content.setLineWrap(true);
//        content.setWrapStyleWord(true);
//        content.setBackground(card.getBackground());
//
//        JPanel topPanel = new JPanel(new BorderLayout());
//        topPanel.setOpaque(false);
//        topPanel.add(title, BorderLayout.WEST);
//
//        JButton deleteBtn = new JButton("Delete");
//        deleteBtn.addActionListener(deleteListener);
//        topPanel.add(deleteBtn, BorderLayout.EAST);
//
//        card.add(topPanel, BorderLayout.NORTH);
//        card.add(new JScrollPane(content), BorderLayout.CENTER);
//
//        return card;
//    }
    private class AddNoteButtonListener implements ActionListener{

        @Override
        public void actionPerformed (ActionEvent e) {
            showNoteEditor();
        }
        
    }
}
