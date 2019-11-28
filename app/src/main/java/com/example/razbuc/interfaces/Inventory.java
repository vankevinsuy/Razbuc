package com.example.razbuc.interfaces;

public interface Inventory {

    void checkNextItem();

    void checkPreviousItem();

    void useItem();

    void dropItem();

    void enterInventory();

    void leaveInventory();

    void relistenItem();
}
