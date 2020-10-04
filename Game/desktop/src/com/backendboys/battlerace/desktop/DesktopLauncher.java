package com.backendboys.battlerace.desktop;

import com.backendboys.battlerace.BattleRace;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * The class that creates the BattleRace class and launches the application
 */
public class DesktopLauncher {

    private static final String TITLE = "BattleRace alpha 1.0";
    private static final int SCREEN_WIDTH = 1280;
    private static final int SCREEN_HEIGHT = 800;

    /**
     * The main function creates the BattleRace class which in turn launches the application
     */
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = TITLE;
        config.width = SCREEN_WIDTH;
        config.height = SCREEN_HEIGHT;

        new LwjglApplication(new BattleRace(), config);
    }
}
