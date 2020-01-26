package com.example.razbuc.items;

import com.example.razbuc.Enumerations.ItemType;

public class Toolbox extends Item {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static ItemType ITEM_TYPE = ItemType.Toolbox;
    final static int CONSUMABLE_DURABILITY = 99;        // If durability not set, this.durability = 99

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates a new toolbox
     *
     * @param name indicating the name of the toolbox
     * @param value indicating the value of the toolbox
     * @param durability indicating the durability of the toolbox
     * @param position indicating the location on the map where the toolbox can be met
     */

    // Consumable creation with custom durability
    public Toolbox(String name,
                   int[] value,
                   int durability,
                   int[] position) {
        super(name, value, ITEM_TYPE, durability, position);
        System.out.println("Constructing a toolbox ...");
    }

    // Consumable creation with default durability
    public Toolbox(String name,
                   int[] value,
                   int[] position) {
        super(name, value, ITEM_TYPE, CONSUMABLE_DURABILITY, position);
        System.out.println("Constructing a toolbox ...");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Gives a name for a specific toolbox
     *
     * @return a string indicating a specific toolbox
     */
    @Override
    public String getNameWithPronoun() {
        return "La boite à outil";
    }

    /** Gives a name for a toolbox
     *
     * @return a string indicating a toolbox
     */
    @Override
    public String getFullName() {
        return "Une boite à outil";
    }
}
