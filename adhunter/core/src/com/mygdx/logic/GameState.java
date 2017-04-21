package com.mygdx.logic;

import java.util.ArrayList;

public class GameState {

    private ArrayList<Level> levels = new ArrayList<Level> ();
    private Level currentLevel;

    private int lives;

    public GameState(){
        lives = 3;

        for (int i = 0; i < 999; i++){
            levels.add(new Level(i+1)); //Adds 999 levels.
        }
        currentLevel = levels.get(0); //Sets first level.
    }

    public void nextLevel(){
        currentLevel = levels.get(currentLevel.getIndex());
    }
    public void takeLife(){
        lives--;
    }



}
