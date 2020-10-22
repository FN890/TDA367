package com.backendboys.battlerace.model.world;

import com.backendboys.battlerace.model.world.ground.IGroundStrategy;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that handles ground creation.
 */
class GroundGenerator {

    private ArrayList<Vector2> vertices;

    GroundGenerator() {
        vertices = new ArrayList<>();
    }

    /**
     * Creates and adds the ground to the world.
     *
     * @param world           The World that the ground should be added to.
     * @param iGroundStrategy Strategy for creating the ground vertices.
     * @param friction        The ground friction.
     */
    void generateGround(World world, IGroundStrategy iGroundStrategy, int friction) {

        vertices = iGroundStrategy.generateGroundVertices();
        ArrayList<Vector2> tempVertices = new ArrayList<>(vertices);

        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        final FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = friction;

        while (!tempVertices.isEmpty()) {

            final ChainShape chainShape = new ChainShape();
            fixtureDef.shape = chainShape;

            Vector2[] vector2s;

            if (tempVertices.size() > 1000) {
                vector2s = new Vector2[1000];
                for (int i = 0; i < 1000; i++) {
                    vector2s[i] = tempVertices.get(i);
                }

            } else {
                vector2s = new Vector2[tempVertices.size()];
                for (int i = 0; i < tempVertices.size(); i++) {
                    vector2s[i] = tempVertices.get(i);
                }
            }
            tempVertices.removeAll(Arrays.asList(vector2s.clone()));
            chainShape.createChain(vector2s);
            Body ground = world.createBody(bodyDef);
            ground.createFixture(fixtureDef);
        }
    }

    public ArrayList<Vector2> getVertices() {
        return vertices;
    }

}
