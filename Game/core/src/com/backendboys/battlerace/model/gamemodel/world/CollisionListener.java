package com.backendboys.battlerace.model.gamemodel.world;

import com.backendboys.battlerace.model.gamemodel.particles.AbstractExplosive;
import com.badlogic.gdx.physics.box2d.*;


public class CollisionListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        if(fixtureA.getUserData() instanceof AbstractExplosive){
            AbstractExplosive abstractExplosive = (AbstractExplosive) fixtureA.getUserData();
            abstractExplosive.explosiveCollided();
        }
        if(fixtureB.getUserData() instanceof AbstractExplosive){
            AbstractExplosive abstractExplosive = (AbstractExplosive) fixtureB.getUserData();
            abstractExplosive.explosiveCollided();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
