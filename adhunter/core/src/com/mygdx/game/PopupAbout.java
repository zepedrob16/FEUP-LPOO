package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by migue on 31/05/2017.
 */

public class PopupAbout implements MenuPopup {

    private Actor background;
    private Table foreground;

    public PopupAbout(){
        drawBackground();
        drawForeground();
    }

    @Override
    public void drawBackground(){
        this.background = new Actor(){
            @Override
            public void draw(Batch batch, float parentAlpha){
                float promptWidth = 1150, promptHeight = 850;

                batch.end();
                Gdx.gl.glEnable(GL20.GL_BLEND);
                Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                ShapeRenderer sr = new ShapeRenderer();
                sr.begin(ShapeRenderer.ShapeType.Filled); //Starts a filled shape.
                sr.setColor(new Color(0, 0, 0, 0.5f));
                sr.rect(GameAdHunter.getInstance().getSCREEN_WIDTH()/2 - promptWidth/2, GameAdHunter.getInstance().getSCREEN_HEIGHT()/2 - promptHeight/2, promptWidth, promptHeight);
                sr.end();
                Gdx.gl.glDisable(GL20.GL_BLEND);
                batch.begin();
            }
        };
    }

    @Override
    public void drawForeground(){
        this.foreground = new Table();


        LabelStyle lblStyle = new LabelStyle();
        lblStyle.font = new BitmapFont(Gdx.files.internal("font/whitney-medium.fnt"));
        foreground.add(new Label("omg nice", new LabelStyle(lblStyle)));
    }

    @Override
    public Actor getBackground(){
        return this.background;
    }

    @Override
    public Actor getForeground(){
        return this.foreground;
    }


}
