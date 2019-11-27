package com.example.razbuc.characters;

import java.util.ArrayList;

public class Hero {

    private String name;
    private int health_points;
    private ArrayList<String> inventory;
    private int movement;
    private int damages;
    private ArrayList<String> allies;
    private ArrayList<Integer> position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth_points() {
        return health_points;
    }

    public void setHealth_points(int health_points) {
        this.health_points = health_points;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getDamages() {
        return damages;
    }

    public void setDamages(int damages) {
        this.damages = damages;
    }

    public ArrayList<String> getAllies() {
        return allies;
    }

    public void setAllies(ArrayList<String> allies) {
        this.allies = allies;
    }

    public ArrayList<Integer> getPosition() {
        return position;
    }

    public void setPosition(ArrayList<Integer> position) {
        this.position = position;
    }


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    public void gainHealth_points(int value) {
        setHealth_points(this.health_points + value);
    }

    public void loseHealth_points(int value) {
        int result = this.health_points - value;
        if (result < 0) {
            setHealth_points(0);
        }
        else {
            setHealth_points(result);
        }
    }

    public void addToInventory(String item) {
        this.inventory.add(item);
    }

    public void removeFromInventory(String item) {
        this.inventory.remove(item);
    }

    public void addToAllies(String ally) {
        this.allies.add(ally);
    }

    public void remoevFromAllies(String ally) {
        this.allies.remove(ally);
    }
}
