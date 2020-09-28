package com.backendboys.battlerace.model.gamemodel.particles;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * A simple class that when created generates an explosion with a set number of particles
 * Uses the ExplosionParticle class to create particles for the explosion
 */
public class Explosion {

    private final ArrayList<ExplosionParticle> explosionParticles = new ArrayList<>();

    /**
     * @param pos          The position for the explosion
     * @param numParticles the number off particles that are set the be generated
     * @param world        The world in which the explosion occurs
     */
    public Explosion(Vector2 pos, int numParticles, World world) {
        for (int i = 0; i < numParticles; i++) {
            float angle = (i / (float) numParticles) * 360 * MathUtils.degreesToRadians;
            Vector2 rayDir = new Vector2((float) Math.sin(angle), (float) Math.cos(angle));
            explosionParticles.add(new ExplosionParticle(world, pos, rayDir));
        }
    }

    void removeSlowParticles() {
        for (ExplosionParticle explosionParticle : explosionParticles) {
            Body explosionBody = explosionParticle.getBody();
            if (explosionBody.getLinearVelocity().y < 50f && explosionBody.getLinearVelocity().x < 50f) {
                explosionBody.getWorld().destroyBody(explosionBody);
                explosionParticles.remove(explosionParticle);
            }
        }
    }
    boolean explosionIsDead(){
        return explosionParticles.isEmpty();
    }
}
