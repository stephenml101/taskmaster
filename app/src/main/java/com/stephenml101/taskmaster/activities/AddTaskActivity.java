package com.stephenml101.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.TaskStateEnum;
import com.amplifyframework.datastore.generated.model.Team;
import com.stephenml101.taskmaster.R;
//import com.stephenml101.taskmaster.models.Tasks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddTaskActivity extends AppCompatActivity {
public static final String TAG = "AddTaskActivity";

Spinner addTaskSpinner;
Spinner teamSpinner;

CompletableFuture<List<Team>> teamFuture = new CompletableFuture<>();
ArrayList<String> teamNames;
ArrayList<Team> team;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        addTaskSpinner = findViewById(R.id.AddTaskEnumSpinner);
        teamSpinner = findViewById(R.id.AddTaskTeamSpinner);
        teamNames = new ArrayList<>();
        team = new ArrayList<>();

        Amplify.API.query(
                ModelQuery.list(Team.class),
                success -> {
                    Log.i(TAG, "Read Team successfully!");
                    for (Team databaseTeam : success.getData() ) {
                        teamNames.add(databaseTeam.getName());
                        team.add(databaseTeam);
                    }
                    teamFuture.complete(team);
                    runOnUiThread(this::setUpSpinners);
                },
                failure -> {
                    teamFuture.complete(null);
                    Log.e(TAG, "Did not read Team successfully!" + failure);
                }
        );

        setUpAddTaskButton();

    }

    public void setUpAddTaskButton(){

        findViewById(R.id.addTaskButtonPageTwo).setOnClickListener(view -> {
            String selectedTeamStringName = teamSpinner.getSelectedItem().toString();
            try {
                team = (ArrayList<Team>) teamFuture.get();
            } catch (InterruptedException | ExecutionException ie){
                ie.printStackTrace();
            }

            Team selectedTeam = team.stream().filter(eachTeam -> eachTeam.getName().equals(selectedTeamStringName)).findAny().orElseThrow(RuntimeException::new);
            String taskName = ((EditText)findViewById(R.id.editTextBoxTaskTitle)).getText().toString();
            String description = ((EditText)findViewById(R.id.editTextBoxTaskDescription)).getText().toString();
//
            Task newTask = Task.builder()
                    .name(taskName)
                    .description(description)
                    .dateCreated(new Temporal.DateTime(new Date(), 0))
                    .taskState((TaskStateEnum)addTaskSpinner.getSelectedItem())
                    .taskOwner(selectedTeam)
                    .build();
            Amplify.API.mutate(
                    ModelMutation.create(newTask),
                    successResponse -> Log.i(TAG, "AddTaskActivity.onCreate().setUpAddTaskButton(): made a new task successfully!"),
                    failureResponse -> Log.i(TAG, "AddTaskActivity.onCreate().setUpAddTaskButton(): failed with this response: " + failureResponse)
            );

           Toast.makeText(this, "Task saved!", Toast.LENGTH_LONG).show();
        });



    }

    public void setUpSpinners(){
    addTaskSpinner.setAdapter(new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            TaskStateEnum.values()
    ));
    teamSpinner.setAdapter(new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            teamNames
    ));
    }
}