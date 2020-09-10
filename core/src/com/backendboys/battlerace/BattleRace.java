package com.backendboys.battlerace;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.controller.MenuController;
import com.backendboys.battlerace.view.screens.GameScreen;
import com.backendboys.battlerace.view.screens.MainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class BattleRace extends Game {

    private Game game;
    private GameController gameController;
    private MenuController menuController;

    @Override
    public void create() {
        this.game = this;

        gameController = new GameController();
        menuController = new MenuController(game);

        Gdx.input.setInputProcessor(gameController);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }

}
