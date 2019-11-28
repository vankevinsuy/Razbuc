package com.example.razbuc.characters.fightingType;

import com.example.razbuc.characters.Character;
import com.example.razbuc.characters.FightingChar;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.Vehicle;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;

public class Hero extends FightingChar {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static int BASIC_MOVEMENT = 1;        // Moving 1 box at a time

    private int basic_movement;
    private int real_movement;      // Basic movement + Vehicle movement
    private Vehicle vehicle;
    private ArrayList<Character> allies;
    private String type;        // Possible types : Artificer, Explorer, Medic, SergeantMajor

// ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Hero(String name,
                ArrayList<Item> inventory,
                int[] position,
                int health_points,
                int basic_damages,
                Weapon weapon) {
        super(name, inventory, position, health_points, basic_damages, weapon);
        System.out.println("Constructing the hero ...");
        this.basic_movement = BASIC_MOVEMENT;
        this.computeReal_movement();
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public int getBasic_movement() {
        return basic_movement;
    }

    public void setBasic_movement(int movement) {
        this.basic_movement = movement;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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

    public void computeReal_movement() {
        if (this.vehicle == null) {
            setReal_movement(this.basic_movement);
        } else {
            setReal_movement(this.basic_movement + this.vehicle.getValue());
        }
    }
}
