package com.backendboys.battlerace.view.screens;

import com.backendboys.battlerace.controller.MenuController;

public class ScreenFactory {

    public static IScreen CreateMainMenu(MenuController menuController) {
        return new MainMenu(menuController);
    }

    public static IScreen CreateOptionsMenu(MenuController menuController) {
        return new OptionsMenu(menuController);
    }

}
