package com.example.razbuc.location;

import com.example.razbuc.GameEntity;

import java.util.ArrayList;

public class District extends GameEntity {
    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Attributes
    private ArrayList<String> possibleDirection;
    private ArrayList<GameEntity> elements;
    private int id;
    private boolean visited;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Constructor

    public District(String name,
                    int[] position) {
        System.out.println("Constructing a district ...");
        this.setName(name);
        this.setPosition(position);
        this.possibleDirection = new ArrayList<>();
        this.elements = new ArrayList<>();
    }

    public ArrayList<String> getPossibleDirection() {
        return possibleDirection;
    }

    public void setPossibleDirection(ArrayList<String> possibleDirection) {
        this.possibleDirection = possibleDirection;
    }

    public void addPossibleDirection(String possibleDirection) {
        this.possibleDirection.add(possibleDirection);
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

    public ArrayList<GameEntity> getElements() {
        return elements;
    }

    public void setElements(ArrayList<GameEntity> elements) {
        this.elements = elements;
    }

    public void addElements(GameEntity elements) {
        this.elements.add(elements);
    }
}
