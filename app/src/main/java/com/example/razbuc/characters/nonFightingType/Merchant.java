package com.example.razbuc.characters.nonFightingType;

import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.characters.NonFightingChar;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.PaperMap;
import com.example.razbuc.items.Toolbox;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;
import java.util.HashMap;

public class Merchant extends NonFightingChar {


    private int index;
    private HashMap<Item,Integer> shop;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Merchant(String name,
                    ArrayList<Item> inventory,
                    int[] position) {
        super(name, inventory, position);
        System.out.println("Constructing a merchant ...");
        this.setType(ElementType.Merchant);
        shop = new HashMap<>();
        shop.put(new Weapon("Arme", 3, new int[]{}), 3);
        shop.put(new PaperMap("Carte", new int[]{}, new int[]{}), 1);
        shop.put(new Toolbox("Boite à outil", new int[]{}, new int[]{}), 2);
        index = 0;
    }

    public String flingDroit(){
        this.index = (this.index + 1) % this.shop.size();
        return sayItemCost();
    }

    public String flingGauche(){
        this.index = (this.index - 1 + this.shop.size()) % this.shop.size();
        return sayItemCost();
    }

    public String sayItemCost(){
        Item item = (Item)this.shop.keySet().toArray()[this.index];
        int cout = this.shop.get(item);
        return (item.getFullName() + " pour " + (cout == 1 ? "une" : cout) + " trousses de soins");
    }

    public int getPrice(){
        Item item = (Item)this.shop.keySet().toArray()[this.index];
        return this.shop.get(item);
    }

    public Item getItem(){
        return (Item) this.shop.keySet().toArray()[this.index];
    }


    @Override
    public String getNameWithPronoun() {
        return "Le marchand";
    }

    @Override
    public String getFullName() {
        return "Un marchand";
    }
}
