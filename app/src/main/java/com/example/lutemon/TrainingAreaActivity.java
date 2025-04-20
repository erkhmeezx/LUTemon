package com.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class TrainingAreaActivity extends AppCompatActivity implements LutemonAdapter.OnLutemonClickListener {
    // ui elements
    private RecyclerView recyclerViewTraining;
    private Button btnTrainSelected;
    // adapter and selected lutemon
    private LutemonAdapter adapter;
    private Lutemon selectedLutemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_area);
        // link views from layout
        recyclerViewTraining = findViewById(R.id.recyclerViewTraining);
        btnTrainSelected = findViewById(R.id.btnTrainSelected);
        // setup the recycler view
        setupRecyclerView();
        // handle train button click
        btnTrainSelected.setOnClickListener(v -> {
            if (selectedLutemon != null) {
                // train the selected lutemon
                Storage.getInstance().trainLutemon(selectedLutemon.getId());
                Toast.makeText(this, selectedLutemon.getName() + " trained successfully! +1 EXP", Toast.LENGTH_SHORT).show();  // shows the message
                adapter.updateLutemons(Storage.getInstance().getTrainingList());  // update list and disable button
                selectedLutemon = null;
                btnTrainSelected.setEnabled(false);
            }
        });
    }

    @Override
    protected void onResume() { //refresh list when activity comes back
        super.onResume();
        if (adapter != null) {
            adapter.updateLutemons(Storage.getInstance().getTrainingList());
        }
    }
    // setup recycler view with layout and adapter
    private void setupRecyclerView() {
        recyclerViewTraining.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonAdapter(Storage.getInstance().getTrainingList(), "training", this);
        recyclerViewTraining.setAdapter(adapter);
    }
    // when a lutemon is selected from the list
    @Override
    public void onLutemonSelected(Lutemon lutemon) {
        selectedLutemon = lutemon;
        btnTrainSelected.setEnabled(true);
        Toast.makeText(this, lutemon.getName() + " selected for training", Toast.LENGTH_SHORT).show();
    }
    // move lutemon to home from training
    @Override
    public void onMoveToHome(int id) {
        Storage.getInstance().moveToHome(id);
        adapter.updateLutemons(Storage.getInstance().getTrainingList());
        // if the selected one was moved, clear selection
        if (selectedLutemon != null && selectedLutemon.getId() == id) {
            selectedLutemon = null;
            btnTrainSelected.setEnabled(false);
        }

        Toast.makeText(this, "Lutemon moved to Home", Toast.LENGTH_SHORT).show();
    }
    // do nothing because it's already in training
    @Override
    public void onMoveToTraining(int id) {
        // already in training
    }

    @Override // move lutemon from training to battle
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