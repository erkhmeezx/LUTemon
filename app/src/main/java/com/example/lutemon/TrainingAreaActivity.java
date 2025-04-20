package com.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class TrainingAreaActivity extends AppCompatActivity implements LutemonAdapter.OnLutemonClickListener {

    private RecyclerView recyclerViewTraining;
    private Button btnTrainSelected;
    private LutemonAdapter adapter;
    private Lutemon selectedLutemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_area);

        recyclerViewTraining = findViewById(R.id.recyclerViewTraining);
        btnTrainSelected = findViewById(R.id.btnTrainSelected);

        setupRecyclerView();

        btnTrainSelected.setOnClickListener(v -> {
            if (selectedLutemon != null) {
                Storage.getInstance().trainLutemon(selectedLutemon.getId());
                Toast.makeText(this, selectedLutemon.getName() + " trained successfully! +1 EXP", Toast.LENGTH_SHORT).show();
                adapter.updateLutemons(Storage.getInstance().getTrainingList());
                selectedLutemon = null;
                btnTrainSelected.setEnabled(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.updateLutemons(Storage.getInstance().getTrainingList());
        }
    }

    private void setupRecyclerView() {
        recyclerViewTraining.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonAdapter(Storage.getInstance().getTrainingList(), "training", this);
        recyclerViewTraining.setAdapter(adapter);
    }

    @Override
    public void onLutemonSelected(Lutemon lutemon) {
        selectedLutemon = lutemon;
        btnTrainSelected.setEnabled(true);
        Toast.makeText(this, lutemon.getName() + " selected for training", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoveToHome(int id) {
        Storage.getInstance().moveToHome(id);
        adapter.updateLutemons(Storage.getInstance().getTrainingList());

        if (selectedLutemon != null && selectedLutemon.getId() == id) {
            selectedLutemon = null;
            btnTrainSelected.setEnabled(false);
        }

        Toast.makeText(this, "Lutemon moved to Home", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoveToTraining(int id) {
        // Already in training
    }

    @Override
    public void onMoveToBattle(int id) {
        Storage.getInstance().moveToBattle(id);
        adapter.updateLutemons(Storage.getInstance().getTrainingList());

        if (selectedLutemon != null && selectedLutemon.getId() == id) {
            selectedLutemon = null;
            btnTrainSelected.setEnabled(false);
        }

        Toast.makeText(this, "Lutemon moved to Battle Arena", Toast.LENGTH_SHORT).show();
    }
}