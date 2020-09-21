package com.backendboys.battlerace.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.backendboys.battlerace.BattleRace;

public class DesktopLauncher {

	private static final String TITLE = "BattleRace alpha 1.0";
	private static final int SCREEN_WIDTH = 1280;
	private static final int SCREEN_HEIGHT = 800;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = TITLE;
		config.width = SCREEN_WIDTH;
		config.height = SCREEN_HEIGHT;

		new LwjglApplication(new BattleRace(), config);
	}
}
