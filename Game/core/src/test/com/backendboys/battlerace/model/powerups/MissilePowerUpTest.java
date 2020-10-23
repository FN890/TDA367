package com.backendboys.battlerace.model.powerups;

import com.backendboys.battlerace.model.particles.ParticleHandler;
import com.backendboys.battlerace.model.player.Player;
import com.backendboys.battlerace.model.powerups.MissilePowerUp;
import com.backendboys.battlerace.model.world.GameWorld;
import com.backendboys.battlerace.model.world.ground.GroundStrategyFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MissilePowerUpTest {

    private final MissilePowerUp missilePowerUp;
    private final Player player;
    private final GameWorld gameWorld;
    private final ParticleHandler particleHandler;

    public MissilePowerUpTest() {
        player = new Player("player");
        gameWorld = new GameWorld(GroundStrategyFactory.getSinCosStrategy(1000, 40, 5), 1);
        particleHandler = new ParticleHandler();
        missilePowerUp = new MissilePowerUp(gameWorld.getWorld(), 1, 1, particleHandler);
    }

    @Test
    public void testUsePowerUp() {
        player.addPowerUp(missilePowerUp);
        assertEquals(1, player.getListPowerUp().size());

        player.addVehicle(gameWorld.getWorld(), 0, 0);

        int oldBodies = gameWorld.getWorld().getBodyCount();

        player.usePowerUp();
        assertEquals(0, player.getListPowerUp().size());
        assertEquals(oldBodies + 1, gameWorld.getWorld().getBodyCount());

    }

}