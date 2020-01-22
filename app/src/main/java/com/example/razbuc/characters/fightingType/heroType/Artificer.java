package com.example.razbuc.characters.fightingType.heroType;

import com.example.razbuc.characters.fightingType.Hero;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.Toolbox;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Artificer extends Hero {

    final static String HERO_TYPE = "Artificer";

    public Artificer() {
        super(HERO_TYPE, Arrays.asList(new Item[] { new Toolbox("Boite à outils",new int[] {0},new int[] {-1,-1}) }), 12,16,12,12,null);
        setType(HERO_TYPE);

    }
}
