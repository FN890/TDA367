package com.backendboys.battlerace;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.controller.MenuController;
import com.backendboys.battlerace.view.screens.GameScreen;
import com.badlogic.gdx.Game;
<<<<<<< HEAD
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
=======
>>>>>>> Created GameWorld

public class BattleRace extends Game {

    private Game game;
    private GameController gameController;
    private MenuController menuController;

    @Override
    public void create() {
        this.game = this;

        gameController = new GameController();
        menuController = new MenuController(game);
<<<<<<< HEAD

        Gdx.input.setInputProcessor(gameController);
=======
        game.setScreen(new GameScreen());
>>>>>>> Created GameWorld
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }

}
