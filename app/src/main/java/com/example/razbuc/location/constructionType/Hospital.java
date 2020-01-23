package com.example.razbuc.location.constructionType;

import com.example.razbuc.items.Item;
import com.example.razbuc.location.Construction;

import java.util.ArrayList;

public class Hospital extends Construction {

    public final static String CONSTRUCTION_TYPE = "Hopital";


    public Hospital(int[] position, String type, ArrayList<Item> inventory) {
        super(CONSTRUCTION_TYPE, position, type, inventory);
    }


    @Override
    public String getFullName(){
        return "Un hhopital";
    }

    @Override
    public String getNameWithPronoun() {
        return "L'hopital";
    }
}
