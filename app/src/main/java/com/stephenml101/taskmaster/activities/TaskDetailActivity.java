package com.stephenml101.taskmaster.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.stephenml101.taskmaster.MainActivity;
import com.stephenml101.taskmaster.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TaskDetailActivity extends AppCompatActivity {
    public final String TAG = "TaskDetailActivity";
    private final MediaPlayer mp = new MediaPlayer();

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

    public void setUpSpeakButton() {
        Button speakButton = findViewById(R.id.taskDetailActivitySpeakButton);
        speakButton.setOnClickListener(v -> {
            String taskName; // set this from a TextView or EditTest or whatever is holding your task name
            taskName = ((TextView)findViewById(R.id.taskDetailActivitySpeakButton)).getText().toString();

            Amplify.Predictions.convertTextToSpeech(
                    taskName,
                    success -> playAudio(success.getAudioData(), taskName),
                    failure -> Log.e(TAG, "Audio conversion of task, " + taskName + ", failed", failure)
            );
        });
    }

    private void playAudio(InputStream data, String textToSpeak) {
        File mp3File = new File(getCacheDir(), "audio.mp3");

        try(OutputStream out = new FileOutputStream(mp3File)) {
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;

            while((bytesRead = data.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            Log.i(TAG, "Audio file finished reading");

            mp.reset();
            mp.setOnPreparedListener(MediaPlayer::start);
            mp.setDataSource(new FileInputStream(mp3File).getFD());
            mp.prepareAsync();

            Log.i(TAG, "Audio played");
            Log.i(TAG, "text to speak: " + textToSpeak);
        } catch(IOException error) {
            Log.e(TAG, "Error writing audio file");
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
