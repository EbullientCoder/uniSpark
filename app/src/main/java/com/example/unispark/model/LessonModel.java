package com.example.unispark.model;

public class LessonModel {
    //Attributes
    private String lessonName;
    private String day;
    private String hour;

    //Methods
    //Constructor


    public LessonModel(String lessonName, String day, String hour) {
        this.lessonName = lessonName;
        this.day = day;
        this.hour = hour;
    }


    public String getLessonName() {
        return lessonName;
    }

    public String getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }



    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

}

