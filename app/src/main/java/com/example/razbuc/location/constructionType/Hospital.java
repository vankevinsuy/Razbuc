package com.example.razbuc.location.constructionType;

import com.example.razbuc.Enumerations.ConstructionType;
import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.items.Item;
import com.example.razbuc.location.Construction;

import java.util.ArrayList;

public class Hospital extends Construction {

    public final static ConstructionType CONSTRUCTION_TYPE = ConstructionType.Hopital;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates a new hospital
     *
     * @param position indicating the location on the map where the hospital can be visited
     * @param type indicating the type of construction
     * @param inventory indicating the list of items that can be found in the hospital
     */
    public Hospital(int[] position, ElementType type, ArrayList<Item> inventory) {
        super(CONSTRUCTION_TYPE.toString(), position, type, CONSTRUCTION_TYPE, inventory);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Gives a name for a specific hospital
     *
     * @return a string indicating a specific hospital
     */
    @Override
    public String getFullName(){
        return "Un hhopital";
    }

    /** Gives a name for a hospital
     *
     * @return a string indicating a hospital
     */
    @Override
    public String getNameWithPronoun() {
        return "L'hopital";
    }
}
