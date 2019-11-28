package com.example.razbuc.characters;

import com.example.razbuc.items.Item;

import java.util.ArrayList;

public abstract class NonFightingChar extends Character {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public NonFightingChar(String name,
                           ArrayList<Item> inventory,
                           int[] position) {
        super(name, inventory, position);
        System.out.println("Constructing a non-fighting character ...");
    }

}
