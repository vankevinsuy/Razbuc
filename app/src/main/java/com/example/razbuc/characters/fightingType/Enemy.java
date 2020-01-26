package com.example.razbuc.characters.fightingType;

import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.characters.FightingChar;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;

public class Enemy extends FightingChar {

    boolean initiative = false;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates a new ennemy
     *
     * @param name indicating the name of the ennemy
     * @param position indicating the location on the map where the ennemy can be met
     * @param health_points indicating the number of hits the ennemy can take before dying
     * @param basic_damages indicating the number of basic hits per turn the ennemy can deal to the player's character
     * @param force indicating the ability score for the "force" trait of the ennemy
     * @param weapon indicating the "weapon" item that the ennemy uses to fight with
     * @param initiative indicating the initiative score of the ennemy
     */
    public Enemy(String name,
                 int[] position,
                 int health_points,
                 int basic_damages,
                 int force,
                 Weapon weapon,
                 boolean initiative) {
        super(name, new ArrayList<Item>(), position, health_points, basic_damages, force, 0, 0, 0, weapon);
        this.initiative = initiative;
        this.setType(ElementType.ENEMY);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public boolean isInitiative() {
        return initiative;
    }

    public void setInitiative(boolean initiative) {
        this.initiative = initiative;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods


    /** Gives a name for a specific ennemy
     *
     * @return a string indicating a specific ennemy
     */
    @Override
    public String getNameWithPronoun() {
        return "Le razbuc";
    }

    /** Gives a name for an ennemy
     *
     * @return a string indicating an ennemy
     */
    @Override
    public String getFullName() {
        return "Un razbuc";
    }

}
