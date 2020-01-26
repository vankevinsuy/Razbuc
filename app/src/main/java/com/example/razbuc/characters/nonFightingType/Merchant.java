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

    /** Creates a new merchant
     *
     * @param name indicating the name of the merchant
     * @param inventory indicating the list of items the merchant can sell
     * @param position indicating the location on the map where the merchant can be met
     */
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


    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Gestures

    /** Fling to the right to check the next selling item
     *
     * @return an output indicating the price of the item
     */
    public String flingDroit(){
        this.index = (this.index + 1) % this.shop.size();
        return sayItemCost();
    }

    /** Fling to the left to check the previous selling item
     *
     * @return an output indicating the price of the item
     */
    public String flingGauche(){
        this.index = (this.index - 1 + this.shop.size()) % this.shop.size();
        return sayItemCost();
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Tells the cost of an item
     *
     * @return a string indicating the price of an item
     */
    public String sayItemCost(){
        Item item = (Item)this.shop.keySet().toArray()[this.index];
        int cout = this.shop.get(item);
        return (item.getFullName() + " pour " + (cout == 1 ? "une" : cout) + " trousses de soins");
    }

    /** (META) Gets the price of a selected item
     *
     * @return the price of an item
     */
    public int getPrice(){
        Item item = (Item)this.shop.keySet().toArray()[this.index];
        return this.shop.get(item);
    }

    /** (META) Gets a selected item
     *
     * @return an item
     */
    public Item getItem(){
        return (Item) this.shop.keySet().toArray()[this.index];
    }

    /** Gives a name for a specific merchant
     *
     * @return a string indicating a specific merchant
     */
    @Override
    public String getNameWithPronoun() {
        return "Le marchand";
    }

    /** Gives a name for a merchant
     *
     * @return a string indicating a merchant
     */
    @Override
    public String getFullName() {
        return "Un marchand";
    }
}
