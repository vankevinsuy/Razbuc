package com.example.razbuc.Enumerations;

public enum ElementType {  // Correspond aux types des elements dans la bdd
    Construction ("Construction"),
    Ennemy ("Ennemy"),
    PNJ ("PNJ"),
    Merchant ("Marchand"),
    Vehicule ("Vehicule"),
    Item ("Item");

    private String name = "";

    //Constructeur
    ElementType(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }

    public static ElementType fromString(String val){
        for(ElementType en:values()){
            if(en.toString().equals(val)){
                return en;
            }
        }
        return ElementType.Construction;
    }
}
