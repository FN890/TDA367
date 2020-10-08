import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.backendboys.battlerace.model.gamemodel.world.GameWorld;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerUpTest {

    private GameWorld gameWorld;
    private Player player;

    public PowerUpTest() {
        gameWorld = new GameWorld();
        player = new Player("player");
    }

    @Test
    public void testPickUpPowerUp() {
        gameWorld.getPowerUps().get(0).onPickup(player);
        assertEquals(1, player.getListPowerUp().size());
    }

}