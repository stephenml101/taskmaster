package com.stephenml101.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.stephenml101.taskmaster.activities.AddTaskActivity;
import com.stephenml101.taskmaster.activities.AllTasksActivity;
import com.stephenml101.taskmaster.activities.SettingsPageActivity;
import com.stephenml101.taskmaster.activities.TaskDetailActivity;
import com.stephenml101.taskmaster.adapters.TaskListRecyclerViewAdapter;
import com.stephenml101.taskmaster.models.Tasks;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TASK_NAME_EXTRA_TAG = "taskName";
    public static final String TASK_DETAIL_EXTRA_TAG = "taskDetail";
    private final String TAG = "MainActivity";
    public static final String DATABASE_NAME = "task_master_database";
    List<Tasks> tasks;
    TaskListRecyclerViewAdapter adapter;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO SETUP DATABASE QUERY

//        tasks = taskMasterDatabase.taskDao().findAllTasks();

        tasks = new ArrayList<>();

        setUpSettingsButton();
        setUpRecyclerView();
        setUpAddTaskButton();
        setUpAllTasksButton();

    }
    @Override
    protected void onResume() {
        super.onResume();
        tasks.clear();
        // TODO SETUP
//        tasks.addAll(taskMasterDatabase.taskDao().findAllTasks());
        adapter.notifyDataSetChanged();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userNickname = preferences.getString(SettingsPageActivity.USER_NICKNAME_TAG, "No nickname");
        ((TextView) findViewById(R.id.mainActivityNicknameTextView)).setText(userNickname + "'s Tasks");
    }
    public void setUpSettingsButton(){
        ((ImageView) findViewById(R.id.mainActivitySettingsImageView)).setOnClickListener(view -> {
            Intent goToSettingsPageIntent = new Intent(MainActivity.this, SettingsPageActivity.class);
            startActivity(goToSettingsPageIntent);
        });
    }

    public void setUpRecyclerView(){
        RecyclerView taskListRecyclerView = (RecyclerView) findViewById(R.id.mainActivityTaskListRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // example of horizontal list
//        ((LinearLayoutManager)layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        taskListRecyclerView.setLayoutManager(layoutManager);
        adapter = new TaskListRecyclerViewAdapter(tasks, this);
        taskListRecyclerView.setAdapter(adapter);
    }





    public void setUpAddTaskButton(){
        Button goToAddTaskButton = (Button) findViewById(R.id.mainAddTaskButton);

        goToAddTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddTaskIntent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(goToAddTaskIntent);
            }
        });
    }

    public void setUpAllTasksButton(){
        Button goToAllTasksButton = (Button) findViewById(R.id.mainAllTasksButton);
        goToAllTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAllTasksIntent = new Intent(MainActivity.this, AllTasksActivity.class);
                startActivity(goToAllTasksIntent);
            }
        });

    }


}