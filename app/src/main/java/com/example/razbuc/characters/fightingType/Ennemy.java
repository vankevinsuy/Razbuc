package com.example.razbuc.characters.fightingType;

import com.example.razbuc.characters.FightingChar;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;

public class Ennemy extends FightingChar {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Ennemy(String name,
                  int[] position,
                  int health_points,
                  int basic_damages,
                  int force,
                  Weapon weapon) {
        super(name, new ArrayList<Item>(), position, health_points, basic_damages, force, weapon);
    }

    @Override
    public String getNameWithPronoun() {
        return null;
    }

    @Override
    public String getFullName() {
        return null;
    }
}
