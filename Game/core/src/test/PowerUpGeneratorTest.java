import com.backendboys.battlerace.model.gamemodel.world.GameWorld;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerUpGeneratorTest {

    private GameWorld gameWorld;

    public PowerUpGeneratorTest() {

    }

    @Test
    public void testNumberofPowerups() {
        assertEquals(0, gameWorld.getNumberOfPowerUps());
        gameWorld = new GameWorld();
        assertEquals(30, gameWorld.getNumberOfPowerUps());
    }

}