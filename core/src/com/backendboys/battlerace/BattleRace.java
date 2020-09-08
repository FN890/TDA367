package com.backendboys.battlerace;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.view.screens.MainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class BattleRace extends Game {

    private Game game;
    private GameController gameController;

    @Override
    public void create() {
        gameController = new GameController();
        Music music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.play();
        music.setLooping(true);
        this.game = this;
        setScreen(new MainMenu());
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }

}
