package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.logic.GameState;

public class GameAdHunter extends Game {
    
    private AssetManager assetManager;
    private SpriteBatch batch;

    private Screen activeScreen;

    private int SCREEN_WIDTH, SCREEN_HEIGHT;

    //Font
    BitmapFont robotoFont;

    @Override
    public void create() {
        SCREEN_WIDTH = Gdx.app.getGraphics().getWidth();
        SCREEN_HEIGHT = Gdx.app.getGraphics().getHeight();

        assetManager = new AssetManager();
        batch = new SpriteBatch();

        this.activeScreen = new ViewMenu(this);
        setScreen(activeScreen);
    }

    @Override
    public void render(){
        super.render();
    }

    @Override
    public void pause(){
        activeScreen.pause();
    }

    @Override
    public void resume(){
        activeScreen.resume();
    }


    @Override
    public void dispose(){
        assetManager.dispose();
        batch.dispose();
        activeScreen.dispose();
    }

    public AssetManager getAssetManager(){
        return assetManager;
    }

    public SpriteBatch getBatch(){
        return batch;
    }

    public int getSCREEN_WIDTH(){
        return SCREEN_WIDTH;
    }

    public int getSCREEN_HEIGHT(){
        return SCREEN_HEIGHT;
    }

}
