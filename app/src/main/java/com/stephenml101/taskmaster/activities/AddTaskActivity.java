package com.stephenml101.taskmaster.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.FileNotFoundException;
import java.io.InputStream;
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
ActivityResultLauncher<Intent> activityResultLauncher;

private String s3Key;
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

        activityResultLauncher = getImagePickingActivityResultLauncher();


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
        setupImageButton();

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

    public void setupImageButton(){
        findViewById(R.id.AddTaskActivityImageSelection).setOnClickListener(v -> {
            launchImageSelectionIntent();
        });
    }
    public void launchImageSelectionIntent(){
        Intent imageFilePickingIntent = new Intent(Intent.ACTION_GET_CONTENT);
        imageFilePickingIntent.setType("*/*");
        imageFilePickingIntent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/jpeg", "image/jpg"});

        activityResultLauncher.launch(imageFilePickingIntent);
    }

    private ActivityResultLauncher<Intent> getImagePickingActivityResultLauncher(){
        ActivityResultLauncher<Intent> imagePickingActivityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                // Uri of image -> the path
                                Uri pickedImageFileUri = result.getData().getData();
                                try {
                                    InputStream pickedImageInputStream = getContentResolver().openInputStream(pickedImageFileUri);
                                    String pickedImageFileName = getFileNameFromUri(pickedImageFileUri);
                                    Log.i(TAG, "Succeeded in getting input stream from file on phone! Filename is:" + pickedImageFileName);
                                    uploadInputStreamToS3(pickedImageInputStream, pickedImageFileName, pickedImageFileUri);
                                } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                );
        return imagePickingActivityResultLauncher;
    }
        public void uploadInputStreamToS3(InputStream pickedImageInputStream, String imageName, Uri pickedImageFileUri){
            Amplify.Storage.uploadInputStream(
                    imageName,
                    pickedImageInputStream,
                    success -> {
                        Log.i(TAG, "Succeeded in getting file uploaded to S3! Key is: " + success.getKey());
                        s3Key = success.getKey();  // non-empty s3ImageKey globally indicates there is an image picked in this activity currently
                        ImageView productImageView = findViewById(R.id.AddTaskActivityImageSelection);
                        InputStream pickedImageInputStreamCopy = null;  // need to make a copy because InputStreams cannot be reused!
                        try
                        {
                            pickedImageInputStreamCopy = getContentResolver().openInputStream(pickedImageFileUri);
                        }
                        catch (FileNotFoundException fnfe)
                        {
                            Log.e(TAG, "Could not get file stream from URI! " + fnfe.getMessage(), fnfe);
                        }
                        productImageView.setImageBitmap(BitmapFactory.decodeStream(pickedImageInputStreamCopy));
                    },
                    failure -> Log.e(TAG, "Upload failed", failure)
            );
        }


        // Taken from https://stackoverflow.com/a/25005243/16889809
        @SuppressLint("Range")
        public String getFileNameFromUri(Uri uri) {
            String result = null;
            if (uri.getScheme().equals("content")) {
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                try {
                    if (cursor != null && cursor.moveToFirst()) {
                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } finally {
                    cursor.close();
                }
            }
            if (result == null) {
                result = uri.getPath();
                int cut = result.lastIndexOf('/');
                if (cut != -1) {
                    result = result.substring(cut + 1);
                }
            }
            return result;
        }

}