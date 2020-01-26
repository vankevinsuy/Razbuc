package com.example.razbuc.location;

import com.example.razbuc.Enumerations.ConstructionType;
import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.GameEntity;
import com.example.razbuc.items.Item;

import java.util.ArrayList;

public class Construction extends GameEntity {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    private ArrayList<Item> inventory;
    private ConstructionType constructionType;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates a new construction
     *
     * @param name indicating the name of the construction
     * @param position indicating the location on the map where the construction can be visited
     * @param type indicating the element
     * @param constructionType indicating type of the construction
     * @param inventory indicating the list of items that can be found in the construction
     */
    public Construction(String name,
                        int[] position,
                        ElementType type,
                        ConstructionType constructionType,
                        ArrayList<Item> inventory) {
        this.setType(type);
        this.inventory = inventory;
        this.constructionType = constructionType;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public ConstructionType getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(ConstructionType constructionType) {
        this.constructionType = constructionType;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Gives a name for a specific construction
     *
     * @return a string indicating a specific construction
     */
    @Override
    public String getFullName(){
        return "";
    }

    /** Gives a name for a construction
     *
     * @return a string indicating a construction
     */
    @Override
    public String getNameWithPronoun() {
        return "";
    }
}
