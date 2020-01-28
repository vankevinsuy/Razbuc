package com.example.razbuc.items;

import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.Enumerations.ItemType;

public abstract class Item extends com.example.razbuc.GameEntity {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    private int[] value;
    private int durability;
    private int quantity;
    private ItemType itemType;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates a new item
     *
     * @param name indicating a name for the item
     * @param value indicating the value of the item
     * @param itemType indicating the type of the item
     * @param durability indicating the durability of the item
     * @param position indicating the location on the map where the item can be found
     */

    public Item(String name,
                int[] value,
                ItemType itemType,
                int durability,
                int[] position) {
        System.out.println("Constructing an item ...");
        this.setName(name);
        this.value = value;
        this.durability = durability;
        this.itemType = itemType;
        this.setPosition(position);
        this.setType(ElementType.Item);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public int[] getValue() {
        return value;
    }

    public void setValue(int[] value) {
        this.value = value;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Reduces the durability of the item
     *
     * @param stacks indicating the number of uses that the item loses
     */
    public void lowerDurability(int stacks) {
        setDurability(this.durability - stacks);
    }

    /** Increases the durability of the item
     *
     * @param stacks indicating the number of uses that the item gains
     */
    public void addDurability(int stacks) {
        setDurability(this.durability + stacks);
    }
}
