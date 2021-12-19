package com.example.unispark.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.model.HomeworkModel;

import java.util.List;

public class HomeworksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //Attributes
    private List<HomeworkModel> items;
    private OnHomeworkBtnClickListener onHomeworkBtnClickListener;
    private String type;

    //Click ExamItem Interface
    public interface OnHomeworkBtnClickListener {
        void onBtnClick(String shortName,
                        String title,
                        String course,
                        String date,
                        String instructions,
                        String points,
                        int id);
    }


    //Methods
    public HomeworksAdapter(List<HomeworkModel> items, OnHomeworkBtnClickListener onHomeworkBtnClickListener, String type){
        this.items = items;
        this.onHomeworkBtnClickListener = onHomeworkBtnClickListener;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeworkViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_homework,
                        parent,
                        false
                ), onHomeworkBtnClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Student Homework
        if(type.equals("STUDENT")){
            HomeworkModel homework = (HomeworkModel) items.get(position);
            ((HomeworkViewHolder) holder).setHomeworkDate(homework);
        }
        //Professor Homework
        else if(type.equals("PROFESSOR")){
            HomeworkModel homework = (HomeworkModel) items.get(position);
            ((HomeworkViewHolder) holder).setProfessorHomeworkDate(homework);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /*@Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }*/


    //Homeworks ViewHolder
    static class HomeworkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Attributes
        private String shortName;
        private String title;
        private String instructions;
        private String points;
        private int id;

        private TextView txtCourse;
        private TextView txtExpiration;
        private Button btnView;
        private OnHomeworkBtnClickListener onHomeworkBtnClickListener;

        //Methods
        //Constructor
        HomeworkViewHolder(@NonNull View itemView, OnHomeworkBtnClickListener onHomeworkBtnClickListener) {
            super(itemView);
            txtCourse = itemView.findViewById(R.id.txt_homework_subject_name);
            txtExpiration = itemView.findViewById(R.id.txt_homework_submit_date);
            btnView = itemView.findViewById(R.id.btn_homework_view);

            this.onHomeworkBtnClickListener = onHomeworkBtnClickListener;
            btnView.setOnClickListener(this);
        }

        //Student
        void setHomeworkDate(HomeworkModel homework){
            txtCourse.setText(homework.getFullname());
            txtExpiration.setText(homework.getExpiration());

            shortName = homework.getShortName();
            title = homework.getTitle();
            instructions = homework.getInstructions();
            points = homework.getPoints();
        }

        //Professor
        void setProfessorHomeworkDate(HomeworkModel homework){
            txtCourse.setText(homework.getTitle().toUpperCase());
            txtExpiration.setText(homework.getExpiration());

            shortName = homework.getShortName();
            title = homework.getTitle();
            instructions = homework.getInstructions();
            points = homework.getPoints();
            id = 1;
        }

        @Override
        public void onClick(View view) {
            onHomeworkBtnClickListener.onBtnClick(shortName,
                    title,
                    txtCourse.getText().toString(),
                    txtExpiration.getText().toString(),
                    instructions,
                    points,
                    id);
        }
    }
}


