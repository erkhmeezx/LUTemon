package com.example.lutemon;

public abstract class Lutemon {
    //instance variables
    protected String name;
    protected String color;
    protected int attack;
    protected int defense;
    protected int experience = 0;
    protected int health;
    protected int maxHealth; // starts with full hp
    protected int id; // assign id
    private static int idCounter = 0;

    public Lutemon(String name, String color, int attack, int defense, int maxHealth) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.id = idCounter++;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getAttack() {
        return attack + experience;
    }

    public int getDefense() {
        return defense;
    }

    public int getExperience() {
        return experience;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getId() {
        return id;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void addExperience() {
        this.experience++;
    }

    public void resetHealth() {
        this.health = maxHealth;
    }

    public void train() {
        this.experience++;
    }

    public boolean defense(Lutemon attacker) {
        int attackPower = attacker.getAttack();
        int damage = Math.max(0, attackPower - this.defense);
        this.health -= damage;

        return this.health > 0;
    }

    @Override
    public String toString() {
        return color + "(" + name + ") att: " + getAttack() +
                "; def: " + defense + "; exp: " + experience +
                "; health: " + health + "/" + maxHealth;
    }
}



