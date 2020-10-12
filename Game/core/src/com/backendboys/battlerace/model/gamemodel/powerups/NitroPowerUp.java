package com.backendboys.battlerace.model.gamemodel.powerups;

import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Class that handles properties of nitro powerups.
 */
public class NitroPowerUp extends AbstractPowerUp {

    public NitroPowerUp(World world, float spawnPosX, float spawnPosY) {
        super(world, spawnPosX, spawnPosY);
    }

    @Override
    public void use(Player player) {
        player.getVehicle().setAcceleration(player.getVehicle().getAcceleration() * 1.5f);
        System.out.println("Used " + this.toString());
    }

    @Override
    public String toString() {
        return "Nitro PowerUp";
    }
}
