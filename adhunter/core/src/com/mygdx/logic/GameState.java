package com.mygdx.logic;

import java.util.ArrayList;
import java.util.Random;

public class GameState {

    private ArrayList<Level> levels = new ArrayList<Level> ();
    private Level currentLevel;
    public ArrayList<Button> levelButtons = new ArrayList<Button>();

    private int lives;

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

    public void nextLevel(){
        currentLevel = levels.get(currentLevel.getIndex());
    }

    public void takeLife(){
        lives--;
    }

    public Level getCurrentLevel() {return currentLevel;}

    public void manageTap(Button button){
        if (button.getAction()){
            System.out.println("Uh oh, you lose one life");
        }

    }

    public ArrayList<Button> getLevelButtons() {
        return  levelButtons;
    }

}
