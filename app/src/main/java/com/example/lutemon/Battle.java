package com.example.lutemon;

import java.util.ArrayList;

public class Battle {
    public interface BattleListener {
        void onBattleUpdate(String message);
        void onBattleEnd(Lutemon winner, Lutemon loser);
    }

    private Lutemon lutemonA;
    private Lutemon lutemonB;
    private ArrayList<String> battleLog;
    private BattleListener listener;

    public Battle(Lutemon lutemonA, Lutemon lutemonB) {
        this.lutemonA = lutemonA;
        this.lutemonB = lutemonB;
        this.battleLog = new ArrayList<>();
    }

    public void setBattleListener(BattleListener listener) {
        this.listener = listener;
    }

    public void start() {
        Lutemon attacker = lutemonA;
        Lutemon defender = lutemonB;

        logBattleStats(attacker.getId(), defender.getId());

        while (true) {
            String attackMessage = attacker.getColor() + "(" + attacker.getName() + ") attacks " +
                    defender.getColor() + "(" + defender.getName() + ")";
            logBattle(attackMessage);

            boolean survived = defender.defense(attacker);

            if (survived) {
                String surviveMessage = defender.getColor() + "(" + defender.getName() + ") manages to escape death.";
                logBattle(surviveMessage);

                // Swap roles
                Lutemon temp = attacker;
                attacker = defender;
                defender = temp;

                logBattleStats(attacker.getId(), defender.getId());
            } else {
                String deathMessage = defender.getColor() + "(" + defender.getName() + ") gets killed.";
                logBattle(deathMessage);
                logBattle("The battle is over.");

                // Award experience to winner
                attacker.addExperience();

                if (listener != null) {
                    listener.onBattleEnd(attacker, defender);
                }
                break;
            }
        }
    }

    private void logBattleStats(int attackerId, int defenderId) {
        Lutemon a = Storage.getInstance().getLutemonById(attackerId);
        Lutemon b = Storage.getInstance().getLutemonById(defenderId);

        String statsMessage = attackerId + ": " + a.toString() + "\n" +
                defenderId + ": " + b.toString();
        logBattle(statsMessage);
    }

    private void logBattle(String message) {
        battleLog.add(message);
        if (listener != null) {
            listener.onBattleUpdate(message);
        }
    }

    public ArrayList<String> getBattleLog() {
        return battleLog;
    }
}