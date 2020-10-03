package com.backendboys.battlerace.model.gamemodel.particles;

public abstract class AbstractExplosive {

    public abstract void explosiveCollided();

    private boolean toBeRemoved = false;

    boolean isToBeRemoved() {
        return toBeRemoved;
    }

    void setToBeRemoved(boolean toBeRemoved) {
        this.toBeRemoved = toBeRemoved;
    }
}
