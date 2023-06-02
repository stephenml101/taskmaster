package com.stephenml101.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.TaskStateEnum;
import com.stephenml101.taskmaster.MainActivity;
import com.stephenml101.taskmaster.R;
//import com.stephenml101.taskmaster.models.Tasks;

import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
public static final String TAG = "AddTaskActivity";

Spinner addTaskSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        addTaskSpinner = findViewById(R.id.AddTaskEnumSpinner);


        setUpAddTaskButton();
        setUpTaskSpinner();
    }

    public void setUpAddTaskButton(){

        findViewById(R.id.addTaskButtonPageTwo).setOnClickListener(view -> {
            String taskName = ((EditText)findViewById(R.id.editTextBoxTaskTitle)).getText().toString();
            String description = ((EditText)findViewById(R.id.editTextBoxTaskDescription)).getText().toString();
//           Tasks newTask = new Tasks(
//                   ((EditText)findViewById(R.id.editTextBoxTaskTitle)).getText().toString(),
//                   ((EditText)findViewById(R.id.editTextBoxTaskDescription)).getText().toString(),
//                   new Date(),
//                   Tasks.TaskStateEnum.fromString(addTaskSpinner.getSelectedItem().toString())
//           );
//           taskMasterDatabase.taskDao().insertATask(newTask);
            Task newTask = Task.builder()
                    .name(taskName)
                            .description(description)
                                    .dateCreated(new Temporal.DateTime(new Date(), 0))
                                            .taskState((TaskStateEnum)addTaskSpinner.getSelectedItem())
                                                    .build();
            Amplify.API.mutate(
                    ModelMutation.create(newTask),
                    successResponse -> Log.i(TAG, "AddTaskActivity.onCreate().setUpAddTaskButton(): made a new task successfully!"),
                    failureResponse -> Log.i(TAG, "AddTaskActivity.onCreate().setUpAddTaskButton(): failed with this response: " + failureResponse)
            );

           Toast.makeText(this, "Task saved!", Toast.LENGTH_LONG).show();
        });



    }

    public void setUpTaskSpinner(){
    addTaskSpinner.setAdapter(new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            TaskStateEnum.values()
    ));
    }
}