package com.example.razbuc.location.constructionType;

import com.example.razbuc.items.Item;
import com.example.razbuc.location.Construction;

import java.util.ArrayList;

public class Building extends Construction {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static String CONSTRUCTION_TYPE = "Building";

    private ArrayList<Item> inventory;


    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Building(String name,
                    int[] position,
                    String type,
                    ArrayList<Item> inventory) {
        super(name, position, CONSTRUCTION_TYPE);
        this.inventory = inventory;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }
}
