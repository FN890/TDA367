package com.backendboys.battlerace;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.controller.MenuController;
import com.backendboys.battlerace.services.GameClient;
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

    public void startMultiplayer() {
        //Start multiplayerController
        //Controller got reference to server model?

        System.out.println("Start mutliplayer");
        Thread gameThread = new Thread(new GameClient("127.0.0.1", 26000));
        gameThread.start();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }

}
