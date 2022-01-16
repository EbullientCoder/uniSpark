package com.example.unispark.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.ProfessorModel;

import java.util.List;

public class ProfessorsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //Attributes
    List<BeanLoggedProfessor> beanLoggedProfessorList;
    OnProfessorClickListener onProfessorClickListener;

    //Interface
    public interface OnProfessorClickListener{
        void onProfessorClick(int profImageID, String firstname, String lastname, String website, List<CourseModel> courses);
    }


    //Methods
    public ProfessorsAdapter(List<BeanLoggedProfessor> beanLoggedProfessorList, OnProfessorClickListener onProfessorClickListener){
        this.beanLoggedProfessorList = beanLoggedProfessorList;
        this.onProfessorClickListener = onProfessorClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfessorViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_professors,
                        parent,
                        false
                ), onProfessorClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BeanLoggedProfessor professor = (BeanLoggedProfessor) beanLoggedProfessorList.get(position);
        ((ProfessorViewHolder) holder).setProfessorDate(professor);
    }

    @Override
    public int getItemCount() {
        return beanLoggedProfessorList.size();
    }


    //First Row
    static class ProfessorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Attributes
        private int imgProfessorID;
        private String firstName;
        private String lastName;
        private String website;
        private List<CourseModel> courses;

        private ImageView imgProfessor;
        private TextView txtProfFirstname;
        private TextView txtProfLastname;
        private OnProfessorClickListener onProfessorClickListener;

        //Methods
        //Constructor
        ProfessorViewHolder(@NonNull View itemView, OnProfessorClickListener onProfessorClickListener) {
            super(itemView);
            imgProfessor = itemView.findViewById(R.id.img_professor_image);
            txtProfFirstname = itemView.findViewById(R.id.txt_professor_name);
            txtProfLastname = itemView.findViewById(R.id.txt_professor_surname);

            this.onProfessorClickListener = onProfessorClickListener;
            itemView.setOnClickListener(this);
        }

        void setProfessorDate(BeanLoggedProfessor professor){
            imgProfessor.setImageResource(professor.getProfilePicture());
            txtProfFirstname.setText(professor.getFirstName());
            txtProfLastname.setText(professor.getLastName());

            imgProfessorID = professor.getProfilePicture();
            firstName = professor.getFirstName();
            lastName = professor.getLastName();
            website = professor.getWebsite();
            courses = professor.getCourses();
        }

        @Override
        public void onClick(View view) {
            onProfessorClickListener.onProfessorClick(imgProfessorID, firstName, lastName, website, courses);
        }
    }

}

