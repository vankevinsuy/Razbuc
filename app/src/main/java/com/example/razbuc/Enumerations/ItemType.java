package com.example.razbuc.Enumerations;

public enum ItemType {
    Consumable ("Trousse de soins"),
    PaperMap ("Carte"),
    Toolbox ("Boite à outil"),
    Weapon ("Pistolet"),
    Misc ("Misc");

    private String name = "";

    //Constructeur
    ItemType(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }

    public static ItemType fromString(String val){
        for(ItemType en:values()){
            if(en.toString().equals(val)){
                return en;
            }
        }
        return ItemType.Consumable;
    }
}
