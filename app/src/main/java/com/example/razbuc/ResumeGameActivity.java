package com.example.razbuc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.razbuc.characters.fightingType.Hero;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.Weapon;
import com.example.razbuc.location.District;
import com.example.razbuc.location.GameMap;

import java.util.ArrayList;

public class ResumeGameActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private GestureDetectorCompat mDetector;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;


    Hero hero;
    TextView textView, currentPosition, elements;
    District district;
    GameMap gameMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_game);
        hideSystemUI();

        textView = findViewById(R.id.directionPossible);
        currentPosition  = findViewById(R.id.currentPosition);
        elements = findViewById(R.id.elements);

        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);

        gameMap = new GameMap();
        gameMap.buildUserSavedMap(getApplicationContext());

        int[] Position = new int[2];

        Position[0] = 0;
        Position[1] = 0;

        int[] value = new int[2];

        value[0] = 2;
        value[1] = 0;

        ArrayList<Item> inventory = new ArrayList<>();
        Weapon arme = new Weapon("couteau", value, 5, Position);

        hero = new Hero(
                "jojjo",
                inventory,
                Position,
                100,
                20,
                arme);

        currentPosition.setText(Integer.toString(hero.getPosition()[0]) + "  " + Integer.toString(hero.getPosition()[1]));
    }




    private void refreshDirection(){
        district = gameMap.getDistrictByPosition(hero.getPosition()[0],hero.getPosition()[1]);
        String directions = "";
        for(String direction : district.getPossibleDirection()){
            directions = directions + "   " + direction;
        }
        textView.setText(directions);
        currentPosition.setText(Integer.toString(hero.getPosition()[0]) + "  " + Integer.toString(hero.getPosition()[1]));
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


    //gesture
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (this.mDetector.onTouchEvent(event)) {
            refreshDirection();
            verifyElementAround();
            return true;
        }
        refreshDirection();
        verifyElementAround();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onFlingRight();
                    } else {
                        onFlingLeft();
                    }
                    result = true;
                }
            }
            else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    onFlingBottom();
                } else {
                    onFlingTop();
                }
                result = true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

    private void onFlingTop() {
        if(verifyMoveDirection("nord")){
            hero.setYposition(hero.getPosition()[1] - 1);
            verifyElementAround();

        }
    }

    private void onFlingBottom() {
        if(verifyMoveDirection("sud")){
            hero.setYposition(hero.getPosition()[1] + 1);
            verifyElementAround();

        }
    }

    private void onFlingLeft() {
        if(verifyMoveDirection("ouest")){
            hero.setXposition(hero.getPosition()[0] - 1);
            verifyElementAround();

        }
    }

    private void onFlingRight() {
        if(verifyMoveDirection("est")){
            hero.setXposition(hero.getPosition()[0] + 1);
            verifyElementAround();
        }
    }

    private boolean verifyMoveDirection(String fling){
        boolean res = false;

        for (String direction : district.getPossibleDirection()){
            if(direction.equals(fling)){
                res = true;
            }
        }

        return res;
    }

    private void verifyElementAround(){

        if (gameMap.getDistrictByPosition(hero.getPosition()).getElements().isEmpty()){
            elements.setText("nothing around");
        }
        else {
            String element_names = "";
            for(GameEntity element : gameMap.getDistrictByPosition(hero.getPosition()).getElements()){
                element_names = element_names + element.getName() + "    ";
            }
            elements.setText(element_names);
        }
    }
}
