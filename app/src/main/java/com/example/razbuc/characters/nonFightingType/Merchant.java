package com.example.razbuc.characters.nonFightingType;

import com.example.razbuc.characters.NonFightingChar;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;

public class Merchant extends NonFightingChar {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Merchant(String name,
                    ArrayList<Item> inventory,
                    int[] position) {
        super(name, inventory, position);
        System.out.println("Constructing a merchant ...");
        this.addToInventory(new Weapon("Kitchen knife", 1, 10, this.getPosition()));        // By default, every merchant sells a basic weapon
    }


}
