package com.example.razbuc.characters;

import com.example.razbuc.GameEntity;
import com.example.razbuc.items.Item;

import java.util.ArrayList;

public abstract class Character extends GameEntity {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    private ArrayList<Item> inventory;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Character(String name,
                     ArrayList<Item> inventory,
                     int[] position) {
        System.out.println("Constructing a character ...");
        this.setName(name);
        this.inventory = inventory;
        this.setPosition(position);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    public void addToInventory(Item item) {
        this.inventory.add(item);
    }

    public void removeFromInventory(Item item) {
        this.inventory.remove(item);
    }

}
