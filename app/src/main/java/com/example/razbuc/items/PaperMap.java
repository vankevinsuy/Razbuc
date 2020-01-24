package com.example.razbuc.items;

import com.example.razbuc.Enumerations.ItemType;

public class PaperMap extends Item {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static ItemType ITEM_TYPE = ItemType.PaperMap;
    final static int CONSUMABLE_DURABILITY = 99;      // If durability not set, this.durability = 99

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    // Consumable creation with custom durability
    public PaperMap(String name,
                    int[] value,
                    int durability,
                    int[] position) {
        super(name, value, ITEM_TYPE, durability, position);
        System.out.println("Constructing a paper map ...");
    }

    // Consumable creation with default durability
    public PaperMap(String name,
                    int[] value,
                    int[] position) {
        super(name, value, ITEM_TYPE, CONSUMABLE_DURABILITY, position);
        System.out.println("Constructing a paper map ...");
    }

    @Override
    public String getNameWithPronoun() {
        return "La carte";
    }

    @Override
    public String getFullName() {
        return "Une carte";
    }
}
