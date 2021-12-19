package com.example.unispark.model;

import java.util.List;

public class StudentModel extends UserModel{
    //Attributes
    private int imageID;
    private String firstName;
    private String lastName;
    private List<CourseModel> courses;

    //Methods
    //Constructor
    public StudentModel(int imageID, String firstName, String lastName, String email, String password, List<CourseModel> courses) {
        super(email, password);
        this.imageID = imageID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = courses;
    }


    //Getter
    public int getImageID() {
        return imageID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public List<CourseModel> getCourses() {
        return courses;
    }


    //Setter
    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }
}



