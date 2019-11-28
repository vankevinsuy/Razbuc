package com.example.razbuc.location;

import com.example.razbuc.Entity;

public class District extends Entity {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public District(String name,
                    int[] position) {
        System.out.println("Constructing a district ...");
        this.setName(name);
        this.setPosition(position);
    }

}
