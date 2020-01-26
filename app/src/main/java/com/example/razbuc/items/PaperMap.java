package com.example.razbuc.items;

import com.example.razbuc.Enumerations.ItemType;

public class PaperMap extends Item {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static ItemType ITEM_TYPE = ItemType.PaperMap;
    final static int CONSUMABLE_DURABILITY = 99;      // If durability not set, this.durability = 99

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates a new paper map
     *
     * @param name indicating the name of the paper map
     * @param value indicating the value of the paper map
     * @param durability indicating the durability of the paper map
     * @param position indicating the location on the map where the paper map can be met
     */

    // Consumable creation with custom durability
    public PaperMap(String name,
                    int[] value,
                    int durability,
                    int[] position) {
        super(name, value, ITEM_TYPE, durability, position);
        System.out.println("Constructing a paper map ...");
    }

    // Consumable creation with default durability
    public PaperMap(String name,
                    int[] value,
                    int[] position) {
        super(name, value, ITEM_TYPE, CONSUMABLE_DURABILITY, position);
        System.out.println("Constructing a paper map ...");
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Gives a name for a specific paper map
     *
     * @return a string indicating a specific paper map
     */
    @Override
    public String getNameWithPronoun() {
        return "La carte";
    }

    /** Gives a name for a paper map
     *
     * @return a string indicating a paper map
     */
    @Override
    public String getFullName() {
        return "Une carte";
    }
}
