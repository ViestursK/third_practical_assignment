package com.example.repositoryg_1;

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
        sharedPreferences = getSharedPreferences("com.example.repositoryg_1.PREFERENCES", MODE_PRIVATE);

        applyTheme();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        String savedText = sharedPreferences.getString("SAVED_TEXT", "");
        EditText inputEditText = findViewById(R.id.textInputEditText);
        inputEditText.setText(savedText);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("SAVED_TEXT", inputEditText.getText().toString());
                editor.apply();
            }
        });

        Button goToSecondButton = findViewById(R.id.goToSecond);
        goToSecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int newThemeMode;

                switch (position) {
                    case 0:
                        newThemeMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                        break;
                    case 1:
                        newThemeMode = AppCompatDelegate.MODE_NIGHT_YES;
                        break;
                    case 2:
                        newThemeMode = AppCompatDelegate.MODE_NIGHT_NO;
                        break;
                    default:
                        newThemeMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                }

                int currentThemeMode = sharedPreferences.getInt("THEME_MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

                if (currentThemeMode != newThemeMode) {
                    editor.putInt("THEME_MODE", newThemeMode);
                    editor.apply();
                    recreate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void applyTheme() {
        int themeMode = sharedPreferences.getInt("THEME_MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        AppCompatDelegate.setDefaultNightMode(themeMode);
    }
}
