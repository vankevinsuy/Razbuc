package com.example.razbuc.items;

public class Toolbox extends Item {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static String ITEM_TYPE = "Toolbox";
    final static int CONSUMABLE_DURABILITY = 99;        // If durability not set, this.durability = 99

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    // Consumable creation with custom durability
    public Toolbox(String name,
                   int[] value,
                   int durability,
                   int[] position) {
        super(name, value, ITEM_TYPE, durability, position);
        System.out.println("Constructing a toolbox ...");
    }

    // Consumable creation with default durability
    public Toolbox(String name,
                   int[] value,
                   int[] position) {
        super(name, value, ITEM_TYPE, CONSUMABLE_DURABILITY, position);
        System.out.println("Constructing a toolbox ...");
    }
}
