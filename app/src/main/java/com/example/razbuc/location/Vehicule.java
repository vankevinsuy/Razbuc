package com.example.razbuc.location;

import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.GameEntity;
import com.example.razbuc.items.Item;

public class Vehicule extends GameEntity {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static ElementType ELEMENT_TYPE = ElementType.Vehicule;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates a new vehicule
     *
     * @param name indicating the name of the vehicule
     * @param position indicating the location on the map where the vehicule can be met
     */
    // Vehicule creation with custom durability
    public Vehicule(String name,
                    int[] position) {
        this.setType(ElementType.Vehicule);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Gives a name for a specific vehicule
     *
     * @return a string indicating a specific vehicule
     */
    @Override
    public String getNameWithPronoun() {
        return "La voiture";
    }

    /** Gives a name for a vehicule
     *
     * @return a string indicating a vehicule
     */
    @Override
    public String getFullName() {
        return "Une voiture";
    }
}
