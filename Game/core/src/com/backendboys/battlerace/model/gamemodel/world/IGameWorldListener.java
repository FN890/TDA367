package com.backendboys.battlerace.model.gamemodel.world;

public interface IGameWorldListener {

    /**
     * Notify listeners that gameWorld.stepWorld() has been called.
     */
    void onGameWorldStepped();

}
