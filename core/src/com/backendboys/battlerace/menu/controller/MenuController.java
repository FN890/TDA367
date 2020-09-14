package com.backendboys.battlerace.menu.controller;

import com.backendboys.battlerace.game.controller.GameController;
import com.backendboys.battlerace.menu.view.screens.IScreen;
import com.backendboys.battlerace.menu.view.screens.ScreenFactory;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MenuController {

    private final Game game;
    private final Music music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

    public MenuController(Game game) {
        this.game = game;
        game.setScreen(ScreenFactory.createMainMenu(this));

        music.play();
        music.setLooping(true);
    }

    public void toSinglePlayer() {
        music.stop();

        GameController gameController = new GameController();
        Gdx.input.setInputProcessor(gameController);
        IScreen gameScreen = ScreenFactory.createGameScreen(gameController);
        game.setScreen(gameScreen);
    }

    public void toMultiPlayer() {

    }

    public void exit() {
        System.exit(0);
    }

    public void toOptions() {
        game.setScreen(ScreenFactory.createOptionsMenu(this));
    }

    public void toMainMenu() {
        game.setScreen(ScreenFactory.createMainMenu(this));
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
