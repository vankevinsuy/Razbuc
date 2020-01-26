package com.example.razbuc.location.constructionType;

import com.example.razbuc.Enumerations.ConstructionType;
import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.items.Item;
import com.example.razbuc.location.Construction;

import java.util.ArrayList;

public class Hospital extends Construction {

    public final static ConstructionType CONSTRUCTION_TYPE = ConstructionType.Hopital;


    public Hospital(int[] position, ElementType type, ArrayList<Item> inventory) {
        super(CONSTRUCTION_TYPE.toString(), position, type, CONSTRUCTION_TYPE, inventory);
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