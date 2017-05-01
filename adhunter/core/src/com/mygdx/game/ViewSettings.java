package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ViewSettings extends ScreenAdapter {

    GameAdHunter game;
    Stage stage;

    private boolean assetsLoaded = false;

    public ViewSettings(GameAdHunter game) {
        this.game = game;
        this.stage = new Stage();

        this.resize(300, 300);

        loadAssets();
        Gdx.input.setInputProcessor(stage);
    }

    public void loadAssets(){
        game.getAssetManager().load("data/pop_up.png", Texture.class);
    }

    public void fillStage(){
        Drawable menu = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("data/pop_up.png", Texture.class)));
        ImageButton menuBtn = new ImageButton(menu);
        menuBtn.setPosition(game.getSCREEN_WIDTH()/2 - menuBtn.getWidth()/2, game.getSCREEN_HEIGHT()/2 - menuBtn.getHeight()/2);
        stage.addActor(menuBtn);
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 1, 1); //Background color.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!assetsLoaded && game.getAssetManager().update()){
            assetsLoaded = true;
            fillStage();
        }
        if (!assetsLoaded) return;

        game.getBatch().begin();

        stage.act();
        stage.draw();

        game.getBatch().end();

    }
}
