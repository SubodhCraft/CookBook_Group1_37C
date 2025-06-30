package Model;

/**
 * Represents a user comment on a recipe.
 */

public class Comment {
    private int id;
    private int recipeId;
    private int userId;
    private String username;  // Username of the commenter
    private String content;   // The actual comment text

    // --- Constructors ---

    public Comment() {
        // Empty constructor
    }

    public Comment(int id, int recipeId, int userId, String username, String content) {
        this.id = id;
        this.recipeId = recipeId;
        this.userId = userId;
        this.username = username;
        this.content = content;
    }

    public Comment(String username, String content) {
        this.username = username;
        this.content = content;
    }

    // --- Getters and Setters ---

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getRecipeId() {
        return recipeId;
    }
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
