package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by migue on 27/04/2017.
 */

public class ViewAbout extends ScreenAdapter{

    private Stage stage;
    private GameAdHunter game;
    private Table table;


    public ViewAbout(GameAdHunter game) {
        this.game = game;
        this.stage = new Stage();
        this.table = new Table();
        Gdx.input.setInputProcessor(stage);

        setupText();
    }

    public void setupText() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getAssetManager().get("font/whitney-medium.ttf", BitmapFont.class);
        Label label = new Label("What is up people my name is j sdak lasd kas saldksa kdas jdksa jdmaks jsakdj asjd askdn" ,labelStyle);
        label.setWrap(true);
        label.setWidth(20); // or even as low as 10
        label.setPosition(100, 100);
        this.table.add(label).width(10f);

        table.setX(game.getSCREEN_WIDTH()/2);
        table.setY(game.getSCREEN_HEIGHT()/2);

        stage.addActor(label);
        stage.addActor(table);
    }

    public void render(float delta){
        Gdx.gl.glClearColor(1, 1, 1, 1); //Background color.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        stage.act();
        stage.draw();

        game.getBatch().end();
    }

}
