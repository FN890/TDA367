package com.backendboys.battlerace.model.world;

public interface GameWorldListener {

    /**
     * Notify listeners that gameWorld.stepWorld() has been used.
     */
    void onGameWorldStepped();

}
