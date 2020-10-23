package com.backendboys.battlerace.view.screens;

import com.backendboys.battlerace.controller.ServerController;

/**
 * Interface for GameScreen, used for encapsulation.
 */
public interface IGameScreen extends IScreen {

    /**
     * Sets the {@link ServerController}.
     */
    void setServerController(ServerController serverController);
}
