package com.example.razbuc.location.constructionType;

import com.example.razbuc.location.Construction;

public class Fireplace extends Construction {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static String CONSTRUCTION_TYPE = "Fireplace";

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Fireplace(String name,
                     int[] position,
                     String type) {
        super(name, position, CONSTRUCTION_TYPE);
    }
}
