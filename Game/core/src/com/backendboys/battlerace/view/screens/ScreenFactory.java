package com.backendboys.battlerace.view.screens;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.controller.MenuController;

/**
 * A factory that returns an IScreen interface.
 * Used for encapsulation.
 */
public class ScreenFactory {

    public static IScreen createMainMenu(MenuController menuController) {
        return new MainMenu(menuController);
    }

    public static IScreen createOptionsMenu(MenuController menuController) {
        return new OptionsMenu(menuController);
    }

    public static IScreen createGameScreen(GameController gameController) {
        return new GameScreen(gameController);
    }

}