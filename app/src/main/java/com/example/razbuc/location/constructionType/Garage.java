package com.example.razbuc.location.constructionType;

import com.example.razbuc.Enumerations.ConstructionType;
import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.items.Item;
import com.example.razbuc.location.Construction;

import java.util.ArrayList;

public class Garage extends Construction {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static ConstructionType CONSTRUCTION_TYPE = ConstructionType.Garage;


    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates a new garage
     *
     * @param position indicating the location on the map where the garage can be visited
     * @param type indicating the type of construction
     * @param inventory indicating the list of items that can be found in the garage
     */
    public Garage(int[] position, ElementType type, ArrayList<Item> inventory) {
        super(CONSTRUCTION_TYPE.toString(), position, type, CONSTRUCTION_TYPE, inventory);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Gives a name for a specific garage
     *
     * @return a string indicating a specific garage
     */
    @Override
    public String getFullName(){
        return "Un garage";
    }

    /** Gives a name for a garage
     *
     * @return a string indicating a garage
     */
    @Override
    public String getNameWithPronoun() {
        return "Le garage";
    }
}
