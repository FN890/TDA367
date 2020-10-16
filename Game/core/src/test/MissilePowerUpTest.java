import com.backendboys.battlerace.model.gamemodel.particles.WorldExplosions;
import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.backendboys.battlerace.model.gamemodel.powerups.MissilePowerUp;
import com.backendboys.battlerace.model.gamemodel.world.GameWorld;
import com.backendboys.battlerace.model.gamemodel.world.ground.GroundStrategyFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MissilePowerUpTest {

    private final MissilePowerUp missilePowerUp;
    private final Player player;
    private final GameWorld gameWorld;
    private final WorldExplosions worldExplosions;

    public MissilePowerUpTest() {
        player = new Player("player");
        gameWorld = new GameWorld(GroundStrategyFactory.getSinCosStrategy(1000, 40, 5), 1);
        worldExplosions = new WorldExplosions();
        missilePowerUp = new MissilePowerUp(gameWorld.getWorld(), 1, 1, worldExplosions);
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