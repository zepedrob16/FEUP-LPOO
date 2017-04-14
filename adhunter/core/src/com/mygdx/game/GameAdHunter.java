package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

public class GameAdHunter extends Game {

    private AssetManager assetManager;

    /**
     * Creates a new game and sets the current screen.
     */
    @Override
    public void create(){
        assetManager = new AssetManager();
        setScreen(new ScreenMainMenu(this));
    }

    /**
     * Returns the asset manager.
     * @return The asset manager.
     */
    AssetManager getAssetManager(){
        return assetManager;
    }

}
