package com.example.razbuc.items;

public class Vehicule extends Item {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static String ITEM_TYPE = "Vehicule";
    final static int VEHICLE_DURABILITY = 10;        // If durability not set, this.durability = 10

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    // Vehicule creation with custom durability
    public Vehicule(String name,
                    int[] value,       // Movement range
                    int durability,
                    int[] position) {
        super(name, value, ITEM_TYPE, durability, position);
        System.out.println("Constructing a vehicle ...");
        setPrice(this.getValue()[0] * 10);         // By default, this.price = this.value * 10 for every vehicle
    }

    // Vehicule creation with default durability
    public Vehicule(String name,
                    int[] value,
                    int[] position) {       // Movement range
        super(name, value, ITEM_TYPE, VEHICLE_DURABILITY, position);
        System.out.println("Constructing a vehicle ...");
        setPrice(this.getValue()[0] * 10);         // By default, this.price = this.value * 10 for every vehicle
    }
}
