package com.example.unispark.controller.applicationcontroller.communications;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.adapter.communications.UniCommunicationsAdapter;
import com.example.unispark.controller.details.DetailsUniCommunication;
import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.UniversityModel;
import com.example.unispark.model.communications.UniversityCommunicationModel;

import java.util.List;

public class ShowUniCommunications implements UniCommunicationsAdapter.OnUniComClickListener{
    //Attributes
    Context context;
    //Communications
    RecyclerView rvUniCommunications;
    UniCommunicationsAdapter communicationsAdapter;
    List<UniversityCommunicationModel> uniCommunicationsItem;
    //User Model
    StudentModel student;
    ProfessorModel professor;
    UniversityModel university;


    //Constructor
    //Student
    public ShowUniCommunications(StudentModel student, Context context, RecyclerView rvUniCommunications){
        this.student = student;
        this.context = context;

        //Communications
        this.rvUniCommunications = rvUniCommunications;
        uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications(student.getFaculty());
        communicationsAdapter = null;
    }

    //Professor
    public ShowUniCommunications(ProfessorModel professor, Context context, RecyclerView rvUniCommunications){
        this.professor = professor;
        this.context = context;

        //Communications
        this.rvUniCommunications = rvUniCommunications;
        uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications(professor.getFaculty());
        communicationsAdapter = null;
    }

    //University
    public ShowUniCommunications(UniversityModel university, Context context, RecyclerView rvUniCommunications){
        this.university = university;
        this.context = context;

        //Communications
        this.rvUniCommunications = rvUniCommunications;
        uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications("all");
        communicationsAdapter = null;
    }


    //Communications Adapter
    public void setCommunicationsAdapter(){
        if(uniCommunicationsItem != null){
            communicationsAdapter = new UniCommunicationsAdapter(uniCommunicationsItem, this);
            rvUniCommunications.setAdapter(communicationsAdapter);
        }
    }



    //On UniversityCommunicationsClick
    @Override
    public void onUniClick(int position) {
        Intent intent = new Intent(context, DetailsUniCommunication.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", uniCommunicationsItem.get(position));
        //Start New Activity from Outside an Activity
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }
}