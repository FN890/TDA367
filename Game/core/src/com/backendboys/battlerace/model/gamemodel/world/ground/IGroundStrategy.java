package com.backendboys.battlerace.model.gamemodel.world.ground;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public interface IGroundStrategy {

    /**
     * Method for generating ground vertices.
     *
     * @return Ground vertices.
     */
    ArrayList<Vector2> generateGroundVertices();

}
