package com.example.razbuc.items;

import com.example.razbuc.Entity;

public abstract class Item extends Entity {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    private int value;
    private String type;        // Possible types : Consumable, Misc, Vehicle, Weapon
    private int durability;
    private int price;          // By default, this.price = this.value for every item

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Item(String name,
                int value,
                String type,
                int durability,
                int[] position) {
        System.out.println("Constructing an item ...");
        this.setName(name);
        this.value = value;
        this.type = type;
        this.durability = durability;
        this.setPosition(position);
        setPrice(value);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    public void lowerDurability(int stacks) {
        setDurability(this.durability - stacks);
    }

    public void addDurability(int stacks) {
        setDurability(this.durability + stacks);
    }
}
