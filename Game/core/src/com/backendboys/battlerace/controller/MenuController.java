package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.BattleRace;
import com.backendboys.battlerace.view.screens.ScreenFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Class that handles switching between screens in menu and logic for settings.
 */
public class MenuController {

    private final BattleRace game;
    private final Music music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

    /**
     * @param game Set Menu screen for game.
     */
    public MenuController(BattleRace game) {
        this.game = game;
        game.setScreen(ScreenFactory.createMainMenu(this));

        //music.play();
        music.setLooping(true);
    }

    /**
     * Starts a single player game.
     */
    public void toSinglePlayer() {
        music.stop();

        game.startSinglePlayer();
    }

    /**
     * Check if background music is playing.
     */
    public boolean isMusicPlaying() {
        return music.isPlaying();
    }

    /**
     * Starts a multiplayer game.
     */
    public void toMultiPlayer() {
        game.setScreen(ScreenFactory.createMultiplayerMenu(this));
        game.serverController = new ServerController(game);
    }

    public void startServer(String name){
        game.startMultiplayer(name);
    }

    public void startServer(String name, String id){
        game.joinMultiplayer(name, id);
    }

    /**
     * Exits the game.
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Open option screen.
     */
    public void toOptions() {
        game.setScreen(ScreenFactory.createOptionsMenu(this));
    }

    /**
     * Open menu screen.
     */
    public void toMainMenu() {
        game.setScreen(ScreenFactory.createMainMenu(this));
    }

    /**
     * @param play Should background music be played or not.
     */
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
