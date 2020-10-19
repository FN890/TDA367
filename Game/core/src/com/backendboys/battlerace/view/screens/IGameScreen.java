package com.backendboys.battlerace.view.screens;

import com.backendboys.battlerace.controller.ServerController;

public interface IGameScreen extends IScreen {

    void setServerController(ServerController serverController);
}
