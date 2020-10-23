package com.backendboys.battlerace.model.world.ground;

import com.backendboys.battlerace.view.game.AbstractRender;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Strategy: Flat.
 */
class FlatStrategy extends AbstractGroundStrategy {

    FlatStrategy(int numberVertices, int minHeight, double step) {
        super(numberVertices, minHeight, step);
    }

    @Override
    public ArrayList<Vector2> generateGroundVertices() {
        ArrayList<Vector2> groundVertices = new ArrayList<>();

        float xPos = 0;
        for (int i = 0; i < numberVertices; i++) {
            xPos += step;
            groundVertices.add(new Vector2(xPos, (float) minHeight));
        }
        return groundVertices;
    }
}
