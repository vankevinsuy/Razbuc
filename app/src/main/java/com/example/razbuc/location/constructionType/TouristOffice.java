package com.example.razbuc.location.constructionType;

import com.example.razbuc.Enumerations.ConstructionType;
import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.items.Item;
import com.example.razbuc.location.Construction;

import java.util.ArrayList;

public class TouristOffice extends Construction {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static ConstructionType CONSTRUCTION_TYPE = ConstructionType.Office_du_tourisme;


    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor
    public TouristOffice(int[] position, ElementType type, ArrayList<Item> inventory) {
        super(CONSTRUCTION_TYPE.toString(), position, type, CONSTRUCTION_TYPE, inventory);
    }

    @Override
    public String getFullName(){
        return "Un office du tourisme";
    }

    @Override
    public String getNameWithPronoun() {
        return "L'office du tourisme";
    }
}
