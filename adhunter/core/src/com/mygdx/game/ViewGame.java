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
import com.mygdx.logic.Button;
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

    private ImageButton adAsset;

    private Label action, timer, levelLabel, stageLabel;

    private int cX, cY, cW, cZ;

    private boolean loaded = false, switchFromPre = true;

    TimeUtils timeUtils = new TimeUtils();

    public ViewGame(GameAdHunter game){
        gameState = new GameState();
        cX = 0; cY = 1; cW = 0; cZ = 1;

        this.game = game;
        this.stage = new Stage();

        loadLabelsPreGame();
        loadAssets();
        Gdx.input.setInputProcessor(stage);

    }

    public void loadLabelsPreGame() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.robotoFont;
        action = new Label(gameState.getCurrentLevel().getAction(), labelStyle);
        action.setPosition(game.getSCREEN_WIDTH()/2 - action.getWidth()/2, game.getSCREEN_HEIGHT() - action.getHeight());
        stage.addActor(action);

        timer = new Label(String.format("%02d", worldTimer), labelStyle);
        timer.setPosition(game.getSCREEN_WIDTH() - timer.getWidth()*2.2f, game.getSCREEN_HEIGHT() - timer.getHeight());
        stage.addActor(timer);
    }

    public void update(float dt) {
        timeCount += dt;
        timer.setText(String.format("%02d.%02d", worldTimer, 100 - timeUtils.timeSinceMillis(startTime)/10));
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

            final Button gameButton = new Button();
            gameButton.setButtonSize(400, 400);
            gameButton.setButtonPos((game.getSCREEN_WIDTH()/(buttonNumber+1)*(i+1)) - gameButton.getButtonWidth()/2, game.getSCREEN_HEIGHT()/2 - gameButton.getButtonHeight()/2);

            gameState.manageButtons(gameButton);
            gameButton.setAction(gameState);

            ImageButton gameImgButton = gameButton.getImgBtn();
            gameImgButton.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent e, float x, float y) {
                    gameState.manageTap(gameButton);
                }
            });
            stage.addActor(gameImgButton);
        }
    }

    public void clearScreen() {
        stage.clear();
        if (switchFromPre) {
            fillGame();
            switchFromPre = false;
        }
    }

    public void loadLabelsGame() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.robotoFont;
        levelLabel = new Label(String.format("Level %02d", gameState.getCurrentLevel().getIndex()), labelStyle);
        levelLabel.setFontScale(0.7f);
        levelLabel.setPosition(game.getSCREEN_WIDTH()/2 - levelLabel.getWidth()/3, game.getSCREEN_HEIGHT() - levelLabel.getHeight());


        stageLabel = new Label(String.format("Stage"), labelStyle);
        stageLabel.setFontScale(0.3f);
        stageLabel.setPosition(game.getSCREEN_WIDTH()/2 - (stageLabel.getWidth()/4), game.getSCREEN_HEIGHT() - stageLabel.getHeight()*1.4f);

        stage.addActor(levelLabel);
        stage.addActor(stageLabel);

    }

    //TODO: Replace these image buttons with textures, simply.
    public void fillGame() {
        Drawable lifeFullTex = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("data/life_full.png", Texture.class)));
        cX = 1;
        cW = 1;
        stage.addActor(timer);
        worldTimer = 14;

        for (int i = 0; i < 3; i++) {
            ImageButton lifeSymbol = new ImageButton(lifeFullTex);
            lifeSymbol.setSize(100, 100);
            lifeSymbol.setPosition(lifeSymbol.getWidth()*1.1f * i + lifeSymbol.getWidth(), game.getSCREEN_HEIGHT() - lifeSymbol.getHeight()*1.7f);

            stage.addActor(lifeSymbol);
        }
        Drawable singleAdTex = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("data/single_ad.png", Texture.class)));
        adAsset = new ImageButton(singleAdTex);
        adAsset.setPosition(game.getSCREEN_WIDTH()/2 - adAsset.getWidth()/2, game.getSCREEN_HEIGHT()/2 - adAsset.getHeight()/1.7f);
        stage.addActor(adAsset);

        generateButtons();

        loadLabelsGame();
    }

    public void generateButtons(){

        Random rnd = new Random();
        int buttonNumber = rnd.nextInt(3);

        System.out.println(Math.round(adAsset.getX()) + " | " + adAsset.getWidth());

        for (int i = 0; i < buttonNumber; i++) {
            Button btn = new Button();

            int buttonWidth = rnd.nextInt(300) + 100;
            int buttonHeight = rnd.nextInt(300) + 100;

            int buttonX = rnd.nextInt(Math.round(adAsset.getWidth())) + Math.round(adAsset.getX());
            int buttonY = rnd.nextInt(Math.round(adAsset.getHeight())) + Math.round(adAsset.getY());

            System.out.println(buttonX + " | " + buttonY);

            btn.setButtonSize(buttonWidth, buttonHeight);
            btn.setButtonPos(buttonX, buttonY);

            gameState.manageButtons(btn);
            stage.addActor(btn.getImgBtn());
        }

        for (int i = 0; i < gameState.getLevelButtons().size(); i++) {
            int buttonWidth = rnd.nextInt(300) + 100;
            int buttonHeight = rnd.nextInt(300) + 100;

            int buttonX = rnd.nextInt(Math.round(adAsset.getWidth())) + Math.round(adAsset.getX());
            int buttonY = rnd.nextInt(Math.round(adAsset.getHeight())) + Math.round(adAsset.getY());

            gameState.levelButtons.get(i).setButtonPos(buttonX, buttonY);
            gameState.levelButtons.get(i).setButtonSize(buttonWidth, buttonHeight);
            stage.addActor(gameState.getLevelButtons().get(i).getImgBtn());
        }
    }

    public void loadAssets(){
        game.getAssetManager().load("buttons/bg_red_up.png", Texture.class);
        game.getAssetManager().load("buttons/bg_red_down.png", Texture.class);
        game.getAssetManager().load("data/life_full.png", Texture.class);
        game.getAssetManager().load("data/life_empty.png", Texture.class);
        game.getAssetManager().load("data/single_ad.png", Texture.class);

        tapSFX = Gdx.audio.newSound(Gdx.files.internal("sfx/button_press.mp3"));
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(cX, cY, cW, cZ); //Backgr
        // nd color.
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

        game.getBatch().end();
    }

}
