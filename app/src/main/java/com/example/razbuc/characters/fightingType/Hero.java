package com.example.razbuc.characters.fightingType;

import com.example.razbuc.characters.Character;
import com.example.razbuc.characters.FightingChar;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.Vehicule;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Hero extends FightingChar {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static int BASIC_MOVEMENT = 1;        // Moving 1 box at a time

    private int basic_movement;
    private int real_movement;      // Basic movement + Vehicule movement
    private Vehicule vehicule;
    private ArrayList<Character> allies;
    private String type;        // Possible types : Artificer, Explorer, Medic, SergeantMajor
    private int perception;
    private int craft;
    private int connaissance;


// ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Hero(String name,
                List<Item> inventory,
                int perception,
                int craft,
                int connaissance,
                int force,
                Weapon weapon) {
        super(name, inventory, new int[] {0,0}, 20, 5, force, weapon);
        this.perception = perception;
        this.craft= craft;
        this.connaissance= connaissance;
        this.basic_movement = BASIC_MOVEMENT;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public int getBasic_movement() {
        return basic_movement;
    }

    public void setBasic_movement(int movement) {
        this.basic_movement = movement;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public ArrayList<Character> getAllies() {
        return allies;
    }

    public void setAllies(ArrayList<Character> allies) {
        this.allies = allies;
    }

    public int getReal_movement() {
        return real_movement;
    }

    public void setReal_movement(int real_movement) {
        this.real_movement = real_movement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    public void addAlly(Character ally) {
        this.allies.add(ally);
    }

    public void removeAlly(Character ally) {
        this.allies.remove(ally);
    }

//    public void computeReal_movement() {
//        if (this.vehicule == null) {
//            setReal_movement(this.basic_movement);
//        } else {
//            setReal_movement(this.basic_movement + this.vehicule.getValue());
//        }
//    }


    public void removeFromInventory(Item item) {
        this.inventory.remove(item);
    }

    public List<Item> getInventory () {
        return inventory;
    }

}
