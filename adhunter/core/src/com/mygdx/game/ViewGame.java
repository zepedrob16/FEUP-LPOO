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
import com.badlogic.gdx.utils.TimeUtils;

public class ViewGame extends ScreenAdapter {
    private float timeCount;
    private Integer worldTimer = 4;
    private GameState gameState;
    private long startTime = System.currentTimeMillis();

    private GameAdHunter game;
    private Stage stage;
    private Sound tapSFX;

    private Label action, timer;

    private int cX, cY, cW, cZ;

    private boolean loaded = false;

    TimeUtils timeUtils = new TimeUtils();

    public ViewGame(GameAdHunter game){
        gameState = new GameState();
        cX = 0; cY = 1; cW = 0; cZ = 1;
        
        this.game = game;
        this.stage = new Stage();


        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.robotoFont;
        action = new Label(gameState.getCurrentLevel().getAction(), labelStyle);
        action.setPosition(game.getSCREEN_WIDTH()/2 - action.getWidth()/2, game.getSCREEN_HEIGHT() - action.getHeight());
        stage.addActor(action);

        timer = new Label(String.format("%02d", worldTimer), labelStyle);
        timer.setPosition(100,100);
        stage.addActor(timer);

        loadAssets();
        Gdx.input.setInputProcessor(stage);

    }

    public void update(float dt) {
        timeCount += dt;
        timer.setText(String.format("%02d:%02d", worldTimer, 100 - timeUtils.timeSinceMillis(startTime)/10));
        if (timeCount >= 1) {
            worldTimer--;
            startTime = System.currentTimeMillis();
            if (worldTimer == -1)
                clearScreen();

            timeCount = 0;
        }
    }

    public void fillPreGame(){

        Drawable upTex = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("buttons/bg_red_up.png", Texture.class)));

        Random rnd = new Random();
        int buttonNumber = rnd.nextInt(3) + 1;

        for (int i = 0; i < buttonNumber; i++) {

            ImageButton gameButton = new ImageButton(upTex);
            gameButton.setSize(400, 400);
            gameButton.setPosition((game.getSCREEN_WIDTH()/(buttonNumber+1)*(i+1)) - gameButton.getWidth()/2, game.getSCREEN_HEIGHT()/2 - gameButton.getHeight()/2);

            stage.addActor(gameButton);
        }
    }

    public void clearScreen() {
        stage.clear();
        fillGame();
    }

    public void fillGame() {
        Drawable lifeFullTex = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("data/life_full.png", Texture.class)));
        cX = 1;
        cW = 1;

        for (int i = 0; i < 3; i++) {
            ImageButton lifeSymbol = new ImageButton(lifeFullTex);
            lifeSymbol.setSize(100,100);
            lifeSymbol.setPosition(lifeSymbol.getWidth()*1.1f * i + lifeSymbol.getWidth(), game.getSCREEN_HEIGHT() - lifeSymbol.getHeight()*2);

            stage.addActor(lifeSymbol);
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
        Gdx.gl.glClearColor(cX, cY, cW, cZ); //Background color.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!loaded){
            if (game.getAssetManager().update() == true){
                loaded = true;
                fillPreGame();
            }
        }

        game.getBatch().begin();
        stage.act();
        update(delta);
        stage.draw();


        if (loaded){
            game.getBatch().draw(game.getAssetManager().get("data/life_full.png", Texture.class), 20, 1000);
        }


        game.getBatch().end();
    }

}
