/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Lesson {

    private int id;
    private int courseId;
    private String title;
    private String videoUrl;
    private int position;
    private int status;
    
    private int done;

    public Lesson() {
    }

    public Lesson(int id, int courseId, String title, String videoUrl, int position, int status) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.videoUrl = videoUrl;
        this.position = position;
        this.status = status;
    }


    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
}
