package com.stephenml101.taskmaster.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stephenml101.taskmaster.R;

@SuppressWarnings("ALL")
public class SettingsPageActivity extends AppCompatActivity {
    public static final String USER_NICKNAME_TAG = "userNickname"; // top of class so other classes can use it- don't declare anywhere else

    SharedPreferences preferences; //used in a click handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        populateNicknameEditText(preferences);
        setUpSaveButton(preferences);
    }

    public void populateNicknameEditText(SharedPreferences preferences){
        // grab value from sharedPreferences and set out EditText with that value
        String userNickname = preferences.getString(USER_NICKNAME_TAG,"");
        ((EditText) findViewById(R.id.settingsNicknameTitleInputTextView)).setText(userNickname);
    }

    public void setUpSaveButton(SharedPreferences preferences){
        Button saveButton = findViewById(R.id.settingsSaveButton);
        saveButton.setOnClickListener(view -> {
            // creating an editor bcause SharedPreferences is read-only
            SharedPreferences.Editor preferenceEditor = preferences.edit(); // fails if you declare preferences in onCreate()

            // grabbing the string we want to save from the user input
            EditText userNicknameEditText = findViewById(R.id.settingsNicknameTitleInputTextView);
            String userNicknameString = userNicknameEditText.getText().toString();

            // save string to shared preferences
            preferenceEditor.putString(USER_NICKNAME_TAG, userNicknameString);
            preferenceEditor.apply(); // Nothing happens if you don't do this

//            Two options
//            Snackbar.make(findViewById(R.id.userProfileActivity), "Nickname Saved!!", Snackbar.LENGTH_SHORT).show();
            Toast.makeText(SettingsPageActivity.this, "Nickname Saved!!", Toast.LENGTH_SHORT).show();
        });
    }
}