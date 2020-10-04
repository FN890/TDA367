package com.backendboys.battlerace.model.gamemodel.particles;

/**
 * A abstract class used to identify
 * Missiles and ExplosionParticles when collisions happen
 */
public abstract class AbstractExplosive {
    /**
     * The method that is called when explosives Collide
     * Called from the collisionListener
     */
    public abstract void explosiveCollided();

    /**
     * When you change this boolean you give the explosive permission to be removed
     */
    private boolean toBeRemoved = false;

    boolean isToBeRemoved() {
        return toBeRemoved;
    }

    void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }
}
