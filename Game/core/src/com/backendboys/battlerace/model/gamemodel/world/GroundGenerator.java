package com.backendboys.battlerace.model.gamemodel.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Class that handles ground creation.
 */
public class GroundGenerator {

    private final int numberVertices;
    private final double step;
    private final float friction;
    private final ArrayList<Vector2> vertices;

    /**
     * @param numberVertices The amount of vertices that the ground should be based on.
     * @param step           The difference on the x-axis between each vertex.
     * @param friction       The ground friction.
     */
    public GroundGenerator(int numberVertices, double step, int friction) {
        this.numberVertices = numberVertices;
        this.step = step;
        this.friction = friction;
        vertices = new ArrayList<>();
    }

    /**
     * Creates and adds the ground to the world.
     *
     * @param world Adds the ground to the specific world.
     */
    public void generateGround(World world) {

        generateVertices();
        ArrayList<Vector2> tempVertices = new ArrayList<>(vertices);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = friction;

        while (!tempVertices.isEmpty()) {

            ChainShape chainShape = new ChainShape();
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

    private void generateVertices() {
        float xPos = 0, yPos = 0;

        for (int i = 0; i < numberVertices; i++) {
            yPos = 30 * (float) Math.sin(xPos * 0.01) + 30;
            xPos += step;
            vertices.add(new Vector2(xPos, yPos + 10));
        }
    }

    public ArrayList<Vector2> getVertices() {
        return vertices;
    }

    public int getNumberVertices() {
        return numberVertices;
    }
}
