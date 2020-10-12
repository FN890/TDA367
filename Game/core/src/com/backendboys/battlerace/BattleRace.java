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
        Gdx.input.setInputProcessor(new GameController(this));
    }

    public void startMultiplayer(String name) {
        ServerController serverController = new ServerController(this);
        serverController.sendMessage("create:" + name);
        serverController.sendMessage("start");
    }

    public void joinMultiplayer(String name, int id) {
        ServerController serverController = new ServerController(this);
        serverController.sendMessage("join:" + id + "," + name);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }

}
