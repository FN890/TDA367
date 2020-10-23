package com.backendboys.battlerace.model.powerups;

import com.backendboys.battlerace.model.player.Player;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Class that handles properties of nitro powerups.
 */
public class NitroPowerUp extends AbstractPowerUp {

    public NitroPowerUp(World world, float spawnPosX, float spawnPosY) {
        super(world, spawnPosX, spawnPosY);
        this.powerUpType = PowerUpType.NITRO;
    }

    @Override
    public void use(Player player) {
        player.getVehicle().setAcceleration(player.getVehicle().getAcceleration() * 1.2f);
    }

}
