package com.example.razbuc.location;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.razbuc.LocalDatabase.RazbucLocalDb;
import com.example.razbuc.characters.fightingType.Ennemy;
import com.example.razbuc.characters.nonFightingType.Merchant;
import com.example.razbuc.characters.nonFightingType.NeutralChar;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.Vehicle;
import com.example.razbuc.location.constructionType.Building;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameMap {

    private RazbucLocalDb razbucLocalDb;
    private ArrayList<District> listDistrict;
    private boolean finished;
    private String name;
    private String type;
    private String chapter;


    public GameMap() {
        this.listDistrict = new ArrayList<>();
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
                                int[] fakeValue = new int[1];
                                fakeValue[0] = 1;
                                d.addElements(new Vehicle(name, fakeValue, districtPosition));
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
                                        d.addElements(new Merchant(name, new ArrayList<Item>(), districtPosition));
                                        break;
                                    case "Neutral":
                                        d.addElements(new NeutralChar(name, new ArrayList<Item>(), districtPosition));
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

    public void buildUserSavedMap(Context context){

        razbucLocalDb = new RazbucLocalDb(context);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userData = db.collection("users").document(razbucLocalDb.getUserId());

        userData.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    // Document found in the offline cache
                    DocumentSnapshot document = task.getResult();

                    Map<String, Object> data = document.getData();
                    Map<String, Object> values = (HashMap<String, Object>) data.get("value");

                    chapter =   (String)    values.get("chapter");
                    finished =  (boolean)   values.get("finished");
                    name =      (String)    values.get("name");
                    type =      (String)    values.get("type");

                    // treatment for each district
                    ArrayList<Map<String, Object>> ListOfdistrict = (ArrayList<Map<String, Object>>) values.get("district");

                    for(Map<String, Object> district : ListOfdistrict){

                        int[] districtPosition = new int[2];

                        districtPosition[0] = Integer.parseInt(district.get("posx").toString());
                        districtPosition[1] = Integer.parseInt(district.get("posy").toString());

                        District d = new District(district.get("name").toString() , districtPosition);
                        d.setDescription((String) district.get("description").toString());
                        d.setId(Integer.parseInt(district.get("id").toString()));
                        d.setVisited((boolean) district.get("visited"));

                        ArrayList<String> directionPossible = (ArrayList<String>) district.get("directionPossible");

                        for(String direction : directionPossible){
                            d.addPossibleDirection(direction);
                        }

                        // treatment for each element
                        ArrayList<Map<String, Object>> listOfElements = (ArrayList<Map<String, Object>>) district.get("elements");

                        for(Map<String, Object> element : listOfElements){

                            String elementType = element.get("type").toString();
                            String elementName = element.get("nom").toString();
                            boolean elementState = (boolean) element.get("state");

                            switch (elementType){
                                case "building":
                                    d.addElements(new Building(elementName, districtPosition, elementType, null));
                                    break;

                                case "vehicule":
                                    int[] fakeValue = new int[1];
                                    fakeValue[0] = 1;
                                    d.addElements(new Vehicle(elementName, fakeValue, districtPosition));
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

                                    d.addElements(new Ennemy(elementName, null, districtPosition, hp, damage));
                                    break;

                                case "PNJ":
                                    switch (name){
                                        case "Marchands":
                                            d.addElements(new Merchant(elementName, new ArrayList<Item>(), districtPosition));
                                            break;
                                        case "Neutral":
                                            d.addElements(new NeutralChar(elementName, new ArrayList<Item>(), districtPosition));
                                            break;
                                    }

                                    break;
                            }
                        }

                        listDistrict.add(d);
                    }

                } else {
                    Log.d("rr", "Cached get failed: ", task.getException());
                }
            }
        });
    }


    public ArrayList<District> getListDistrict() {
        return this.listDistrict;
    }
}
