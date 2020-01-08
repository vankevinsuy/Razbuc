package com.example.razbuc;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import com.example.razbuc.characters.fightingType.Hero;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.Weapon;
import com.example.razbuc.location.District;
import com.example.razbuc.location.GameMap;
import com.example.razbuc.location.constructionType.Building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ResumeGameActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private TextToSpeech mTTS;
    private GestureDetectorCompat mDetector;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private HashMap<GameEntity, String> MapgestureByElement;

    private String CURRENT_ACTION;


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


        CURRENT_ACTION = getResources().getString(R.string.action_move);

        //set text reader
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
                        introduction();
                    }

                }
                else
                {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });


// FAKE DATAS
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

    private void introduction(){
        speak(getResources().getString(R.string.resume_game));
        speak(getResources().getString(R.string.cancel_interaction));
    }


//    GESTURE METHODS IMPLEMENTATION
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        refreshDirection();
        verifyElementAround(false, false);
        return super.onTouchEvent(event);
    }

    // the onSingleTapConfirmed is currently used for refreshing the position and describing the environnement again
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        refreshDirection();
        verifyElementAround(true, true);
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

    //not used
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    //not used
    @Override
    public void onShowPress(MotionEvent e) {
    }

    //not used
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    //not used
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Toast.makeText(getApplicationContext(), "mode déplacement", Toast.LENGTH_SHORT).show();
        speak("faire un fling dans une direction pour vous déplacer");
        CURRENT_ACTION = getResources().getString(R.string.action_move);
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
        switch (CURRENT_ACTION){
            case "move":
                if(verifyMoveDirection("nord")){
                    hero.setYposition(hero.getPosition()[1] - 1);
                    verifyElementAround(true, true);
                    refreshDirection();
                }
                break;

            case "interaction":
//                speak("interaction activée");
                actionByElement("Fling haut");
//                CURRENT_ACTION = getResources().getString(R.string.action_move);
                break;
        }
    }

    private void onFlingBottom() {
        switch (CURRENT_ACTION){
            case "move":
                if(verifyMoveDirection("sud")){
                    hero.setYposition(hero.getPosition()[1] + 1);
                    verifyElementAround(true, true);
                    refreshDirection();
                }
                break;

            case "interaction":
//                speak("interaction activée");
                actionByElement("Fling bas");
//                CURRENT_ACTION = getResources().getString(R.string.action_move);
                break;
        }
    }

    private void onFlingLeft() {
        switch (CURRENT_ACTION){
            case "move":
                if(verifyMoveDirection("ouest")){
                    hero.setXposition(hero.getPosition()[0] - 1);
                    verifyElementAround(true, true);
                    refreshDirection();
                }
                break;

            case "interaction":
//                speak("interaction activée");
                actionByElement("Fling gauche");
//                CURRENT_ACTION = getResources().getString(R.string.action_move);
                break;
        }
    }

    private void onFlingRight() {
        switch (CURRENT_ACTION){
            case "move":
                if(verifyMoveDirection("est")){
                    hero.setXposition(hero.getPosition()[0] + 1);
                    verifyElementAround(true, true);
                    refreshDirection();
                }
                break;

            case "interaction":
//                speak("interaction activée");
                actionByElement("Fling droit");
//                CURRENT_ACTION = getResources().getString(R.string.action_move);
                break;
        }
    }








    private void speak(String txt) {
        mTTS.setPitch(1);
        mTTS.setSpeechRate(1);
        mTTS.speak(txt, TextToSpeech.QUEUE_ADD,null, txt);
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

    private boolean verifyMoveDirection(String fling){
        boolean res = false;

        for (String direction : district.getPossibleDirection()){
            if(direction.equals(fling)){
                res = true;
            }
        }

        return res;
    }

    private void verifyElementAround(boolean talk, boolean modifyCURRENT_ACTION){
        // saying which element are around
        MapgestureByElement = new HashMap<>();
        String[] availableGesture = getResources().getStringArray(R.array.gestures_for_interaction);
        int indexGesture = 0;

        if (gameMap.getDistrictByPosition(hero.getPosition()).getElements().isEmpty()){
            elements.setText("nothing around");
            speak("rien autour de moi.");
        }
        else {
            String element_names = "";
            ArrayList<GameEntity> listOfElements = gameMap.getDistrictByPosition(hero.getPosition()).getElements();
            for(GameEntity element : listOfElements){
                MapgestureByElement.put(element,availableGesture[indexGesture]);
                indexGesture = indexGesture + 1;

                element_names = element_names + element.getName() + "    ";
                if(talk){
                    if(!element.isVisited()){
                        speak("autour de moi il y a  " + element.getName());
                        sayHowToInteract(element);
                    }
                }
            }
            elements.setText(element_names);

            if(modifyCURRENT_ACTION){
                CURRENT_ACTION = getResources().getString(R.string.action_interact);
            }
        }
    }

    private void sayHowToInteract(GameEntity element){
        String type = element.getClass().getSimpleName();

        switch (type){
            case "Building":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(element.equals(entry.getKey())){
                        speak("pour visiter " + element.getName() + " faite un " + entry.getValue());
                    }
                }
                break;

            case "Vehicule":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(element.equals(entry.getKey())){
                        speak("pour prendre " + element.getName() + " faite un " + entry.getValue());
                    }
                }
                break;

            case "Ennemy":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(element.equals(entry.getKey())){
                        speak("pour combattre " + element.getName() + " faite un " + entry.getValue());
                    }
                }
                break;

            case "Merchant":
            case "NeutralChar":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(element.equals(entry.getKey())){
                        speak("pour parler avec " + element.getName() + " faite un " + entry.getValue());
                    }
                }
                break;
        }
    }

    private void actionByElement(String action){
        GameEntity entity = null;

        for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
            if (entry.getValue().equals(action)){
                entity = entry.getKey();
            }
        }

        // en fcontion du type de l'element, on intéragit d'une certaine façon
        String type = entity.getClass().getSimpleName();

        switch (type){
            case "Building":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(entity.equals(entry.getKey())){
                        // recupérer tout ce qu'il y a dans le building et passer le state à true
                        if(!entity.isVisited()){
                            Building building = (Building) entity;

                            building.setInventory(new ArrayList<Item>()); // iventaire du building set à vide

                            if(building.getInventory().size() == 0){
                                speak(entity.getName() + " est vide");
                            }
                            else {
                                for (Item item : building.getInventory()){
                                    speak(entity.getName() + " ajoutée à l'inventaire.");
                                    hero.addToInventory(item);
                                }
                            }
                            entity.setVisited(true);
                        }
                        else {
                            speak(entity.getName() + "déjà visité");
                            continue;
                        }
                    }
                }
                break;

            case "Vehicule":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(entity.equals(entry.getKey())){
                        if(!entity.isVisited()){
                            hero.addToInventory((Item) entity);
                            speak(entity.getName() + " ajoutée à l'inventaire.");
                            entity.setVisited(true);
                        }
                        else {
                            speak(entity.getName() + "déjà visité");
                            continue;
                        }
                    }
                }
                break;

            case "Ennemy":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(entity.equals(entry.getKey())){
                        if(!entity.isVisited()){

                        }
                        else {
                            continue;
                        }
                    }
                }
                break;

            case "Merchant":
            case "NeutralChar":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(entity.equals(entry.getKey())){
                        if(!entity.isVisited()){

                        }
                    }
                }
                break;
        }

    }
}
