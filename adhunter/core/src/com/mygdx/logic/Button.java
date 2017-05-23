package com.mygdx.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import java.util.Random;

public class Button extends Actor {

    boolean action;
    private Texture backgroundUp, backgroundDown, activeBG;

    public Button(final boolean pressable){
        setColor(); //Randomizes the button color.
        this.activeBG = this.backgroundUp; //Default texture is up.

        if (!pressable) return; //Skip listeners if it isn't pressable.

        addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                activeBG = backgroundDown;
                GameState.getInstance().manageTap();
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                activeBG = backgroundUp;
            }
        });
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(this.activeBG, this.getX(), this.getY());
    }

    public void setColor(){
        Random rnd = new Random();

        switch(rnd.nextInt(6)){
            case 0:
                this.backgroundUp = new Texture(Gdx.files.internal("buttons/btn_up_red.png"));
                this.backgroundDown = new Texture(Gdx.files.internal("buttons/btn_down_red.png"));
                break;

            case 1:
                this.backgroundUp = new Texture(Gdx.files.internal("buttons/btn_up_yellow.png"));
                this.backgroundDown = new Texture(Gdx.files.internal("buttons/btn_down_yellow.png"));
                break;

            case 2:
                this.backgroundUp = new Texture(Gdx.files.internal("buttons/btn_up_green.png"));
                this.backgroundDown = new Texture(Gdx.files.internal("buttons/btn_down_green.png"));
                break;

            case 3:
                this.backgroundUp = new Texture(Gdx.files.internal("buttons/btn_up_blue.png"));
                this.backgroundDown = new Texture(Gdx.files.internal("buttons/btn_down_blue.png"));
                break;

            case 4:
                this.backgroundUp = new Texture(Gdx.files.internal("buttons/btn_up_purple.png"));
                this.backgroundDown = new Texture(Gdx.files.internal("buttons/btn_down_purple.png"));
                break;
        }
    }

    public void setAction(String action){
        this.action = (action == "PRESS") ? true : false;
    }

    public boolean getAction(){
        return this.action;
    }

}