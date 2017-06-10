package com.mygdx.logic;

import java.util.ArrayList;
import java.util.Random;

public class GameState {

    //Singleton initialization.
    private static GameState instance = null;
    public static GameState getInstance(){
        if (instance == null) instance = new GameState();
        return instance;
    }

    //Level handling.
    private ArrayList<Level> levels = new ArrayList<Level> ();
    private Level currentLevel;

    //Game resources.
    private int lives = 3;
    private boolean all = false;
    public ArrayList<Button> levelButtons = new ArrayList<Button>(); //Buttons except pre-game
    public ArrayList<Button> preGameButtons = new ArrayList<Button>(); //Pre-game buttons

    private boolean reset = false;

    public GameState(){
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
        else {
            manageLevels();
            return true;
        }
    }
    public void manageLevels(){
        if (currentLevel.getIndex() % 10 == 0 && !all)
            return;
        else if (currentLevel.getStage() == currentLevel.getSteps()) {
                nextLevel();
                return;
            }
        else {
                currentLevel.nextStage();
                return;
            }
    }

    public int getLives() {
        return lives;
    }

    public void setResetFalse() {reset = false;}

    public void setAllTrue() {this.all = true;}

    public boolean getReset() { return reset; }

    public ArrayList<Level> getLevels() { return levels; }
}
