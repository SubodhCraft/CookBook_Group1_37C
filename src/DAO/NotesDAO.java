/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.MySqlConnection;
import Model.NoteModel;
import Model.Notes;
import Model.SelfNote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author angelapradhan
 */
public class NotesDAO {
    MySqlConnection mysql = new MySqlConnection();
    
//     private Connection conn;

//    public NoteDao(Connection conn) {
//        this.conn = conn;
//    }

    public List<SelfNote> getAllNotesByUser(int userId)  {
        List<SelfNote> notes = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM Notes WHERE user_id = ? ORDER BY updated_at DESC";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,userId);
        
        
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            notes.add(new SelfNote(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getInt("user_id")
            ));
            
        }
        
//            notes.add(new Notes(rs.getInt("id"), rs.getString("title"), rs.getString("content")));
//        }
//        return notes;
    }catch (SQLException ex){
        Logger.getLogger(NotesDAO.class.getName()).log(Level.SEVERE, null, ex);
    }finally {
            mysql.closeConnection(conn);
        }
        return notes;
}

    public void addNote(SelfNote note) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO Notes (title, content, user_id) VALUES (?, ?, ?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
        pstmt.setString(1, note.getTitle());
        pstmt.setString(2, note.getDescription());
        pstmt.setInt(3, note.getUserId());
        pstmt.executeUpdate();
    }catch (SQLException ex){
        Logger.getLogger(NotesDAO.class.getName()).log(Level.SEVERE,null,ex);
    }finally {
            mysql.closeConnection(conn);
        }
    }

    public void updateNote(SelfNote note){
         Connection conn = mysql.openConnection();
        String sql = "UPDATE Notes SET title=?, content=? WHERE id=? AND user_id = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
        pstmt.setString(1, note.getTitle());
        pstmt.setString(2, note.getDescription());
        pstmt.setInt(3, note.getId());
        pstmt.setInt(4, note.getUserId());
        pstmt.executeUpdate();
    }catch(SQLException ex){
       Logger.getLogger(NotesDAO.class.getName()).log(Level.SEVERE,null,ex); 
    }finally {
            mysql.closeConnection(conn);
        }
    }

    public void deleteNote(int id, int userId) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM Notes WHERE id=? and user_id = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
        pstmt.setInt(1, id);
        pstmt.setInt(2, userId);
        pstmt.executeUpdate();
    }catch(SQLException ex){
        Logger.getLogger(NotesDAO.class.getName()).log(Level.SEVERE,null,ex); 
    }finally {
            mysql.closeConnection(conn);
        }
    }
}

