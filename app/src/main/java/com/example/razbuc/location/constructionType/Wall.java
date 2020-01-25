package com.example.razbuc.location.constructionType;

import com.example.razbuc.Enumerations.ConstructionType;
import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.items.Item;
import com.example.razbuc.location.Construction;

import java.util.ArrayList;

public class Wall extends Construction {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static ConstructionType CONSTRUCTION_TYPE = ConstructionType.Mur;


    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor
    public Wall(int[] position, ElementType type) {
        super(CONSTRUCTION_TYPE.toString(), position, type, CONSTRUCTION_TYPE, new ArrayList<Item>());
    }

    @Override
    public String getFullName(){
        return "Un mur";
    }

    @Override
    public String getNameWithPronoun() {
        return "Le mur";
    }
}
