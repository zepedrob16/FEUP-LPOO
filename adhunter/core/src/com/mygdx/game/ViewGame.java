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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.logic.GameState;

import java.util.Random;

public class ViewGame extends ScreenAdapter {

    private GameState gameState;

    private GameAdHunter game;
    private Stage stage;
    private Sound tapSFX;

    private Label action;

    private boolean loaded = false;

    public ViewGame(GameAdHunter game){
        gameState = new GameState();
        
        this.game = game;
        this.stage = new Stage();


        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.robotoFont;
        action = new Label(gameState.getCurrentLevel().getAction(), labelStyle);
        action.setPosition(game.getSCREEN_WIDTH()/2 - action.getWidth()/2, action.getHeight()/2);
        stage.addActor(action);

        loadAssets();
        Gdx.input.setInputProcessor(stage);

    }

    public void fillStage(){
        Drawable upTex = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("buttons/bg_red_up.png", Texture.class)));
        Drawable downTex = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("buttons/bg_red_down.png", Texture.class)));

        Random rnd = new Random();
        int buttonNumber = rnd.nextInt(3) + 1;

        for (int i = 0; i < buttonNumber; i++) {

            ImageButton gameButton = new ImageButton(upTex, downTex);
            gameButton.setSize(400, 400);
            gameButton.setPosition((game.getSCREEN_WIDTH()/(buttonNumber+1)*(i+1)) - gameButton.getWidth()/2, game.getSCREEN_HEIGHT()/2 - gameButton.getHeight()/2);

            gameButton.addListener(new ClickListener() {

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    tapSFX.play();
                    return true;
                }

            });

            stage.addActor(gameButton);
        }
    }

    public void loadAssets(){
        game.getAssetManager().load("buttons/bg_red_up.png", Texture.class);
        game.getAssetManager().load("buttons/bg_red_down.png", Texture.class);
        game.getAssetManager().load("data/life_full.png", Texture.class);
        game.getAssetManager().load("data/life_empty.png", Texture.class);

        tapSFX = Gdx.audio.newSound(Gdx.files.internal("sfx/button_press.mp3"));
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 1, 0, 1); //Background color.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!loaded){
            if (game.getAssetManager().update() == true){
                loaded = true;
                fillStage();
            }
        }

        game.getBatch().begin();
        stage.act();
        stage.draw();

        if (loaded){
            game.getBatch().draw(game.getAssetManager().get("data/life_full.png", Texture.class), 20, 1000);
        }


        game.getBatch().end();
    }

}
