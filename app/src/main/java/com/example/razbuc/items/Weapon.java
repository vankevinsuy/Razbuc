package com.example.razbuc.items;

import com.example.razbuc.Enumerations.ItemType;

public class Weapon extends Item {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static ItemType ITEM_TYPE = ItemType.Weapon;
    final static int WEAPON_DURABILITY = 10;        // If durability not set, this.durability = 10

    private int damage;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    // Weapon creation with default durability
    public Weapon(String name,
                  int damage,
                  int[] position) {
        super(name, new int[]{0}, ITEM_TYPE, WEAPON_DURABILITY, position);
        System.out.println("Constructing a weapon ...");
        this.damage = damage;
    }

    @Override
    public String getNameWithPronoun() {
        return "L'arme";
    }

    @Override
    public String getFullName() {
        return "Une arme";
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
