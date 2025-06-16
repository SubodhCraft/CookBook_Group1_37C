package Model;

public class Recipe {
    private int id; // Recipe ID (primary key)
    private String name;
    private int duration;
    private String process;
    private String imagePath;
    private String category;  // ADD THIS

   public Recipe(String name, int duration, String process, String imagePath, String category, int id) {
       this.id=id;
       this.name = name;
        this.duration = duration;
        this.process = process;
        this.imagePath = imagePath;
        this.category = category; 
        
    }
   //for the recipelistner
   public Recipe(String name, int duration, String process, String imagePath, String category) {
    this.name = name;
    this.duration = duration;
    this.process = process;
    this.imagePath = imagePath;
    this.category = category;
}


    // Constructor with ID (for loading recipes from DB)
    public Recipe(int id, String name, int duration, String process, String imagePath, String category) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.process = process;
        this.imagePath = imagePath;
        this.category = category;  // <-- NEW: set category here
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public String getProcess() {
        return process;
    }

    public String getImagePath() {
        return imagePath;
    }
    public String getCategory() { 
        return category;
    }

    // Setter for id (important to set after DB insert)
    public void setId(int id) {
        this.id = id;
    }

    // Optional setters if you want to edit these fields after creation
    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public void setCategory(String category) {  
        this.category = category;
    }
}
