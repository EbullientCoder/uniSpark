package unispark.view.viewadapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.unispark.R;
import unispark.engeneeringclasses.bean.courses.BeanCourse;


import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Attributes
    private List<BeanCourse> bCourses;
    private OnCourseClickListener onCourseClickListener;
    private OnCourseBtnClickListener onCourseBtnClickListener;
    private String type;


    //Interface
    //Click on the Course
    public interface OnCourseClickListener {
        void onCourseClick(int position);
    }
    //Click on the Button
    public interface  OnCourseBtnClickListener{
        void onButtonClick(int position);
    }


    //Methods
    //Professor
    public CoursesAdapter(OnCourseClickListener onCourseClickListener, String type){
        this.onCourseClickListener = onCourseClickListener;
        this.type = type;
    }

    //Student
    public CoursesAdapter(OnCourseClickListener onCourseClickListener,
                          OnCourseBtnClickListener onCourseBtnClickListener, String type){

        this.onCourseClickListener = onCourseClickListener;
        this.onCourseBtnClickListener = onCourseBtnClickListener;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_courses,
                        parent,
                        false
                ), onCourseClickListener, onCourseBtnClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(type.equals("JOIN")){
            BeanCourse courseModel = (BeanCourse) bCourses.get(position);
            ((CourseViewHolder) holder).setJoinCourseDate(courseModel);
        }
        else if(type.equals("LEAVE")){
            BeanCourse courseModel = (BeanCourse) bCourses.get(position);
            ((CourseViewHolder) holder).setLeaveCourseDate(courseModel);
        }
        else if(type.equals("PROFESSOR")){
            BeanCourse courseModel = (BeanCourse) bCourses.get(position);
            ((CourseViewHolder) holder).setProfessorCourseDate(courseModel);
        }
    }

    @Override
    public int getItemCount() {
        return bCourses.size();
    }



    //First Row
    static class CourseViewHolder extends RecyclerView.ViewHolder{
        //Attributes
        String shortName;
        String id;
        String session;
        String link;
        OnCourseClickListener onCourseClickListener;
        OnCourseBtnClickListener onCourseBtnClickListener;

        TextView fullName;
        TextView aa;
        TextView cfu;
        Button btnJoinLeave;
        LinearLayout buttonLayout;


        //Constructor
        //Student
        CourseViewHolder(@NonNull View itemView, OnCourseClickListener onCourseClickListener, OnCourseBtnClickListener onCourseBtnClickListener) {
            super(itemView);
            fullName = itemView.findViewById(R.id.txt_course_subject_name);
            aa = itemView.findViewById(R.id.txt_course_aa_date);
            cfu = itemView.findViewById(R.id.txt_course_cfu);
            buttonLayout = itemView.findViewById(R.id.lyt_contain_course_button);

            this.onCourseClickListener = onCourseClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCourseClickListener.onCourseClick(getAdapterPosition());
                }
            });

            this.onCourseBtnClickListener = onCourseBtnClickListener;
            btnJoinLeave = itemView.findViewById(R.id.btn_join_leave_course);
            btnJoinLeave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCourseBtnClickListener.onButtonClick(getAdapterPosition());
                }
            });
        }


        void setJoinCourseDate(BeanCourse courseModel) {
            fullName.setText(courseModel.getFullName());
            shortName = courseModel.getShortName();
            aa.setText(courseModel.getCourseYear());
            cfu.setText(courseModel.getCfu());
            id = courseModel.getId();
            session = courseModel.getSession();
            link = courseModel.getLink();
            btnJoinLeave.setText("JOIN");
        }

        void setLeaveCourseDate(BeanCourse courseModel) {
            fullName.setText(courseModel.getFullName());
            shortName = courseModel.getShortName();
            aa.setText(courseModel.getCourseYear());
            cfu.setText(courseModel.getCfu());
            id = courseModel.getId();
            session = courseModel.getSession();
            link = courseModel.getLink();
            btnJoinLeave.setText("LEAVE");
        }

        void setProfessorCourseDate(BeanCourse courseModel) {
            fullName.setText(courseModel.getFullName());
            shortName = courseModel.getShortName();
            aa.setText(courseModel.getCourseYear());
            cfu.setText(courseModel.getCfu());
            id = courseModel.getId();
            session = courseModel.getSession();
            link = courseModel.getLink();
            btnJoinLeave.setVisibility(View.INVISIBLE);
            buttonLayout.setVisibility(View.INVISIBLE);
        }
    }


    public void setbCourses(List<BeanCourse> bCourses) {
        this.bCourses = bCourses;
    }
}

