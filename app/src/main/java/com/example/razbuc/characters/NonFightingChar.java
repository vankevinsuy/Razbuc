package com.example.razbuc.characters;

import com.example.razbuc.items.Item;

import java.util.List;

public abstract class NonFightingChar extends Character {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates a new non-fighting character
     *
     * @param name indicating the name of the character
     * @param inventory indicating the list of items the character possesses
     * @param position indicating the location on the map where the character can be met
     */
    public NonFightingChar(String name,
                           List<Item> inventory,
                           int[] position) {
        super(name, inventory, position);
    }

}
