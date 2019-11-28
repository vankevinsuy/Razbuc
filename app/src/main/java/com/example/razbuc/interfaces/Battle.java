package com.example.razbuc.interfaces;

import com.example.razbuc.characters.fightingType.Ennemy;

public interface Battle {

    void attack(Ennemy ennemy);

    void isAlive();

    void flee();

    void useHealItem();

    void useItem();
}
