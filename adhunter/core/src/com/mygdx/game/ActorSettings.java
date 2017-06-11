package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ActorSettings extends ScreenAdapter {

    private GameAdHunter game;
    private Skin ds;
    private Stage stage;

    private final Music mainMusic;

    public ActorSettings(GameAdHunter game, final Music mainMusic) {
        this.game = game;
        this.ds = new Skin(Gdx.files.internal("uiskin.json"));
        this.mainMusic = mainMusic;
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        setupTable();
    }

    public void setupTable(){

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getAssetManager().get("font/whitney-medium.ttf", BitmapFont.class);
        Label music = new Label("SOUND VOLUME", labelStyle);
        music.setPosition(game.getSCREEN_WIDTH()/2 - music.getWidth()/2, game.getSCREEN_HEIGHT() - music.getHeight());
        stage.addActor(music);

        Label back = new Label("BACK", labelStyle);
        back.setPosition(game.getSCREEN_WIDTH() - back.getWidth(), back.getHeight());
        back.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new ViewMenu(game));
                dispose();
            }
        });
        stage.addActor(back);

        final Slider musicSlider = new Slider(0, 100, 1, false, ds);
        final Slider SFXSlider = new Slider(0, 100, 1, false, ds);
        musicSlider.setValue(100); //Initial value.
        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainMusic.setVolume(musicSlider.getPercent());
            }
        });
        musicSlider.setScale(2,2);
        musicSlider.getStyle().knob.setMinHeight(100);
        musicSlider.scaleBy(3);
        musicSlider.setPosition(game.getSCREEN_WIDTH()/2,game.getSCREEN_HEIGHT()/2);

        SFXSlider.setValue(100); //Initial value.
        SFXSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainMusic.setVolume(musicSlider.getPercent());
            }
        });
        SFXSlider.getStyle().knob.setMinHeight(100);
        SFXSlider.scaleBy(3);
        SFXSlider.setPosition(game.getSCREEN_WIDTH()/2,game.getSCREEN_HEIGHT()/2 - 100);

        stage.addActor(musicSlider);
        stage.addActor(SFXSlider);
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(1, 1, 1, 1); //Background color.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        stage.act();
        stage.draw();

        game.getBatch().end();
    }

}
