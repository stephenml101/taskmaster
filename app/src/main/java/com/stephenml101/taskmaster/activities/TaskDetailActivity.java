package com.stephenml101.taskmaster.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.stephenml101.taskmaster.MainActivity;
import com.stephenml101.taskmaster.R;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        setUpTaskName();
        setUpTaskDetails();
        setUpLocationDetails();
    }

    public void setUpTaskName(){
        Intent callingIntent = getIntent();
        String taskNameString = null;

        if(callingIntent != null){
            taskNameString = callingIntent.getStringExtra(MainActivity.TASK_NAME_EXTRA_TAG);
        }

        TextView taskDetailsInfoTextView = findViewById(R.id.taskDetailTitle);
        if(taskNameString != null){
            taskDetailsInfoTextView.setText(taskNameString);
        } else {
            taskDetailsInfoTextView.setText(R.string.no_task_name);
        }
    }

    public void setUpTaskDetails(){
        Intent callingIntent = getIntent();
        String taskDetail = null;

        if(callingIntent != null){
            taskDetail= callingIntent.getStringExtra(MainActivity.TASK_DETAIL_EXTRA_TAG);
        }

        TextView taskDetailsInfoTextView = findViewById(R.id.taskDetailTextView);
        if(taskDetail != null){
            taskDetailsInfoTextView.setText(taskDetail);
        } else {
            taskDetailsInfoTextView.setText(R.string.no_task_detail);
        }
    }

    public void setUpLocationDetails(){
        Intent callingIntent = getIntent();
        String taskLatitude = null;
        String taskLongitude = null;

        if(callingIntent != null){
            taskLatitude = callingIntent.getStringExtra(MainActivity.TASK_LATITUDE_EXTRA_TAG);
            taskLongitude = callingIntent.getStringExtra(MainActivity.TASK_LONGITUDE_EXTRA_TAG);
        }

        TextView taskDetailsInfoTextView = findViewById(R.id.LocationTextView);
        if(taskLatitude!= null && taskLongitude != null){
            taskDetailsInfoTextView.setText("Location: " + taskLatitude + ", " + taskLongitude);
        } else {
            taskDetailsInfoTextView.setText("No Location");
        }
    }
}
