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

    /** Creates a new wall
     *
     * @param position indicating the location on the map of the wall
     * @param type indicating the type of the construction
     */
    public Wall(int[] position, ElementType type) {
        super(CONSTRUCTION_TYPE.toString(), position, type, CONSTRUCTION_TYPE, new ArrayList<Item>());
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Gives a name for a specific wall
     *
     * @return a string indicating a specific wall
     */
    @Override
    public String getFullName(){
        return "Un mur";
    }

    /** Gives a name for a wall
     *
     * @return a string indicating a wall
     */
    @Override
    public String getNameWithPronoun() {
        return "Le mur";
    }
}
