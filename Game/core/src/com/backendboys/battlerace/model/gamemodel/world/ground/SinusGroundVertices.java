package com.backendboys.battlerace.model.gamemodel.world.ground;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class SinusGroundVertices implements IGroundVertices {

    @Override
    public ArrayList<Vector2> generateGroundVertices(int numberVertices, double step) {

        ArrayList<Vector2> groundVertices = new ArrayList<>();

        float xPos = 0;
        for (int i = 0; i < numberVertices; i++) {
            float yPos = 30 * MathUtils.sin(xPos * 0.01f) + 40;
            xPos += step;
            groundVertices.add(new Vector2(xPos, yPos));
        }
        return groundVertices;
    }
}
