package com.backendboys.battlerace.model.world.ground;

abstract class AbstractGroundStrategy implements IGroundStrategy {

    protected final int numberVertices;
    protected final int minHeight;
    protected final double step;

    AbstractGroundStrategy(int numberVertices, int minHeight, double step) {
        this.numberVertices = numberVertices;
        this.minHeight = minHeight;
        this.step = step;
    }
}
