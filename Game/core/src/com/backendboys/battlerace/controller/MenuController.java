package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.BattleRace;
import com.backendboys.battlerace.view.screens.IScreen;
import com.backendboys.battlerace.view.screens.ScreenFactory;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MenuController {

    private final BattleRace game;
    private final Music music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

    public boolean isMusicPlaying() {
        return music.isPlaying();
    }

    /**
     * @param game Set Menu screen for game
     */
    public MenuController(BattleRace game) {
        this.game = game;
        game.setScreen(ScreenFactory.createMainMenu(this));

        music.play();
        music.setLooping(true);
    }

    public void toSinglePlayer() {
        music.stop();

        game.startSinglePlayer();
    }

    public void toMultiPlayer() {
        game.startMultiplayer();
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
