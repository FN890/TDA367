package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.BattleRace;
import com.backendboys.battlerace.view.screens.IScreen;
import com.backendboys.battlerace.view.screens.ScreenFactory;

/**
 * Class that handles switching between screens in menu and logic for settings.
 */
public class MenuController {

    private final BattleRace game;

    private final IScreen optionsMenu = ScreenFactory.createOptionsMenu(this);
    private final IScreen mainMenu = ScreenFactory.createMainMenu(this);

    /**
     * @param game Set Menu screen for game.
     */
    public MenuController(BattleRace game) {
        this.game = game;
        game.setScreen(mainMenu);
    }

    /**
     * Starts a single player game.
     */
    public void toSinglePlayer() {
        game.startSinglePlayer();
    }

    /**
     * Starts a multiplayer screen.
     */
    public void toMultiPlayerScreen() {
        game.setScreen(ScreenFactory.createMultiplayerMenu(this));
    }

    /**
     * Exits the game.
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Open option screen.
     */
    public void toOptions() {
        game.setScreen(optionsMenu);
    }

    /**
     * Open menu screen.
     */
    public void toMainMenu() {
        game.setScreen(mainMenu);
    }

    public BattleRace getGame() {
        return game;
    }

}
