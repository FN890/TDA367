package com.backendboys.battlerace;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.controller.MenuController;
import com.backendboys.battlerace.view.screens.ScreenFactory;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * When created it starts the application
 * Created in the DesktopLauncher
 */
public class BattleRace extends Game {

    @Override
    public void create() {
        new MenuController(this);
    }

    /**
     * Changes the screen to the main menu
     */
    public void startMenu() {
        new MenuController(this);
    }

    /**
     * Starts the game in singleplayer (Switches to GameScreen)
     */
    public void startSinglePlayer() {
        GameController gameController = new GameController(this, false);
        Gdx.input.setInputProcessor(gameController);
        setScreen(ScreenFactory.createGameScreen(gameController));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }

}
