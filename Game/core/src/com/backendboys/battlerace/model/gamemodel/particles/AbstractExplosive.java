package com.backendboys.battlerace.model.gamemodel.particles;

public abstract class AbstractExplosive {

    public abstract void collisionExplosion();

    private boolean toBeRemoved = false;

    public boolean isToBeRemoved() {
        return toBeRemoved;
    }

    public void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }
}
