package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Creates and displays a menu for the tutorial
 *
 * @author Miguel Mano Fernandes and José Borges
 * @version 1.0
 *
 */

public class ViewTutorial extends ScreenAdapter{

    private Stage stage;
    private GameAdHunter game;

    /**
     * ViewTutorial constructor
     *
     * @param game
     *      Current game
     */

    public ViewTutorial(GameAdHunter game) {
        this.game = game;
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        setupText();
    }

    /**
     * Creates and displays the text shown on screen
     */

    public void setupText() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getAssetManager().get("font/whitney-medium.ttf", BitmapFont.class);
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
