package com.backendboys.battlerace.model.powerups;

import com.backendboys.battlerace.model.GameModel;
import com.backendboys.battlerace.model.player.Player;
import com.backendboys.battlerace.model.powerups.NitroPowerUp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerUpTest {

    private GameModel gameModel;
    private Player player;

    public PowerUpTest() {
        gameModel = new GameModel();
        player = new Player("player");
    }

    @Test
    public void testPickUpPowerUp() {
        player.addPowerUp(gameModel.getPowerUps().get(0));
        assertEquals(1, player.getListPowerUp().size());
    }

    @Test
    public void testUsePowerUp() {
        Player player2 = new Player("Player2");
        player2.addVehicle(gameModel.getGameWorld().getWorld(), 10, 10);
        player2.addPowerUp(new NitroPowerUp(gameModel.getGameWorld().getWorld(), 50, 50));
        assertEquals(player2.getListPowerUp().size(), 1);
        player2.usePowerUp();
        assertEquals(player2.getListPowerUp().size(), 0);
    }


}