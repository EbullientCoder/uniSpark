package com.example.common.applicationcontroller;

import com.example.common.bean.professor.BeanLoggedProfessor;
import com.example.common.bean.student.BeanLoggedStudent;
import com.example.common.bean.university.BeanLoggedUniversity;
import com.example.common.bean.login.BeanUser;
import com.example.common.database.dao.ProfessorDAO;
import com.example.common.database.dao.StudentDAO;
import com.example.common.database.dao.UniversityDAO;
import com.example.common.exceptions.WrongUsernameOrPasswordException;
import com.example.common.model.ProfessorModel;
import com.example.common.model.StudentModel;
import com.example.common.model.UniversityModel;

import java.sql.SQLException;

import javax.security.auth.login.LoginException;



public class LoginAppController {

    private static String errorMessage = "Wrong username and/or password";

    //LoginAppController Methods
    public BeanLoggedStudent studentLogin(BeanUser user) throws WrongUsernameOrPasswordException, SQLException {
        BeanLoggedStudent beanLoggedStudent = null;
        StudentModel student = null;
        try {
            student = StudentDAO.selectStudent(user.getEmail(), user.getPassword());
            beanLoggedStudent = new BeanLoggedStudent();


            beanLoggedStudent.setFirstName(student.getFirstName());
            beanLoggedStudent.setLastName(student.getLastName());
            beanLoggedStudent.setProfilePicture(student.getProfilePicture());
            beanLoggedStudent.setId(student.getId());
            beanLoggedStudent.setFaculty(student.getFaculty());
            beanLoggedStudent.setAcademicYear(student.getAcademicYear());
            beanLoggedStudent.setCourses(student.getCourses());
            beanLoggedStudent.setBookedExams(student.getBookedExams());
            beanLoggedStudent.setYear(student.getUniYear());

        } catch (LoginException e) {
            e.printStackTrace();
            throw new WrongUsernameOrPasswordException(errorMessage);
        }

        return beanLoggedStudent;
    }

    public BeanLoggedProfessor professorLogin(BeanUser user) throws WrongUsernameOrPasswordException, SQLException {

        BeanLoggedProfessor beanLoggedProfessor = null;
        ProfessorModel professor = null;

        try {
            professor = ProfessorDAO.selectProfessor(user.getEmail(), user.getPassword());
            beanLoggedProfessor = new BeanLoggedProfessor();


            beanLoggedProfessor.setFirstName(professor.getFirstName());
            beanLoggedProfessor.setLastName(professor.getLastName());
            beanLoggedProfessor.setProfilePicture(professor.getProfilePicture());
            beanLoggedProfessor.setId(professor.getId());
            beanLoggedProfessor.setFaculty(professor.getFaculty());
            beanLoggedProfessor.setWebsite(professor.getWebsite());
            beanLoggedProfessor.setCourses(professor.getCourses());
            beanLoggedProfessor.setExams(professor.getExams());
            beanLoggedProfessor.setHomeworks(professor.getHomeworks());
        } catch (LoginException e) {
            e.printStackTrace();
            throw new WrongUsernameOrPasswordException(errorMessage);
        }

        return beanLoggedProfessor;
    }

    public BeanLoggedUniversity universityLogin(BeanUser user) throws WrongUsernameOrPasswordException, SQLException {
        BeanLoggedUniversity beanLoggedUniversity = null;
        UniversityModel university = null;

        try {
            university = UniversityDAO.selectUniversity(user.getEmail(), user.getPassword());
            beanLoggedUniversity = new BeanLoggedUniversity();
            beanLoggedUniversity.setFaculties(university.getFaculties());
            beanLoggedUniversity.setName(university.getName());
            beanLoggedUniversity.setProfilePicture(university.getProfilePicture());
            beanLoggedUniversity.setStreetAddress(university.getStreetAddress());

        } catch (LoginException e) {
            e.printStackTrace();
            throw new WrongUsernameOrPasswordException(errorMessage);
        }

        return beanLoggedUniversity;
    }
}