package com.backendboys.battlerace;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.view.screens.MainMenu;
import com.badlogic.gdx.Game;

public class BattleRace extends Game {

    private Game game;
    private GameController gameController;

    @Override
    public void create() {
        gameController = new GameController();
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
