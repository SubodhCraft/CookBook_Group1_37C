/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Notes;
import java.sql.*;
import java.util.*;

/**
 *
 * @author LEGION
 */
public class NoteDao {
    MySqlConnection mysql = new MySqlConnection();
    
//     private Connection conn;

//    public NoteDao(Connection conn) {
//        this.conn = conn;
//    }

    public List<Notes> getAllNotes()  {
        List<Notes> notes = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM Notes ORDER BY updated_at DESC";
        try(PreparedStatement pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = pstmt.executeQuery()){

        while (rs.next()) {
            Notes note = new Notes(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("content")
            );
            notes.add(note);
        }
//            notes.add(new Notes(rs.getInt("id"), rs.getString("title"), rs.getString("content")));
//        }
//        return notes;
    }catch (SQLException ex){
        Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, ex);
    }finally {
            mysql.closeConnection(conn);
        }
        return notes;
}

    public void addNote(Notes note) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO Notes (title, content) VALUES (?, ?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
        pstmt.setString(1, note.getTitle());
        pstmt.setString(2, note.getContent());
        pstmt.executeUpdate();
    }catch (SQLException ex){
        Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE,null,ex);
    }finally {
            mysql.closeConnection(conn);
        }
    }

    public void updateNote(Notes note){
         Connection conn = mysql.openConnection();
        String sql = "UPDATE Notes SET title=?, content=? WHERE id=?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
        pstmt.setString(1, note.getTitle());
        pstmt.setString(2, note.getContent());
        pstmt.setInt(3, note.getId());
        pstmt.executeUpdate();
    }catch(SQLException ex){
       Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE,null,ex); 
    }finally {
            mysql.closeConnection(conn);
        }
    }

    public void deleteNote(int id) {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM Notes WHERE id=?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }catch(SQLException ex){
        Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE,null,ex); 
    }finally {
            mysql.closeConnection(conn);
        }
    }
}
