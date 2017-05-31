package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;

public interface MenuPopup {

    void drawBackground();
    void drawForeground();
    Actor getBackground();
    Actor getForeground();
}
