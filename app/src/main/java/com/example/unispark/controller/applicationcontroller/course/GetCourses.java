package com.example.unispark.controller.applicationcontroller.course;

import com.example.unispark.bean.BeanCourse;
import com.example.unispark.bean.BeanCoursesNames;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.model.CourseModel;


import java.util.ArrayList;
import java.util.List;

public class GetCourses {


    public List<BeanCourse> getAvaliableCourses(BeanLoggedStudent student){

        List<BeanCourse> bCourses = new ArrayList<>();

        if (!student.getCourses().isEmpty()){
            List<CourseModel> avaliableCourses;
            avaliableCourses = CourseDAO.selectAvailableCourses(student.getFaculty(), student.getUniYear(), student.getCourses());
            CourseModel course;
            for (int i = 0; i < avaliableCourses.size(); i++){
                course = avaliableCourses.get(i);
                bCourses.add(new BeanCourse(course.getId(),
                        course.getShortName(),
                        course.getFullName(),
                        course.getCourseYear(),
                        course.getCfu(),
                        course.getSession(),
                        course.getLink(),
                        course.getFaculty(),
                        course.getUniYear()));
            }
        }

        return bCourses;
    }


    public BeanCoursesNames getCoursesNamesByFaculty(List<String> faculties){
        BeanCoursesNames bCourses = new BeanCoursesNames();
        List<CourseModel> courses = new ArrayList<>();
        List<String> coursesNames = new ArrayList<>();

        for (int i = 0; i < faculties.size(); i++){
            courses = CourseDAO.selectCourses(faculties.get(i));
            if (!courses.isEmpty()){
                courses.addAll(courses);
            }
        }

        for (int j = 0; j < courses.size(); j++){
            coursesNames.add(courses.get(j).getFullName());
        }

        bCourses.setCourses(coursesNames);


        return bCourses;
    }

    //Get Courses
    public List<BeanCourse> getFacultyCourses(List<String> faculties){
        List<BeanCourse> bCourses = new ArrayList<>();
        List<CourseModel> courses = new ArrayList<>();

        for (int i = 0; i < faculties.size(); i++){
            courses = CourseDAO.selectCourses(faculties.get(i));
            if (!courses.isEmpty()){
                for (int j = 0; j < courses.size(); j++){
                    bCourses.add(new BeanCourse(courses.get(j).getId(),
                            courses.get(j).getShortName(),
                            courses.get(j).getFullName(),
                            courses.get(j).getCourseYear(),
                            courses.get(j).getCfu(),
                            courses.get(j).getSession(),
                            courses.get(j).getLink(),
                            courses.get(j).getFaculty(),
                            courses.get(j).getUniYear()));
                }
            }
        }




        return bCourses;
    }



    public List<BeanCourse> getCourses(BeanLoggedStudent student){
        List<CourseModel> courses = student.getCourses();
        List<BeanCourse> bCourses = new ArrayList<>();
        CourseModel course;
        for (int i = 0; i < courses.size(); i++){
            course = courses.get(i);
            bCourses.add(new BeanCourse(course.getId(),
                    course.getShortName(),
                    course.getFullName(),
                    course.getCourseYear(),
                    course.getCfu(),
                    course.getSession(),
                    course.getLink(),
                    course.getFaculty(),
                    course.getUniYear()));
        }

        return bCourses;
    }

    public List<BeanCourse> getCourses(BeanLoggedProfessor professor){
        List<CourseModel> courses = professor.getCourses();
        List<BeanCourse> bCourses = new ArrayList<>();
        CourseModel course;
        for (int i = 0; i < courses.size(); i++){
            course = courses.get(i);
            bCourses.add(new BeanCourse(course.getId(),
                    course.getShortName(),
                    course.getFullName(),
                    course.getCourseYear(),
                    course.getCfu(),
                    course.getSession(),
                    course.getLink(),
                    course.getFaculty(),
                    course.getUniYear()));
        }

        return bCourses;
    }

    public List<String> getCoursesNames(BeanLoggedProfessor bProfessor){
        List<String> coursesNames = new ArrayList<>();
        List<CourseModel> courses = bProfessor.getCourses();

        for(int i = 0; i < courses.size(); i++) coursesNames.add(courses.get(i).getShortName());

        return coursesNames;
    }




}