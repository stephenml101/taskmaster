package com.stephenml101.taskmaster.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;


import androidx.appcompat.app.AppCompatActivity;

import com.stephenml101.taskmaster.R;

@SuppressWarnings("ALL")
public class SettingsPageActivity extends AppCompatActivity {
    public static final String TAG = "SettingsProfileActivity";
    public static final String USER_NICKNAME_TAG = "userNickname"; // top of class so other classes can use it- don't declare anywhere else

    SharedPreferences preferences; //used in a click handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        populateNicknameEditText(preferences);
        setUpSaveButton(preferences);
        setUpLoginButton();
        setUpLogoutButton();
    }

    public void populateNicknameEditText(SharedPreferences preferences){
        // grab value from sharedPreferences and set out EditText with that value
        String userNickname = preferences.getString(USER_NICKNAME_TAG,"");
        ((EditText) findViewById(R.id.settingsNicknameTitleInputTextView)).setText(userNickname);
    }

    public void setUpSaveButton(SharedPreferences preferences){
        Button saveButton = findViewById(R.id.settingsSaveButton);
        saveButton.setOnClickListener(view -> {
            SharedPreferences.Editor preferenceEditor = preferences.edit();

            EditText userNicknameEditText = findViewById(R.id.settingsNicknameTitleInputTextView);
            String userNicknameString = userNicknameEditText.getText().toString();

            preferenceEditor.putString(USER_NICKNAME_TAG, userNicknameString);
            preferenceEditor.apply();

//            Two options
//            Snackbar.make(findViewById(R.id.userProfileActivity), "Nickname Saved!!", Snackbar.LENGTH_SHORT).show();
            Toast.makeText(SettingsPageActivity.this, "Nickname Saved!!", Toast.LENGTH_SHORT).show();
        });
    }

    public void setUpLoginButton() {
        Button loginButton = findViewById(R.id.userProfileActivityLoginButton);
        loginButton.setOnClickListener(v -> {
            Intent goToLoginActivity = new Intent(SettingsPageActivity.this, LoginActivity.class);
            startActivity(goToLoginActivity);
        });
    }

    public void setUpLogoutButton() {
        Button logoutButton = findViewById(R.id.userProfileActivityLogoutButton);
        logoutButton.setOnClickListener(v -> {
            AuthSignOutOptions options = AuthSignOutOptions.builder()
                    .globalSignOut(true)
                    .build();

            Amplify.Auth.signOut(options, signOutResult -> {
                if (signOutResult instanceof AWSCognitoAuthSignOutResult.CompleteSignOut) {
                    Log.i(TAG,"Global logout successful!");
                } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.PartialSignOut) {
                    Log.i(TAG,"Partial logout successful!");
                } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.FailedSignOut) {
                    Log.i(TAG,"Logout failed: " + signOutResult.toString());
                }
            });
        });
    }
}