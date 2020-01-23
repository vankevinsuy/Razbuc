package com.example.razbuc.location.constructionType;

import com.example.razbuc.items.Item;
import com.example.razbuc.location.Construction;

import java.util.ArrayList;

public class Building extends Construction {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static String CONSTRUCTION_TYPE = "Batiment";


    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor
    public Building(int[] position, String type, ArrayList<Item> inventory) {
        super(CONSTRUCTION_TYPE, position, type, inventory);
    }

    @Override
    public String getFullName(){
        return "Un batiment";
    }

    @Override
    public String getNameWithPronoun() {
        return "Le batiment";
    }
}
