package com.mygdx.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

/*
public class Button {

    private Color[] tints;
    private Texture up, down;
    private ImageButton imageButton;

    public Button(){
        fillTints();
        generate();
    }

    public void fillTints() {
        tints[0] = new Color(1, 0, 0, 1);
        tints[1] = new Color(0, 1, 0, 1);
        tints[2] = new Color(0, 0, 1, 1);
        tints[3] = new Color(1, 1, 0, 1);
        tints[4] = new Color(0, 1, 1, 1);
        tints[5] = new Color(1, 0, 1, 1);
    }

    public void generate(){

        Drawable btnUp = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("assets/buttons/button_up.png"))));
        Drawable btnDown = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("assets/buttons/button_down.png"))));
        ImageButton imageButton = new ImageButton(btnUp, btnDown);
    }

    public ImageButton getImageButton(){
        return imageButton;
    }
}
*/

public class Button extends Actor {

    boolean press;
    private Texture activeBG, backgroundUp, backgroundDown;

    public Button(final boolean pressable, float width, float height) {
        setColor(); //Randomizes the button color.
        this.setSize(width, height);

        if (!pressable) return; //Skip listeners if it isn't pressable.

        addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //GameState.getInstance().manageTap(this);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
        });
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(this.activeBG, this.getX(), this.getY());
    }

    public void setColor() {
        Random rnd = new Random();

        switch (rnd.nextInt(6)) {
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

    public void setAction(String action) {
        this.press = action.equals("PRESS");
    }

    public void setGameButtonAction(String action) {
        this.press = !action.equals("PRESS");
    }

    public boolean getAction() {
        return this.press;
    }
}


