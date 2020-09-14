package com.backendboys.battlerace;

import com.backendboys.battlerace.game.controller.GameController;
import com.backendboys.battlerace.menu.controller.MenuController;
import com.badlogic.gdx.Game;

public class BattleRace extends Game {

    @Override
    public void create() {
        new MenuController(this);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }

}
