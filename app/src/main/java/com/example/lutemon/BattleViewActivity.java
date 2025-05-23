package com.example.lutemon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
// activity that displays and manages the battle betwen lutemons
public class BattleViewActivity extends AppCompatActivity implements Battle.BattleListener {

    private TextView textViewBattleLog; // displays progress
    private Button btnBackToArena;  // returns to battle selection
    private StringBuilder battleLog; // gathers battle messages

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_view);
    // ui
        textViewBattleLog = findViewById(R.id.textViewBattleLog);
        btnBackToArena = findViewById(R.id.btnBackToArena);
        battleLog = new StringBuilder();
    // set up
        btnBackToArena.setOnClickListener(v -> finish());

        // Get the Lutemons from the intent
        int lutemon1Id = getIntent().getIntExtra("lutemon1_id", -1);
        int lutemon2Id = getIntent().getIntExtra("lutemon2_id", -1);

        if (lutemon1Id != -1 && lutemon2Id != -1) {
            Lutemon lutemon1 = Storage.getInstance().getLutemonById(lutemon1Id);
            Lutemon lutemon2 = Storage.getInstance().getLutemonById(lutemon2Id);

            if (lutemon1 != null && lutemon2 != null) {
                // Start the battle
                Battle battle = new Battle(lutemon1, lutemon2);
                battle.setBattleListener(this);
                battle.start();
            }
        }
    }

    @Override // updates the battle log with new messages
    public void onBattleUpdate(String message) {
        battleLog.append(message).append("\n\n");
        textViewBattleLog.setText(battleLog.toString());
    }

    @Override // handles conclusion
    public void onBattleEnd(Lutemon winner, Lutemon loser) {
        // remove the loser from storage
        Storage.getInstance().removeLutemon(loser.getId());

        // return the winner to the battle area
        Storage.getInstance().moveToBattle(winner.getId());
    }
}