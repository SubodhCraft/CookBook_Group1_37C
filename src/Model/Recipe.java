/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Jay pradhan
 */
public class Recipe {
    private String name;
    private int duration;
    private String process;
     private String imagePath;

    public Recipe(String name, int duration,String process,String imagePath) {
        this.name = name;
        this.duration = duration;
        this.process = process;
        this.imagePath = imagePath;
    
}
    public String getName() { return name; }
    public int getDuration() { return duration; }
    public String getProcess() { return process; }
    public String getImagePath() { return imagePath;}
}
