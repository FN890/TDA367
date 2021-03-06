package com.backendboys.battlerace.model.particles;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * A class that when created generates an explosion with a set number of particles
 * Uses the ExplosionParticle class to create particles for the explosion
 */
class Explosion {
    /**
     * A list of all the particles created in the explosion
     */
    private final ArrayList<ExplosionParticle> explosionParticles = new ArrayList<>();

    /**
     * @param pos          The position for the explosion
     * @param numParticles the number off particles that are set the be generated
     * @param world        The world in which the explosion occurs
     */
    Explosion(Vector2 pos, int numParticles, World world) {
        for (int i = 0; i < numParticles; i++) {
            float angle = (i / (float) numParticles) * 360 * MathUtils.degreesToRadians;
            Vector2 rayDir = new Vector2((float) Math.sin(angle), (float) Math.cos(angle));
            explosionParticles.add(new ExplosionParticle(world, pos, rayDir));
        }
    }

    /**
     * Removes explosionParticles if they have collided or if they are under the world
     * The particle is removed from the world and the explosionParticles list
     */
    void removeCollidedParticles() {

        ArrayList<ExplosionParticle> destroyedParticles = new ArrayList<>();
        for (ExplosionParticle explosionParticle : explosionParticles) {
            Body explosionBody = explosionParticle.getBody();
            if (explosionParticle.getToBeRemoved()) {
                explosionBody.getWorld().destroyBody(explosionBody);
                destroyedParticles.add(explosionParticle);
            } else if (explosionParticle.getPosition().y <= 0) {
                explosionBody.getWorld().destroyBody(explosionBody);
                destroyedParticles.add(explosionParticle);
            }
        }
        for (ExplosionParticle destroyedParticle : destroyedParticles) {
            explosionParticles.remove(destroyedParticle);
        }
    }

    public ArrayList<IParticle> getExplosionParticles() {
        return new ArrayList<IParticle>(explosionParticles);
    }

    int getNParticles() {
        return explosionParticles.size();
    }

    /**
     * @return returns true if: all particles are removed
     */
    boolean explosionIsDead() {
        return explosionParticles.isEmpty();
    }
}
