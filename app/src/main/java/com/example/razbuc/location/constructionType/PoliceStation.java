package com.example.razbuc.location.constructionType;

import com.example.razbuc.Enumerations.ConstructionType;
import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.items.Item;
import com.example.razbuc.location.Construction;

import java.util.ArrayList;

public class PoliceStation extends Construction {

    public final static ConstructionType CONSTRUCTION_TYPE = ConstructionType.Poste_de_police;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates a new police station
     *
     * @param position indicating the location on the map where the police station can be visited
     * @param type indicating the type of construction
     * @param inventory indicating the list of items that can be found in the police station
     */
    public PoliceStation(int[] position, ElementType type, ArrayList<Item> inventory) {
        super(CONSTRUCTION_TYPE.toString(), position, type, CONSTRUCTION_TYPE, inventory);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Gives a name for a specific police station
     *
     * @return a string indicating a specific police station
     */
    @Override
    public String getFullName(){
        return "Un poste de police";
    }

    /** Gives a name for a police station
     *
     * @return a string indicating a police station
     */
    @Override
    public String getNameWithPronoun() {
        return "Le poste de police";
    }
}
