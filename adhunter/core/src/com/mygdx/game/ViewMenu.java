package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import sun.rmi.runtime.Log;

public class ViewMenu extends ScreenAdapter {

    private final GameAdHunter game;
    private ImageButton settingsButton;

    private Stage stage;


    //Font
    FreeTypeFontGenerator generator;
    BitmapFont font;
    Label pressToPlay;

    private Texture texSettings;

    public ViewMenu(GameAdHunter game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());

        //Font
        this.generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Octovetica.ttf"));
        setFontProperties();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        pressToPlay = new Label("tap to play", labelStyle);
        pressToPlay.setPosition(500,500);

        fillStage();
        //Drawable d = new TextureRegionDrawable(new TextureRegion(texSettings));
    }

    public void loadTextures(){
        texSettings = new Texture(Gdx.files.internal("buttons/settings_icon.png"));
    }

    public void fillStage(){

        TextureRegion myTextureRegion = new TextureRegion(texSettings);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);



        Actor settings = new Actor();
        settings.setBounds(0, 0, 3000, 3000);
        settings.addListener(new ClickListener(){
           public void clicked(InputEvent e, float x, float y){
               dispose();
           }
        });
        settings.toFront();
        stage.addActor(settings);
        stage.addActor(button);
        stage.addActor(pressToPlay);

    }

    public void setFontProperties(){
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 160;
        parameter.color = Color.BLACK;
        font = generator.generateFont(parameter);
    }

    @Override
    public void dispose(){
        game.getBatch().dispose();
        //font.dispose();
        generator.dispose();
    }

    @Override
    public void show(){
        Gdx.gl.glClearColor(1, 1, 1, 1); //Background color.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();

        stage.act();
        stage.draw();

        game.getBatch().end();
    }

}
