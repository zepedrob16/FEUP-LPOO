package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Creates and displays a menu for the "about" section
 *
 * @author Miguel Mano Fernandes and José Borges
 * @version 1.0
 *
 */

public class ViewAbout extends ScreenAdapter{

    private Stage stage;
    private GameAdHunter game;
    private Table table;

    /**
     *  ViewAbout constructor
     *
     * @param game
     *      The current game
     */

    public ViewAbout(GameAdHunter game) {
        this.game = game;
        this.stage = new Stage();
        this.table = new Table();
        Gdx.input.setInputProcessor(stage);

        setupText();
    }

    /**
     * Creates and displays the text on screen
     */
    public void setupText() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getAssetManager().get("font/whitney-medium.ttf", BitmapFont.class);
        Label label = new Label("AdHunter was developed by Miguel Mano Fernandes and José Pedro Borges as the final project for " ,labelStyle);
        Label label2 = new Label("a college class named LPOO (Object Oriented Programming Laboratory - OOPL) which was" ,labelStyle);
        Label label3 = new Label("attended in the 2nd year of our degree. Since we didn't want to copy other famous games and make" ,labelStyle);
        Label label4 = new Label("a much less detailed version of them we tried coming up with an original concept for our game, " ,labelStyle);
        Label label5 = new Label("thus boosting our creativity and game developing skills." ,labelStyle);

        label.setWrap(true);
        label.setWidth(5); // or even as low as 10

        label.setPosition(50, 700);
        label.setFontScale(0.5f);
        this.table.add(label).width(15f);

        table.setX(game.getSCREEN_WIDTH()/2);
        table.setY(game.getSCREEN_HEIGHT()/2);
        label2.setFontScale(0.5f);
        label2.setPosition(50,600);
        label3.setPosition(50,500);
        label3.setFontScale(0.5f);
        label4.setPosition(50, 400);
        label4.setFontScale(0.5f);
        label5.setPosition(50, 300);
        label5.setFontScale(0.5f);

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


        stage.addActor(label);
        stage.addActor(label2);
        stage.addActor(label3);
        stage.addActor(label4);
        stage.addActor(label5);
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
