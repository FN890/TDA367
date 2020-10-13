package com.backendboys.battlerace.model.gamemodel.world.ground;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class SinusDividedXGroundVertices implements IGroundVertices {


    @Override
    public ArrayList<Vector2> generateGroundVertices(int numberVertices, double step) {

        ArrayList<Vector2> groundVertices = new ArrayList<>();

        float xPos = 0;
        for (int i = 0; i < numberVertices; i++) {
            float yPos = 30 * MathUtils.sin(xPos * 0.005f) * MathUtils.cos(xPos * 0.015f) * (float) Math.atan(xPos * 0.0015) + 40;
            xPos += step + 2;
            groundVertices.add(new Vector2(xPos, yPos));
        }
        return groundVertices;
    }


}
