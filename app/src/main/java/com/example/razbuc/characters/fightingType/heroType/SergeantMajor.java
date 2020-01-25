package com.example.razbuc.characters.fightingType.heroType;

import com.example.razbuc.characters.fightingType.Hero;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;
import java.util.Arrays;

public class SergeantMajor extends Hero {

    final static String HERO_TYPE = "SergentMajor";

    public SergeantMajor() {
        super(HERO_TYPE, new ArrayList<Item>(), 12,12,12,16, new Weapon("Pistolet", 3,new int[] {-1,-1}));
    }
}
