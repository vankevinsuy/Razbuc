package com.example.razbuc.location.constructionType;

import com.example.razbuc.location.Building;

public class Hotel extends Building {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static String CONSTRUCTION_TYPE = "Hotel";

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Hotel(String name,
                 int[] position,
                 String type) {
        super(name, position, CONSTRUCTION_TYPE);
    }
}
