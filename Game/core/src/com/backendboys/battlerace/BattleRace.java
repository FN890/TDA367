package com.backendboys.battlerace;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.controller.MenuController;
import com.backendboys.battlerace.controller.ServerController;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * When created it starts the application
 * Created in the DesktopLauncher
 */
public class BattleRace extends Game {

    private ServerController serverController;
    private GameController gameController;

    @Override
    public void create() {
        gameController = new GameController(this);
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
        Gdx.input.setInputProcessor(gameController);
        gameController.setGameScreen();
    }


    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }

    public ServerController getServerController() {
        return gameController.getServerController();
    }
}
