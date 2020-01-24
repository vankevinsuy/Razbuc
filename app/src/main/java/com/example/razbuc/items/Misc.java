package com.example.razbuc.items;

import com.example.razbuc.Enumerations.ItemType;

public class Misc extends Item {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static ItemType ITEM_TYPE = ItemType.Misc;
    final static int MISC_DURABILITY = 10;        // If durability not set, this.durability = 10
    final static int[] MISC_VALUE = {10};       // Default value for misc is 10

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    // Consumable creation with custom durability
    public Misc(String name,
                int durability,
                int[] position) {
        super(name, MISC_VALUE, ITEM_TYPE, durability, position);
        System.out.println("Constructing a misc ...");
    }

    // Consumable creation with default durability
    public Misc(String name,
                int[] position) {
        super(name, MISC_VALUE, ITEM_TYPE, MISC_DURABILITY, position);
        System.out.println("Constructing a misc ...");
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
