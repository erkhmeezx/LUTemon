package com.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCreateLutemon = findViewById(R.id.btnCreateLutemon);
        Button btnManageLutemons = findViewById(R.id.btnManageLutemons);
        Button btnTrainingArea = findViewById(R.id.btnTrainingArea);
        Button btnBattleArena = findViewById(R.id.btnBattleArena);

        btnCreateLutemon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateLutemonActivity.class);
            startActivity(intent);
        });

        btnManageLutemons.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ManageLutemonsActivity.class);
            startActivity(intent);
        });

        btnTrainingArea.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TrainingAreaActivity.class);
            startActivity(intent);
        });

        btnBattleArena.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BattleArenaActivity.class);
            startActivity(intent);
        });
    }
}