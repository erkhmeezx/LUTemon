package com.example.lutemon;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage {
    private static Storage instance = null;
    private HashMap<Integer, Lutemon> home;
    private HashMap<Integer, Lutemon> trainingArea;
    private HashMap<Integer, Lutemon> battleArea;

    private Storage() {
        home = new HashMap<>();
        trainingArea = new HashMap<>();
        battleArea = new HashMap<>();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public void addLutemon(Lutemon lutemon) {
        home.put(lutemon.getId(), lutemon);
    }

    public Lutemon getLutemonById(int id) {
        if (home.containsKey(id)) {
            return home.get(id);
        } else if (trainingArea.containsKey(id)) {
            return trainingArea.get(id);
        } else if (battleArea.containsKey(id)) {
            return battleArea.get(id);
        }
        return null;
    }

    public ArrayList<Lutemon> getHomeList() {
        return new ArrayList<>(home.values());
    }

    public ArrayList<Lutemon> getTrainingList() {
        return new ArrayList<>(trainingArea.values());
    }

    public ArrayList<Lutemon> getBattleList() {
        return new ArrayList<>(battleArea.values());
    }

    public void moveToHome(int id) {
        Lutemon lutemon = getLutemonById(id);
        if (lutemon != null) {
            if (trainingArea.containsKey(id)) {
                trainingArea.remove(id);
            } else if (battleArea.containsKey(id)) {
                battleArea.remove(id);
            }
            lutemon.resetHealth();
            home.put(id, lutemon);
        }
    }

    public void moveToTraining(int id) {
        Lutemon lutemon = getLutemonById(id);
        if (lutemon != null) {
            if (home.containsKey(id)) {
                home.remove(id);
            } else battleArea.remove(id);
            trainingArea.put(id, lutemon);
        }
    }

    public void moveToBattle(int id) {
        Lutemon lutemon = getLutemonById(id);
        if (lutemon != null) {
            if (home.containsKey(id)) {
                home.remove(id);
            } else if (trainingArea.containsKey(id)) {
                trainingArea.remove(id);
            }
            battleArea.put(id, lutemon);
        }
    }

    public void trainLutemon(int id) {
        if (trainingArea.containsKey(id)) {
            trainingArea.get(id).train();
        }
    }

    public void removeLutemon(int id) {
        if (home.containsKey(id)) {
            home.remove(id);
        } else if (trainingArea.containsKey(id)) {
            trainingArea.remove(id);
        } else if (battleArea.containsKey(id)) {
            battleArea.remove(id);
        }
    }
}