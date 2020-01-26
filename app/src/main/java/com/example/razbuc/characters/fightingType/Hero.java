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

    public boolean hasToolbox(){
        for (Item item : this.getInventory()){
            if (item.getItemType() == ItemType.Toolbox)
                return true;
        }
        return false;
    }

    public boolean hasPaperMap(){
        for (Item item : this.getInventory()){
            if (item.getItemType() == ItemType.PaperMap)
                return true;
        }
        return false;
    }

    public boolean hasMamie(){
        for (Character ally : this.allies){
            if (ally.getType() == ElementType.PNJ)
                return true;
        }
        return false;
    }

    public boolean hasVehicule(){
        return (vehicule != null);
    }


// ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

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

    public void addAlly(Character ally) {
        this.allies.add(ally);
    }

    public void removeAlly(Character ally) {
        this.allies.remove(ally);
    }

//    public void computeReal_movement() {
//        if (this.vehicule == null) {
//            setReal_movement(this.basic_movement);
//        } else {
//            setReal_movement(this.basic_movement + this.vehicule.getValue());
//        }
//    }

    public boolean hasCareKit(){
        for (Item item : this.getInventory()){
            if (item.getItemType() == ItemType.Consumable)
                return true;
        }
        return false;
    }

    public void useCareKit(){
        removeCareKit(1);
        if (this.getHealth_points() <= 15)
            this.gainHealth_points(5);
        else
            this.setHealth_points(20);
    }

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

    public int getNumberOfCareKit(){
        int count = 0;
        for (Item item : this.getInventory()){
            if (item.getItemType() == ItemType.Consumable) {
                count++;
            }
        }
        return count;
    }


}
