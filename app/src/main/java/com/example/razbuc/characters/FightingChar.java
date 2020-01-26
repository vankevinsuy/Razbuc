package com.example.razbuc.characters;

import com.example.razbuc.items.Item;
import com.example.razbuc.items.Weapon;

import java.util.List;

public abstract class FightingChar extends Character {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    private int health_points;
    private int basic_damages;
    private int real_damages;       // Basic_damages + Weapon damage
    private Weapon weapon;
    private int force;
    private int perception;
    private int craft;
    private int connaissance;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates a new fighting character
     *
     * @param name indicating the name of the character
     * @param inventory indicating the list of items the character possesses
     * @param position indicating the location where the character can be met
     * @param health_points indicating the number of hits the character can take before dying
     * @param basic_damages indicating the number of basic hits per turn the character can deal to another character
     * @param force indicating the ability score for the "force" trait of the character
     * @param perception indicating the ability score for the "perception" trait of the character
     * @param craft indicating the ability score for the "craft" trait of the character
     * @param connaissance indicating the ability score for the "connaissance" trait of the character
     * @param weapon indicating the "weapon" item that the character uses to fight with
     */
    public FightingChar(String name,
                        List<Item> inventory,
                        int[] position,
                        int health_points,
                        int basic_damages,
                        int force,
                        int perception,
                        int craft,
                        int connaissance,
                        Weapon weapon) {
        super(name, inventory, position);
        System.out.println("Constructing a fighting character ...");
        this.force=force;
        this.perception = perception;
        this.craft = craft;
        this.connaissance = connaissance;
        this.health_points = health_points;
        this.basic_damages = basic_damages;
        this.weapon = weapon;
    }

// ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public int getHealth_points() {
        return health_points;
    }

    public void setHealth_points(int health_points) {
        this.health_points = health_points;
    }

    public int getBasic_damages() {
        return basic_damages;
    }

    public void setBasic_damages(int damages) {
        this.basic_damages = damages;
    }


    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getPerception() {
        return perception;
    }

    public void setPerception(int perception) {
        this.perception = perception;
    }

    public int getCraft() {
        return craft;
    }

    public void setCraft(int craft) {
        this.craft = craft;
    }

    public int getConnaissance() {
        return connaissance;
    }

    public void setConnaissance(int connaissance) {
        this.connaissance = connaissance;
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Adds health points to the character current health
     *
     * @param value indicating the amount of points for the character to be healed
     */
    public void gainHealth_points(int value) {
        setHealth_points(this.getHealth_points() + value);
    }

    /** Removes health points to the character current health
     *
     * @param value indicating the amount of points for the character to be hit
     */
    public void loseHealth_points(int value) {
        int result = this.getHealth_points() - value;
        if (result < 0) {
            setHealth_points(0);
        } else {
            setHealth_points(result);
        }
    }

    /** Computes the real final hit points the character will deal per turn
     *
     * @return the sum of the character's basic damages and its weapon damages
     */
    public int getReal_damages() {
        if (this.weapon == null) {
            return this.basic_damages;
        } else {
            return this.basic_damages + this.weapon.getDamage();
        }
    }

    /** Check if the fighting character possesses a weapon
     *
     * @return a boolean indicating if the fighting character has a weapon
     */
    public boolean hasWeapon(){
        return this.weapon != null;
    }

}
