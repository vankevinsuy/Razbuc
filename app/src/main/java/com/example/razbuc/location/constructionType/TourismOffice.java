package com.example.razbuc.location.constructionType;

import com.example.razbuc.location.Building;

public class TourismOffice extends Building {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static String CONSTRUCTION_TYPE = "Tourism office";
    private int[] PAPERMAP_DROP = {25};

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public TourismOffice(String name,
                         int[] position,
                         String type) {
        super(name, position, CONSTRUCTION_TYPE);
        setPapermap_drop(PAPERMAP_DROP);
    }

}
