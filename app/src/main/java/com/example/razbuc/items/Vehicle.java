package com.example.razbuc.items;

public class Vehicle extends Item {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    final static String ITEM_TYPE = "Vehicle";
    final static int VEHICLE_DURABILITY = 10;        // If durability not set, this.durability = 10

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    // Vehicle creation with custom durability
    public Vehicle(String name,
                   int[] value,       // Movement range
                   int durability,
                   int[] position) {
        super(name, value, ITEM_TYPE, durability, position);
        System.out.println("Constructing a vehicle ...");
        setPrice(this.getValue()[0] * 10);         // By default, this.price = this.value * 10 for every vehicle
    }

    // Vehicle creation with default durability
    public Vehicle(String name,
                   int[] value,
                   int[] position) {       // Movement range
        super(name, value, ITEM_TYPE, VEHICLE_DURABILITY, position);
        System.out.println("Constructing a vehicle ...");
        setPrice(this.getValue()[0] * 10);         // By default, this.price = this.value * 10 for every vehicle
    }
}
