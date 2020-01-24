package com.example.razbuc.location;

import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.GameEntity;
import com.example.razbuc.items.Item;

public class Vehicule extends GameEntity {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static ElementType ELEMENT_TYPE = ElementType.Vehicule;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    // Vehicule creation with custom durability
    public Vehicule(String name,
                    int[] position) {
        this.setType(ElementType.Vehicule);
    }

    @Override
    public String getNameWithPronoun() {
        return "La voiture";
    }

    @Override
    public String getFullName() {
        return "Une voiture";
    }
}
