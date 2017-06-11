package com.mygdx.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.Random;

/**
 * Class containing all information regarding the buttons
 *
 * @author Miguel Mano Fernandes and José Borges
 * @version 1.0
 *
 */

public class Button {

    private boolean press;
    private ImageButton imgBtn;

    private Color[] tints;
    private Texture bgUp, bgDown;

    /**
     * Button constructor
     */

    public Button(){
        bgUp = new Texture(Gdx.files.internal("buttons/button_up.png"));
        bgDown = new Texture(Gdx.files.internal("buttons/button_down.png"));
        tints = new Color[50];
        fillTints();
        generate();
    }

    /**
     * Fills all different tints used in the buttons
     */

    public void fillTints(){
        for (int i = 0; i < 50; i++) {
            Random rnd = new Random();
            tints[i] = new Color(rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat(), 1);
        }
    }

    /**
     * Sets an action for a button (whether to press or avoid it)
     *
     * @param gameState
     *      Current gameState
     */
    public void setAction(GameState gameState) {
        if (gameState.getCurrentLevel().getAction() == "AVOlD")
            press = false;
        if (gameState.getCurrentLevel().getAction() == "PRESS")
            press = true;
    }

    /**
     * Sets the opposite action of the pregame buttons
     *
     * @param gameState
     *      Current gameState
     */

    public void setGameButtonAction(GameState gameState) {
        if (gameState.getCurrentLevel().getAction() == "AVOlD")
            press = true;
        if (gameState.getCurrentLevel().getAction() == "PRESS")
            press = false;
    }

    /**
     * Generates an ImageButton with two sprites
     */

    public void generate(){
        ImageButton.ImageButtonStyle btnStyle = new ImageButton.ImageButtonStyle();

        Sprite sBgUp = new Sprite(bgUp), sBgDown = new Sprite(bgDown);

        SpriteBatch spriteBatch = new SpriteBatch();
        spriteBatch.begin();
        sBgUp.draw(spriteBatch);
        spriteBatch.end();

        Random rnd = new Random();
        int index = rnd.nextInt(6);

        sBgUp.setColor(tints[index]);
        sBgDown.setColor(tints[index]);


        Drawable dBgUp = new SpriteDrawable(sBgUp);
        Drawable dBgDown = new SpriteDrawable(sBgDown);

        btnStyle.imageUp = dBgUp;
        btnStyle.imageDown = dBgDown;

        imgBtn = new ImageButton(btnStyle);
    }

    /**
     * Sets a button's size
     *
     * @param width
     *      Width of the button
     * @param height
     *      Height of the button
     * @return
     *      Returns true if the width and height are correctly used, ie the same size
     */
    public boolean setButtonSize(float width, float height) {
        if (width != height) return false;
        imgBtn.setSize(width, height);
        return true;
    }

    /**
     * Sets a button's position
     *
     * @param x
     *      X coordinate of the button
     * @param y
     *      Y coordinate of the button
     */

    public void setButtonPos(float x, float y) {
        imgBtn.setPosition(x,y);
    }

    public boolean getAction(){
        return this.press;
    }
    public ImageButton getImgBtn(){
        return this.imgBtn;
    }
    public float getButtonWidth() {
        return imgBtn.getWidth();
    }
    public float getButtonHeight() {
        return imgBtn.getHeight();
    }
    public Color[] getTints() {return tints;}

}