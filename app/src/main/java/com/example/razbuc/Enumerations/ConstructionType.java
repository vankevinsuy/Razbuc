package com.example.razbuc.Enumerations;

public enum ConstructionType { // Correspond au nom des construction dans la bdd
    Batiment ("Batiment"),
    Hopital ("Hopital"),
    Poste_de_police ("Poste de police"),
    Garage ("Garage"),
    Office_du_tourisme ("Office du tourisme"),
    Mur ("Mur");


    private String name = "";

    //Constructeur
    ConstructionType(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }

    public static ConstructionType fromString(String val){
        for(ConstructionType en:values()){
            if(en.toString().equals(val)){
                return en;
            }
        }
        return ConstructionType.Batiment;
    }
}
