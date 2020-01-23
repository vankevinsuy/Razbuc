package com.example.razbuc.characters;

import com.example.razbuc.items.Item;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;
import java.util.List;

public abstract class FightingChar extends Character {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    private int health_points;
    private int basic_damages;
    private int real_damages;       // Basic_damages + Weapon damage
    private Weapon weapon;
    private int force;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public FightingChar(String name,
                        List<Item> inventory,
                        int[] position,
                        int health_points,
                        int basic_damages,
                        int force,
                        Weapon weapon) {
        super(name, inventory, position);
        System.out.println("Constructing a fighting character ...");
        this.force=force;
        this.health_points = health_points;
        this.basic_damages = basic_damages;
        this.weapon = weapon;
        this.computeReal_damages();
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

    public int getReal_damages() {
        return real_damages;
    }

    public void setReal_damages(int real_damages) {
        this.real_damages = real_damages;
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

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    public void gainHealth_points(int value) {
        setHealth_points(this.getHealth_points() + value);
    }

    public void loseHealth_points(int value) {
        int result = this.getHealth_points() - value;
        if (result < 0) {
            setHealth_points(0);
        } else {
            setHealth_points(result);
        }
    }

    public void computeReal_damages() {
        if (this.weapon == null) {
            setReal_damages(this.basic_damages);
        } else {
            setReal_damages(this.basic_damages + this.weapon.getValue()[0]);
        }
    }

}
