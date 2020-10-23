package com.backendboys.battlerace.model.powerups;

import com.backendboys.battlerace.model.particles.ParticleHandler;
import com.backendboys.battlerace.model.player.Player;
import com.backendboys.battlerace.model.powerups.MissilePowerUp;
import com.backendboys.battlerace.model.powerups.NitroPowerUp;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private final Player player;
    private final String testName = "Dummy";
    private final World world;
    private final Random random;
    private final ParticleHandler particleHandler = new ParticleHandler();
    private final int vehicleStartPosX = 10;
    private final int vehicleStartPosY = 10;

    public PlayerTest() {
        player = new Player(testName);
        world = new World(new Vector2(0, -10), true);
        player.addVehicle(world, vehicleStartPosX, vehicleStartPosY);
        this.random = new Random();
    }

    @Test
    public void playerCreated() {
        assertNotNull(player);
    }

    @Test
    public void playerCheckName() {
        assertEquals(testName, player.getName());
    }

    @Test
    public void playerAddVehicle() {
        player.addVehicle(world, 10, 10);
        assertNotNull(player.getVehicle());
    }


    /**
     * test a random amount of power ups and check if that amount exist in player.
     */
    @Test
    public void PlayerAddPowerUp() {
        int amountPowerUp1 = random.nextInt(100);
        int amountPowerUp2 = random.nextInt(100);

        for (int i = 0; i < amountPowerUp1; i++) {
            player.addPowerUp(new MissilePowerUp(world, 1, 1, particleHandler));
        }
        for (int i = 0; i < amountPowerUp2; i++) {
            player.addPowerUp(new NitroPowerUp(world, 1, 1));
        }

        assertEquals(amountPowerUp1 + amountPowerUp2, player.getListPowerUp().size());
        assertTrue(player.getNextPowerUp() instanceof NitroPowerUp);
    }


    @Test
    public void PlayerUsePowerUp() {
        int amountPowerUp1 = random.nextInt(100);
        int amountPowerUp2 = random.nextInt(100);

        for (int i = 0; i < amountPowerUp1; i++) {
            player.addPowerUp(new MissilePowerUp(world, 1, 1, particleHandler));
        }
        for (int i = 0; i < amountPowerUp2; i++) {
            player.addPowerUp(new NitroPowerUp(world, 1, 1));
        }

        for (int i = 0; i < amountPowerUp2 + amountPowerUp1; i++) {
            player.usePowerUp();
        }

        assertEquals(0, player.getListPowerUp().size());
    }


}