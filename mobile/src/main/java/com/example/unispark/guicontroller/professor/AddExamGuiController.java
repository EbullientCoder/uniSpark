package com.example.unispark.guicontroller.professor;

import com.example.common.applicationcontroller.exams.AddExamAppController;
import com.example.unispark.Session;
import com.example.common.bean.courses.BeanCourse;
import com.example.common.bean.exams.BeanBookExam;
import com.example.common.bean.professor.BeanLoggedProfessor;
import com.example.common.exceptions.ExamAlreadyExists;
import com.example.common.exceptions.GenericException;
import com.example.unispark.view.professor.fragment.AddExamView;

public class AddExamGuiController extends AddItemGuiController{


    private AddExamView addExamView;


    public AddExamGuiController(Session session, AddExamView addExamView) {
        super(session, addExamView, null, null);
        this.addExamView = addExamView;
    }

    public void addExam(String date, String courseSelection, String hour, String building, String classroom){

        if (courseSelection.equals("") || hour.equals("") || building.equals("") || classroom.equals("")){
            this.addExamView.setMessage("All fields are required");
        }

        else{

            BeanLoggedProfessor professor = (BeanLoggedProfessor) this.session.getUser();
            BeanCourse beanCourse = this.getCourses(professor).get(this.getCoursePosition());

            //Bean Exam
            BeanBookExam bExam;
            bExam = new BeanBookExam();
            bExam.setId(1);
            bExam.setName(beanCourse.getFullName());
            bExam.setYear(beanCourse.getCourseYear());
            bExam.setDate(date + hour);
            bExam.setCfu(beanCourse.getCfu());
            bExam.setClassroom(classroom);
            bExam.setBuilding(building);

            //Application Controller
            AddExamAppController addExamAppController = new AddExamAppController();
            try {

                addExamAppController.addExam(bExam, professor);
                this.addExamView.setMessage("Exam added");
                this.addExamView.dismiss();

            } catch (ExamAlreadyExists | GenericException e) {
                e.printStackTrace();
                this.addExamView.setMessage(e.getMessage());
            }
        }
    }


}