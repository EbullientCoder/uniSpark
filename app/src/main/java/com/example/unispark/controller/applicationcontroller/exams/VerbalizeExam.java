package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.bean.BeanBookExam;
import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.exceptions.DatabaseOperationError;
import com.example.unispark.exceptions.ExamException;
import com.example.unispark.exceptions.ExamNotYetOccured;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;


public class VerbalizeExam {
    //Verbalize Exam
    public void verbalizeExam(BeanBookExam exam, BeanStudentSignedToExam student, String result) throws ExamNotYetOccured, GenericException {
        //Create new Verbalized Exam
        VerbalizedExamModel vExam = new VerbalizedExamModel(exam.getId(), exam.getName(), exam.getDate(), exam.getDate(), exam.getCFU(), result);

        //Add Verbalized Exam to the DB
        try {
            ExamsDAO.addExamGrade(vExam, student.getId());
        } catch (ExamException e) {
            e.printStackTrace();
            throw new ExamNotYetOccured("Exam has not yet occured");
        } catch (DatabaseOperationError databaseOperationError) {
            databaseOperationError.printStackTrace();
            throw new GenericException("Cannot add grade, try again");
        }

    }
}