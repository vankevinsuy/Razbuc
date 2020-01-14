package com.example.razbuc.items;

public abstract class Item extends com.example.razbuc.GameEntity {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    private int[] value;
    private String type;        // Possible types : Consumable, Misc, Paper map, Toolbox, Vehicule, Weapon
    private int durability;
    private int price;          // By default, this.price = this.value for every item
    private int quantity;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Item(String name,
                int[] value,
                String type,
                int durability,
                int[] position) {
        System.out.println("Constructing an item ...");
        this.setName(name);
        this.value = value;
        this.type = type;
        this.durability = durability;
        this.setPosition(position);
        setPrice(value[0]);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public int[] getValue() {
        return value;
    }

    public void setValue(int[] value) {
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
