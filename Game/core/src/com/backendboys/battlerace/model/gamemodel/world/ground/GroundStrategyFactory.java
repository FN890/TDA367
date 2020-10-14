package com.backendboys.battlerace.model.gamemodel.world.ground;

public class GroundStrategyFactory {

    private GroundStrategyFactory(){}

    public static IGroundStrategy getSinStrategy(int numberVertices, int minHeight, double step) {
        return new SinStrategy(numberVertices, minHeight, step);
    }

    public static IGroundStrategy getSinCosStrategy(int numberVertices, int minHeight, double step) {
        return new SinCosStrategy(numberVertices, minHeight, step);
    }

}
