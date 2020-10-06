package com.backendboys.battlerace.model.gamemodel.particles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * This is the class that creates the explosions in the world and the class that is exposed outside the particles package
 */
public class WorldExplosions {
    /**
     * A list of all the explosions that have been created in the world
     */
    private static final int MAX_MISSILES = 10;
    private final ArrayList<Explosion> explosions = new ArrayList<>();
    private final ArrayList<OnImpactMissile> missiles = new ArrayList<>();

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

    public int getNumberOffExplosions() {
        return explosions.size();
    }

    public int getTotalExplosionParticles() {
        int particles = 0;
        for (Explosion explosion : explosions) {
            particles += explosion.getNParticles();
        }
        return particles;
    }

    /**
     * Removes particles and Explodes collided Missiles
     */
    public void removeCollidedMissilesAndParticles() {
        removeCollidedParticles();
        removeAndExplodeMissiles();
    }

    /**
     * Removes ExplosionParticles that have collided from the world
     * Also removes Explosions from the ExplosionsList if there are no more particles in that particular Explosion Object
     */
    private void removeCollidedParticles() {
        ArrayList<Explosion> removedExplosions = new ArrayList<>();
        for (Explosion explosion : explosions) {
            explosion.removeCollidedParticles();
            if (explosion.explosionIsDead()) {
                removedExplosions.add(explosion);
            }
        }
        for (Explosion removedExplosion : removedExplosions) {
            explosions.remove(removedExplosion);
        }
    }

    /**
     * Explodes Missiles if they have collided and creates an explosion at the collision point
     */
    private void removeAndExplodeMissiles() {
        ArrayList<OnImpactMissile> removedMissiles = new ArrayList<>();
        for (OnImpactMissile missile : missiles) {
            if (missile.getIsToBeRemoved()) {
                Body body = missile.getBody();
                addExplosion(body.getPosition(), OnImpactMissile.getNumParticles(), body.getWorld());
                removedMissiles.add(missile);
                body.getWorld().destroyBody(body);
            }
        }
        for (OnImpactMissile missile : removedMissiles) {
            missiles.remove(missile);
        }
    }

    /**
     * Adds a missile to the world
     *
     * @param world The world where the missile spawns
     * @param pos   the position of the missile
     */
    public void addMissile(Vector2 pos, World world) {
        //if (missiles.size() < MAX_MISSILES) {
        missiles.add(new OnImpactMissile(world, pos));
        //}

    }
}
