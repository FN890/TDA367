package com.backendboys.battlerace.view.screens;

import com.backendboys.battlerace.controller.MenuController;

public class ScreenFactory {

    public static IScreen createMainMenu(MenuController menuController) {
        return new MainMenu(menuController);
    }

    public static IScreen createOptionsMenu(MenuController menuController) {
        return new OptionsMenu(menuController);
    }

    public static IScreen createGameScreen() {
        return new GameScreen();
    }

}
