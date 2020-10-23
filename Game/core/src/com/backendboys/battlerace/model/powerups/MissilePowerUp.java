package com.backendboys.battlerace.model.powerups;

import com.backendboys.battlerace.model.particles.ParticleHandler;
import com.backendboys.battlerace.model.player.Player;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Class that handles the properties of the missile powerup.
 */
class MissilePowerUp extends AbstractPowerUp {

    private final ParticleHandler particleHandler;

    public MissilePowerUp(World world, float spawnPosX, float spawnPosY, ParticleHandler particleHandler) {
        super(world, spawnPosX, spawnPosY);
        this.particleHandler = particleHandler;
        this.powerUpType = PowerUpType.MISSILE;
    }

    @Override
    public void use(Player player) {
        particleHandler.addMissile(player.getPosition(), player.getWorld(), player.getRotation(), player.getLinearVelocity(), true);
    }

}
