package com.example.razbuc.characters;

import com.example.razbuc.GameEntity;
import com.example.razbuc.items.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class Character extends GameEntity {


    private List<Item> inventory;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates a new character.
     *
     * @param name indicating the name of the character
     * @param inventory indicating the list of items the character possesses
     * @param position indicating the location on the map where the character can be met
     */
    public Character(String name,
                     List<Item> inventory,
                     int[] position) {
        System.out.println("Constructing a character ...");
        this.inventory = new ArrayList<>(inventory);
        this.setName(name);
        this.setPosition(position);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public List<Item> getInventory () {
        return inventory;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Adds an item to the inventory of a character
     *
     * @param item indicating which item should be added to the inventory of the character
     */
    public void addToInventory(Item item) {
        this.inventory.add(item);
    }

    /** Removes an item from the inventory of a character
     *
     * @param item indicating which item should be removed from the inventory of the character
     */
    public void removeFromInventory(Item item) {
        this.inventory.remove(item);
    }

}
