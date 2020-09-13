package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.view.screens.GameScreen;
import com.backendboys.battlerace.view.screens.ScreenFactory;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MenuController {

    private final Game game;
    private final Music music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

    public MenuController(Game game) {
        this.game = game;
        game.setScreen(ScreenFactory.CreateMainMenu(this));

        music.play();
        music.setLooping(true);
    }

    public void toSinglePlayer() {
        music.stop();
        game.setScreen(new GameScreen());
    }

    public void toMultiPlayer() {

    }

    public void exit() {
        System.exit(0);
    }

    public void toOptions() {
        game.setScreen(ScreenFactory.CreateOptionsMenu(this));
    }

    public void toMainMenu() {
        game.setScreen(ScreenFactory.CreateMainMenu(this));
    }

    public void playMenuMusic(boolean play) {
        if (play) {
            if (!music.isPlaying()) {
                music.play();
                music.setLooping(true);
            }
            return;
        }

        if (music.isPlaying()) {
            music.stop();
        }
    }

}
