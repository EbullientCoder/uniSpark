package unispark.controller.guicontroller.student;




import android.content.Intent;

import unispark.engeneeringclasses.others.Session;
import unispark.engeneeringclasses.bean.courses.BeanCourse;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.controller.appcontroller.course.ManageCourses;
import unispark.engeneeringclasses.exceptions.CourseAlreadyJoined;
import unispark.engeneeringclasses.exceptions.CourseDoesNotExist;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.view.details.DetailsCourseView;
import unispark.view.student.fragment.JoinCourseView;

import java.util.List;

public class JoinCourseGuiController extends StudentBaseGuiController {

    private JoinCourseView joinCourseView;
    private List<BeanCourse> beanCourses;
    private List<BeanCourse> beanJoinedCourses;


    public JoinCourseGuiController(Session session, List<BeanCourse> beanJoinedCourses, JoinCourseView joinCourseView) {
        super(session, joinCourseView.getContext());
        this.joinCourseView = joinCourseView;
        this.beanJoinedCourses = beanJoinedCourses;
    }


    public void showAvaliableCourses(){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        ManageCourses getCoursesController = new ManageCourses();
        this.beanCourses = getCoursesController.getAvaliableCourses(student);
        this.joinCourseView.setCoursesAdapter(this.getBeanCourses());

    }

    public void joinCourse(int position){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        ManageCourses joinCourseAppController = new ManageCourses();
        BeanCourse course = this.beanCourses.get(position);
        try {
            joinCourseAppController.joinCourse(student, course);

            //Notify the Joined Courses Adapter
            this.beanJoinedCourses.add(0, course);
            this.joinCourseView.setJoinedCoursesAdapter(this.getBeanJoinedCourses());
            this.joinCourseView.notifyDataChanged();

            //Remove Course from the Available Courses
            this.beanCourses.remove(position);
            this.joinCourseView.dismiss();

        } catch (GenericException | CourseDoesNotExist | CourseAlreadyJoined e) {
            e.printStackTrace();
            this.joinCourseView.setErrorMessage(e.getMessage());
        }
    }


    public void showCourseDetails(int position){
        Intent intent = new Intent(this.joinCourseView.getContext(), DetailsCourseView.class);
        intent.putExtra("Course", this.beanCourses.get(position));
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        this.joinCourseView.getContext().startActivity(intent);
    }



    public List<BeanCourse> getBeanCourses() {
        return beanCourses;
    }

    public List<BeanCourse> getBeanJoinedCourses() {
        return beanJoinedCourses;
    }
}
