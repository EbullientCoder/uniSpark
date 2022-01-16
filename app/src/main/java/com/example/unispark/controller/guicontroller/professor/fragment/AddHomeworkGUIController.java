package com.example.unispark.controller.guicontroller.professor.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.time.OffsetDateTime;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.unispark.R;
import com.example.unispark.adapter.HomeworksAdapter;
import com.example.unispark.bean.BeanCourse;
import com.example.unispark.bean.BeanHomework;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.controller.applicationcontroller.course.GetCourses;
import com.example.unispark.controller.applicationcontroller.homeworks.AddHomework;
import com.example.unispark.exceptions.GenericException;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;

public class AddHomeworkGUIController extends DialogFragment{


    //Dismiss Button
    ImageButton btnDismiss;
    //Add Homework Button
    Button btnAddHomework;
    //Title
    TextInputLayout txtTitle;
    //Instructions
    TextInputLayout txtInstructions;
    //Date Picker
    TextView txtDisplayDate;
    ImageButton btnSelectDate;
    DatePickerDialog.OnDateSetListener dateListener;
    Calendar calendar;
    String date;
    //Points
    TextInputLayout txtPoints;
    //Course Selector
    List<String> courses;
    String courseSelection;
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    //Professor Model
    BeanLoggedProfessor bProfessor;
    List<BeanCourse> bCourses;
    //Homework Model
    BeanHomework bHomework;
    List<BeanHomework> bHomeworkList = null;
    HomeworksAdapter homeworksAdapter = null;
    int i;


    //Constructor
    public AddHomeworkGUIController(BeanLoggedProfessor bProfessor){
        this.bProfessor = bProfessor;
    }

    public AddHomeworkGUIController(BeanLoggedProfessor bProfessor, List<BeanHomework> bHomeworkList, HomeworksAdapter homeworksAdapter) {
        //Getting Professor Object
        this.bProfessor = bProfessor;
        this.bHomeworkList = bHomeworkList;
        this.homeworksAdapter = homeworksAdapter;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_homework, container, false);
        getDialog().setTitle("Simple Dialog");

        //Dismiss Button
        btnDismiss = rootView.findViewById(R.id.btn_goback);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        //DropDown Selector
        GetCourses getCoursesController = new GetCourses();
        bCourses = getCoursesController.getCourses(bProfessor);
        courses = getCoursesController.getCoursesNames(bProfessor);

        autoCompleteTxt = rootView.findViewById(R.id.select_course);
        adapterItems = new ArrayAdapter<>(getContext(), R.layout.item_container_item, courses);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                courseSelection = (String)parent.getItemAtPosition(position);

                //Getting the selected Course position
                i = position;
            }
        });



        //Date Picker
        calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        txtDisplayDate = rootView.findViewById(R.id.txt_selected_date);
        OffsetDateTime offset = OffsetDateTime.now();
        txtDisplayDate.setText(offset.getDayOfMonth() + " / " + offset.getMonthValue() + " / " + offset.getYear());

        btnSelectDate = rootView.findViewById(R.id.btn_select_date);
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListener,
                        year,
                        month,
                        day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                date = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);

                txtDisplayDate.setText(date);
            }
        };



        //Creating the Homework & Adding it into the Database
        //Title
        txtTitle = rootView.findViewById(R.id.txt_add_homework_title);
        //Instructions
        txtInstructions = rootView.findViewById(R.id.txt_add_homework_instructions);
        //Points
        txtPoints = rootView.findViewById(R.id.txt_add_homework_points);

        //Add Homework
        btnAddHomework = rootView.findViewById(R.id.btn_add_homework_add);
        btnAddHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = txtTitle.getEditText().getText().toString();
                String instructions = txtInstructions.getEditText().getText().toString();
                String points = txtPoints.getEditText().getText().toString();

                //Homework Object
                bHomework = new BeanHomework(
                        bCourses.get(i).getShortName(),
                        bCourses.get(i).getFullName(),
                        title,
                        date,
                        instructions,
                        points,
                        bProfessor.getId());


                //Application Controller
                AddHomework addHomeworkAppController = new AddHomework();
                try {
                    addHomeworkAppController.addHomework(bHomework);

                    //Notify the Homework Adapter
                    if(bHomeworkList != null && homeworksAdapter != null){
                        bHomeworkList.add(bHomework);
                        homeworksAdapter.notifyDataSetChanged();
                    }
                    dismiss();
                } catch (GenericException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        return rootView;
    }
}