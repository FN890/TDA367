package com.backendboys.battlerace.view.screens;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.controller.MenuController;

/**
 * A factory that returns an IScreen interface.
 * Used for encapsulation.
 */
public class ScreenFactory {

    private ScreenFactory() {
    }

    /**
     * Creates a {@link MainMenu} Screen.
     *
     * @return The screen in the form of {@link IScreen}
     */
    public static IScreen createMainMenu(MenuController menuController) {
        return new MainMenu(menuController);
    }

    /**
     * Creates a {@link OptionsMenu} Screen.
     *
     * @return The screen in the form of {@link IScreen}
     */
    public static IScreen createOptionsMenu(MenuController menuController) {
        return new OptionsMenu(menuController);
    }

    /**
     * Creates a {@link GameScreen} Screen.
     *
     * @return The screen in the form of {@link IScreen}
     */
    public static IGameScreen createGameScreen(GameController gameController, boolean debugMode) {
        return new GameScreen(gameController, debugMode);
    }

    /**
     * Creates a {@link MultiplayerMenu} Screen.
     *
     * @return The screen in the form of {@link IScreen}
     */
    public static IScreen createMultiplayerMenu(MenuController menuController) {
        return new MultiplayerMenu(menuController);
    }
}
