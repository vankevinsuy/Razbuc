package com.example.razbuc.characters.fightingType;

import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.R;
import com.example.razbuc.characters.FightingChar;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;

public class Ennemy extends FightingChar {

    boolean initiative = false;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Ennemy(String name,
                  int[] position,
                  int health_points,
                  int basic_damages,
                  int force,
                  Weapon weapon,
                  boolean initiative) {
        super(name, new ArrayList<Item>(), position, health_points, basic_damages, force, 0, 0, 0, weapon);
        this.initiative = initiative;
        this.setType(ElementType.Ennemy);
    }

    @Override
    public String getNameWithPronoun() {
        return null;
    }

    @Override
    public String getFullName() {
        return null;
    }

    public boolean isInitiative() {
        return initiative;
    }

    public void setInitiative(boolean initiative) {
        this.initiative = initiative;
    }
}
