package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.view.screens.MainMenu;
import com.backendboys.battlerace.view.screens.OptionsMenu;
import com.badlogic.gdx.Game;

public class MenuController {
    private Game game;
    final private MainMenu mainMenu = new MainMenu(this);
    final private OptionsMenu optionsMenu = new OptionsMenu(this);

    public MenuController(Game game) {
        this.game = game;
        game.setScreen(mainMenu);
    }

    public void playPressed() {

    }

    public void multiPlayerPressed() {

    }

    public void exitPressed() {
        System.exit(0);
    }

    public void optionsPressed() {
        game.setScreen(optionsMenu);
    }

    public void backToMainMenuPressed() {
        game.setScreen(mainMenu);
    }
}
