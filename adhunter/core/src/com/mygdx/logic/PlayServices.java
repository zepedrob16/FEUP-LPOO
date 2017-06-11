package com.mygdx.logic;

/**
 * Class containing all Google Play Services functions
 *
 * @author Miguel Mano Fernandes and José Borges
 * @version 1.0
 *
 */

public interface PlayServices {

    void signIn();
    void signOut();
    void rateGame();
    void unlockAchievement(String achievement);
    void increment(String achievement);

    void submitScore(int highScore);
    void showAchievement();
    void showScore();
    boolean isSignedIn();
}
