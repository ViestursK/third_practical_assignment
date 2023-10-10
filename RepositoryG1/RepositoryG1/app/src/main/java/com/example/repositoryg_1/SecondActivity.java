package com.example.repositoryg_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Intent;

public class SecondActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("com.example.repositoryg_1.PREFERENCES", MODE_PRIVATE);

        applyTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button readPreferencesButton = findViewById(R.id.readPreferencesButton);
        TextView savedValueTextView = findViewById(R.id.secondActivText);

        readPreferencesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedValue = sharedPreferences.getString("SAVED_TEXT", "");

                if (savedValue.isEmpty()) {
                    Toast.makeText(SecondActivity.this, "Nothing found", Toast.LENGTH_SHORT).show();
                } else {
                    savedValueTextView.setText(savedValue);
                }
            }
        });


        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void applyTheme() {
        int themeMode = sharedPreferences.getInt("THEME_MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        AppCompatDelegate.setDefaultNightMode(themeMode);
    }
}
