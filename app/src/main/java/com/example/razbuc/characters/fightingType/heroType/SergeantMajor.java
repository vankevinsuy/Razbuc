package com.example.razbuc.characters.fightingType.heroType;

import com.example.razbuc.characters.fightingType.Hero;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;
import java.util.Arrays;

public class SergeantMajor extends Hero {

    final static String HERO_TYPE = "SergentMajor";

    public SergeantMajor() {
        super(HERO_TYPE, Arrays.asList(new Item[] { new Weapon("Pistolet",new int[] {0},new int[] {-1,-1}) }), 12,12,12,16,null);
        setType(HERO_TYPE);
    }
}
