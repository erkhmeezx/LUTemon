package com.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateLutemonActivity extends AppCompatActivity {

    private EditText editTextName;
    private RadioGroup radioGroupTypes;
    private RadioButton radioWhite, radioGreen, radioPink, radioOrange, radioBlack;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lutemon);

        editTextName = findViewById(R.id.editTextName);
        radioGroupTypes = findViewById(R.id.radioGroupTypes);
        radioWhite = findViewById(R.id.radioWhite);
        radioGreen = findViewById(R.id.radioGreen);
        radioPink = findViewById(R.id.radioPink);
        radioOrange = findViewById(R.id.radioOrange);
        radioBlack = findViewById(R.id.radioBlack);
        btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(v -> createLutemon());
    }

    private void createLutemon() {
        String name = editTextName.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter a name for your Lutemon", Toast.LENGTH_SHORT).show();
            return;
        }

        Lutemon newLutemon = null;
        int selectedId = radioGroupTypes.getCheckedRadioButtonId();

        if (selectedId == R.id.radioWhite) {
            newLutemon = new WhiteLutemon(name);
        } else if (selectedId == R.id.radioGreen) {
            newLutemon = new GreenLutemon(name);
        } else if (selectedId == R.id.radioPink) {
            newLutemon = new PinkLutemon(name);
        } else if (selectedId == R.id.radioOrange) {
            newLutemon = new OrangeLutemon(name);
        } else if (selectedId == R.id.radioBlack) {
            newLutemon = new BlackLutemon(name);
        }

        if (newLutemon != null) {
            Storage.getInstance().addLutemon(newLutemon);
            Toast.makeText(this, name + " ("+newLutemon.getColor()+") created successfully!", Toast.LENGTH_SHORT).show();
            editTextName.setText("");
            radioWhite.setChecked(true);
        }
    }
}