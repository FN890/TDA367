package com.backendboys.battlerace.model.gamemodel.world.ground;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public interface IGroundVertices {

    /**
     * Method for generating ground vertices.
     *
     * @param numberVertices The amount of vertices that the ground should be based on.
     * @param step           The difference on the x-axis between each vertex.
     * @return Ground vertices.
     */
    ArrayList<Vector2> generateGroundVertices(int numberVertices, double step);

}
