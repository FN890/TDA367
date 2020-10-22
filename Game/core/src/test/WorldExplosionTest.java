import com.backendboys.battlerace.model.gamemodel.particles.ParticleHandler;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorldExplosionTest {
    final ParticleHandler particleHandler;
    final World world;
    final Random random;

    public WorldExplosionTest() {
        particleHandler = new ParticleHandler();
        world = new World(new Vector2(0, -10), true);
        this.random = new Random();
    }

    @Test
    public void createExplosionTest() {
        assertNotNull(particleHandler);
    }

    /**
     * Test's if you are able to add explosions to the world
     */
    @Test
    public void addExplosionsTest() {
        int numParticles = 30;
        int randomExplosionsA = random.nextInt(100);
        int randomExplosionsB = random.nextInt(100);
        for (int i = 0; i < randomExplosionsA; i++) {
            particleHandler.addExplosion(new Vector2(i, i), numParticles, world);
        }
        for (int i = 0; i < randomExplosionsB; i++) {
            particleHandler.addExplosion(new Vector2(i, i), numParticles, world);
        }

        assertTrue(particleHandler.getNumberOffExplosions() == randomExplosionsA + randomExplosionsB
                && particleHandler.getTotalExplosionParticles() == numParticles * (randomExplosionsA + randomExplosionsB));
    }

    @Test
    public void addMissileTest() {
        Vector2 startVelocity = new Vector2(0, 0);
        int randomMissilesA = random.nextInt(100);
        int randomMissilesB = random.nextInt(100);
        for (int i = 0; i < randomMissilesA; i++) {
            particleHandler.addMissile(new Vector2(i, i), world, 1f, startVelocity, false);
        }
        for (int i = 0; i < randomMissilesB; i++) {
            particleHandler.addMissile(new Vector2(i, i), world, 1f, startVelocity, false);
        }
        assertTrue(particleHandler.getMissiles().size() == randomMissilesA + randomMissilesB);
    }


}