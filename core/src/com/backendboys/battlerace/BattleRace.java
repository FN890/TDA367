package com.backendboys.battlerace;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.controller.MenuController;
import com.backendboys.battlerace.view.screens.MainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class BattleRace extends Game {

    private Game game;
    private GameController gameController;
    private MenuController menuController;
    @Override
    public void create() {
        this.game = this;

        gameController = new GameController();
        menuController = new MenuController(game);
        Music music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.play();
        music.setLooping(true);

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }

}
