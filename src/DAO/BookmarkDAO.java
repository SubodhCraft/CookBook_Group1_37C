package DAO;

import Database.Database;
import Model.BookmarkModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class BookmarkDAO {
    private final Database db;

    public BookmarkDAO(Database db) {
        this.db = db;
    }

    // Modified to return boolean
    public boolean addBookmark (int userId,int recipeId) {
        try(Connection conn = db.openConnection()){
            String checkQuery = "SELECT * FROM bookmarks WHERE user_id=? AND recipe_id =?";
            PreparedStatement stmt = conn.prepareStatement(checkQuery);
            stmt.setInt(1, userId);
            stmt.setInt(2, recipeId);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                return false;
            }
        
//        if(isBookmarked(userId, recipeId)){
//            return false;
//        }
        
        String sql = "INSERT INTO bookmarks(user_id,recipe_id) VALUES (?,?)";
//        try(Connection conn = db.openConnection();

        PreparedStatement pstmt = conn.prepareStatement(sql);
//                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, userId);
            pstmt.setInt(2, recipeId);
            pstmt.executeUpdate();
            return true;
//            return pstmt.executeUpdate()>0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    // Check if it's already bookmarked first
//    if (getBookmarkedRecipeIds().contains(recipeId)) {
//        return false; // Already bookmarked, do nothing
//    }
//
//    String query = "INSERT INTO bookmarks (recipe_id) VALUES (?)";
//    try (Connection conn = db.openConnection();
//         PreparedStatement stmt = conn.prepareStatement(query)) {
//
//        stmt.setInt(1, recipeId);
//        int rowsAffected = stmt.executeUpdate();
//        return rowsAffected > 0;

//    }
//       catch (SQLException e) {
//        e.printStackTrace();
//        return false;
//    }
}
    
    public boolean isBookmarked(int userId, int recipeId){
        String sql = "SELECT 1 FROM bookmarks WHERE user_id = ? AND recipe_id = ?"; 
        try(Connection conn = db.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, userId);
            pstmt.setInt(2, recipeId);
            try(ResultSet rs = pstmt.executeQuery()){
               return rs.next(); 
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean removeBookmark(int userId, int recipeId){
        String sql = "DELETE FROM bookmarks WHERE user_id = ? and recipe_id = ?";
        try(Connection conn = db.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, userId);
            pstmt.setInt(2, recipeId);
            return pstmt.executeUpdate()>0;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean toggleBookmark(int userId, int recipeId){
        if(isBookmarked(userId, recipeId)){
            return removeBookmark(userId, recipeId);
        }else{
            return addBookmark(userId, recipeId);
        }
    }
    
    public List<BookmarkModel> getBookmarksByUser(int userId){
        List<BookmarkModel> bookmarks = new ArrayList<>();
        String sql = "SELECT * FROM bookmarks WHERE user_id = ?";
        
        try(Connection conn = db.openConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int recipeId = rs.getInt("recipe_id");
                bookmarks.add(new BookmarkModel(id,userId, recipeId));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return bookmarks;
    }


    public Set<Integer> getBookmarkedRecipeIds(int userId) {
        Set<Integer> ids = new HashSet<>();
        String query = "SELECT recipe_id FROM bookmarks WHERE user_id=?";

        try (Connection conn = db.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)){
                pstmt.setInt(1,userId);
             ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ids.add(rs.getInt("recipe_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }




}
 