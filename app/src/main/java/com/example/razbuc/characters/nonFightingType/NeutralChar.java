package com.example.razbuc.characters.nonFightingType;

import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.characters.NonFightingChar;
import com.example.razbuc.items.Item;

import java.util.ArrayList;

public class NeutralChar extends NonFightingChar {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public NeutralChar(String name,
                       ArrayList<Item> inventory,
                       int[] position) {
        super(name, inventory, position);
        System.out.println("Constructing a neutral character ...");
        this.setType(ElementType.PNJ);
    }

    @Override
    public String getNameWithPronoun() {
        return null;
    }

    @Override
    public String getFullName() {
        return null;
    }
}
