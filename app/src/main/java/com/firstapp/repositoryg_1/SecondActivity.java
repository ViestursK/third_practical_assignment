package com.firstapp.repositoryg_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatDelegate;

public class SecondActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize SharedPreferences for storing preferences
        sharedPreferences = getSharedPreferences("com.example.repository_1.PREFERENCES", MODE_PRIVATE);

        // Apply the selected theme
        applyTheme();

        // Find and initialize UI elements
        TextView savedValueTextView = findViewById(R.id.textView);
        Button readPreferencesButton = findViewById(R.id.readBtn);
        Button backButton = findViewById(R.id.backBtn);

        // Set up click listener to read and display saved value
        readPreferencesButton.setOnClickListener(view -> readAndDisplaySavedValue(savedValueTextView));

        // Set up click listener for the back button to finish the activity
        backButton.setOnClickListener(view -> finish());
    }

    private void readAndDisplaySavedValue(TextView savedValueTextView) {
        // Read the saved value from SharedPreferences
        String savedValue = sharedPreferences.getString("SAVED_TEXT", "");

        if (savedValue.isEmpty()) {
            // Display a toast message if the saved value is empty
            showEmptyMessage();
        } else {
            // Display the saved value in the TextView
            savedValueTextView.setText(savedValue);
        }
    }

    private void showEmptyMessage() {
        // Show a toast message when there's nothing saved
        Toast.makeText(SecondActivity.this, "Nothing found", Toast.LENGTH_SHORT).show();
    }

    private void applyTheme() {
        // Apply the selected theme mode from SharedPreferences
        int themeMode = sharedPreferences.getInt("THEME_MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        AppCompatDelegate.setDefaultNightMode(themeMode);
    }
}
