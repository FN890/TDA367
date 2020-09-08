package com.backendboys.battlerace;

import com.backendboys.battlerace.controller.GameController;
import com.badlogic.gdx.ApplicationAdapter;

public class BattleRace extends ApplicationAdapter {

    private GameController gameController;

    @Override
    public void create() {
        gameController = new GameController();
    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {

    }

}
