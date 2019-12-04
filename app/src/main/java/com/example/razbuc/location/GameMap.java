package com.example.razbuc.location;

import android.icu.lang.UCharacter;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.razbuc.characters.fightingType.Ennemy;
import com.example.razbuc.characters.nonFightingType.Merchant;
import com.example.razbuc.characters.nonFightingType.NeutralChar;
import com.example.razbuc.items.Vehicle;
import com.example.razbuc.location.constructionType.Building;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class GameMap {

    private ArrayList<District> listDistrict;
    private boolean finished;
    private String name;
    private String type;
    private String chapter;


    public GameMap() {
        this.listDistrict = new ArrayList<>();
        builBasicdMap();
    }

    public void builBasicdMap(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                chapter = (String) dataSnapshot.child("chapter").getValue();
                finished = (boolean)dataSnapshot.child("finished").getValue();
                name = (String) dataSnapshot.child("name").getValue();
                type = (String) dataSnapshot.child("type").getValue();

                // treatment for each district
                for (DataSnapshot FirebaseDistrict : dataSnapshot.child("district").getChildren()) {

                    int[] districtPosition = new int[2];

                    districtPosition[0] = Integer.parseInt(FirebaseDistrict.child("posx").getValue().toString());
                    districtPosition[1] = Integer.parseInt(FirebaseDistrict.child("posy").getValue().toString());

                    District d = new District((String)FirebaseDistrict.child("name").getValue(), districtPosition);
                    d.setDescription((String)FirebaseDistrict.child("description").getValue());
                    d.setId(Integer.parseInt(FirebaseDistrict.child("id").getValue().toString()));
                    d.setVisited((boolean)FirebaseDistrict.child("visited").getValue());

                    for (DataSnapshot element : FirebaseDistrict.child("elements").getChildren()){
                        String type = element.child("type").getValue().toString();
                        String name = element.child("nom").getValue().toString();
                        String state = element.child("state").getValue().toString();


                        switch (type){

                            case "building":
                                d.addElements(new Building(name, districtPosition, type, null));
                                break;

                            case "vehicule":
                                d.addElements(new Vehicle(name, 0, districtPosition));
                                break;

                            case "Ennemy":
                                Random rand = new Random();
                                int hp = rand.nextInt(20);
                                int damage = 0;

                                if(name.equals("Mamie")){
                                    damage = rand.nextInt(6);
                                }
                                else {
                                    damage = rand.nextInt(4);
                                }

                                d.addElements(new Ennemy(name, null, districtPosition, hp, damage));
                                break;

                            case "PNJ":
                                switch (name){
                                    case "Marchands":
                                        d.addElements(new Merchant(name, null, districtPosition));
                                        break;
                                    case "Neutral":
                                        d.addElements(new NeutralChar(name, null, districtPosition));
                                        break;
                                }

                                break;

                        }
                    }


                    // get the possible direction where we can move
                    for (DataSnapshot direction : FirebaseDistrict.child("directionPossible").getChildren()){
                        d.addPossibleDirection(direction.getValue().toString());
                    }


                    listDistrict.add(d);
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("FAIL", "epic fail");
            }
        });
    }


    public ArrayList<District> getListDistrict() {
        return listDistrict;
    }
}
