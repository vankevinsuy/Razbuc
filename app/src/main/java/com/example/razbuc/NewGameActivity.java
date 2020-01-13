package com.example.razbuc;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import java.util.Locale;

public class NewGameActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private TextToSpeech mTTS;
    private String currentStep;
    private GestureDetectorCompat mDetector;
    private String[] heroList;
    private int selectedHero;
    private boolean canDetectEvent = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        heroList=getResources().getStringArray(R.array.list_hero_type);

        hideSystemUI();
        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);


//        GameMap gameMap = new GameMap();
//        gameMap.builBasicdMap();

        //read text
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS)
                {
                    int result = mTTS.setLanguage(Locale.FRENCH);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }

                    else
                    {
                        scenario();
                    }

                }
                else
                {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

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


    private void speak(int resourceId) {
        this.canDetectEvent = false;
        mTTS.setPitch(1);
        mTTS.setSpeechRate(1);
        mTTS.speak(getResources().getString(resourceId), TextToSpeech.QUEUE_ADD, null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                waitUntilSpeakEnds();
            }
        }).start();
    }
    private void speak(String textToSpeak){
        this.canDetectEvent = false;
        mTTS.setPitch(1);
        mTTS.setSpeechRate(1);
        mTTS.speak(textToSpeak, TextToSpeech.QUEUE_ADD, null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                waitUntilSpeakEnds();
            }
        }).start();
    }

    private void waitUntilSpeakEnds(){
        while (mTTS.isSpeaking()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.canDetectEvent = true;
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }

        super.onDestroy();
    }


    public void scenario(){
        currentStep = "welcome";
        speak(R.string.welcome);

        currentStep = "start_new_game";
        speak(R.string.start_new_game);
        // wait for double tap

    }




    //gesture

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (!this.canDetectEvent)
            return false;

        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        if (!this.canDetectEvent)
            return false;

        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        if (!this.canDetectEvent)
            return false;
        switch (currentStep){
            case "start_new_game":
                currentStep = "choose_hero";
                speak(R.string.choose_hero);

                currentStep = "describe_characters";
                speak(R.string.describe_characters);
                currentStep = "select_hero";
                selectedHero=0;
                speak(heroList[selectedHero]);
                break;
            case "select_hero":
                currentStep = "start_game";
                speak(R.string.story);
                Intent resumeGameActivity = new Intent(getApplicationContext(), ResumeGameActivity.class);
                resumeGameActivity.putExtra("new", false);
                resumeGameActivity.putExtra("hero",heroList[selectedHero] );
                startActivity(resumeGameActivity);
                break;
        }


        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        if (!this.canDetectEvent)
            return false;

        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        if (!this.canDetectEvent)
            return false;

        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        if (!this.canDetectEvent)
            return;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if (!this.canDetectEvent)
            return false;

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (!this.canDetectEvent)
            return false;

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        if (!this.canDetectEvent)
            return;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (!this.canDetectEvent)
            return false;

        if (e1.getX()>e2.getX()){
            return onFlingRight();
        }
        if (e1.getX()<e2.getX()){
            return onFlingLeft();
        }
        return true;
    }
    public boolean onFlingRight(){
        switch (currentStep) {
            case "select_hero":
                selectedHero=(selectedHero+1) % heroList.length;
                speak(heroList[selectedHero]);
        }
        return true;
    }
    public boolean onFlingLeft() {
        switch (currentStep) {
            case "select_hero":
                selectedHero=(selectedHero-1+heroList.length)% heroList.length;
                speak(heroList[selectedHero]);

        }
        return true;
    }


}
