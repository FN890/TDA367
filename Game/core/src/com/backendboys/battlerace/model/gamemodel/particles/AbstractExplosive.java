package com.backendboys.battlerace.model.gamemodel.particles;

/**
 * A abstract class used to identify
 * Missiles and ExplosionParticles when collisions happen
 * This info is then used to call the explosiveCollided(); method
 */
public abstract class AbstractExplosive {

    private boolean toBeRemoved;

    public AbstractExplosive() {
        this.toBeRemoved = false;
    }

    /**
     * The method that is called when explosives Collide
     * Called from the collisionListener
     */
    public abstract void explosiveCollided();
    /**
     * When you change this boolean you give the explosive permission to be removed
     */

    boolean getIsToBeRemoved() {
        return toBeRemoved;
    }

    void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }
}
