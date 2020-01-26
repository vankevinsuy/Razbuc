package com.example.razbuc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.razbuc.LocalDatabase.RazbucLocalDb;
import com.example.razbuc.items.PaperMap;
import com.example.razbuc.location.GameMap;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class SplashScreen extends AppCompatActivity {

    private ImageView splashScreenImgView;
    private RazbucLocalDb razbucLocalDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        razbucLocalDb = new RazbucLocalDb(getApplicationContext());
        this.splashScreenImgView = findViewById(R.id.splashScreenImageView);

        hideSystemUI();

        Picasso.get().load(R.drawable.splash).fit().into(this.splashScreenImgView);

        razbucLocalDb.clearDatabase(); // Sert lorsque la db n'est pas supprimé à la désinstallation de l'appli (bug ?)

        // after 2 seconds, destroy SplashScreen Activity and launch MainActivity
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                // verify if we have to create a new game
                if(razbucLocalDb.isFirstUse()){
                    //add new user in firestore and local database
                    createNewUser();
                    Intent NewGameActivity = new Intent(getApplicationContext(), NewGameActivity.class);
                    startActivity(NewGameActivity);
                    finish();
                }
                else {
                    Intent ResumeGameActivity = new Intent(getApplicationContext(), ResumeGameActivity.class);
                    ResumeGameActivity.putExtra("new", false);
                    startActivity(ResumeGameActivity);
                    finish();
//                    Intent NewGameActivity = new Intent(getApplicationContext(), NewGameActivity.class);
//                    startActivity(NewGameActivity);
//                    finish();
                }
            }
        }, 2000);

    }

    // remove the top bar
    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // add new user in firestore and save the generated user id in our local database
    private void createNewUser(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = getResources().getString(R.string.Firestore_add_new_user);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        String res = response.substring(2,100).split(",")[0];
                        String idNewUser = res.replaceAll("\"", "");
                        razbucLocalDb.saveUserId(idNewUser);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //System.out.println(error);
                //razbucLocalDb.saveUserId("E6hWSIVl0kjcgSiXaRKD");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
