package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ViewMenu extends ScreenAdapter {

    private final GameAdHunter game;
    private ImageButton settingsButton;

    private Stage stage;

    //Font
    FreeTypeFontGenerator generator;
    BitmapFont font;
    Label pressToPlay;

    //Textures
    private Texture texSettings, texAbout, texHelp;

    //Sound
    private Music mainMusic;
    private Sound tapSFX;

    public ViewMenu(GameAdHunter game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());

        //Sound
        this.mainMusic = Gdx.audio.newMusic(Gdx.files.internal("sfx/main_music_1.mp3"));
        mainMusic.play();

        this.tapSFX = Gdx.audio.newSound(Gdx.files.internal("sfx/button_press.mp3"));

        //Font
        this.generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Thin.ttf"));
        setFontProperties();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.robotoFont;
        pressToPlay = new Label("tap to play", labelStyle);
        pressToPlay.setPosition(game.getSCREEN_WIDTH()/2 - pressToPlay.getWidth()/2, game.getSCREEN_HEIGHT()/2 - pressToPlay.getHeight()/2);

        loadAssets();
        Gdx.input.setInputProcessor(stage);
        fillStage();
    }

    public void loadAssets(){
        texSettings = new Texture(Gdx.files.internal("buttons/settings_icon.png"));
        texAbout = new Texture(Gdx.files.internal("buttons/about_icon.png"));
        texHelp = new Texture(Gdx.files.internal("buttons/help_icon.png"));
    }

    public void overlaySettings(){

        //TODO: Add blur filter.
        ShapeRenderer sr = new ShapeRenderer();
        sr.begin(ShapeRenderer.ShapeType.Filled); //Starts a filled shape.
        sr.identity();

    }

    public void fillStage(){
        Actor a = new Actor();
        a.setBounds(0, 0, game.getSCREEN_WIDTH(), game.getSCREEN_HEIGHT());
        a.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent e, float x, float y){
                game.setScreen(new ViewGame(game));
                tapSFX.play();
                dispose();
            }

        });
        stage.addActor(a);

        stage.addActor(pressToPlay);

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texSettings));
        ImageButton settingsButton = new ImageButton(drawable);
        settingsButton.setSize(100, 100);
        settingsButton.setPosition(game.getSCREEN_WIDTH() - settingsButton.getWidth()*4.4f, game.getSCREEN_HEIGHT() - settingsButton.getHeight()*2);
        settingsButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent e, float x, float y){
                game.setScreen(new ViewSettings(game));

                tapSFX.play();
            }

        });

        Drawable about = new TextureRegionDrawable(new TextureRegion(texAbout));
        ImageButton aboutButton = new ImageButton(about);
        aboutButton.setSize(100, 100);
        aboutButton.setPosition(game.getSCREEN_WIDTH() - aboutButton.getWidth()*3.2f, game.getSCREEN_HEIGHT() - aboutButton.getHeight()*2);
        aboutButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent e, float x, float y){
               //game.setScreen(new ViewAbout(game));
                tapSFX.play();
            }

        });

        Drawable help = new TextureRegionDrawable(new TextureRegion(texHelp));
        ImageButton helpButton = new ImageButton(help);
        helpButton.setSize(100, 100);
        helpButton.setPosition(game.getSCREEN_WIDTH() - helpButton.getWidth()*2, game.getSCREEN_HEIGHT() - helpButton.getHeight()*2);
        helpButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent e, float x, float y){
              //  game.setScreen(new ViewTutorial(game));
                tapSFX.play();
            }

        });



        stage.addActor(settingsButton);
        stage.addActor(aboutButton);
        stage.addActor(helpButton);

    }

    public void setFontProperties(){
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 160;
        parameter.color = Color.BLACK;
        game.robotoFont = generator.generateFont(parameter);
    }

    @Override
    public void dispose(){
        game.getBatch().dispose();
        stage.dispose();
        generator.dispose();
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

    @Override
    public void show(){

    }

}
