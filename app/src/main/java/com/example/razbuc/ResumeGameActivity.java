package com.example.razbuc;

import android.content.Intent;
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

import com.example.razbuc.characters.NonFightingChar;
import com.example.razbuc.characters.fightingType.Ennemy;
import com.example.razbuc.characters.fightingType.Hero;
import com.example.razbuc.characters.fightingType.heroType.Artificer;
import com.example.razbuc.characters.fightingType.heroType.Explorer;
import com.example.razbuc.characters.fightingType.heroType.Medic;
import com.example.razbuc.characters.fightingType.heroType.SergeantMajor;
import com.example.razbuc.items.Consumable;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.Misc;
import com.example.razbuc.location.Construction;
import com.example.razbuc.location.District;
import com.example.razbuc.location.GameMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class ResumeGameActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private TextToSpeech mTTS;
    private boolean TextToSpeechReady;
    private GestureDetectorCompat mDetector;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private HashMap<GameEntity, String> MapgestureByElement;

    private boolean canDetectEvent = true;

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

        gameMap = new GameMap();
        gameMap.buildUserSavedMap(getApplicationContext());


        CURRENT_ACTION = getResources().getString(R.string.action_move);

        //set text reader
        TextToSpeechReady = false;
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS)
                {
                    int result = mTTS.setLanguage(Locale.FRENCH);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }
                    else{
                        TextToSpeechReady = true;
                    }
                }
                else
                {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!TextToSpeechReady || !gameMap.isReady()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        init();
                    }
                });
            }
        }).start();
    }

    private void init(){
        Intent intent=getIntent();
        boolean isNew = intent.getBooleanExtra("new", false);
        if (isNew){
            String hero = intent.getStringExtra("hero");
            if (hero == null)
                return;
            switch (hero){
                case "Artificier":
                    this.hero= new Artificer();
                    break;
                case "Militaire":
                    this.hero=new Explorer();
                    break;
                case "Médecin":
                    this.hero=new Medic();
                    break;
                case "Explorateur":
                    this.hero=new SergeantMajor();
                    break;
            }
        }
        else{
            //récupération du héros via l'enregistrement dans la base qui n'est pas encore fait
            speak(R.string.resume_game);
            this.hero= new Artificer();
        }
        speak(R.string.information);
        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);
        describeDistrict();
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


    private void describeDistrict(){
        District district = gameMap.getDistrictByPosition(hero.getPosition());
        if (district.isVisited())
            verifyElementAround(true, true);
        else{
            switch(district.getId()){
                case 1: speak(R.string.map1); break;
                case 2: speak(R.string.map2); break;
                //case 3: speak(R.string.map3); break;
                case 4: speak(R.string.map4); break;
                //case 5: speak(R.string.map5); break;
                //case 6: speak(R.string.map6); break;
                case 7: speak(R.string.map7); break;
                //case 8: speak(R.string.map8); break;
                //case 9: speak(R.string.map9); break;
                default: speak("Texte non implémenté");
            }
            district.setVisited(true);
        }
        CURRENT_ACTION = getResources().getString(R.string.action_interact);
        verifyElementAround(false, false);
    }

    private void refreshDirection(){
        district = gameMap.getDistrictByPosition(hero.getPosition()[0],hero.getPosition()[1]);
        StringBuilder directions = new StringBuilder();
        StringBuilder sayDirections = new StringBuilder("Vous pouvez vous déplacer vers ");
        boolean first = true;
        for(String direction : district.getPossibleDirection()){
            directions.append("   ").append(direction);
            String apostrophe = (direction.equals("nord") || direction.equals("sud")) ? "le " : "l'";
            if (first) {
                sayDirections.append(apostrophe).append(adaptDirectionForSpeak(direction));
                first = false;
            }
            else
                sayDirections.append(", ou ").append(apostrophe).append(adaptDirectionForSpeak(direction));
        }
        sayDirections.append(".");
        speak(sayDirections.toString());
        textView.setText(directions.toString());
        currentPosition.setText((hero.getPosition()[0] + "  " + hero.getPosition()[1]));
    }

    private String adaptDirectionForSpeak(String direction){
        switch (direction){
            case "est":
                return "èste";
            case "nord":
            case "sud":
            case "ouest":
                return direction;
        }
        return "";
    }

    private void verifyElementAround(boolean talk, boolean modifyCURRENT_ACTION){
        // saying which element are around
        MapgestureByElement = new HashMap<>();
        String[] availableGesture = getResources().getStringArray(R.array.gestures_for_interaction);
        int indexGesture = 0;
        boolean firstElement = true;
        if (gameMap.getDistrictByPosition(hero.getPosition()).nothingToInteract()){
            elements.setText("nothing around");
            if (talk)
                speak("Il n'y a rien autour de moi.");
        }
        else {
            StringBuilder element_names = new StringBuilder();
            ArrayList<GameEntity> listOfElements = gameMap.getDistrictByPosition(hero.getPosition()).getElements();
            for(GameEntity element : listOfElements){
                MapgestureByElement.put(element,availableGesture[indexGesture]);
                indexGesture = indexGesture + 1;

                element_names.append(element.getName()).append("    ");
                if(talk){
                    if(!element.isVisited()){
                        if (firstElement) {
                            if (talk)
                                speak("Autour de moi il y a " + element.getFullName());
                            firstElement = false;
                        }
                        else{
                            if (talk)
                                speak(", Ainsi qu'" + element.getFullName());
                        }
                        sayHowToInteract(element);
                    }
                }
            }
            elements.setText(element_names.toString());

            if(modifyCURRENT_ACTION){
                CURRENT_ACTION = getResources().getString(R.string.action_interact);
            }
        }
        if (talk)
            speak(R.string.mode_deplacement);
    }

    private void sayHowToInteract(GameEntity element){
        String type = element.getClass().getSimpleName();

        switch (type){
            case "Building":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(element.equals(entry.getKey())){
                        speak(" pour visiter " + element.getNameWithPronoun() + ", faite un " + entry.getValue());
                    }
                }
                break;

            case "Vehicule":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(element.equals(entry.getKey())){
                        speak("Pour essayer de la démarrer, faite un " + entry.getValue());
                    }
                }
                break;

            case "Ennemy":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(element.equals(entry.getKey())){
                        speak("pour combattre, faite un " + entry.getValue());
                    }
                }
                break;

            case "Merchant":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(element.equals(entry.getKey())){
                        speak("pour echanger avec " + element.getName() + " faite un " + entry.getValue());
                    }
                }
                break;
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
                break;
            }
        }

        // en fonction du type de l'element, on intéragit d'une certaine façon
        if (entity == null)
            return;
        String type = entity.getType();

        switch (type){
            case "building":
                // recupérer tout ce qu'il y a dans le building et passer le state à true
                Construction building = (Construction) entity;
                if(!building.isVisited()){

                    int trousse_de_soins = 0;
                    boolean item_found = false;
                    for (Item item : building.getInventory()){
                        if (rollTheDice("perception")) {
                            item_found = true;
                            if (item.getName().equals("trousse de soin"))
                                trousse_de_soins++;
                            else
                                speak("Vous avez trouvé " + item.getFullName() + ".");
                            hero.addToInventory(item);
                        }
                    }
                    if (trousse_de_soins > 0)
                        speak("Vous avez trouvé " + ((trousse_de_soins == 1) ? "une" : trousse_de_soins) + " trousse de soins.");
                    if (!item_found)
                        speak("Vous n'avez rien trouvé dans ce bâtiment.");
                    building.setVisited(true);
                }
                else {
                    speak(building.getNameWithPronoun() + " a déjà été visité.");
                }
                break;

            case "vehicule":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(entity.equals(entry.getKey())){
                        if(!this.hero.hasToolbox()){
                            speak(R.string.cannot_use_car);
                        }
                    }
                }
                break;

            case "ennemy":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(entity.equals(entry.getKey())){
                        if(!entity.isVisited()){
                            Ennemy ennemy = (Ennemy) entity;
                            combatMode(ennemy);
                        }
                        else {
                            speak("aucun monstre dans les parages");
                        }
                    }
                }
                break;

            case "merchant":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if (entity.equals(entry.getKey())) {
                        speak("Voyons ce qui nous intéresse");
                        NonFightingChar merchant = (NonFightingChar) entity;
                        buyingMode(merchant);
                    }
                }
            case "neutralChar":
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(entity.equals(entry.getKey())){
                        if(!entity.isVisited()){
                            speak("Wassup");
                        }
                        else {
                            speak("Wassup the sequel");
                        }
                    }
                }
                break;
        }
    }

    private void combatMode(Ennemy ennemy) {
        Hero hero = this.hero;

        // Fight to the death
        while (hero.getHealth_points() > 0 && ennemy.getHealth_points() > 0) {
            // Hero attacks first
            ennemy.loseHealth_points(hero.getReal_damages());
            // Ennemy attacks second
            hero.loseHealth_points(ennemy.getBasic_damages());
        }

        // Fight results
        if (ennemy.getHealth_points() <= 0) {
            speak("You won the fight");
        } else {
            speak ("You lose the fight");
        }
    }

    private void buyingMode(NonFightingChar merchant) {
        Hero hero = this.hero;

        Misc money = new Misc("Money", new int[] {0});
        Consumable pills = new Consumable("Pills", new int[] {3,5}, new int[] {0});
        for (Item item : hero.getInventory()) {
            if (item.equals(money)) {
                hero.removeFromInventory(item);
                hero.addToInventory(pills);
            }
        }
    }


    private boolean rollTheDice(String aptitude){
        Random r = new Random();
        int skill;
        switch (aptitude){
            case "force":
                skill = this.hero.getForce();
                break;
            case "perception":
                skill = this.hero.getPerception();
                break;
            case "craft":
                skill = this.hero.getCraft();
                break;
            case "connaissance":
                skill = this.hero.getConnaissance();
                break;
            default:
                return false;
        }
        int result = r.nextInt(20) + 1;
        return (result <= skill);
    }






    /******* GESTURE METHODS IMPLEMENTATION *******/

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        //refreshDirection();
        //verifyElementAround(false, false);
        return super.onTouchEvent(event);
    }
    // the onSingleTapConfirmed is currently used for refreshing the position and describing the environnement again
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        if (!this.canDetectEvent) {
            mTTS.stop();               /// Pour couper le dialogue avec un clic lors des tests
            this.canDetectEvent = true;     /// idem
            return false;
        }
        verifyElementAround(true, true);
        return false;
    }
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        if (!this.canDetectEvent)
            return false;
        return false;
    }
    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        if (!this.canDetectEvent)
            return false;
        return false;
    }
    //not used
    @Override
    public boolean onDown(MotionEvent e) {
        if (!this.canDetectEvent)
            return false;
        return false;
    }
    //not used
    @Override
    public void onShowPress(MotionEvent e) {
        //if (!this.canDetectEvent)
            //return;
    }
    //not used
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if (!this.canDetectEvent)
            return false;
        return false;
    }
    //not used
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
        Toast.makeText(getApplicationContext(), "mode déplacement", Toast.LENGTH_SHORT).show();
        refreshDirection();
        CURRENT_ACTION = getResources().getString(R.string.action_move);
        if ((hero.getPosition()[0] + hero.getPosition()[1]) == 0) {
            speak("Faites un fling dans une direction pour vous déplacer.");
            speak(R.string.mode_interaction);
        }
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (!this.canDetectEvent)
            return false;
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
                    describeDistrict();
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
                    describeDistrict();
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
                    describeDistrict();
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
                    describeDistrict();
                }
                break;

            case "interaction":
//                speak("interaction activée");
                actionByElement("Fling droit");
//                CURRENT_ACTION = getResources().getString(R.string.action_move);
                break;
        }
    }

    private boolean verifyMoveDirection(String fling){
        if (!this.canDetectEvent)
            return false;
        boolean res = false;

        for (String direction : district.getPossibleDirection()){
            if(direction.equals(fling)){
                res = true;
            }
        }

        return res;
    }






    /****** Fonctions de dialogues *******/

    private void speak(String txt) {
        this.canDetectEvent = false;
        mTTS.setPitch(1);
        mTTS.setSpeechRate(1);
        mTTS.speak(txt, TextToSpeech.QUEUE_ADD,null, txt);
        new Thread(new Runnable() {
            @Override
            public void run() {
                waitUntilSpeakEnds();
            }
        }).start();
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
}
