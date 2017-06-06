package com.mygdx.test;
import com.mygdx.logic.Button;
import com.mygdx.logic.GameState;

import org.testng.annotations.Test;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ButtonTests {

    @Test
    public void testsTinting(){
        Button button = new Button();
        assertEquals(button.getTints().length, 50);
    }

    @Test
    public void testsSizeAndPosition(){
        Button button = new Button();

        assertEquals(button.setButtonSize(250, 250), true);
        assertEquals(button.getButtonHeight(), 250, 0.1);
        assertEquals(button.setButtonSize(250, 300), false);
        assertEquals(button.getButtonHeight(), 250, 0.1);
        assertEquals(button.getButtonWidth(), 250, 0.1);


    }

}
