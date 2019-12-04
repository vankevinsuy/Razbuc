package com.example.razbuc.location;

import com.example.razbuc.GameEntity;

import java.util.ArrayList;

public class District extends GameEntity {
    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes
    private String description;
    private ArrayList<String> possibleDirection;
    private Object elements;
    private int id;
    private boolean visited;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public District(String name,
                    int[] position) {
        System.out.println("Constructing a district ...");
        this.setName(name);
        this.setPosition(position);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getPossibleDirection() {
        return possibleDirection;
    }

    public void setPossibleDirection(ArrayList<String> possibleDirection) {
        this.possibleDirection = possibleDirection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
