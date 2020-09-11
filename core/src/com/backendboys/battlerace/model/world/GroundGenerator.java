package com.backendboys.battlerace.model.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class GroundGenerator {

    private int numberVertices;
    private double step;
    private final ArrayList<Vector2> vertices;

    public GroundGenerator(int numberVertices, double step) {
        this.numberVertices = numberVertices;
        this.step = step;
        vertices = new ArrayList<>();
    }


    // TODO: 2020-09-08  case i == vertices.length - 1 
    public void generateGround(World world) {
        generateVertices();

        for (int i = 0; i < vertices.size(); i++) {
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.friction = 5;

            PolygonShape shape = new PolygonShape();
            if (i + 1 < vertices.size()) {
                shape.setAsBox(vertices.get(i + 1).x - vertices.get(i).x, (float) 1);
                fixtureDef.shape = shape;
                Body ground = world.createBody(bodyDef);
                ground.createFixture(fixtureDef);
                float rads = calculateAngle(vertices.get(i), vertices.get(i + 1));
                ground.setTransform(vertices.get(i).x, vertices.get(i).y, rads);
            }
            shape.dispose();
        }
    }

    private void generateVertices() {
        float xPos = 0, yPos = 0;
        for (int i = 0; i < numberVertices; i++) {
            yPos = (10 * (float) Math.sin(xPos * 0.1)) + 1;
            xPos += step;
            vertices.add(new Vector2(xPos, yPos + 10));
        }
    }

    private float calculateAngle(Vector2 vec, Vector2 vec2) {
        double value = (double) (vec2.y - vec.y) / (double) (vec2.x - vec.x);
        double aTan = Math.atan(value);
        double degrees = Math.toDegrees(aTan);
        float rads = (float) (Math.toRadians(degrees));
        return rads;
    }

    public int getNumberVertices() {
        return numberVertices;
    }

    public double getStep() {
        return step;
    }
}
