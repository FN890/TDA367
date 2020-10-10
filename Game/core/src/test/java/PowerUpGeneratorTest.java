import com.backendboys.battlerace.model.gamemodel.world.GameWorld;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerUpGeneratorTest {

    private GameWorld gameWorld;


    @Test
    public void testNumberofPowerups() {
        gameWorld = new GameWorld();
        assertEquals(30, gameWorld.getNumberOfPowerUps());
    }

}