package com.backendboys.battlerace.model.gamemodel.particles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * This is the class that creates the explosions in the world and the class that is exposed outside the particles package
 */
public class WorldExplosions {
    /**
     * A list of all the explosions that have been created in the world
     */
    private final ArrayList<Explosion> explosions = new ArrayList<>();

    /**
     * Adds an explosion to the world at a set position
     *
     * @param pos          position for the explosion
     * @param numParticles the number off particles that are set the be generated
     * @param world        The world in which the explosion occurs
     */
    public void addExplosion(Vector2 pos, int numParticles, World world) {
        if (numParticles > 0) {
            explosions.add(new Explosion(pos, numParticles, world));
        }
    }

    /**
     * removes "dead explosions" by checking if all the particles have slowed down to a set value
     * if all particles have slowed down it is removed from the explosions list
     */
    public void removeDeadExplosions() {
        for (Explosion explosion : explosions) {
            explosion.removeSlowParticles();
            if (explosion.explosionIsDead()) {
                explosions.remove(explosion);
            }
        }
    }
}
