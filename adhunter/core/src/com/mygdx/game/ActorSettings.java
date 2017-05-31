package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ActorSettings {

    private GameAdHunter game;
    private Table table;
    private Actor popup;
    private Skin ds;

    private final Music mainMusic;

    public ActorSettings(GameAdHunter game, final Music mainMusic) {
        this.game = game;
        this.ds = new Skin(Gdx.files.internal("uiskin.json"));
        this.table = new Table();
        this.mainMusic = mainMusic;

        setupTable();
    }

    public void setupTable(){
        table.setDebug(true); //So that we see the table limits.
        table.setSize(1150, 850);

        final Label pitchValue = new Label("Music", ds);
        table.add(pitchValue);

        table.setX(game.getSCREEN_WIDTH()/2);
        table.setY(game.getSCREEN_HEIGHT()/2);

        final Slider musicSlider = new Slider(0, 100, 1, false, ds);
        musicSlider.setValue(100); //Initial value.
        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainMusic.setVolume(musicSlider.getPercent());
            }
        });
        musicSlider.setScale(2,2);
        musicSlider.getStyle().knob.setMinHeight(100);
        table.add(musicSlider).width(300).height(300);
        table.row();
        table.add(new Label("SFX", ds));
        table.add(musicSlider);
    }

    public void drawBackground(){
        this.popup = new Actor(){
            @Override
            public void draw(Batch batch, float parentAlpha){
                float promptWidth = 1150, promptHeight = 850;

                batch.end();
                ShapeRenderer sr = new ShapeRenderer();
                sr.begin(ShapeRenderer.ShapeType.Filled); //Starts a filled shape.
                sr.setColor(new Color(0, 1, 1, 0.5f));
                sr.rect(game.getSCREEN_WIDTH()/2 - promptWidth/2, game.getSCREEN_HEIGHT()/2 - promptHeight/2, promptWidth, promptHeight);
                sr.end();
                batch.begin();
            }
        };
    }

    public Table getTable(){
        return this.table;
    }
    public Actor getPopup(){
        return this.popup;
    }

}
