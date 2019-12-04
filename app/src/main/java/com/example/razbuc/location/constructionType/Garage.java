package com.example.razbuc.location.constructionType;

import com.example.razbuc.location.Building;

public class Garage extends Building {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static String CONSTRUCTION_TYPE = "Garage";
    private int[] TOOLBOX_DROP = {0};

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Garage(String name,
                  int[] position,
                  String type) {
        super(name, position, CONSTRUCTION_TYPE);
        setToolbox_drop(TOOLBOX_DROP);
    }

}
