package com.example.razbuc.items;

import com.example.razbuc.Enumerations.ItemType;

public class Consumable extends Item {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static ItemType ITEM_TYPE = ItemType.Consumable;
    final static int CONSUMABLE_DURABILITY = 1;        // If durability not set, this.durability = 1

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    // Consumable creation with custom durability
    public Consumable(String name,
                      int[] value,
                      int durability,        // Healing amount
                      int[] position) {
        super(name, value, ITEM_TYPE, durability, position);
        System.out.println("Constructing a consumable ...");
    }

    // Consumable creation with default durability
    public Consumable(String name,
                      int[] value,
                      int[] position) {      // Healing amount
        super(name, value, ITEM_TYPE, CONSUMABLE_DURABILITY, position);
        System.out.println("Constructing a consumable ...");
    }

    @Override
    public String getNameWithPronoun() {
        return "La trousse de soins";
    }

    @Override
    public String getFullName() {
        return "Une trousse de soins";
    }
}
