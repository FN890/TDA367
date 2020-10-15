package com.backendboys.battlerace.model.gamemodel.world.ground;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

/**
 * Strategy: Sin * Cos * aTan.
 */
class SinCosATanStrategy extends AbstractGroundStrategy {

    SinCosATanStrategy(int numberVertices, int minHeight, double step) {
        super(numberVertices, minHeight, step);
    }

    @Override
    public ArrayList<Vector2> generateGroundVertices() {
        ArrayList<Vector2> groundVertices = new ArrayList<>();

        float xPos = 0;
        for (int i = 0; i < numberVertices; i++) {
            float yPos = 30 * MathUtils.sin(xPos * 0.0005f) * MathUtils.cos(xPos * 0.015f) * (float) Math.atan(xPos * 0.0015) + minHeight;
            xPos += step;
            groundVertices.add(new Vector2(xPos, yPos));
        }
        return groundVertices;
    }
}
