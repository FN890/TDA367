package com.backendboys.battlerace.model.gamemodel.powerups;

import com.backendboys.battlerace.model.gamemodel.particles.ParticleHandler;
import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Class that handles the properties of the missile powerup.
 */
public class MissilePowerUp extends AbstractPowerUp {

    private final ParticleHandler particleHandler;

    public MissilePowerUp(World world, float spawnPosX, float spawnPosY, ParticleHandler particleHandler) {
        super(world, spawnPosX, spawnPosY);
        this.particleHandler = particleHandler;
    }

    @Override
    public void use(Player player) {
        particleHandler.addMissile(player.getPosition(), player.getWorld(), player.getRotation(), player.getLinearVelocity(), true);
    }

}
