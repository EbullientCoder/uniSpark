package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.bean.BeanBookExam;
import com.example.unispark.bean.BeanExamType;
import com.example.unispark.bean.BeanVerbalizeExam;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.facade.ExamsFacade;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowExams {
    //Student
    //Page: Verbalized ExamModel
    public List<BeanExamType> verbalizedExams(BeanLoggedStudent student){
        List<BeanExamType> bExams = new ArrayList<>();

        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        try{

            List<VerbalizedExamModel> verbalizedExams = ExamsDAO.getVerbalizedExams(student.getId());

            for (int i = 0; verbalizedExams != null && i < verbalizedExams.size(); i++){
                VerbalizedExamModel vExam = verbalizedExams.get(i);
                bExams.add(new BeanExamType(0, new BeanVerbalizeExam(vExam.getId(), vExam.getName(), vExam.getYear(), vExam.getDate(), vExam.getCFU(), vExam.getResult())));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bExams;
    }


    //Page: Failed ExamModel
    public List<BeanExamType> failedExams(BeanLoggedStudent student){
        List<BeanExamType> bExams = new ArrayList<>();

        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        try{
            List<VerbalizedExamModel> failedExams = ExamsDAO.getFailedExams(student.getId());
            for (int i = 0; failedExams != null && i < failedExams.size(); i++){
                VerbalizedExamModel vExam = failedExams.get(i);
                bExams.add(new BeanExamType(0, new BeanVerbalizeExam(vExam.getId(), vExam.getName(), vExam.getYear(), vExam.getDate(), vExam.getCFU(), vExam.getResult())));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bExams;
    }


    //Page: Upcoming StudentExamsGUIController
    public List<BeanExamType> bookExams(BeanLoggedStudent student){
        List<BeanExamType> bExams = new ArrayList<>();


        try{
            //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
            List<BookExamModel> bookExams = ExamsFacade.getInstance().getExams(student.getId(), false);
            for (int i = 0; bookExams != null && i < bookExams.size(); i++){
                BookExamModel bExam = bookExams.get(i);
                bExams.add(new BeanExamType(2, new BeanBookExam(bExam.getId(), bExam.getName(), bExam.getYear(), bExam.getDate(), bExam.getCFU(), bExam.getClassroom(), bExam.getBuilding())));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bExams;
    }


    //Page: Booked StudentExamsGUIController
    public List<BeanExamType> bookedExams(BeanLoggedStudent student){
        List<BeanExamType> bExams = new ArrayList<>();

        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        try{
            List<BookExamModel> bookedExams = ExamsDAO.getBookedExams(student.getId());
            for (int i = 0; bookedExams != null && i < bookedExams.size(); i++){
                BookExamModel bExam = bookedExams.get(i);
                bExams.add(new BeanExamType(3, new BeanBookExam(bExam.getId(), bExam.getName(), bExam.getYear(), bExam.getDate(), bExam.getCFU(), bExam.getClassroom(), bExam.getBuilding())));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bExams;
    }


    //Professor
    public List<BeanExamType> assignedExams(BeanLoggedProfessor professor){
        List<BeanExamType> bExams = new ArrayList<>();


        try {
            List<BookExamModel> exams;
            exams = ExamsFacade.getInstance().getExams(String.valueOf(professor.getId()), true);
            for (int i = 0; exams != null && i < exams.size(); i++){
                BookExamModel bExam = exams.get(i);
                bExams.add(new BeanExamType(1, new BeanBookExam(bExam.getId(), bExam.getName(), bExam.getYear(), bExam.getDate(), bExam.getCFU(), bExam.getClassroom(), bExam.getBuilding())));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bExams;
    }
}
