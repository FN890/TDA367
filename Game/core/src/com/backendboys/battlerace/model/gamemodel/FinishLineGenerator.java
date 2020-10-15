package com.backendboys.battlerace.model.gamemodel;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;


/**
 * Class that handles generation of a finish line.
 */
public class FinishLineGenerator {

    private final ArrayList<Vector2> groundVertices;
    private ArrayList<Vector2> finishLineVertices;

    /**
     * @param groundVertices List of vertices for the ground in the world.
     */
    public FinishLineGenerator(ArrayList<Vector2> groundVertices) {
        this.groundVertices = groundVertices;
    }

    /**
     * Generates the finish line and spawns it in the world.
     *
     * @param world The world the finish line will spawn in.
     */
    public void generateFinishLine(World world) {

        Body body;

        finishLineVertices = generateVertices();

        generateVertices();

        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        final FixtureDef fixtureDef = new FixtureDef();
        final PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(4f, 4f);

        fixtureDef.shape = boxShape;

        for (int i = 0; i < finishLineVertices.size(); i++) {
            bodyDef.position.set(finishLineVertices.get(i));

            body = world.createBody(bodyDef);
            body.createFixture(fixtureDef);

            body.getFixtureList().get(0).setSensor(true);
        }

        boxShape.dispose();
    }

    private ArrayList<Vector2> generateVertices() {
        ArrayList<Vector2> vertices = new ArrayList<>();

        float xPos, yPos;

        xPos = groundVertices.get(500).x;
        yPos = groundVertices.get((int) (xPos / 5)).y + 4;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 20; j++) {
                vertices.add(new Vector2(xPos, yPos));
                yPos += 4;
            }
            xPos += 8;
            yPos = groundVertices.get((int) (xPos / 5)).y + 4;
        }
        return vertices;
    }

    /**
     * @return The vertices for the finish line.
     */
    public ArrayList<Vector2> getFinishLineVertices() {
        return finishLineVertices;
    }
}
