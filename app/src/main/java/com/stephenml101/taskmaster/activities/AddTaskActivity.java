package com.stephenml101.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stephenml101.taskmaster.MainActivity;
import com.stephenml101.taskmaster.R;
import com.stephenml101.taskmaster.models.Tasks;

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
           Tasks newTask = new Tasks(
                   ((EditText)findViewById(R.id.editTextBoxTaskTitle)).getText().toString(),
                   ((EditText)findViewById(R.id.editTextBoxTaskDescription)).getText().toString(),
                   new Date(),
                   Tasks.TaskStateEnum.fromString(addTaskSpinner.getSelectedItem().toString())
           );
//           taskMasterDatabase.taskDao().insertATask(newTask);
           Toast.makeText(this, "Task saved!", Toast.LENGTH_LONG).show();
        });





//        Button addTaskButton = (Button) findViewById(R.id.addTaskButtonPageTwo);
//        addTaskButton.setText("ADD TASK");
//
//        //grabbing a view and setting its values in one line
//        ((Button) findViewById(R.id.addTaskButtonPageTwo)).setText(R.string.add_task_text_view);
//
//        // Step 2/3 add onCLickListener
//        addTaskButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Step 4
//                System.out.println("Pressed!");
//                ((TextView) findViewById(R.id.submittedTextViewPageTwo)).setText(R.string.submitted_confirmation);
//            }
//        });
    }

    public void setUpTaskSpinner(){
    addTaskSpinner.setAdapter(new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            Tasks.TaskStateEnum.values()
    ));
    }
}