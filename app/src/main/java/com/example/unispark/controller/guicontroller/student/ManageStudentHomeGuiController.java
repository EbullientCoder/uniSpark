package com.example.unispark.controller.guicontroller.student;

import android.content.Context;
import android.content.Intent;

import com.example.unispark.bean.BeanHomework;
import com.example.unispark.bean.communications.BeanProfessorCommunication;
import com.example.unispark.bean.communications.BeanUniCommunication;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.communications.ShowCommunications;
import com.example.unispark.controller.applicationcontroller.homeworks.ShowHomeworks;
import com.example.unispark.controller.guicontroller.BottomNavigationMenuGuiController;
import com.example.unispark.view.details.DetailsHomeworkView;
import com.example.unispark.view.details.DetailsProfCommunicationView;
import com.example.unispark.view.details.DetailsUniCommunicationView;

import java.sql.SQLException;
import java.util.List;

public class ManageStudentHomeGuiController extends BottomNavigationMenuGuiController {

    public List<BeanUniCommunication> getUniCommunications(BeanLoggedStudent student){

        List<BeanUniCommunication> communications;
        //Applicative Controller
        ShowCommunications communicationsController = new ShowCommunications();
        communications = communicationsController.showUniversityCommunications(student);


        return communications;
    }



    public List<BeanProfessorCommunication> getProfessorsCommunications(BeanLoggedStudent student){

        List<BeanProfessorCommunication> communications;
        //Applicative Controller
        ShowCommunications communicationsController = new ShowCommunications();
        communications = communicationsController.showProfessorCommunications(student);


        return communications;
    }


    public List<BeanHomework> getHomeworks(BeanLoggedStudent student){

        List<BeanHomework> homeworks = null;
        //Applicative Controller
        ShowHomeworks showHomeworksController = new ShowHomeworks();
        try {
            homeworks = showHomeworksController.getHomework(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return homeworks;

    }

    public void showDetailsCommunication(Context context, BeanUniCommunication communication){
        Intent intent = new Intent(context, DetailsUniCommunicationView.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", communication);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



    public void showDetailsCommunication(Context context, BeanProfessorCommunication communication){
        Intent intent = new Intent(context, DetailsProfCommunicationView.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", communication);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void showHomeworkDetails(Context context, BeanHomework homework){

        Intent intent = new Intent(context, DetailsHomeworkView.class);
        //Pass Items to the new Activity
        intent.putExtra("Homework", homework);
        intent.putExtra("StudentHomeView", "StudentHome");
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

}
