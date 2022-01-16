package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.bean.BeanBookExam;
import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.model.exams.BookExamModel;

import java.util.ArrayList;
import java.util.List;

public class ShowSignedToExamStudents {
    //Show the Students that have booked an Exam
    public List<BeanStudentSignedToExam> showBookedStudents(BeanBookExam exam){
        List<BeanStudentSignedToExam> studentsItem;
        studentsItem = ExamsDAO.getStudentsBookedExam(exam.getId());

        return studentsItem;

    }
}