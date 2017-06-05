package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ViewMenu extends ScreenAdapter {

    private final GameAdHunter game;

    private Stage stage;

    //Textures
    private Texture texSettings, texAbout, texHelp;

    //Sound
    private Music mainMusic;
    private Sound tapSFX;

    //Fonts
    private BitmapFont whitneyBold, whitneyMedium;

    public ViewMenu(GameAdHunter game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());

        //Sound
        this.mainMusic = game.getAssetManager().get("sfx/main_music_1.mp3", Music.class);
        this.tapSFX = game.getAssetManager().get("sfx/button_press.mp3", Sound.class);
        mainMusic.play();

        //Font
        this.whitneyMedium = game.getAssetManager().get("font/whitney-medium.ttf", BitmapFont.class);
        this.whitneyBold = game.getAssetManager().get("font/whitney-bold.ttf", BitmapFont.class);

        fillStage();
    }

    public void fillStage(){

        Actor a = new Actor();
        a.setBounds(0, 0, game.getSCREEN_WIDTH(), game.getSCREEN_HEIGHT());
        a.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new ViewGame(game));
                tapSFX.play();
                dispose();
                return true;
            }
        });
        stage.addActor(a);

        Drawable dACH = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("buttons/achievements_icon.png", Texture.class)));
        ImageButton bACH = new ImageButton(dACH);
        bACH.setBounds(50, 50, 170, 170);
        bACH.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.playServices.showAchievement();
                return true;
            }
        });
        stage.addActor(bACH);

        Drawable dLEAD = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("buttons/leaderboards_icon.png", Texture.class)));
        ImageButton bLEAD = new ImageButton(dLEAD);
        bLEAD.setBounds(280, 50, 170, 170);
        bLEAD.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.playServices.showScore();
                return true;
            }
        });
        stage.addActor(bLEAD);

        Drawable dSETT = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("buttons/settings_icon.png", Texture.class)));
        ImageButton bSETT = new ImageButton(dSETT);
        bSETT.setBounds(game.getSCREEN_WIDTH() - 680, game.getSCREEN_HEIGHT() - 250, 170, 170);
        bSETT.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ActorSettings as = new ActorSettings(game, mainMusic);
                stage.addActor(as.getPopup());
                stage.addActor(as.getTable());

                tapSFX.play();
                return true;
            }
        });
        stage.addActor(bSETT);

        Drawable dABOUT = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("buttons/about_icon.png", Texture.class)));
        ImageButton bABOUT = new ImageButton(dABOUT);
        bABOUT.setBounds(game.getSCREEN_WIDTH() - 460, game.getSCREEN_HEIGHT() - 250, 170, 170);
        bABOUT.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                MenuPopup menuPopup = new PopupAbout();
                stage.addActor(menuPopup.getBackground());
                stage.addActor(menuPopup.getForeground());

                tapSFX.play();
                return true;
            }
        });
        stage.addActor(bABOUT);

        Drawable dHELP = new TextureRegionDrawable(new TextureRegion(game.getAssetManager().get("buttons/help_icon.png", Texture.class)));
        ImageButton bHELP = new ImageButton(dHELP);
        bHELP.setBounds(game.getSCREEN_WIDTH() - 240, game.getSCREEN_HEIGHT() - 250, 170, 170);
        stage.addActor(bHELP);

    }

    @Override
    public void dispose(){
        stage.dispose();
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(1, 1, 1, 1); //Background color.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.BACK) || Gdx.input.getAccelerometerX() > 50){
            Gdx.input.vibrate(100);
            Gdx.app.exit();
        }

        game.getBatch().begin();
        game.getBatch().draw(game.getAssetManager().get("menu-background.png", Texture.class), 0, 0);

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(this.whitneyBold, "Ad Hunter");
        this.whitneyBold.draw(game.getBatch(), "Ad Hunter", game.getSCREEN_WIDTH()/2 - glyphLayout.width/2, game.getSCREEN_HEIGHT()/2 + glyphLayout.height/2 + 40);

        glyphLayout.setText(this.whitneyMedium, "Tap anywhere to start");
        this.whitneyMedium.draw(game.getBatch(), "Tap anywhere to start", game.getSCREEN_WIDTH()/2 - glyphLayout.width/2, game.getSCREEN_HEIGHT()/2 + glyphLayout.height/2 - 100);
        game.getBatch().end();

        game.getBatch().begin();
        stage.act();
        stage.draw();
        game.getBatch().end();

    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);
    }

}
