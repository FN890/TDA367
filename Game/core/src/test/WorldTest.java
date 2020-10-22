import com.backendboys.battlerace.model.world.GameWorld;
import com.backendboys.battlerace.model.world.ground.GroundStrategyFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldTest {

    private final GameWorld gameWorld;
    private final static int NUMBER_VERTICES = 1000;

    public WorldTest() {
        gameWorld = new GameWorld(GroundStrategyFactory.getSinCosStrategy(NUMBER_VERTICES, 60, 5), 1);
    }

    @Test
    public void verticesTest() {
        assertEquals(gameWorld.getGroundVertices().size(), NUMBER_VERTICES);
    }


}
