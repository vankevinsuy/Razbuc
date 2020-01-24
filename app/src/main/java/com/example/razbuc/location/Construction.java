package com.example.razbuc.location;

import com.example.razbuc.Enumerations.ConstructionType;
import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.GameEntity;
import com.example.razbuc.items.Item;

import java.util.ArrayList;

public class Construction extends GameEntity {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    private ArrayList<Item> inventory;
    private ConstructionType constructionType;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Construction(String name,
                        int[] position,
                        ElementType type,
                        ConstructionType constructionType,
                        ArrayList<Item> inventory) {
        this.setType(type);
        this.inventory = inventory;
        this.constructionType = constructionType;
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
