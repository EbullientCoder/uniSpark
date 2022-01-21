package com.example.unispark.controller.applicationcontroller.communications;

import com.example.unispark.R;
import com.example.unispark.bean.communications.BeanProfessorCommunication;
import com.example.unispark.bean.communications.BeanUniCommunication;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.facade.CommunicationsFacade;
import com.example.unispark.model.communications.ProfessorCommunicationModel;
import com.example.unispark.model.communications.UniversityCommunicationModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowCommunications {


    public List<BeanProfessorCommunication> showProfessorCommunications(BeanLoggedStudent student)  {
       List<BeanProfessorCommunication> beanProfCommunicationList = new ArrayList<>();

        List<ProfessorCommunicationModel> profCommunicationsItem;
        List<String> courseShortnames = new ArrayList<>();
        List<String> courseFullNames = new ArrayList<>();

        //Getting Courses Short and Full names
        if(!student.getCourses().isEmpty()){
            for(int i = 0; i < student.getCourses().size(); i++) {
                courseShortnames.add(student.getCourses().get(i).getShortName());
                courseFullNames.add(student.getCourses().get(i).getFullName());
            }

            try{
                profCommunicationsItem = CommunicationsFacade.getInstance().getAllCoursesCommunications(courseShortnames, courseFullNames);

                for (int j = 0; j < profCommunicationsItem.size(); j++){
                    BeanProfessorCommunication bCommunication;
                    bCommunication = new BeanProfessorCommunication();
                    bCommunication.setProfilePhoto(profCommunicationsItem.get(j).getProfilePhoto());
                    bCommunication.setShortCourseName(profCommunicationsItem.get(j).getShortCourseName());
                    bCommunication.setFullName(profCommunicationsItem.get(j).getFullName());
                    bCommunication.setProfessorName(profCommunicationsItem.get(j).getProfessorName());
                    bCommunication.setDate(profCommunicationsItem.get(j).getDate());
                    bCommunication.setType(profCommunicationsItem.get(j).getType());
                    bCommunication.setCommunication(profCommunicationsItem.get(j).getCommunication());
                    beanProfCommunicationList.add(bCommunication);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return beanProfCommunicationList;

    }


    //Student
    public List<BeanUniCommunication> showUniversityCommunications(BeanLoggedStudent student) {
        List<BeanUniCommunication> beanUniCommunicationList = new ArrayList<>();

        List<UniversityCommunicationModel> uniCommunicationsItem;
        try {
            uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications(student.getFaculty());
            for (int i = 0; i < uniCommunicationsItem.size(); i ++){
                BeanUniCommunication beanUniCommunication;
                beanUniCommunication = new BeanUniCommunication();
                beanUniCommunication.setBackground(uniCommunicationsItem.get(i).getBackground());
                beanUniCommunication.setTitle(uniCommunicationsItem.get(i).getTitle());
                beanUniCommunication.setDate(uniCommunicationsItem.get(i).getDate());
                beanUniCommunication.setCommunication(uniCommunicationsItem.get(i).getCommunication());
                beanUniCommunication.setFaculty(uniCommunicationsItem.get(i).getFaculty());

                beanUniCommunicationList.add(beanUniCommunication);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return beanUniCommunicationList;

    }

    //Professor
    public List<BeanUniCommunication> showUniversityCommunications(BeanLoggedProfessor professor) {
        List<BeanUniCommunication> beanUniCommunicationList = new ArrayList<>();

        List<UniversityCommunicationModel> uniCommunicationsItem;
        try {
            uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications(professor.getFaculty());

            for (int i = 0; i < uniCommunicationsItem.size(); i ++){
                BeanUniCommunication beanUniCommunication;
                beanUniCommunication = new BeanUniCommunication();
                beanUniCommunication.setBackground(uniCommunicationsItem.get(i).getBackground());
                beanUniCommunication.setTitle(uniCommunicationsItem.get(i).getTitle());
                beanUniCommunication.setDate(uniCommunicationsItem.get(i).getDate());
                beanUniCommunication.setCommunication(uniCommunicationsItem.get(i).getCommunication());
                beanUniCommunication.setFaculty(uniCommunicationsItem.get(i).getFaculty());

                beanUniCommunicationList.add(beanUniCommunication);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return beanUniCommunicationList;

    }

    //University
    public List<BeanUniCommunication> showUniversityCommunications() {
        List<BeanUniCommunication> beanUniCommunicationList = new ArrayList<>();

        List<UniversityCommunicationModel> uniCommunicationsItem;
        try {
            uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications("all");

            for (int i = 0; i < uniCommunicationsItem.size(); i ++){
                BeanUniCommunication beanUniCommunication;
                beanUniCommunication = new BeanUniCommunication();
                beanUniCommunication.setBackground(uniCommunicationsItem.get(i).getBackground());
                beanUniCommunication.setTitle(uniCommunicationsItem.get(i).getTitle());
                beanUniCommunication.setDate(uniCommunicationsItem.get(i).getDate());
                beanUniCommunication.setCommunication(uniCommunicationsItem.get(i).getCommunication());
                beanUniCommunication.setFaculty(uniCommunicationsItem.get(i).getFaculty());

                beanUniCommunicationList.add(beanUniCommunication);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return beanUniCommunicationList;

    }
}
