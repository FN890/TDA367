package com.backendboys.battlerace.model.gamemodel.world;

public interface GameWorldListener {

    /**
     * Notify listeners that gameWorld.stepWorld() has been used.
     */
    void onGameWorldStepped();

}
