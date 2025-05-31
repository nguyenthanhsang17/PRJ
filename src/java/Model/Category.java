/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Category {
    private int id;
    private String name;
    private int status;
    private int NumberCourse;

    public Category() {
    }

    public Category(int id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Category(int id, String name, int status, int NumberCourse) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.NumberCourse = NumberCourse;
    }

    public int getNumberCourse() {
        return NumberCourse;
    }

    public void setNumberCourse(int NumberCourse) {
        this.NumberCourse = NumberCourse;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
