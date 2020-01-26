package com.example.razbuc.characters.nonFightingType;

import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.R;
import com.example.razbuc.characters.NonFightingChar;
import com.example.razbuc.characters.fightingType.Hero;
import com.example.razbuc.items.Item;

import java.util.ArrayList;

public class NeutralChar extends NonFightingChar {

    private int _state = 1;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    /** Creates a neutral character
     *
     * @param name indicating the name of the neutral character
     * @param inventory indicating the list of items the neutral character possesses
     * @param position indicating the location on the map where the neutral character can be met
     */
    public NeutralChar(String name,
                       ArrayList<Item> inventory,
                       int[] position) {
        super(name, inventory, position);
        System.out.println("Constructing a neutral character ...");
        this.setType(ElementType.PNJ);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Gives a name for a specific neutral character
     *
     * @return a string indicating a specific neutral character
     */
    @Override
    public String getNameWithPronoun() {
        return "La mamie";
    }

    /** Gives a name for a neutral character
     *
     * @return a string indicating a neutral character
     */
    @Override
    public String getFullName() {
        return "Une mamie";
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Gestures

    /** Tells a dialog option on state 1
     *
     * @param fling indicating the direction of the fling
     * @return a string indicating a dialog line
     */
    private int state_1(String fling){
        switch (fling){
            case "Fling droit":
                this._state = 2;
                return R.string.mamie_2;
            case "Fling gauche":
                this._state = 0;
                return R.string.mamie_wrong_answer;
        }
        return R.string.mamie_wrong_answer;
    }

    /** Tells a dialog option on state 2
     *
     * @param fling indicating the direction of the fling
     * @return a string indicating a dialog line
     */
    private int state_2(String fling){
        switch (fling){
            case "Fling droit":
                this._state = 0;
                return R.string.mamie_wrong_answer;
            case "Fling gauche":
                this._state = 3;
                return R.string.mamie_3;
        }
        return R.string.mamie_wrong_answer;
    }

    /** Resets the dialog options
     *
     */
    public void resetDialog(){
        this._state = 1;
    }

    /** Relaunch the dialog options
     *
     * @return a string indicating a dialog line, specific to the "grandma" character
     */
    public int repeat(){
        switch (this._state){
            case 1:
                return R.string.mamie_1;
            case 2:
                return R.string.mamie_2;
        }
        return R.string.mamie_1;
    }

    /** Chooses the outcome according to the player's answer
     *
     * @param fling indicating the direction of the fling
     * @return a string indicating a dialog line
     */
    public int answer(String fling){
        switch(this._state){
            case 1:
                return state_1(fling);
            case 2:
                return state_2(fling);
        }
        return R.string.mamie_wrong_answer;
    }

    /** Check if the dialog has ended
     *
     * @return a boolean of the dialog's state
     */
    public boolean isDialogEnded(){
        return (this._state == 0 || this._state == 3);
    }

    /** Tells if the player's dialog state
     *
     * @param hero indicating the player's character
     * @return a string indicating a dialog line
     */
    public int endDialog(Hero hero){
        if (this._state == 3) {
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
