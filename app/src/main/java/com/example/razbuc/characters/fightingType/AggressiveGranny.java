package com.example.razbuc.characters.fightingType;

import com.example.razbuc.items.Weapon;

public class AggressiveGranny extends Enemy {

    /** Creates a new grandma
     *
     * @param name indicating the name of the grandma
     * @param position indicating the location on the map where the grandma can be met
     * @param health_points indicating the number of hits the grandma can take before dying
     * @param basic_damages indicating the number of basic hits per turn the grandma can deal to another character
     * @param force indicating the ability score for the "force" trait of the grandma
     * @param weapon indicating the "weapon" item that the grandma uses to fight with
     * @param initiative indicating the initiative score of the grandma
     */
    public AggressiveGranny(String name, int[] position, int health_points, int basic_damages, int force, Weapon weapon, boolean initiative) {
        super(name, position, health_points, basic_damages, force, weapon, initiative);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Custom methods

    /** Gives a name for a specific grandma
     *
     * @return a string indicating a specific grandma
     */
    @Override
    public String getNameWithPronoun() {
        return "La mamie";
    }

    /** Gives a name for a grandma
     *
     * @return a string indicating a grandma
     */
    @Override
    public String getFullName() {
        return "Une mamie";
    }
}
