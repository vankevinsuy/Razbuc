package com.example.razbuc.location;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GameMap {

    private int nbCOL;
    private int nbLINE;
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

                for (DataSnapshot snapshot: dataSnapshot.child("district").getChildren()) {

                    int[] position = new int[2];

                    position[0] = Integer.parseInt(snapshot.child("posx").getValue().toString());
                    position[1] = Integer.parseInt(snapshot.child("posy").getValue().toString());

                    District d = new District((String)snapshot.child("name").getValue(), position);
                    d.setDescription((String)snapshot.child("description").getValue());
                    d.setId(Integer.parseInt(snapshot.child("id").getValue().toString()));
                    d.setVisited((boolean)snapshot.child("visited").getValue());
                    //d.setElements((String)snapshot.child("element").getValue());

                    Object directions = snapshot.child("directionPossible").getValue();

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
}
