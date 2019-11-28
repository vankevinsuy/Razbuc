package com.example.razbuc.location;

public class Construction extends District {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    private String type;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public Construction(String name,
                        int[] position,
                        String type) {
        super(name, position);
        this.type = type;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
