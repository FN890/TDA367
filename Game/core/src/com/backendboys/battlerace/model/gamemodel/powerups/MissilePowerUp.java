package com.backendboys.battlerace.model.gamemodel.powerups;

import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Class that handles the properties of the missile powerup.
 */
public class MissilePowerUp extends AbstractPowerUp {

    public MissilePowerUp(World world, float spawnPosX, float spawnPosY) {
        super(world, spawnPosX, spawnPosY);
    }

    @Override
    public void use(Player player) {
        System.out.println("Used " +this.toString());
    }

    @Override
    public String toString() {
        return "Missile PowerUp";
    }
}
