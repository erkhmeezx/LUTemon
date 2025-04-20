package com.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
// activity for selecting lutemons to battle
public class BattleArenaActivity extends AppCompatActivity implements LutemonAdapter.OnLutemonClickListener {
    // ui
    private RecyclerView recyclerViewBattle;
    private TextView textViewLutemon1, textViewLutemon2;
    private Button btnStartBattle;
    private LutemonAdapter adapter;
    // selected lutemons for battle
    private Lutemon lutemon1;
    private Lutemon lutemon2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_arena);

        recyclerViewBattle = findViewById(R.id.recyclerViewBattle);
        textViewLutemon1 = findViewById(R.id.textViewLutemon1);
        textViewLutemon2 = findViewById(R.id.textViewLutemon2);
        btnStartBattle = findViewById(R.id.btnStartBattle);

        setupRecyclerView();
        // starts the battle when 2 lutemons are selected
        btnStartBattle.setOnClickListener(v -> {
            if (lutemon1 != null && lutemon2 != null) {
                Intent intent = new Intent(BattleArenaActivity.this, BattleViewActivity.class);
                intent.putExtra("lutemon1_id", lutemon1.getId());
                intent.putExtra("lutemon2_id", lutemon2.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.updateLutemons(Storage.getInstance().getBattleList());
        }

        // Reset selections when returning to this screen
        lutemon1 = null;
        lutemon2 = null;
        textViewLutemon1.setText("None selected");
        textViewLutemon2.setText("None selected");
        btnStartBattle.setEnabled(false);
    }

    private void setupRecyclerView() {
        recyclerViewBattle.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonAdapter(Storage.getInstance().getBattleList(), "battle", this);
        recyclerViewBattle.setAdapter(adapter);
    }
   // lutemon selection and deselection logic for battle
    @Override
    public void onLutemonSelected(Lutemon lutemon) {
        if (lutemon1 == null) {
            lutemon1 = lutemon;
            textViewLutemon1.setText(lutemon.toString());
        } else if (lutemon2 == null && lutemon.getId() != lutemon1.getId()) {
            lutemon2 = lutemon;
            textViewLutemon2.setText(lutemon.toString());
            btnStartBattle.setEnabled(true);
        } else if (lutemon.getId() == lutemon1.getId()) {
            // Deselect
            lutemon1 = null;
            textViewLutemon1.setText("None selected");
            btnStartBattle.setEnabled(false);
            // handles deselection
        } else if (lutemon2 != null && lutemon.getId() == lutemon2.getId()) {
            // deselect
            lutemon2 = null;
            textViewLutemon2.setText("None selected");
            btnStartBattle.setEnabled(false);
        }
    }

    @Override
    public void onMoveToHome(int id) {
        Storage.getInstance().moveToHome(id);
        adapter.updateLutemons(Storage.getInstance().getBattleList());

        // Reset selections if moved Lutemon was selected
        if (lutemon1 != null && lutemon1.getId() == id) {
            lutemon1 = null;
            textViewLutemon1.setText("None selected");
            btnStartBattle.setEnabled(false);
        }

        if (lutemon2 != null && lutemon2.getId() == id) {
            lutemon2 = null;
            textViewLutemon2.setText("None selected");
            btnStartBattle.setEnabled(false);
        }

        Toast.makeText(this, "Lutemon moved to Home", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoveToTraining(int id) {
        Storage.getInstance().moveToTraining(id);
        adapter.updateLutemons(Storage.getInstance().getBattleList());

        // reset selections if moved lutemon was selected
        if (lutemon1 != null && lutemon1.getId() == id) {
            lutemon1 = null;
            textViewLutemon1.setText("None selected");
            btnStartBattle.setEnabled(false);
        }

        if (lutemon2 != null && lutemon2.getId() == id) {
            lutemon2 = null;
            textViewLutemon2.setText("None selected");
            btnStartBattle.setEnabled(false);
        }

        Toast.makeText(this, "Lutemon moved to Training Area", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoveToBattle(int id) {
        // already in battle
    }
}