package com.mygdx.logic;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Level {

    public ArrayList<Button> buttons = new ArrayList<Button>();

    private float timeLeft;
    private int index, steps, stage; //steps is the total number of stages

    private String action;

    public Level(int index){

        this.index = index;
        stage = 1;

        if (index >= 1 && index <= 10) timeLeft = 15f;
        if (index >= 11 && index <= 20) timeLeft = 12f;
        if (index >= 21 && index <= 30) timeLeft = 8f;
        if (index >= 31) timeLeft = 5f;

        if (index % 2 == 0) steps = 5;
        else steps = 3;

        if (index % 10 == 0){ //Boss level.
            timeLeft = 10f;
            steps = 1;
        }

        //Generates a new action (50% chance for each).
        Random rnd = new Random();
        if (rnd.nextInt(2) == 0) action = "PRESS";
        else action = "AVOlD";

    }

    public int numButtons() {
        if (index % 2 != 0 && index < 25)
            return ((index+1)/2 + 1);
        return (index/2 + 1);
    }

    public int getIndex(){
        return index;
    }

    public int getSteps() { return steps; }

    public void nextStage(){
        stage++;
    }

    public void resetStage() {
        stage = 1;
    }

    public int getStage() { return stage; }

    public String getAction(){return action;}

}
