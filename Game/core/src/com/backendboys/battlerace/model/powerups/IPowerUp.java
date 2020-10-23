package com.backendboys.battlerace.model.powerups;

import com.backendboys.battlerace.model.player.Player;
import com.backendboys.battlerace.model.world.GameWorldListener;
import com.badlogic.gdx.math.Vector2;

/**
 * Interface for power ups.
 * This interface is used in all classes that uses power ups in some way.
 */
public interface IPowerUp extends GameWorldListener {

    /**
     * Removes the power up from the world, called when picked up by a player.
     */
    void remove();

    /**
     * The logic for what happens when a player uses a power up.
     *
     * @param player The player who used the power up.
     */
    void use(Player player);

    /**
     * Getter for the position of the power up in the world.
     *
     * @return The position in the world in the form a {@link Vector2}.
     */
    Vector2 getPosition();

    /**
     * Return the specific type of the power up.
     * @return the type.
     */
    PowerUpType getPowerUpType();

}
