package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

import static com.badlogic.gdx.Input.Keys.R;

/**
 * Class that contains most information regarding the game, including textures, timers and important actors
 *
 * @author Miguel Mano Fernandes and José Borges
 * @version 1.0
 *
 */
public class ViewGame extends ScreenAdapter {

    private float timeCount;
    private Integer worldTimer = 2;
    private GameState gameState;
    private long startTime = System.currentTimeMillis();

    private GameAdHunter game;
    private Stage stage;

    private ImageButton adAsset;

    private Label action, timer, levelLabel, stageLabel;

    private int preGameButtons, gameButtons;

    private boolean loaded = false, switchFromPre = true, all = false;

    TimeUtils timeUtils = new TimeUtils();

    /**
     * ViewGame constructor
     *
     * @param game
     *      The current game
     */

    public ViewGame(GameAdHunter game){
        gameState = new GameState();


        this.game = game;
        this.stage = new Stage();

        game.getAssetManager().get("sfx/game_music_1.mp3", Music.class).play();

        game.playServices.increment("CgkIn--a47IMEAIQAA");
        game.playServices.increment("CgkIn--a47IMEAIQAQ");

        loadLabelsPreGame();
        Gdx.input.setInputProcessor(stage);

    }

    /**
     * Loads avoid/press and timer labels to pregame
     */

    public void loadLabelsPreGame() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getAssetManager().get("font/whitney-medium.ttf", BitmapFont.class);
        action = new Label(gameState.getCurrentLevel().getAction(), labelStyle);
        action.setPosition(game.getSCREEN_WIDTH()/2 - action.getWidth()/2, game.getSCREEN_HEIGHT() - action.getHeight());
        stage.addActor(action);

        timer = new Label(String.format("%02d", worldTimer), labelStyle);
        timer.setPosition(game.getSCREEN_WIDTH() - timer.getWidth()*2.2f, game.getSCREEN_HEIGHT() - timer.getHeight());
        stage.addActor(timer);
    }

    /**
     * Updates the timer ingame
     * @param dt
     *      Time passed since last tick
     */

    public void update(float dt) {
        timeCount += dt;
        timer.setText(String.format("%02d.%02d", worldTimer, 100 - timeUtils.timeSinceMillis(startTime)/10));
        if (timeCount >= 1) {
            worldTimer--;
            startTime = System.currentTimeMillis();
            if (worldTimer == -1) {
                clearScreen();
            }

            timeCount = 0;
        }
    }

    /**
     * Fills the pregame with the correct number of buttons regarding the current level
     */

    public void fillPreGame(){
        gameState.preGameButtons.clear();

        int buttonNumber;

        if(gameState.getCurrentLevel().numButtons() < 4)
            buttonNumber = 1;

        else {
            Random rnd = new Random();
            buttonNumber = rnd.nextInt(3) + 1;
        }

        for (int i = 0; i < buttonNumber; i++) {

            final Button gameButton = new Button();
            gameButton.setButtonSize(400, 400);
            gameButton.setButtonPos((game.getSCREEN_WIDTH()/(buttonNumber+1)*(i+1)) - gameButton.getButtonWidth()/2, game.getSCREEN_HEIGHT()/2 - gameButton.getButtonHeight()/2);

            gameState.managePreGameButtons(gameButton);
            gameButton.setAction(gameState);

            ImageButton gameImgButton = gameButton.getImgBtn();
            stage.addActor(gameImgButton);
        }
        preGameButtons = buttonNumber;
    }

    /**
     * Adds a listener to a specific button
     *
     * @param gameButton
     *      The button in question
     */

    public void configureButton(final Button gameButton) {
        ImageButton gameImgButton = gameButton.getImgBtn();
        gameImgButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent e, float x, float y) {
                if (gameState.manageTap(gameButton)) {
                    if (gameState.getReset()){
                        reset();
                        checkAchievement();
                        gameState.setResetFalse();
                        return;
                    }
                    else if (gameState.getCurrentLevel().getIndex() % 10 == 0) {
                        level10(gameButton);
                        return;
                    }

                    randomizeButtons();
                }
                randomizeButtons();
                loadLives();
                clearLabels();
                loadLabelsGame();
            }
        });
    }

    /**
     * Function used to skip to the next level
     */

    public void reset() {
        clearScreen();
        clearLabels();
        worldTimer=2;
        loadLabelsPreGame();
        fillPreGame();
        switchFromPre = true;
    }

    /**
     * Function used to skip to the next stage
     */

    public void clearScreen() {
        stage.clear();
        if (switchFromPre) {
            gameState.levelButtons.clear();
            fillGame();
            switchFromPre = false;
        }
        else if (this.worldTimer == -1) {
            game.getAssetManager().get("sfx/game_music_1.mp3", Music.class).stop();
            game.playServices.submitScore(gameState.getCurrentLevel().getIndex());
            game.setScreen(new ViewMenu(game));
        }
    }

    /**
     * Removes labels used to keep track of the current level and stage
     */

    public void clearLabels() {
        stageLabel.remove();
        levelLabel.remove();
    }

    /**
     * Fills stage and level labels onto game
     */

    public void loadLabelsGame() {

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getAssetManager().get("font/whitney-medium.ttf", BitmapFont.class);
        levelLabel = new Label(String.format("Level %02d", gameState.getCurrentLevel().getIndex()), labelStyle);
        levelLabel.setFontScale(0.7f);
        levelLabel.setPosition(game.getSCREEN_WIDTH()/2 - levelLabel.getWidth()/3, game.getSCREEN_HEIGHT() - levelLabel.getHeight());


        stageLabel = new Label(String.format("Stage %02d/%02d", gameState.getCurrentLevel().getStage(), gameState.getCurrentLevel().getSteps()), labelStyle);
        stageLabel.setFontScale(0.3f);
        stageLabel.setPosition(game.getSCREEN_WIDTH()/2 - (stageLabel.getWidth()/4), game.getSCREEN_HEIGHT() - stageLabel.getHeight()*1.4f);

        stage.addActor(levelLabel);
        stage.addActor(stageLabel);

    }

    /**
     * Fills game screen with all textures, labels and timer
     */

    public void fillGame() {
        stage.addActor(timer);
        loadTimer();

        loadLives();

        Drawable singleAdTex = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("data/single_ad.png", Texture.class)));
        adAsset = new ImageButton(singleAdTex);
        adAsset.setPosition(game.getSCREEN_WIDTH()/2 - adAsset.getWidth()/2, game.getSCREEN_HEIGHT()/2 - adAsset.getHeight()/1.7f);
        stage.addActor(adAsset);

        generateButtons();
        for (int i = 0; i < gameState.preGameButtons.size(); i++) {
            configureButton(gameState.preGameButtons.get(i));
        }

        loadLabelsGame();
    }

    /**
     * Loads the lives textures
     */

    public void loadLives() {

        Drawable lifeFullTex = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("data/life_full.png", Texture.class)));
        Drawable lifeEmptyTex = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("data/life_empty.png", Texture.class)));

        if (gameState.getLives() == 0){
            game.getAssetManager().get("sfx/game_music_1.mp3", Music.class).stop();
            game.playServices.submitScore(gameState.getCurrentLevel().getIndex());
            game.setScreen(new ViewMenu(game));
        }


        for (int i = 0; i < gameState.getLives(); i++) {
            ImageButton lifeSymbol = new ImageButton(lifeFullTex);
            lifeSymbol.setSize(100, 100);
            lifeSymbol.setPosition(lifeSymbol.getWidth()*1.1f * i + lifeSymbol.getWidth(), game.getSCREEN_HEIGHT() - lifeSymbol.getHeight()*1.7f);

            stage.addActor(lifeSymbol);
        }
        if (gameState.getLives() == 2) {
            ImageButton lifeSymbol = new ImageButton(lifeEmptyTex);
            lifeSymbol.setSize(100, 100);
            lifeSymbol.setPosition(lifeSymbol.getWidth()*1.1f * 2 + lifeSymbol.getWidth(), game.getSCREEN_HEIGHT() - lifeSymbol.getHeight()*1.7f);
            stage.addActor(lifeSymbol);
        }
        else if (gameState.getLives() == 1) {
            for (int i = 1; i < 3; i++) {
                ImageButton lifeSymbol = new ImageButton(lifeEmptyTex);
                lifeSymbol.setSize(100, 100);
                lifeSymbol.setPosition(lifeSymbol.getWidth() * 1.1f * i + lifeSymbol.getWidth(), game.getSCREEN_HEIGHT() - lifeSymbol.getHeight() * 1.7f);

                stage.addActor(lifeSymbol);
            }
        }



    }

    /**
     * Function used when the user is in a level multiple of 10. It's purpose is to determine whether or not the user has clicked correct buttons
     *
     * @param btn
     *      Button the user clicked and that is going to be removed from screen
     */

    public void level10(Button btn) {
        System.out.println(gameButtons);

        if (gameState.getCurrentLevel().getAction() == "AVOlD") {
            if (gameButtons == 2) {
                gameState.setAllTrue();
                btn.getImgBtn().remove();
                gameButtons--;
            } else {
                btn.getImgBtn().remove();
                gameButtons--;
            }
        }
        else {
            if (preGameButtons == 1) {
                gameState.nextLevel();
                gameState.setAllTrue();
                reset();
            }
            if (preGameButtons == 2) {
                gameState.setAllTrue();
                btn.getImgBtn().remove();
                preGameButtons--;
            } else {
                btn.getImgBtn().remove();
                preGameButtons--;
            }
        }

    }

    /**
     * Function used to generate ingame buttons
     */

    public void generateButtons(){

        Random rnd = new Random();

        gameButtons = gameState.getCurrentLevel().numButtons() - preGameButtons;

        for (int i = 0; i < gameButtons; i++) {
            final Button btn = new Button();

            int buttonWidth = 150;//rnd.nextInt(50);
            int buttonHeight = 150;//rnd.nextInt(50);

            int buttonX = 2000, buttonY = 2000;

            while (buttonX >= (adAsset.getWidth() + adAsset.getX())
                    || buttonY >= (adAsset.getHeight() + adAsset.getY())){

                buttonX = rnd.nextInt(Math.round(adAsset.getWidth())) + Math.round(adAsset.getX());
                buttonY = rnd.nextInt(Math.round(adAsset.getHeight())) + Math.round(adAsset.getY());

            }

            final ImageButton gameImgButton = btn.getImgBtn();
            gameImgButton.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent e, float x, float y) {
                    if (gameState.manageTap(btn))
                        if (gameState.getReset()){
                            reset();
                            checkAchievement();
                            gameState.setResetFalse();
                            return;
                        }
                        else if (gameState.getCurrentLevel().getIndex() % 10 == 0) {
                            level10(btn);
                            return;
                        }
                    randomizeButtons();
                    loadLives();
                    clearLabels();
                    loadLabelsGame();
                }
            });

            btn.setButtonSize(buttonWidth, buttonHeight);
            btn.setButtonPos(buttonX, buttonY);
            btn.setGameButtonAction(gameState);

            gameState.manageButtons(btn);
            stage.addActor(btn.getImgBtn());
        }

        // Adds the buttons loaded in preGame
        for (int i = 0; i < gameState.preGameButtons.size(); i++) {
            int buttonWidth = 150;//rnd.nextInt(50);
            int buttonHeight = 150;//rnd.nextInt(50);

            int buttonX = 2000, buttonY = 2000;

            while (buttonX >= (Math.round(adAsset.getWidth()) + Math.round(adAsset.getX())) || buttonY >= (Math.round(adAsset.getHeight()) + Math.round(adAsset.getY()))){

                buttonX = rnd.nextInt(Math.round(adAsset.getWidth())) + Math.round(adAsset.getX());
                buttonY = rnd.nextInt(Math.round(adAsset.getHeight())) + Math.round(adAsset.getY());

            }

            gameState.preGameButtons.get(i).setButtonPos(buttonX, buttonY);
            gameState.preGameButtons.get(i).setButtonSize(buttonWidth, buttonHeight);
            stage.addActor(gameState.preGameButtons.get(i).getImgBtn());
        }
    }

    /**
     * Function used to switch randomly the buttons position
     */

    //USED TO SWITCH THE BUTTON POSITION
    public void randomizeButtons() {
        Random rnd = new Random();

        for (int i = 0; i < gameState.getCurrentLevel().numButtons() - preGameButtons; i++) {
            int buttonWidth = 150;//rnd.nextInt(50);
            int buttonHeight = 150;//rnd.nextInt(50);

            int buttonX = 2000, buttonY = 2000;

            while (buttonX >= (adAsset.getWidth() + adAsset.getX())
                    || buttonY >= (adAsset.getHeight() + adAsset.getY())){

                buttonX = rnd.nextInt(Math.round(adAsset.getWidth())) + Math.round(adAsset.getX());
                buttonY = rnd.nextInt(Math.round(adAsset.getHeight())) + Math.round(adAsset.getY());

            }
            gameState.levelButtons.get(i).setButtonPos(buttonX, buttonY);
            gameState.levelButtons.get(i).setButtonSize(buttonWidth, buttonHeight);
        }

        for (int i = 0; i < gameState.preGameButtons.size(); i++) {
            int buttonWidth = 150;//rnd.nextInt(50);
            int buttonHeight = 150;//rnd.nextInt(50);

            int buttonX = 2000, buttonY = 2000;

            while (buttonX >= (adAsset.getWidth() + adAsset.getX())
                    || buttonY >= (adAsset.getHeight() + adAsset.getY())){

                buttonX = rnd.nextInt(Math.round(adAsset.getWidth())) + Math.round(adAsset.getX());
                buttonY = rnd.nextInt(Math.round(adAsset.getHeight())) + Math.round(adAsset.getY());
            }

            gameState.preGameButtons.get(i).setButtonPos(buttonX, buttonY);
            gameState.preGameButtons.get(i).setButtonSize(buttonWidth, buttonHeight);
        }
    }

    /**
     * Sets the correct time according to the level being played
     */

    public void loadTimer() {
        if (gameState.getCurrentLevel().getIndex() < 10)
            worldTimer = 14;
        else if (gameState.getCurrentLevel().getIndex() < 20)
            worldTimer = 11;
        else if (gameState.getCurrentLevel().getIndex() < 30)
            worldTimer = 7;
        else if (gameState.getCurrentLevel().getIndex() % 10 == 0)
            worldTimer = 9;
        else
            worldTimer = 2;
    }

    /**
     * Checks whether or not the user has concluded an achievement
     */
    public void checkAchievement() {
        if (gameState.getCurrentLevel().getIndex() == 10)
            game.getPlayServices().unlockAchievement("CgkIn--a47IMEAIQAw");
        if (gameState.getCurrentLevel().getIndex() == 20)
            game.getPlayServices().unlockAchievement("CgkIn--a47IMEAIQBA");
        if (gameState.getCurrentLevel().getIndex() == 30)
            game.getPlayServices().unlockAchievement("CgkIn--a47IMEAIQAg");
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(1, 1, 1, 1); //Background color.
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
