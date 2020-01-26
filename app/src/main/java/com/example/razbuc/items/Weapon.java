package com.example.razbuc.items;

import com.example.razbuc.Enumerations.ItemType;

public class Weapon extends Item {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static ItemType ITEM_TYPE = ItemType.Weapon;
    final static int WEAPON_DURABILITY = 10;        // If durability not set, this.durability = 10

    private int damage;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates a new weapon
     *
     * @param name indicating the name of the weapon
     * @param damage indicating the damage hit points of the weapon
     * @param position indicating the location on the map where the weapon can be met
     */
    // Weapon creation with default durability
    public Weapon(String name,
                  int damage,
                  int[] position) {
        super(name, new int[]{0}, ITEM_TYPE, WEAPON_DURABILITY, position);
        System.out.println("Constructing a weapon ...");
        this.damage = damage;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Gives a name for a specific weapon
     *
     * @return a string indicating a specific weapon
     */
    @Override
    public String getNameWithPronoun() {
        return "L'arme";
    }

    /** Gives a name for a weapon
     *
     * @return a string indicating a weapon
     */
    @Override
    public String getFullName() {
        return "Une arme";
    }

}
