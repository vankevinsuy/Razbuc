package com.example.razbuc.characters.fightingType;

import com.example.razbuc.characters.FightingChar;
import com.example.razbuc.items.Item;

import java.util.ArrayList;

public class Ennemy extends FightingChar {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Ennemy(String name,
                  ArrayList<Item> inventory,
                  int[] position,
                  int health_points,
                  int basic_damages) {
        super(name, inventory, position, health_points, basic_damages, null);
    }
}
