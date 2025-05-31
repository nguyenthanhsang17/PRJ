/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Courses {
    private int id;
    private String title;
    private String description;
    private int instructorId;
    private int categoryId;
    private Date createdAt;
    private int status;
    private String category;
    private String instructorName;
    
    private int vote_Count;
    
    private int enrolled_students;
    
    private double  avgVote;

    public double getAvgVote() {
        return avgVote;
    }

    public void setAvgVote(double avgVote) {
        this.avgVote = avgVote;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }
    
    
    
    private int IsOwner;
    public String g;
    public Courses() {
    }

    public int getVote_Count() {
        return vote_Count;
    }

    public void setVote_Count(int vote_Count) {
        this.vote_Count = vote_Count;
    }

    public int getEnrolled_students() {
        return enrolled_students;
    }

    public void setEnrolled_students(int enrolled_students) {
        this.enrolled_students = enrolled_students;
    }
    
    
    

    public Courses(int id, String title, String description, int instructorId, int categoryId, Date createdAt, int status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
        this.status = status;
    }

    public int getIsOwner() {
        return IsOwner;
    }

    public void setIsOwner(int IsOwner) {
        this.IsOwner = IsOwner;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public String FormatDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = sdf.format(createdAt);
        return  formattedDate;
    }
    
    
}
