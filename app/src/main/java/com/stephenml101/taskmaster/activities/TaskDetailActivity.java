package com.stephenml101.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.stephenml101.taskmaster.MainActivity;
import com.stephenml101.taskmaster.R;

import java.awt.font.TextAttribute;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        setUpTaskName();
        setUpTaskDetails();
    }

    public void setUpTaskName(){
        Intent callingIntent = getIntent();
        String taskNameString = null;

        if(callingIntent != null){
            taskNameString = callingIntent.getStringExtra(MainActivity.TASK_NAME_EXTRA_TAG);
        }

        TextView taskDetailsInfoTextView = (TextView) findViewById(R.id.taskDetailTitle);
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

        TextView taskDetailsInfoTextView = (TextView) findViewById(R.id.taskDetailTextView);
        if(taskDetail != null){
            taskDetailsInfoTextView.setText(taskDetail);
        } else {
            taskDetailsInfoTextView.setText(R.string.no_task_detail);
        }
    }
}
