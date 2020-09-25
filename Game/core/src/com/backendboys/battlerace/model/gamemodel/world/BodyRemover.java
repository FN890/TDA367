package com.backendboys.battlerace.model.gamemodel.world;

import com.backendboys.battlerace.model.gamemodel.particles.ExplosionParticle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class BodyRemover {
    //TODO: This only removes bomb particles ATM there is probably a better way to do this.
    // We could use it to remove other bodies aswell (powerups and such)
    static void removeBodies(World world) {
        Array<Body> bodies = new Array<>();
        world.getBodies(bodies);
        for (Body b : bodies) {
            if (b.getUserData() instanceof ExplosionParticle) {
                if (b.getLinearVelocity().y < 50f && b.getLinearVelocity().x < 50f) {
                    world.destroyBody(b);
                }
            }
        }
    }

}
