package com.backendboys.battlerace.model.gamemodel.powerups;

import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.backendboys.battlerace.model.gamemodel.world.GameWorldListener;
import com.badlogic.gdx.math.Vector2;

public interface IPowerUp extends GameWorldListener {

    void remove();

    void use(Player player);

    Vector2 getPosition();

}
