package com.example.razbuc.items;

public class Weapon extends Item {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static String ITEM_TYPE = "Weapon";
    final static int WEAPON_DURABILITY = 10;        // If durability not set, this.durability = 10

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    // Weapon creation with custom durability
    public Weapon(String name,
                  int[] value,        // Weapon damage
                  int durability,
                  int[] position) {
        super(name, value, ITEM_TYPE, durability, position);
        System.out.println("Constructing a weapon ...");
        setPrice(this.getValue()[0] * 2);         // By default, this.price = this.value*2 for every weapon
    }

    // Weapon creation with default durability
    public Weapon(String name,
                  int[] value,
                  int[] position) {
        super(name, value, ITEM_TYPE, WEAPON_DURABILITY, position);
        System.out.println("Constructing a weapon ...");
        setPrice(this.getValue()[0] * 2);         // By default, this.price = this.value*2 for every weapon
    }

    @Override
    public String getNameWithPronoun() {
        return "L'arme";
    }

    @Override
    public String getFullName() {
        return "Une arme";
    }
}
