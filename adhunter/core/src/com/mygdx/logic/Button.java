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

public class Button  {

    private boolean action;
    private ImageButton imgBtn;
    GameState gameState;

    private Color[] tints;
    private Texture bgUp, bgDown, outUp, outDown;

    public Button(){
        bgUp = new Texture(Gdx.files.internal("buttons/button_up.png"));
        bgDown = new Texture(Gdx.files.internal("buttons/button_down.png"));
        outUp = new Texture(Gdx.files.internal("buttons/outline1_up.png"));
        //outDown = new Texture(Gdx.files.internal("buttons/outline2_up.png"));
        tints = new Color[6];
        fillTints();
        generate();
    }

    public void fillTints(){
        tints[0] = new Color(1, 0, 0, 1);
        tints[1] = new Color(0, 1, 0, 1);
        tints[2] = new Color(0, 0, 1, 1);
        tints[3] = new Color(1, 1, 0, 1);
        tints[4] = new Color(1, 0, 1, 1);
        tints[5] = new Color(0, 1, 1, 1);
    }

    public void setAction(GameState gameState) {
        if (gameState.getCurrentLevel().getAction() == "AVOlD")
            action = true;
        if (gameState.getCurrentLevel().getAction() == "PRESS")
            action = false;
    }

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

    public void setButtonSize(float width, float height) {
        imgBtn.setSize(width, height);
    }

    public void setButtonPos(float x, float y) {
        imgBtn.setPosition(x,y);
    }

    public boolean getAction(){
        return this.action;
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

}
