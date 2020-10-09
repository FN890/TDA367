package com.backendboys.battlerace.model.gamemodel.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class FinishLineGenerator {

    private ArrayList<Vector2> groundVertices;
    private ArrayList<Vector2> finishLineVerts;

    public FinishLineGenerator(ArrayList<Vector2> groundVertices) {
        this.groundVertices = groundVertices;
    }

    public void generateFinishLine(World world) {

        Body body = null;

        finishLineVerts = generateVertices();

        generateVertices();

        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        final FixtureDef fixtureDef = new FixtureDef();
        final PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(4f, 4f);

        fixtureDef.shape = boxShape;

        for (int i = 0; i < finishLineVerts.size(); i++) {
            bodyDef.position.set(finishLineVerts.get(i));

            body = world.createBody(bodyDef);
            body.createFixture(fixtureDef);
        }

        for (int i = 0; i < body.getFixtureList().size; i++) {
            body.getFixtureList().get(i).setSensor(true);
        }

        boxShape.dispose();
    }

    private ArrayList<Vector2> generateVertices() {
        ArrayList<Vector2> vertices = new ArrayList<>();

        float xPos, yPos;

        xPos = groundVertices.get(500).x;
        yPos = groundVertices.get((int)(xPos / 5)).y + 4;

        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 20; j++) {
                vertices.add(new Vector2(xPos, yPos));
                yPos += 4;
            }
            xPos += 8;
            yPos = groundVertices.get((int)(xPos / 5)).y + 4;
        }
        return vertices;
    }

    public ArrayList<Vector2> getFinishLineVerts() {
        return finishLineVerts;
    }
}
