package com.example.razbuc.location.constructionType;

import com.example.razbuc.items.Item;
import com.example.razbuc.location.Construction;

import java.util.ArrayList;

public class PoliceStation extends Construction {

    public final static String CONSTRUCTION_TYPE = "Poste de police";


    public PoliceStation(int[] position, String type, ArrayList<Item> inventory) {
        super(CONSTRUCTION_TYPE, position, type, inventory);
    }


    @Override
    public String getFullName(){
        return "Un poste de police";
    }

    @Override
    public String getNameWithPronoun() {
        return "Le poste de police";
    }
}
