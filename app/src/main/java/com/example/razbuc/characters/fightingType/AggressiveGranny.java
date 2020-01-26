package com.example.razbuc.characters.fightingType;

import com.example.razbuc.items.Weapon;

public class AggressiveGranny extends Enemy {

    public AggressiveGranny(String name, int[] position, int health_points, int basic_damages, int force, Weapon weapon, boolean initiative) {
        super(name, position, health_points, basic_damages, force, weapon, initiative);
    }

    @Override
    public String getNameWithPronoun() {
        return "La mamie";
    }

    @Override
    public String getFullName() {
        return "Une mamie";
    }
}
