package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.logic.GameState;

public class ViewGame extends ScreenAdapter {

    private GameState gameState;

    private GameAdHunter game;
    private Stage stage;
    private Sound tapSFX;

    private boolean loaded = true;

    public ViewGame(GameAdHunter game){
        gameState = new GameState();
        
        this.game = game;
        this.stage = new Stage();

        loadAssets();
        Gdx.input.setInputProcessor(stage);

    }

    public void fillStage(){
        Drawable upTex = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("buttons/bg_red_up.png", Texture.class)));
        Drawable downTex = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("buttons/bg_red_down.png", Texture.class)));
        ImageButton settingsButton = new ImageButton(upTex, downTex);

        settingsButton.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                tapSFX.play();
                return true;
            }

        });

        stage.addActor(settingsButton);
    }

    public void loadAssets(){
        game.getAssetManager().load("buttons/bg_red_up.png", Texture.class);
        game.getAssetManager().load("buttons/bg_red_down.png", Texture.class);

        tapSFX = Gdx.audio.newSound(Gdx.files.internal("sfx/button_press.mp3"));
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 1, 0, 1); //Background color.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Gdx.app.log("tag", Float.toString(game.getAssetManager().getProgress()));

        if (game.getAssetManager().getProgress() == 1 && loaded){
            fillStage();
            loaded = false;
        }

        game.getAssetManager().update();

        game.getBatch().begin();
        stage.act();
        stage.draw();
        game.getBatch().end();
    }

}
