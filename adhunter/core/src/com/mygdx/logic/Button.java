package com.mygdx.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import java.util.Random;

public class Button extends Actor {

    boolean action;
    private Color[] tints;
    private Texture bgUp, bgDown, outUp, outDown;

    private Sprite sBgUp, sBgDown;
    private Sprite activeBG, activeOutline;

    public Button(){
        this.tints = new Color[6];
        loadAssets();

        addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                activeBG = sBgDown;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                activeBG = sBgUp;
            }

        });
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(activeBG, this.getX(), this.getY());
        //batch.draw(activeOutline, this.getX(), this.getY());
    }

    public void loadAssets(){
        bgUp = new Texture(Gdx.files.internal("buttons/button_up.png"));
        bgDown = new Texture(Gdx.files.internal("buttons/button_down.png"));
        outUp = new Texture(Gdx.files.internal("buttons/outline1_up.png"));
        //outDown = new Texture(Gdx.files.internal("buttons/outline2_up.png"));

        tints[0] = new Color(1, 0, 0, 1);
        tints[1] = new Color(0, 1, 0, 1);
        tints[2] = new Color(0, 0, 1, 1);
        tints[3] = new Color(1, 1, 0, 1);
        tints[4] = new Color(1, 0, 1, 1);
        tints[5] = new Color(0, 1, 1, 1);

        tintAssets();
    }

    public void tintAssets(){

        Random rnd = new Random();
        int index = rnd.nextInt(6);

        sBgUp = new Sprite(bgUp);
        sBgDown = new Sprite(bgDown);

        sBgUp.setColor(tints[index]);
        sBgDown.setColor(tints[index]);

        activeBG = sBgUp;
    }

    public void setAction(String action){
        this.action = (action == "PRESS") ? true : false;
    }

    public boolean getAction(){
        return this.action;
    }

}