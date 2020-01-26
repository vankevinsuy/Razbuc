package com.example.razbuc.location.constructionType;

import com.example.razbuc.Enumerations.ConstructionType;
import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.items.Item;
import com.example.razbuc.location.Construction;

import java.util.ArrayList;

public class Building extends Construction {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static ConstructionType CONSTRUCTION_TYPE = ConstructionType.Batiment;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates a new building
     *
     * @param position indicating the location on the map where the building can be visited
     * @param type indicating the type of construction
     * @param inventory indicating the list of items that can be found in the building
     */
    public Building(int[] position, ElementType type, ArrayList<Item> inventory) {
        super(CONSTRUCTION_TYPE.toString(), position, type, CONSTRUCTION_TYPE, inventory);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Gives a name for a specific building
     *
     * @return a string indicating a specific building
     */
    @Override
    public String getFullName(){
        return "Un batimant";
    }

    /** Gives a name for a building
     *
     * @return a string indicating a building
     */
    @Override
    public String getNameWithPronoun() {
        return "Le batimant";
    }
}
