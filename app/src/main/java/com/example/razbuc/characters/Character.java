package com.example.razbuc.characters;

import com.example.razbuc.GameEntity;
import com.example.razbuc.items.Item;

import java.util.ArrayList;

public abstract class Character extends GameEntity {


    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Character(String name,
                     int[] position) {
        System.out.println("Constructing a character ...");
        this.setName(name);
        this.setPosition(position);
    }

}
