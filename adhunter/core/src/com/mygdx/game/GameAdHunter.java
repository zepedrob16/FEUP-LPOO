package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import com.mygdx.logic.GameState;
import com.mygdx.logic.PlayServices;

public class GameAdHunter extends Game {

    private AssetManager assetManager;
    private SpriteBatch batch;
    private Screen activeScreen;

    private static int SCREEN_WIDTH, SCREEN_HEIGHT;

    public static PlayServices playServices; //Google Play Services

    public GameAdHunter(PlayServices playServices){
        this.playServices = playServices;
    }

    @Override
    public void create() {
        SCREEN_WIDTH = Gdx.app.getGraphics().getWidth();
        SCREEN_HEIGHT = Gdx.app.getGraphics().getHeight();

        assetManager = new AssetManager();
        batch = new SpriteBatch();

        this.loadFonts();
        this.loadAssets();
    }

    public void loadFonts(){
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreeTypeFontLoaderParameter medium = new FreeTypeFontLoaderParameter();
        medium.fontFileName = "font/whitney-medium.ttf";
        medium.fontParameters.size = 80;
        medium.fontParameters.color = Color.BLACK;
        assetManager.load("font/whitney-medium.ttf", BitmapFont.class, medium);

        FreeTypeFontLoaderParameter bold = new FreeTypeFontLoaderParameter();
        bold.fontFileName = "font/whitney-bold.ttf";
        bold.fontParameters.size = 190; //Do not exceed 200, it WILL cause chars to become invisible.
        bold.fontParameters.color = Color.BLACK;
        assetManager.load("font/whitney-bold.ttf", BitmapFont.class, bold);
    }

    public void loadAssets(){

        //Textures.
        assetManager.load("menu-background.png", Texture.class);
        assetManager.load("buttons/achievements_icon.png", Texture.class);
        assetManager.load("buttons/leaderboards_icon.png", Texture.class);
        assetManager.load("buttons/settings_icon.png", Texture.class);
        assetManager.load("buttons/about_icon.png", Texture.class);
        assetManager.load("buttons/help_icon.png", Texture.class);

        //Music & SFX.
        assetManager.load("sfx/main_music_1.mp3", Music.class);
        assetManager.load("sfx/game_music_1.mp3", Music.class);
        assetManager.load("sfx/button_press.mp3", Sound.class);
        assetManager.load("sfx/windows_critical.mp3", Sound.class);

        //Game assets.
        assetManager.load("data/life_full.png", Texture.class);
        assetManager.load("data/life_empty.png", Texture.class);
        assetManager.load("data/single_ad.png", Texture.class);
        assetManager.load("buttons/button_up.png", Texture.class);
        assetManager.load("buttons/button_down.png", Texture.class);
    }

    @Override
    public void render(){
        super.render();

        if (assetManager.getProgress() == 1) return;

        if (assetManager.update()){
            this.activeScreen = new ViewMenu(this);
            setScreen(activeScreen);
        }

    }

    @Override
    public void pause(){
        if (activeScreen != null) activeScreen.pause();
    }

    @Override
    public void resume(){
        if (activeScreen != null) activeScreen.resume();
    }

    @Override
    public void resize(int width, int height){

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

    public static int getSCREEN_WIDTH(){
        return SCREEN_WIDTH;
    }

    public static int getSCREEN_HEIGHT(){
        return SCREEN_HEIGHT;
    }

    public PlayServices getPlayServices() { return playServices;}

}
