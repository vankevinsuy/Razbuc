package com.example.razbuc.location.constructionType;

import com.example.razbuc.location.Building;

public class PoliceStation extends Building {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static String CONSTRUCTION_TYPE = "Police station";
    private int[] WEAPON_DROP = {25};

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public PoliceStation(String name,
                         int[] position,
                         String type) {
        super(name, position, CONSTRUCTION_TYPE);
        setWeapon_drop(WEAPON_DROP);
    }

}
