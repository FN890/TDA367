package com.backendboys.battlerace.model.gamemodel.world.ground;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Strategy: Sin.
 */
public class SinStrategy extends AbstractGroundStrategy {

    SinStrategy(int numberVertices, int minHeight, double step) {
        super(numberVertices, minHeight, step);
    }

    @Override
    public ArrayList<Vector2> generateGroundVertices() {
        ArrayList<Vector2> groundVertices = new ArrayList<>();

        float xPos = 0;
        for (int i = 0; i < numberVertices; i++) {
            float yPos = 30 * MathUtils.sin(xPos * 0.01f) + minHeight;
            xPos += step;
            groundVertices.add(new Vector2(xPos, yPos));
        }
        return groundVertices;
    }
}

