package com.backendboys.battlerace.model.gamemodel;

import com.backendboys.battlerace.model.gamemodel.particles.AbstractExplosive;
import com.backendboys.battlerace.model.gamemodel.powerups.AbstractPowerUp;
import com.backendboys.battlerace.model.gamemodel.vehicle.IVehicle;
import com.badlogic.gdx.physics.box2d.*;

/**
 * A class that handles the logic for what happens when bodies collide in the world
 */
class CollisionListener implements ContactListener {

    private GameModel model;

    CollisionListener(GameModel model) {
        this.model = model;
    }

    /**
     * Everytime two bodies collide in the world a contact is created between two fixtures
     * The data that a contact provides is used to make thing happen when specific bodies collide
     * Example: when a Missile collides with anything it explodes
     *
     * @param contact A contact has two Fixture objects which in turn can be used to check if the userData matches a specific object
     */
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        checkExplosivesContact(fixtureA, fixtureB);
        checkPowerUpsConctact(fixtureA, fixtureB);
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

    private void checkExplosivesContact(Fixture fixtureA, Fixture fixtureB) {
        if (fixtureA.getUserData() instanceof AbstractExplosive && !(fixtureB.getUserData() instanceof IVehicle)) {

            AbstractExplosive abstractExplosive = (AbstractExplosive) fixtureA.getUserData();
            abstractExplosive.explosiveCollided();
        }
        if (fixtureB.getUserData() instanceof AbstractExplosive && !(fixtureA.getUserData() instanceof IVehicle)) {

            AbstractExplosive abstractExplosive = (AbstractExplosive) fixtureB.getUserData();
            abstractExplosive.explosiveCollided();
        }
    }

    private void checkPowerUpsConctact(Fixture fixtureA, Fixture fixtureB) {
        if (fixtureA.getUserData() instanceof AbstractPowerUp && fixtureB.getUserData() instanceof IVehicle) {
            AbstractPowerUp abstractPowerUp = (AbstractPowerUp) fixtureA.getUserData();
            System.out.println(abstractPowerUp.toString());
            model.getPlayer().addPowerUp(abstractPowerUp);
            model.removePowerUp(abstractPowerUp);
        }
    }

}
