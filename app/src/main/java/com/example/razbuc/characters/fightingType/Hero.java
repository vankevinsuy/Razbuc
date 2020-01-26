package com.example.razbuc.characters.fightingType;

import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.Enumerations.ItemType;
import com.example.razbuc.characters.Character;
import com.example.razbuc.characters.FightingChar;
import com.example.razbuc.items.Item;
import com.example.razbuc.location.Vehicule;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Hero extends FightingChar {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static int BASIC_MOVEMENT = 1;        // Moving 1 box at a time

    private int basic_movement;
    private int real_movement;      // Basic movement + Vehicule movement
    private Vehicule vehicule;
    private ArrayList<Character> allies = new ArrayList<>();

// ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates the player's character
     *
     * @param name indicating the name of the player's character
     * @param inventory indicating the list of items the player's character possesses
     * @param perception indicating the ability score for the "perception" trait of the player's character
     * @param craft indicating the ability score for the "craft" trait of the player's character
     * @param connaissance indicating the ability score for the "connaissance" trait of the player's character
     * @param force indicating the ability score for the "force" trait of the player's character
     * @param weapon indicating the "weapon" item that the player's character uses to fight with
     */
    public Hero(String name,
                List<Item> inventory,
                int perception,
                int craft,
                int connaissance,
                int force,
                Weapon weapon) {
        super(name, inventory, new int[] {0,0}, 20, 5, force, perception, craft, connaissance, weapon);
        this.basic_movement = BASIC_MOVEMENT;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public int getBasic_movement() {
        return basic_movement;
    }

    public void setBasic_movement(int movement) {
        this.basic_movement = movement;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public ArrayList<Character> getAllies() {
        return allies;
    }

    public void setAllies(ArrayList<Character> allies) {
        this.allies = allies;
    }

    public int getReal_movement() {
        return real_movement;
    }

    public void setReal_movement(int real_movement) {
        this.real_movement = real_movement;
    }

    @Override
    public String getNameWithPronoun() {
        return null;
    }

    @Override
    public String getFullName() {
        return null;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Adds a character to the player's allies
     *
     * @param ally indicating the character to recruit
     */
    public void addAlly(Character ally) {
        this.allies.add(ally);
    }

    /** Removes a character from the player's allies
     *
     * @param ally indicating the character to remove
     */
    public void removeAlly(Character ally) {
        this.allies.remove(ally);
    }

    /** Computes the total movement points per turn that the player's character can move at most
     *
     * @return the sum of the player's character basic movement points and its vehicule movement points
     */
//    public void computeReal_movement() {
//        if (this.vehicule == null) {
//            setReal_movement(this.basic_movement);
//        } else {
//            setReal_movement(this.basic_movement + this.vehicule.getValue());
//        }
//    }

    /** Check if the player's character possesses a care kit
     *
     * @return a boolean indicating if the player's character has a care kit
     */
    public boolean hasCareKit(){
        for (Item item : this.getInventory()){
            if (item.getItemType() == ItemType.Consumable)
                return true;
        }
        return false;
    }

    /** Uses the care kit according to the player's character current health points
     *
     */
    public void useCareKit(){
        removeCareKit(1);
        if (this.getHealth_points() <= 15)
            this.gainHealth_points(5);
        else
            this.setHealth_points(20);
    }

    /** Removes the used care kit
     *
     * @param number indicating the number of care kits to be removed from the player's character's inventory
     */
    public void removeCareKit(int number){
        for (int k = 0; k < number; k++) {
            for (Item item : this.getInventory()) {
                if (item.getItemType() == ItemType.Consumable) {
                    this.getInventory().remove(item);
                    break;
                }
            }
        }
    }

    /** Gets the number of care kits that the player's character possesses
     *
     * @return the number of care kits
     */
    public int getNumberOfCareKit(){
        int count = 0;
        for (Item item : this.getInventory()){
            if (item.getItemType() == ItemType.Consumable) {
                count++;
            }
        }
        return count;
    }

    /** Check if the player's character possesses a toolbox
     *
     * @return a boolean indicating if the player's character has a toolbox
     */
    public boolean hasToolbox(){
        for (Item item : this.getInventory()){
            if (item.getItemType() == ItemType.Toolbox)
                return true;
        }
        return false;
    }

    /** Check if the player's character possesses a paper map in its inventory
     *
     * @return a boolean indicating if the player's character has a paper map
     */
    public boolean hasPaperMap(){
        for (Item item : this.getInventory()){
            if (item.getItemType() == ItemType.PaperMap)
                return true;
        }
        return false;
    }

    /** Check if the player's character got a grandma as an ally
     *
     * @return a boolean indicating if a grandma is an ally to the player's character
     */
    public boolean hasMamie(){
        for (Character ally : this.allies){
            if (ally.getType() == ElementType.PNJ)
                return true;
        }
        return false;
    }

    /** Check if the player's character possesses a vehicule
     *
     * @return a boolean indicating if the player's character has a vehicule
     */
    public boolean hasVehicule(){
        return (vehicule != null);
    }
}
