package com.example.razbuc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.razbuc.location.District;
import com.example.razbuc.location.GameMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameMap gameMap = new GameMap();

        for(District district : gameMap.getListDistrict()){
            Log.d("Position X du district", Integer.toString(district.getPosition()[0]));
        }
    }
}
