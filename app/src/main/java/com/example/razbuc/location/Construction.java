package com.example.razbuc.location;

import com.example.razbuc.GameEntity;
import com.example.razbuc.items.Item;

import java.util.ArrayList;

public class Construction extends GameEntity {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    private ArrayList<Item> inventory;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Construction(String name,
                        int[] position,
                        String type,
                        ArrayList<Item> inventory) {
        this.setType(type);
        this.inventory = inventory;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String getFullName(){
        return "";
    }

    @Override
    public String getNameWithPronoun() {
        return "";
    }
}
