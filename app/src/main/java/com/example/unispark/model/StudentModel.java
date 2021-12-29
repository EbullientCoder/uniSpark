package com.example.unispark.model;


import com.example.unispark.model.exams.BookingExamModel;
import com.example.unispark.model.exams.ExamGradeModel;

import java.util.List;

public class StudentModel extends UserModel{
    //Attributes

    private int imageID;
    private String firstName;
    private String lastName;
    private String faculty;
    private String academicYear;
    private String id;
    private List<CourseModel> courses;
    private List<BookingExamModel> bExams;
    private List<BookingExamModel> upcomingExams;
    private List<ExamGradeModel> vExams;
    private List<ExamGradeModel> fExams;


    //Methods
    //Constructor
    public StudentModel(int imageID,
                        String firstName,
                        String lastName,
                        String email,
                        String password,
                        String faculty,
                        String academicYear,
                        String id,
                        List<CourseModel> courses,
                        List<BookingExamModel> bExams,
                        List<BookingExamModel> upcomingExams,
                        List<ExamGradeModel> vExams,
                        List<ExamGradeModel> fExams
    ) {
        super(email, password);
        this.imageID = imageID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.faculty = faculty;
        this.academicYear = academicYear;
        this.id = id;
        this.courses = courses;
        this.bExams = bExams;
        this.upcomingExams = upcomingExams;
        this.vExams = vExams;
        this.fExams = fExams;

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

    public String getFaculty() {
        return faculty;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getId() {
        return id;
    }

    public List<CourseModel> getCourses() {
        return courses;
    }

    public List<BookingExamModel> getbExams() {
        return bExams;
    }

    public List<BookingExamModel> getUpcomingExams() {
        return upcomingExams;
    }

    public List<ExamGradeModel> getvExams() {
        return vExams;
    }

    public List<ExamGradeModel> getfExams() {
        return fExams;
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

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }

    public void setbExams(List<BookingExamModel> bExams) {
        this.bExams = bExams;
    }

    public void setUpcomingExams(List<BookingExamModel> upcomingExams) {
        this.upcomingExams = upcomingExams;
    }

    public void setvExams(List<ExamGradeModel> vExams) {
        this.vExams = vExams;
    }

    public void setfExams(List<ExamGradeModel> fExams) {
        this.fExams = fExams;
    }
}






