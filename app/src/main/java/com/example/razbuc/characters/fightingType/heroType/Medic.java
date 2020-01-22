package com.example.razbuc.characters.fightingType.heroType;

import com.example.razbuc.characters.fightingType.Hero;
import com.example.razbuc.items.Consumable;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;
import java.util.Arrays;

public class Medic extends Hero {

    final static String HERO_TYPE = "Medic";

    public Medic() {
        super(HERO_TYPE, Arrays.asList(new Item[] { new Consumable("Trousse de soins",new int[] {0},new int[] {-1,-1}) }), 12,12,16,12,null);
        setType(HERO_TYPE);
    }
}
