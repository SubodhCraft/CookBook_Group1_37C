/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LEGION
 */
public class Notes {

    private int id;
    private String title;
    private String content;

    public Notes(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Notes(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public int getId(){
        return id; 
    }
    public String getTitle() {
        return title; 
    }
    public String getContent() {
        return content; 
    }

    public void setTitle(String title) {
        this.title = title; 
    }
    public void setContent(String content) {
        this.content = content; 
    }
}
