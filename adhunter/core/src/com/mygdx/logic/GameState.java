package com.mygdx.logic;

import java.util.ArrayList;
import java.util.Random;

public class GameState {

    private static GameState instance = null; //Creates the singleton.
    public static GameState getInstance(){
        if (instance == null) instance = new GameState(); //If singleton doesn't exist, create it.
        return instance;
    }

    private ArrayList<Level> levels = new ArrayList<Level> ();
    private Level currentLevel;
    public ArrayList<Button> levelButtons = new ArrayList<Button>(); //Buttons except pre-game
    public ArrayList<Button> preGameButtons = new ArrayList<Button>(); //Pre-game buttons
    private PlayServices playServices;

    private int lives;

    private boolean reset = false;

    public GameState(){
        lives = 3;

        for (int i = 0; i < 999; i++){
            levels.add(new Level(i+1)); //Adds 999 levels.
        }
        currentLevel = levels.get(0); //Sets first level.
    }

    public void manageButtons(Button button) {
        levelButtons.add(button);
    }

    public void managePreGameButtons(Button button) { preGameButtons.add(button); }

    public void nextLevel(){
        currentLevel = levels.get(currentLevel.getIndex());
        currentLevel.resetStage();
        reset = true;
    }

    public void takeLife(){
        this.lives--;
    }

    public Level getCurrentLevel() {return currentLevel;}

    public boolean manageTap(Button btn){
        if (!btn.getAction()) {
            takeLife();
            return false;
        }
        else if (btn.getAction()){
            if (currentLevel.getStage() == currentLevel.getSteps()) {
                nextLevel();
                return true;
            }
            else {
                currentLevel.nextStage();
                return true;
            }
        }
        return false;
    }

    public int getLives() {
        return lives;
    }

    public void setResetFalse() {reset = false; return;}

    public boolean getReset() { return reset; }
}
