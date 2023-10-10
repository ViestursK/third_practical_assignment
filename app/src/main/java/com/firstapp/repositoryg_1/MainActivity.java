package com.firstapp.repositoryg_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SharedPreferences for storing preferences
        sharedPreferences = getSharedPreferences("com.example.repository_1.PREFERENCES", MODE_PRIVATE);

        // Apply the selected theme
        applyTheme();

        // Find and initialize UI elements
        EditText inputEditText = findViewById(R.id.editText);
        Button saveButton = findViewById(R.id.saveBtn);
        Button goToSecondButton = findViewById(R.id.goToSecondBtn);
        Spinner spinner = findViewById(R.id.spinner);

        // Load saved text into the EditText
        loadSavedText(inputEditText);

        // Set up click listeners for buttons
        saveButton.setOnClickListener(view -> saveTextToPreferences(inputEditText));
        goToSecondButton.setOnClickListener(this::startSecondActivity);

        // Set up theme selection handling for the Spinner
        setupThemeSelection(spinner);
    }

    private void loadSavedText(EditText editText) {
        // Load previously saved text from SharedPreferences
        String savedText = sharedPreferences.getString("SAVED_TEXT", "");
        editText.setText(savedText);
    }

    private void saveTextToPreferences(EditText editText) {
        // Save the text entered by the user to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("SAVED_TEXT", editText.getText().toString());
        editor.apply();
    }

    private void startSecondActivity(View view) {
        // Start the SecondActivity
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    private void setupThemeSelection(Spinner spinner) {
        // Set up theme selection handling for the Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int newThemeMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

                // Determine the selected theme mode
                if (position == 1) {
                    newThemeMode = AppCompatDelegate.MODE_NIGHT_YES;
                } else if (position == 2) {
                    newThemeMode = AppCompatDelegate.MODE_NIGHT_NO;
                }

                // Get the current theme mode
                int currentThemeMode = sharedPreferences.getInt("THEME_MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

                // Apply the new theme mode if it's different from the current one
                if (currentThemeMode != newThemeMode) {
                    editor.putInt("THEME_MODE", newThemeMode);
                    editor.apply();
                    recreate(); // Recreate the activity to apply the new theme
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void applyTheme() {
        // Apply the selected theme mode from SharedPreferences
        int themeMode = sharedPreferences.getInt("THEME_MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        AppCompatDelegate.setDefaultNightMode(themeMode);
    }
}
