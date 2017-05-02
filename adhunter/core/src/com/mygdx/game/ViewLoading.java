package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;

public class ViewLoading extends ScreenAdapter {

    private GameAdHunter game;
    private Stage stage;

    public ViewLoading(GameAdHunter game){
        this.game = game;
        this.stage = new Stage();

        stage.addActor(new ProgressBar(0, 100, 1, false, new ProgressBar.ProgressBarStyle()));
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
