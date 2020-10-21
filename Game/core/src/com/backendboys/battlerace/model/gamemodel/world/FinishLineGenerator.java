package com.backendboys.battlerace.model.gamemodel.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;


/**
 * Class that handles generation of a finish line.
 */
public class FinishLineGenerator {

    private static final int SPACE_BETWEEN_BLOCKS_X = 8;
    private static final int SPACE_BETWEEN_BLOCKS_Y = 4;
    private static final int ROWS = 5;
    private static final int COLUMNS = 20;
    private static final int STEP = 5;

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
            body.createFixture(fixtureDef).setUserData(this);

            body.getFixtureList().get(0).setSensor(true);
        }

        boxShape.dispose();
    }

    private ArrayList<Vector2> generateVertices() {
        ArrayList<Vector2> vertices = new ArrayList<>();

        float xPos, yPos;

        xPos = groundVertices.get(groundVertices.size() - SPACE_BETWEEN_BLOCKS_X).x;
        yPos = groundVertices.get((int) (xPos / 5) - 1).y + SPACE_BETWEEN_BLOCKS_Y;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                vertices.add(new Vector2(xPos, yPos));
                yPos += 4;
            }
            xPos -= SPACE_BETWEEN_BLOCKS_X;
            yPos = groundVertices.get((int) (xPos / STEP)).y + SPACE_BETWEEN_BLOCKS_Y;
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
