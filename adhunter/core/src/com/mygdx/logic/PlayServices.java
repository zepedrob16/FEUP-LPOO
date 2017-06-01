package com.mygdx.logic;

public interface PlayServices {

    void signIn();
    void signOut();
    void rateGame();
    void unlockAchievement(String achievement);

    void submitScore(int highScore);
    void showAchievement();
    void showScore();
    boolean isSignedIn();
}
