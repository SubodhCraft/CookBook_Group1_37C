package Model;

public class BookmarkModel {
    private int id;
    private int userId; // NEW: to distinguish user
    private int recipeId;

    // Constructor
    public BookmarkModel(int id, int userId, int recipeId) {
        this.id = id;
        this.userId = userId;
        this.recipeId = recipeId;
    }

    // Getters and setters
    public int getId() {
         return id; 
        }
    public int getUserId() {
         return userId;
         }
    public int getRecipeId() { 
        return recipeId; 
    }

    public void setId(int id) { 
        this.id = id;
     }
    public void setUserId(int userId) { 
        this.userId = userId; 
    }
    public void setRecipeId(int recipeId) {
         this.recipeId = recipeId;
         }
}
