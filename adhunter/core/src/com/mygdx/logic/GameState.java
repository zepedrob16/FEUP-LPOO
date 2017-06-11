package com.mygdx.logic;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class containing all information regarding the game state
 *
 * @author Miguel Mano Fernandes and José Borges
 * @version 1.0
 *
 */

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

    /**
     * GameState constructor
     */

    public GameState(){
        for (int i = 0; i < 999; i++){
            levels.add(new Level(i+1)); //Adds 999 levels.
        }
        currentLevel = levels.get(0); //Sets first level.
    }

    /**
     * Adds a button to a vector containing all level buttons
     *
     * @param button
     *  Button to be added
     */
    public void manageButtons(Button button) {
        levelButtons.add(button);
    }

    /**
     * Adds a button to a vector containing all pregame buttons
     *
     * @param button
     *  Button to be added
     */

    public void managePreGameButtons(Button button) { preGameButtons.add(button); }

    /**
     * Advances a level
     */

    public void nextLevel(){
        currentLevel = levels.get(currentLevel.getIndex());
        currentLevel.resetStage();
        reset = true;
    }

    /**
     * Removes 1 life
     */

    public void takeLife(){
        this.lives--;
    }

    public Level getCurrentLevel() {return currentLevel;}

    /**
     * Manages a tap to a specific button. Returns false if the button was not supposed to be clicked
     *
     * @param btn
     *  Button that was pressed
     * @return
     *  True or false depending on whether the click was correct or not
     */

    public boolean manageTap(Button btn){
        if (!btn.getAction()) {
            Gdx.input.vibrate(200);
            takeLife();
            return false;
        }
        else {
            manageLevels();
            return true;
        }
    }

    /**
     * Decides whether to advance a level or a stage
     */
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
