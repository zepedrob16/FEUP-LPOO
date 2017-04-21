package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;


public class ViewSettings extends ScreenAdapter {



    public ViewSettings(GameAdHunter game) {
        //this.game = game;
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 1, 1); //Background color.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //game.getBatch().begin();

        //stage.act();
        //stage.draw();

        //game.getBatch().end();

        //if (Gdx.input.isTouched())
        // dispose();
    }
}
