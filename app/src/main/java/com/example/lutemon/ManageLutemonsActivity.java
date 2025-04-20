package com.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class ManageLutemonsActivity extends AppCompatActivity implements LutemonAdapter.OnLutemonClickListener {

    private RecyclerView recyclerViewHome;
    private RecyclerView recyclerViewTraining;
    private RecyclerView recyclerViewBattle;

    private LutemonAdapter homeAdapter;
    private LutemonAdapter trainingAdapter;
    private LutemonAdapter battleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_lutemons);

        recyclerViewHome = findViewById(R.id.recyclerViewHome);
        recyclerViewTraining = findViewById(R.id.recyclerViewTraining);
        recyclerViewBattle = findViewById(R.id.recyclerViewBattle);

        setupRecyclerViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAdapters();
    }

    private void setupRecyclerViews() {
        // Home RecyclerView
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(this));
        homeAdapter = new LutemonAdapter(Storage.getInstance().getHomeList(), "home", this);
        recyclerViewHome.setAdapter(homeAdapter);

        // Training RecyclerView
        recyclerViewTraining.setLayoutManager(new LinearLayoutManager(this));
        trainingAdapter = new LutemonAdapter(Storage.getInstance().getTrainingList(), "training", this);
        recyclerViewTraining.setAdapter(trainingAdapter);

        // Battle RecyclerView
        recyclerViewBattle.setLayoutManager(new LinearLayoutManager(this));
        battleAdapter = new LutemonAdapter(Storage.getInstance().getBattleList(), "battle", this);
        recyclerViewBattle.setAdapter(battleAdapter);
    }

    private void updateAdapters() {
        homeAdapter.updateLutemons(Storage.getInstance().getHomeList());
        trainingAdapter.updateLutemons(Storage.getInstance().getTrainingList());
        battleAdapter.updateLutemons(Storage.getInstance().getBattleList());
    }

    @Override
    public void onLutemonSelected(Lutemon lutemon) {
        // Not used in this activity
    }

    @Override
    public void onMoveToHome(int id) {
        Storage.getInstance().moveToHome(id);
        updateAdapters();
        Toast.makeText(this, "Lutemon moved to Home", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoveToTraining(int id) {
        Storage.getInstance().moveToTraining(id);
        updateAdapters();
        Toast.makeText(this, "Lutemon moved to Training Area", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoveToBattle(int id) {
        Storage.getInstance().moveToBattle(id);
        updateAdapters();
        Toast.makeText(this, "Lutemon moved to Battle Arena", Toast.LENGTH_SHORT).show();
    }
}