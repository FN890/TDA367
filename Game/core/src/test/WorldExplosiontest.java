import com.backendboys.battlerace.model.gamemodel.particles.WorldExplosions;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorldExplosiontest {
    WorldExplosions worldExplosions = new WorldExplosions();
    World world = new World(new Vector2(0, -100), true);

    @Test
    public void createExplosionTest() {
        assertNotNull(worldExplosions);
    }

    /**
     * Test's if you are able to add an explosion and the particles to the world
     */
    @Test
    public void addExplosionTest() {
        worldExplosions.addExplosion(new Vector2(1, 2), 30, world);
        assertTrue(worldExplosions.getNumberOffExplosions() == 1 && worldExplosions.getTotalExplosionParticles() == 30);
    }


}