package com.example.razbuc.location;

import com.example.razbuc.items.Consumable;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.PaperMap;
import com.example.razbuc.items.Toolbox;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;
import java.util.Random;

public abstract class Building extends District {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    private String type;
    private ArrayList<Item> inventory;
    private int[] consumable_drop = {};
    private int[] papermap_drop = {};
    private int[] toolbox_drop = {};
    private int[] weapon_drop = {};

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Building(String name,
                    int[] position,
                    String type) {
        super(name, position);
        this.type = type;       // Possible types : Garage, Hospital, Hotel, Police station, Tourism office
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public int[] getConsumable_drop() {
        return consumable_drop;
    }

    public void setConsumable_drop(int[] consumable_drop) {
        this.consumable_drop = consumable_drop;
    }

    public int[] getPapermap_drop() {
        return papermap_drop;
    }

    public void setPapermap_drop(int[] papermap_drop) {
        this.papermap_drop = papermap_drop;
    }

    public int[] getToolbox_drop() {
        return toolbox_drop;
    }

    public void setToolbox_drop(int[] toolbox_drop) {
        this.toolbox_drop = toolbox_drop;
    }

    public int[] getWeapon_drop() {
        return weapon_drop;
    }

    public void setWeapon_drop(int[] weapon_drop) {
        this.weapon_drop = weapon_drop;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    public void addToInventory(Item item) {
        this.inventory.add(item);
    }

    public void removeFromInventory(Item item) {
        this.inventory.remove(item);
    }

    public ArrayList<Item> scavenge() {
        ArrayList<Item> loot = new ArrayList<>();
        int diceroll = new Random().nextInt(100 + 1);

        for (int i=0; i<consumable_drop.length; i++) {
            if (diceroll >= consumable_drop[i]) {
                int[] consumble_value = {3,4};
                loot.add(new Consumable("Consumable", consumble_value, this.getPosition()));
            }
        }

        for (int i=0; i<papermap_drop.length; i++) {
            if (diceroll >= papermap_drop[i]) {
                int[] papermap_value = {10};
                loot.add(new PaperMap("Paper map", papermap_value, this.getPosition()));
            }
        }

        for (int i=0; i<toolbox_drop.length; i++) {
            if (diceroll >= toolbox_drop[i]) {
                int[] toolbox_value = {10};
                loot.add(new Toolbox("Toolbox", toolbox_value, this.getPosition()));
            }
        }

        for (int i=0; i<weapon_drop.length; i++) {
            if (diceroll >= weapon_drop[i]) {
                int[] weapon_value = {5,7};
                loot.add(new Weapon("Weapon", weapon_value, this.getPosition()));
            }
        }

        return loot;

    }
}
