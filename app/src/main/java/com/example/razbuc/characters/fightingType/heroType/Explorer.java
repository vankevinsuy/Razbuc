package com.example.razbuc.characters.fightingType.heroType;

import com.example.razbuc.characters.fightingType.Hero;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.PaperMap;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;
import java.util.Arrays;

public class Explorer extends Hero {

    final static String HERO_TYPE = "Explorer";


    public Explorer() {
        super(HERO_TYPE, Arrays.asList(new Item[] { new PaperMap("Carte",new int[] {0},new int[] {-1,-1}) }), 16,12,12,12,null);
        setType(HERO_TYPE);
    }
}
