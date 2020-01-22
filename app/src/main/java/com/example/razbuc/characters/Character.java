package com.example.razbuc.characters;

import com.example.razbuc.GameEntity;
import com.example.razbuc.items.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class Character extends GameEntity {


    private List<Item> inventory;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Character(String name,
                     List<Item> inventory,
                     int[] position) {
        System.out.println("Constructing a character ...");
        this.inventory = inventory;
        this.setName(name);
        this.setPosition(position);
    }


    public void addToInventory(Item item) {
        this.inventory.add(item);
    }

    public void removeFromInventory(Item item) {
        this.inventory.remove(item);
    }

}
