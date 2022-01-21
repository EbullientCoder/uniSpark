package com.example.unispark.view.university.fragment;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.unispark.R;
import com.example.unispark.controller.guicontroller.university.AddScheduleGuiController;
import com.example.unispark.viewadapter.LessonAdapter;
import com.example.unispark.bean.courses.BeanCoursesNames;
import com.example.unispark.bean.BeanLesson;
import com.example.unispark.bean.login.BeanLoggedUniversity;

import java.util.List;


public class AddScheduleView extends DialogFragment {


    //Dismiss Button
    ImageButton btnDismiss;
    //Add StudentScheduleGUIController Button
    Button btnAddSchedule;
    //Course Selector
    BeanCoursesNames bCoursesNames;
    AutoCompleteTextView autoCompleteTxtCourse;
    ArrayAdapter<String> adapterItemsCourse;
    String courseSelection = "";
    //Day Selector
    List<String> days;
    AutoCompleteTextView autoCompleteTxtDay;
    ArrayAdapter<String> adapterItemsDay;
    String daySelection = "";
    //Hour Selector
    List<String> hours;
    AutoCompleteTextView autoCompleteTxtHour;
    ArrayAdapter<String> adapterItemsHour;
    String hourSelection = "";
    int indexDay;
    LessonAdapter lessonAdapter;

    //Bean
    BeanLoggedUniversity bUniversity;
    List<BeanLesson> schedulesItem;

    int i;



    private AddScheduleGuiController scheduleGuiController;

    String DAYS[] = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
    String HOURS[] = {"08:30 - 09:15", "09:30 - 10:15", "10:30 - 11:15", "11:30 - 12:15", "12:30 - 13:15", "14:00 - 14:45", "15:00 - 15:45", "16:00 - 16:45", "17:00 - 17:45"};


    //Methods
    //Constructor
    public AddScheduleView(BeanLoggedUniversity bUniversity, LessonAdapter lessonAdapter, List<BeanLesson> schedulesItem, int indexDay) {
        //Getting Professor Object
        this.bUniversity = bUniversity;
        this.schedulesItem = schedulesItem;
        this.lessonAdapter = lessonAdapter;
        this.indexDay =  indexDay;
        this.scheduleGuiController = new AddScheduleGuiController();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_schedule, container, false);
        getDialog().setTitle("Simple Dialog");

        //Dismiss Button
        btnDismiss = rootView.findViewById(R.id.btn_schedule_goback);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        //Courses
        //Gui Controller: Get Courses
        bCoursesNames = scheduleGuiController.showCoursesNames(bUniversity.getFaculties());

        autoCompleteTxtCourse = rootView.findViewById(R.id.add_schedule_course);
        adapterItemsCourse = new ArrayAdapter<>(getContext(), R.layout.item_container_item, bCoursesNames.getCourses());
        autoCompleteTxtCourse.setAdapter(adapterItemsCourse);
        autoCompleteTxtCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                courseSelection = (String)parent.getItemAtPosition(position);
            }
        });

        //Days
        autoCompleteTxtDay = rootView.findViewById(R.id.add_schedule_day);
        adapterItemsDay = new ArrayAdapter<>(getContext(), R.layout.item_container_item, DAYS);
        autoCompleteTxtDay.setAdapter(adapterItemsDay);
        autoCompleteTxtDay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                daySelection = (String)parent.getItemAtPosition(position);
            }
        });

        //Hours
        autoCompleteTxtHour = rootView.findViewById(R.id.add_schedule_hour);
        adapterItemsHour = new ArrayAdapter<>(getContext(), R.layout.item_container_item, HOURS);
        autoCompleteTxtHour.setAdapter(adapterItemsHour);
        autoCompleteTxtHour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hourSelection = (String)parent.getItemAtPosition(position);
            }
        });



        //Add StudentScheduleGUIController
        btnAddSchedule = rootView.findViewById(R.id.btn_add_schedule_add);
        btnAddSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scheduleGuiController.addSchedule(getContext(), getDialog(), DAYS[indexDay], courseSelection, daySelection, hourSelection, schedulesItem, lessonAdapter);
            }
        });

        return rootView;
    }
}