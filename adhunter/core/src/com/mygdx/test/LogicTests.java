package com.mygdx.test;
import com.mygdx.logic.Button;
import com.mygdx.logic.GameState;

import org.testng.annotations.Test;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LogicTests {

    @org.junit.Test
    public void testsLives(){
        assertEquals(GameState.getInstance().getLives(), 3);

        GameState.getInstance().takeLife();
        assertEquals(GameState.getInstance().getLives(), 2);
    }

    @org.junit.Test
    public void testsLevelCreationAndInit(){
        assertEquals(GameState.getInstance().getCurrentLevel().getIndex(), 1);
        assertEquals(GameState.getInstance().getLevels().size(), 999);
    }

    @org.junit.Test
    public void testsBossBattles(){

        for (int i = 0; i < 999; i++){
            if (GameState.getInstance().getLevels().get(i).getIndex() % 10 == 0){
                assertEquals(GameState.getInstance().getLevels().get(i).getSteps(), 1);
            }
        }
    }

    @org.junit.Test(timeout=3000)
    public void testRandomLevelAction(){

        int avoids = 0, presses = 0;

        for (int i = 0; i < 999; i++){
            if (GameState.getInstance().getLevels().get(i).getAction() == "PRESS") presses++;
            else avoids++;
        }
        float percentageAvoids = avoids/10, percentagePresses = presses/10;
        assertEquals(percentageAvoids, percentagePresses, 10); //10 of tolerance.
    }



}
