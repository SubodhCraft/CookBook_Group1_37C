package Model;

public class Recipe {
    private int id; // Recipe ID (primary key)
    private String name;
    private int duration;
    private String process;
    private String imagePath;
    private String category;
    private String qrCodePath;   // New field for QR code path
//    private String Link;
    private double reward;
    private boolean completed;

    // Constructors

    // Constructor without ID, with QR path
    public Recipe(String name, int duration, String process, String imagePath, String category, String qrCodePath) {
        this.name = name;
        this.duration = duration;
        this.process = process;
        this.imagePath = imagePath;
        this.category = category;
        this.qrCodePath = qrCodePath;
    }

    // Overloaded constructor without QR path (for backward compatibility)
    public Recipe(String name, int duration, String process, String imagePath, String category) {
        this(name, duration, process, imagePath, category, null);
    }

    // Constructor with ID and QR path (loading from DB)
    public Recipe(int id, String name, int duration, String process, String imagePath, String category, String qrCodePath) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.process = process;
        this.imagePath = imagePath;
        this.category = category;
        this.qrCodePath = qrCodePath;
    }

    // Overloaded constructor with ID but without QR path (backward compatibility)
    public Recipe(int id, String name, int duration, String process, String imagePath, String category) {
        this(id, name, duration, process, imagePath, category, null);
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
//     public String getLink() {
//        return Link;
//    }
//
//    public void setLink(String Link) {
//        this.Link = Link;
//    }
//     public Recipe(String name, int duration, String process, String imagePath, String category, String qrCodePath, String Link) {
//        this.name = name;
//        this.duration = duration;
//        this.process = process;
//        this.imagePath = imagePath;
//        this.category = category;
//        this.qrCodePath = qrCodePath;
//        this.Link=Link;
//    }
}
