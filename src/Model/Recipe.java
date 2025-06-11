package Model;

public class Recipe {
    private int id; // Recipe ID (primary key)
    private String name;
    private int duration;
    private String process;
    private String imagePath;

    // Constructor without ID (for creating new recipes)
    public Recipe(String name, int duration, String process, String imagePath) {
        this.name = name;
        this.duration = duration;
        this.process = process;
        this.imagePath = imagePath;
    }

    // Constructor with ID (for loading recipes from DB)
    public Recipe(int id, String name, int duration, String process, String imagePath) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.process = process;
        this.imagePath = imagePath;
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
}
