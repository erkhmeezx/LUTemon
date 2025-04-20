package com.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
// This is the main activity that handles the navigation for the app
// also provides all major/main features through button navigation
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCreateLutemon = findViewById(R.id.btnCreateLutemon);
        Button btnManageLutemons = findViewById(R.id.btnManageLutemons);
        Button btnTrainingArea = findViewById(R.id.btnTrainingArea);
        Button btnBattleArena = findViewById(R.id.btnBattleArena);
    //  set up navigation to create lutemon screen
        btnCreateLutemon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateLutemonActivity.class);
            startActivity(intent);
        });
        //set up navigation to manage lutemons screen
        btnManageLutemons.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ManageLutemonsActivity.class);
            startActivity(intent);
        });
        // set up navigation to training area
        btnTrainingArea.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TrainingAreaActivity.class);
            startActivity(intent);
        });
    // set up navigation to battle arena
        btnBattleArena.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BattleArenaActivity.class);
            startActivity(intent);
        });
    }
}