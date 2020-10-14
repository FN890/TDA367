package com.backendboys.battlerace.view.screens;

import com.backendboys.battlerace.controller.ServerController;
import com.badlogic.gdx.Screen;

/**
 * Interface that is used only for the ScreenFactory so that it returns an interface and not a class
 */
public interface IScreen extends Screen {

    void setServerController(ServerController serverController);
}
