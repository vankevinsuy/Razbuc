package com.example.razbuc;

public abstract class GameEntity {

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes

    private String name;
    private String description;
    private int[] position = new int[2];
    private boolean state = false;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Basic getters and setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public void setXposition(int x){this.position[0] = x;}

    public void setYposition(int y){this.position[1] = y;}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return state;
    }

    public void setVisited(boolean state) {
        this.state = state;
    }
}
