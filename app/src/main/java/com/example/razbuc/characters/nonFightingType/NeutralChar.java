package com.example.razbuc.characters.nonFightingType;

import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.R;
import com.example.razbuc.characters.NonFightingChar;
import com.example.razbuc.characters.fightingType.Hero;
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
        return "La mamie";
    }

    @Override
    public String getFullName() {
        return "Une mamie";
    }


    private int state = 1;

    private int state_1(String fling){
        switch (fling){
            case "Fling droit":
                this.state = 2;
                return R.string.mamie_2;
            case "Fling gauche":
                this.state = 0;
                return R.string.mamie_wrong_answer;
        }
        return R.string.mamie_wrong_answer;
    }
    private int state_2(String fling){
        switch (fling){
            case "Fling droit":
                this.state = 0;
                return R.string.mamie_wrong_answer;
            case "Fling gauche":
                this.state = 3;
                return R.string.mamie_3;
        }
        return R.string.mamie_wrong_answer;
    }
    public void resetDialog(){
        this.state = 1;
    }
    public int repeat(){
        switch (this.state){
            case 1:
                return R.string.mamie_1;
            case 2:
                return R.string.mamie_2;
        }
        return R.string.mamie_1;
    }
    public int answer(String fling){
        switch(this.state){
            case 1:
                return state_1(fling);
            case 2:
                return state_2(fling);
        }
        return R.string.mamie_wrong_answer;
    }

    public boolean isDialogEnded(){
        return (this.state == 0 || this.state == 3);
    }

    public int endDialog(Hero hero){
        if (this.state == 3) {
            if (hero.hasToolbox()) {
                this.setVisited(true);
                hero.addAlly(this);
                return R.string.mamie_3_oui;
            } else {
                return R.string.mamie_3_non;
            }
        }
        return R.string.rien;
    }
}
