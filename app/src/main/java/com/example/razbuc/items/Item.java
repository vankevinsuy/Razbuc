package com.example.razbuc.items;

import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.Enumerations.ItemType;

public abstract class Item extends com.example.razbuc.GameEntity {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    private int[] value;
    private int durability;
    private int price;          // By default, this.price = this.value for every item
    private int quantity;
    private ItemType itemType;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

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
        this.setPrice(value[0]);
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    public void lowerDurability(int stacks) {
        setDurability(this.durability - stacks);
    }

    public void addDurability(int stacks) {
        setDurability(this.durability + stacks);
    }
}
