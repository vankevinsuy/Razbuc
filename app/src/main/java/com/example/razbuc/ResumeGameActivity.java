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

import com.example.razbuc.Enumerations.ConstructionType;
import com.example.razbuc.Enumerations.ElementType;
import com.example.razbuc.Enumerations.ItemType;
import com.example.razbuc.characters.FightingChar;
import com.example.razbuc.characters.NonFightingChar;
import com.example.razbuc.characters.fightingType.AgressiveGranny;
import com.example.razbuc.characters.fightingType.Ennemy;
import com.example.razbuc.characters.fightingType.Hero;
import com.example.razbuc.characters.fightingType.heroType.Artificer;
import com.example.razbuc.characters.fightingType.heroType.Explorer;
import com.example.razbuc.characters.fightingType.heroType.Medic;
import com.example.razbuc.characters.fightingType.heroType.SergeantMajor;
import com.example.razbuc.characters.nonFightingType.Merchant;
import com.example.razbuc.characters.nonFightingType.NeutralChar;
import com.example.razbuc.items.Consumable;
import com.example.razbuc.items.Item;
import com.example.razbuc.items.Misc;
import com.example.razbuc.items.PaperMap;
import com.example.razbuc.items.Toolbox;
import com.example.razbuc.items.Weapon;
import com.example.razbuc.location.Construction;
import com.example.razbuc.location.District;
import com.example.razbuc.location.GameMap;
import com.example.razbuc.location.Vehicule;
import com.example.razbuc.location.constructionType.Building;

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

    private boolean STORY_END = false;


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
                    this.hero=new SergeantMajor();
                    break;
                case "Médecin":
                    this.hero=new Medic();
                    break;
                case "Explorateur":
                    this.hero=new Explorer();
                    break;
            }
            speak(R.string.story);
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
        CURRENT_ACTION = getResources().getString(R.string.action_interact);
        District district = gameMap.getDistrictByPosition(hero.getPosition());
        if (district.isVisited())
            verifyElementAround(true, true);
        else{
            textByDistrict(district, false);
            district.setVisited(true);
            verifyElementAround(false, false);
        }
    }
    public void textByDistrict(District district, boolean victory){
        switch(district.getId()){
            case 1:
                speak(R.string.map1);
                break;
            case 2:
                speak(R.string.map2);
                break;
            case 3:
                if (victory)
                    speak(R.string.map3_victory);
                else
                    speak(R.string.map3);
                break;
            case 4:
                if (victory)
                    speak(R.string.map4_success);
                else
                    speak(R.string.map4);
                break;
            case 5:
                speak(R.string.map5);
                break;
            case 6:
                speak(R.string.map6);
                break;
            case 7:
                speak(R.string.map7);
                break;
            case 8:
                speak(R.string.map8);
                break;
            case 9:
                if (victory)
                    speak(R.string.map9_success);
                else
                    speak(R.string.map9);
                break;
            default: speak("Texte non implémenté");
        }
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
                if (element.getType() == ElementType.Ennemy && !element.isVisited()){
                    if (!hero.hasVehicule()) {
                        Ennemy ennemy = (Ennemy) element;
                        combatMode(ennemy);
                        ennemy.setVisited(true);
                    }
                    else{
                        speak(R.string.crush_ennemy);
                        element.setVisited(true);
                        textByDistrict(gameMap.getDistrictByPosition(hero.getPosition()), true);
                    }
                }
                if(talk){
                    if(!element.isVisited()){
                        if (firstElement) {
                            speak("Autour de moi il y a " + element.getFullName());
                            firstElement = false;
                        }
                        else
                            speak(", Ainsi qu'" + element.getFullName());
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
        ElementType type = element.getType();

        switch (type){
            case Construction:
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(element.equals(entry.getKey())){
                        if (((Construction)element).getConstructionType() == ConstructionType.Mur)
                            speak(" pour examiner le mur, faites un " + entry.getValue());
                        else
                            speak(" pour visiter " + element.getNameWithPronoun() + ", faite un " + entry.getValue());
                    }
                }
                break;

            case Vehicule:
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(element.equals(entry.getKey())){
                        speak("Pour essayer de la démarrer, faite un " + entry.getValue());
                    }
                }
                break;

            case Ennemy:
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(element.equals(entry.getKey())){
                        speak("pour combattre, faite un " + entry.getValue());
                    }
                }
                break;

            case Merchant:
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(element.equals(entry.getKey())){
                        speak("pour echanger avec " + element.getNameWithPronoun() + " faite un " + entry.getValue());
                    }
                }
                break;
            case PNJ:
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(element.equals(entry.getKey())){
                        speak("Pour parler avec " + element.getNameWithPronoun() + " faite un " + entry.getValue());
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
        ElementType type = entity.getType();

        switch (type){
            case Construction:
                // recupérer tout ce qu'il y a dans le building et passer le state à true
                Construction building = (Construction) entity;
                if(!building.isVisited()) {
                    switch (building.getConstructionType()) {
                        case Garage:
                            this.building_fight = building;
                            combatMode(new Ennemy("Razbuc", hero.getPosition(), 15, 2, 10, null, false));
                            break;
                        case Mur:
                            if (this.hero.hasVehicule()){
                                speak("Vous parvenez à détruire le mur.");
                                speak(R.string.mamie_mechante);
                                Ennemy ennemy = new AgressiveGranny("Mamie", new int[]{}, 20, 4, 12, null, true);
                                combatMode(ennemy);
                                this.STORY_END = true;
                            }
                            else{
                                speak(R.string.map9);
                            }
                            break;
                        default:
                            int trousse_de_soins = 0;
                            boolean item_found = false;
                            for (Item item : building.getInventory()) {
                                if (rollTheDice("perception", this.hero)) {
                                    item_found = true;
                                    if (item.getItemType() == ItemType.Consumable)
                                        trousse_de_soins++;
                                    else
                                        speak("Vous avez trouvé " + item.getFullName() + ".");
                                    if (item.getItemType() == ItemType.Weapon)
                                        hero.setWeapon((Weapon)item);
                                    else
                                        hero.addToInventory(item);
                                }
                            }
                            if (trousse_de_soins > 0)
                                speak("Vous avez trouvé " + ((trousse_de_soins == 1) ? "une" : trousse_de_soins) + " trousse de soins.");
                            if (!item_found)
                                speak("Vous n'avez rien trouvé dans ce bâtiment.");
                            building.setVisited(true);
                            break;
                    }
                }
                else {
                    speak(building.getNameWithPronoun() + " a déjà été visité.");
                }
                break;

            case Vehicule:
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(entity.equals(entry.getKey())){
                        if(!this.hero.hasMamie()){
                            speak(R.string.cannot_use_car);
                        }
                        else{
                            speak(R.string.use_car);
                            this.hero.setVehicule((Vehicule)entity);
                            entity.setVisited(true);
                        }
                    }
                }
                break;

            case Merchant:
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if (entity.equals(entry.getKey())) {
                        speak(R.string.marchand);
                        Merchant merchant = (Merchant) entity;
                        buyingMode(merchant);
                    }
                }
            case PNJ:
                for(Map.Entry<GameEntity, String> entry : MapgestureByElement.entrySet()) {
                    if(entity.equals(entry.getKey())){
                        dialogueMode((NeutralChar)entity);
                    }
                }
                break;
        }
    }

    private Construction building_fight = null;
    private Ennemy ennemy;
    private void combatMode(Ennemy ennemy) {
        CURRENT_ACTION = getResources().getString(R.string.action_combat);
        this.ennemy = ennemy;
        speak("Un combat est lancé contre " + this.ennemy.getNameWithPronoun());
        if (ennemy.isInitiative()){
            speak(R.string.initiative);
            ennemyAttack();
        }
        speak("Pour attaquer, double-cliquez.");
    }
    Random r = new Random();
    private void ennemyAttack(){
        if (this.ennemy.getName().equals("Mamie")){
            String[] ennemyAttack = getResources().getStringArray(R.array.granny_attack);
            speak(ennemyAttack[r.nextInt(ennemyAttack.length)]);
        }
        else{
            String[] ennemyAttack = getResources().getStringArray(R.array.ennemy_attack);
            speak(ennemyAttack[r.nextInt(ennemyAttack.length)]);
        }

        boolean success = rollTheDice("force", this.ennemy);
        if (success){
            //String[] attack_success = getResources().getStringArray(R.array.ennemy_attack_success);
            //speak(attack_success[r.nextInt(attack_success.length)]);
            this.hero.loseHealth_points(this.ennemy.getReal_damages());
            checkCombatStatus();
            checkHeroHealth();
        }
        else{
            String[] attack_failure = getResources().getStringArray(R.array.ennemy_attack_failure);
            speak(attack_failure[r.nextInt(attack_failure.length)]);
        }
    }
    private void heroAttack(){
        if (this.hero.hasWeapon()){
            String[] attack = getResources().getStringArray(R.array.attack_with_weapon);
            speak(attack[r.nextInt(attack.length)]);
        }
        else {
            String[] attack = getResources().getStringArray(R.array.attack);
            speak(attack[r.nextInt(attack.length)]);
        }

        boolean success = rollTheDice("force", hero);
        boolean end = false;
        if (success){
            this.ennemy.loseHealth_points(hero.getReal_damages());
            end = checkCombatStatus();
            if (end)
                return;
            String[] attack_success = getResources().getStringArray(R.array.attack_success);
            speak(attack_success[r.nextInt(attack_success.length)]);
        }
        else{
            String[] attack_failure = getResources().getStringArray(R.array.attack_failure);
            speak(attack_failure[r.nextInt(attack_failure.length)]);
        }
        ennemyAttack();
    }
    public boolean checkCombatStatus(){
        if (this.hero.getHealth_points() <= 0){
            speak("Je suis désolé de vous annoncer que vous n'avez pas survécu à ce combat.");
            game_ended();
            return true;
        }
        else if (this.ennemy.getHealth_points() <= 0){
            if (this.building_fight != null){
                building_fight.setVisited(true);
                speak("Le razbuc s'écroule. Vous fouillez le garage et trouvez une boite à outils.");
                hero.addToInventory(building_fight.getInventory().get(0));
                this.building_fight = null;
            }
            else {
                if (STORY_END)
                    speak(R.string.end);
                else
                    textByDistrict(gameMap.getDistrictByPosition(hero.getPosition()), true);
            }
            if (STORY_END)
                game_ended();
            else
                CURRENT_ACTION = getResources().getString(R.string.action_interact);
            return true;
        }
        return false;
    }
    public void checkHeroHealth(){
        int health = hero.getHealth_points();
        if (health == 20){
            speak("Vous êtes en parfaite santé");
        }
        else if(health >= 15){
            speak("Vous êtes un peu blessé.");
        }
        else if(health >= 10){
            speak("Vous ne vous sentez pas très bien.");
        }
        else if (health >= 5){
            speak("Vous êtes très blessé.");
            speak("Vous pouvez utiliser une trousse de soins avec un fling gauche.");
        }
        else{
            speak("Le prochain coup risque de vous être fatal.");
            speak("Vous pouvez utiliser une trousse de soins avec un fling gauche.");
        }
    }

    public void healPlayer(){
        if (hero.getHealth_points() == 20){
            speak("Vous n'avez pas besoins de vous soigner, vous êtes déjà en parfaites santé.");
        }
        else if (!hero.hasCareKit()){
            speak("Vous n'avez aucune trousse de soins sur vous.");
        }
        else{
            hero.useCareKit();
            speak("Vous venez d'utiliser une trousse de soins.");
            checkHeroHealth();
        }
    }





    private Merchant merchant;
    private void buyingMode(Merchant merchant) {
        this.merchant = merchant;
        CURRENT_ACTION = getResources().getString(R.string.action_commercer);
        speak(R.string.marchand_interaction);
    }
    private void buyingMode_fling(String fling){
        int number = this.hero.getNumberOfCareKit();
        switch (fling){
            case "Fling haut":
                CURRENT_ACTION = getResources().getString(R.string.action_interact);
                speak("Rien ne vous zinteresse, vous dites au revoir au marchand.");
                break;
            case "Fling droit":
                speak(this.merchant.flingDroit());
                speak("Vous avez " + (number == 1 ? "une" : number) + " trousses de soins.");
                break;
            case "Fling gauche":
                speak(this.merchant.flingGauche());
                speak("Vous avez " + (number == 1 ? "une" : number) + " trousses de soins.");
                break;
        }
    }
    public void buy(){
        int price = this.merchant.getPrice();
        int number = this.hero.getNumberOfCareKit();
        if (number < price){
            speak("Vous n'avez pas assez de trousse de soins pour acheter cela.");
            speak("Cet objet coute " + (price == 1 ? "une" : price) + " trousses de soins");
            speak("Vous n'avez que " + (number == 1 ? "une" : number) + " trousses de soins");
            return;
        }
        Item item = this.merchant.getItem();
        switch (item.getItemType()){
            case Weapon:
                if (this.hero.hasWeapon()){
                    speak("Vous possédez déjà une arme.");
                }
                else{
                    this.hero.setWeapon(new Weapon("Arme", 3, new int[]{}));
                    this.hero.removeCareKit(price);
                    number-= price;
                    speak("Vous avez acheté un pistolet.");
                    speak("Il vous reste " + (number == 1 ? "une" : number) + " trousses de soins");
                }
                break;
            case PaperMap:
                if (this.hero.hasPaperMap()){
                    speak("Vous possédez déjà une carte.");
                }
                else{
                    this.hero.addToInventory(new PaperMap("Carte", new int[]{}, new int[]{}));
                    this.hero.removeCareKit(price);
                    number-= price;
                    speak("Vous avez acheté une carte.");
                    speak("Il vous reste " + (number == 1 ? "une" : number) + " trousses de soins");
                }
                break;
            case Toolbox:
                if (this.hero.hasToolbox()){
                    speak("Vous possédez déjà une boite à outils.");
                }
                else{
                    this.hero.addToInventory(new Toolbox("Boite à outils", new int[]{}, new int[]{}));
                    this.hero.removeCareKit(price);
                    number-= price;
                    speak("Vous avez acheté une boite à outils.");
                    speak("Il vous reste " + (number == 1 ? "une" : number) + " trousses de soins");
                }
                break;
        }
        speak("Faites un fling droit ou gauche pour changer d'object.");
        speak(this.merchant.sayItemCost());
    }





    private NeutralChar pnj;
    private void dialogueMode(NeutralChar pnj){
        CURRENT_ACTION = getResources().getString(R.string.action_dialogue);
        this.pnj = pnj;
        pnj.resetDialog();
        speak(pnj.repeat());
    }
    private void answer(String fling){
        speak(pnj.answer(fling));
        if (pnj.isDialogEnded()){
            speak(pnj.endDialog(this.hero));
            speak("La conversation est terminée.");
            CURRENT_ACTION = getResources().getString(R.string.action_interact);
        }
    }


    private boolean rollTheDice(String aptitude, FightingChar character){
        Random r = new Random();
        int skill;
        switch (aptitude){
            case "force":
                skill = character.getForce();
                break;
            case "perception":
                skill = character.getPerception();
                break;
            case "craft":
                skill = character.getCraft();
                break;
            case "connaissance":
                skill = character.getConnaissance();
                break;
            default:
                return false;
        }
        int result = r.nextInt(20) + 1;
        return (result <= skill);
    }




    public void game_ended(){
        CURRENT_ACTION = getResources().getString(R.string.action_end);
        speak(R.string.game_finished);
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
        switch (CURRENT_ACTION){
            case "interaction":
            case "move":
                verifyElementAround(true, true);
                checkHeroHealth();
                break;
            case "dialogue":
                speak(this.pnj.repeat());
                break;
            case "combat":
                checkHeroHealth();
                speak("Double-cliquez pour attaquez.");
                break;
            case "commerce":
                int number = hero.getNumberOfCareKit();
                speak(R.string.marchand_interaction);
                speak(this.merchant.sayItemCost());
                speak("Vous avez " + (number == 1 ? "une" : number) + " trousses de soins.");
                break;
        }
        return false;
    }
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        if (!this.canDetectEvent)
            return false;
        switch (CURRENT_ACTION){
            case "combat":
                heroAttack();
                break;
            case "commerce":
                buy();
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
        if (!CURRENT_ACTION.equals(getResources().getString(R.string.action_combat))) {
            Toast.makeText(getApplicationContext(), "mode déplacement", Toast.LENGTH_SHORT).show();
            refreshDirection();
            CURRENT_ACTION = getResources().getString(R.string.action_move);
            if ((hero.getPosition()[0] + hero.getPosition()[1]) == 0) {
                speak("Faites un fling dans une direction pour vous déplacer.");
                speak(R.string.mode_interaction);
            }
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
                actionByElement("Fling haut");
                break;
            case "commerce":
                buyingMode_fling("Fling haut");
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
                actionByElement("Fling bas");
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
            case "combat":
                healPlayer();
                break;
            case "dialogue":
                answer("Fling gauche");
                break;
            case "commerce":
                buyingMode_fling("Fling gauche");
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
                actionByElement("Fling droit");
                break;
            case "dialogue":
                answer("Fling droit");
                break;
            case "commerce":
                buyingMode_fling("Fling droit");
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
