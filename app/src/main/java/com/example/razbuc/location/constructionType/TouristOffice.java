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

    /** Creates a new tourist office
     *
     * @param position indicating the location on the map where the tourist office can be visited
     * @param type indicating the type of construction
     * @param inventory indicating the list of items that can be found in the tourist office
     */
    public TouristOffice(int[] position, ElementType type, ArrayList<Item> inventory) {
        super(CONSTRUCTION_TYPE.toString(), position, type, CONSTRUCTION_TYPE, inventory);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Gives a name for a specific tourist office
     *
     * @return a string indicating a specific tourist office
     */
    @Override
    public String getFullName(){
        return "Un office du tourisme";
    }

    /** Gives a name for a tourist office
     *
     * @return a string indicating a tourist office
     */
    @Override
    public String getNameWithPronoun() {
        return "L'office du tourisme";
    }
}
