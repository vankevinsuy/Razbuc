package com.example.razbuc.location.constructionType;

import com.example.razbuc.location.Building;

public class Hospital extends Building {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static String CONSTRUCTION_TYPE = "Hospital";
    private int[] CONSUMABLE_DROP = {50, 80, 95};

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Hospital(String name,
                    int[] position,
                    String type) {
        super(name, position, CONSTRUCTION_TYPE);
        setConsumable_drop(CONSUMABLE_DROP);
    }

}
