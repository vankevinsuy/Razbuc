package com.example.razbuc.characters;

import com.example.razbuc.items.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class NonFightingChar extends Character {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public NonFightingChar(String name,
                           List<Item> inventory,
                           int[] position) {
        super(name, inventory, position);
        System.out.println("Constructing a non-fighting character ...");
    }

}
