import com.backendboys.battlerace.model.gamemodel.world.GroundGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroundGeneratorTest {

    private GroundGenerator groundGenerator;
    private final static int NUMBER_VERTICES = 14200;
    private final static float STEP = 1;
    private final static int FRICTION = 1;

    public GroundGeneratorTest() {
        groundGenerator = new GroundGenerator(NUMBER_VERTICES, STEP, FRICTION);
    }

    @Test
    public void testVertices() {
        assertEquals(NUMBER_VERTICES, groundGenerator.getNumberVertices());
    }

    @Test
    public void testAddedGround() {
        World world = new World(new Vector2(0, -10), true);
        assertEquals(world.getBodyCount(), 0);
        groundGenerator.generateGround(world);
        assertTrue(world.getBodyCount() != 0);
    }


}
