package com.stephenml101.taskmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.stephenml101.taskmaster.activities.AddTaskActivity;
import com.stephenml101.taskmaster.activities.AllTasksActivity;
import com.stephenml101.taskmaster.activities.SettingsPageActivity;
import com.stephenml101.taskmaster.adapters.TaskListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TASK_NAME_EXTRA_TAG = "taskName";
    public static final String TASK_DETAIL_EXTRA_TAG = "taskDetail";

    private final String TAG = "MainActivity";
    List<Task> tasks;
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
    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    @Override
    protected void onResume() {
        super.onResume();
        tasks.clear();

        Amplify.API.query(
                ModelQuery.list(Task.class),
                success -> {
                    Log.i(TAG, "Read tasks successfully!");
                    tasks.clear();
                    for(Task databaseTask : success.getData()){
                        String selectedTeamName = "Task Masterpieces";
                        if(databaseTask.getTaskOwner() != null){
                            if(databaseTask.getTaskOwner().getName().equals(selectedTeamName)) {
                                tasks.add(databaseTask);
                            }
                        }

                    }
                    runOnUiThread(()-> adapter.notifyDataSetChanged());
                },
                failure -> Log.i(TAG, "Did not read tasks successfully!")
        );



        adapter.notifyDataSetChanged();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userNickname = preferences.getString(SettingsPageActivity.USER_NICKNAME_TAG, "No nickname");
        ((TextView) findViewById(R.id.mainActivityNicknameTextView)).setText(userNickname + "'s Tasks");
    }
    public void setUpSettingsButton(){
        findViewById(R.id.mainActivitySettingsImageView).setOnClickListener(view -> {
            Intent goToSettingsPageIntent = new Intent(MainActivity.this, SettingsPageActivity.class);
            startActivity(goToSettingsPageIntent);
        });
    }

    public void setUpRecyclerView(){
        RecyclerView taskListRecyclerView = findViewById(R.id.mainActivityTaskListRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // example of horizontal list
//        ((LinearLayoutManager)layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        taskListRecyclerView.setLayoutManager(layoutManager);
        adapter = new TaskListRecyclerViewAdapter(tasks, this);
        taskListRecyclerView.setAdapter(adapter);
    }





    public void setUpAddTaskButton(){
        Button goToAddTaskButton = findViewById(R.id.mainAddTaskButton);

        goToAddTaskButton.setOnClickListener(view -> {
            Intent goToAddTaskIntent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(goToAddTaskIntent);
        });
    }

    public void setUpAllTasksButton(){
        Button goToAllTasksButton = findViewById(R.id.mainAllTasksButton);
        goToAllTasksButton.setOnClickListener(view -> {
            Intent goToAllTasksIntent = new Intent(MainActivity.this, AllTasksActivity.class);
            startActivity(goToAllTasksIntent);
        });

    }


}