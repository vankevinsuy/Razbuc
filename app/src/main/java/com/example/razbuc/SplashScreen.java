package com.example.razbuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SplashScreen extends AppCompatActivity {

    private ImageView splashScreenImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        this.splashScreenImgView = findViewById(R.id.splashScreenImageView);

        Picasso.get().load(R.drawable.splash).fit().into(this.splashScreenImgView);


        // after 2 seconds, destroy SplashScreen Activity and launch MainActivity
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
                finish();
            }
        }, 2000);
    }
}
