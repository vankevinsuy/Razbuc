package com.example.razbuc.characters;

import com.example.razbuc.items.Item;
import com.example.razbuc.items.PaperMap;
import com.example.razbuc.items.Toolbox;
import com.example.razbuc.items.Weapon;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public abstract class NonFightingChar extends Character {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor



    public NonFightingChar(String name,
                           List<Item> inventory,
                           int[] position) {
        super(name, inventory, position);
    }





}
