package unispark.engeneeringclasses.bean.professor;



import unispark.engeneeringclasses.bean.courses.BeanCourse;

import java.io.Serializable;
import java.util.List;

public class BeanProfessorDetails extends BeanProfessor implements Serializable {


    private List<BeanCourse> courses;


    public List<BeanCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<BeanCourse> courses) {
        this.courses = courses;
    }
}
