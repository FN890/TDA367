package com.backendboys.battlerace.model.gamemodel.powerups;

import com.backendboys.battlerace.model.gamemodel.particles.WorldExplosions;
import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Class that handles the properties of the missile powerup.
 */
public class MissilePowerUp extends AbstractPowerUp {

    WorldExplosions worldExplosions;

    public MissilePowerUp(World world, float spawnPosX, float spawnPosY, WorldExplosions worldExplosions) {
        super(world, spawnPosX, spawnPosY);
        this.worldExplosions = worldExplosions;
    }

    @Override
    public void use(Player player) {
        worldExplosions.addMissile(player.getPosition(), player.getWorld(), player.getRotation());
        System.out.println("Used " + this.toString());
    }

    @Override
    public String toString() {
        return "Missile PowerUp";
    }
}
