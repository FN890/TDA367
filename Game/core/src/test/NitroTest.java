import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.backendboys.battlerace.model.gamemodel.powerups.NitroPowerUp;
import com.backendboys.battlerace.model.gamemodel.world.GameWorld;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NitroTest {

    private NitroPowerUp nitroPowerUp;
    private Player player;
    private GameWorld gameWorld;

    public NitroTest() {
        nitroPowerUp = new NitroPowerUp();
        player = new Player("player");
        gameWorld = new GameWorld();
    }

    @Test
    public void testPickUpPowerUp() {
        player.addPowerUp(nitroPowerUp);
        assertEquals(1, player.getListPowerUp().size());

        player.addVehicle(gameWorld.getWorld(), 0, 0);

        float oldAcceleration = player.getVehicle().getAcceleration();

        player.usePowerUp();

        assertEquals(oldAcceleration * 1.5f, player.getVehicle().getAcceleration());

    }

}