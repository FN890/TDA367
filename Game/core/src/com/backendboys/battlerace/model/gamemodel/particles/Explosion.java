package com.backendboys.battlerace.model.gamemodel.particles;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

public class Explosion {


    public Explosion(Vector2 pos, int numParticles, World world) {
        for (int i = 0; i < numParticles; i++) {
            float angle = (i / (float) numParticles) * 360 * MathUtils.degreesToRadians;
            Vector2 rayDir = new Vector2((float) Math.sin(angle), (float) Math.cos(angle));
            ExplosionParticle explosionParticle = new ExplosionParticle(world, pos, rayDir);
        }
    }

}
