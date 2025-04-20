package com.example.lutemon;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage {
    // this is the only instance of storage
    // stores the lutemons in different areas ( training, home, battle )
    private static Storage instance = null;
    private HashMap<Integer, Lutemon> home;
    private HashMap<Integer, Lutemon> trainingArea;
    private HashMap<Integer, Lutemon> battleArea;
    // private constructor s it can't be created from the outside
    private Storage() {
        home = new HashMap<>();
        trainingArea = new HashMap<>();
        battleArea = new HashMap<>();
    }
    // this gives the single instance of storage
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
    // add a new lutemon to the home area
    public void addLutemon(Lutemon lutemon) {
        home.put(lutemon.getId(), lutemon);
    }
    // get a lutemon from any area using its id
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
    // gets a list of all lutemons at home
    public ArrayList<Lutemon> getHomeList() {
        return new ArrayList<>(home.values());
    }
    // gets a list of all lutemons in training
    public ArrayList<Lutemon> getTrainingList() {
        return new ArrayList<>(trainingArea.values());
    }
    // gets a list of all lutemons in battle
    public ArrayList<Lutemon> getBattleList() {
        return new ArrayList<>(battleArea.values());
    }
    // !!! move a lutemon to home and reset its health
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
    // moves a lutemon to training
    public void moveToTraining(int id) {
        Lutemon lutemon = getLutemonById(id);
        if (lutemon != null) {
            if (home.containsKey(id)) {
                home.remove(id);
            } else battleArea.remove(id);
            trainingArea.put(id, lutemon);
        }
    }
    // moves a lutemon to battle
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
    // trains the lutemon ( only in training area )
    public void trainLutemon(int id) {
        if (trainingArea.containsKey(id)) {
            trainingArea.get(id).train();
        }
    }
    // removes a lutemon from the game
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