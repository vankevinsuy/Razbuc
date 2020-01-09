package com.example.razbuc.characters.fightingType.heroType;

import com.example.razbuc.characters.fightingType.Hero;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.PaperMap;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;

public class Explorer extends Hero {

    final static String HERO_TYPE = "Explorer";

    /*public Explorer (){
        super("Litota", new ArrayList<Item>(new PaperMap()));
    }*/

    public Explorer(String name,
                    ArrayList<Item> inventory,
                    int[] position,
                    int health_points,
                    int basic_damages,
                    Weapon weapon) {
        super(name, inventory, position, health_points, basic_damages, weapon);
        setType(HERO_TYPE);
    }
}
