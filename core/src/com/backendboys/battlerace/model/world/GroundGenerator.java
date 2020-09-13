package com.backendboys.battlerace.model.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class GroundGenerator {

    private int numberVertices;
    private double step;
    private float friction;
    private final ArrayList<Vector2> vertices;

    public GroundGenerator(int numberVertices, double step) {
        this.numberVertices = numberVertices;
        this.step = step;
        friction = 5;
        vertices = new ArrayList<>();
    }

    public void generateGround(World world) {
        generateVertices();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = friction;

        for (int i = 0; i < vertices.size(); i++) {
            PolygonShape shape = new PolygonShape();
            if (i + 1 < vertices.size()) {
                shape.setAsBox(calculateDistance(vertices.get(i), vertices.get(i + 1)), (float) 1);
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
            yPos = (10 * (float) Math.sin(xPos * 0.1)) + 10;
            xPos += step;
            vertices.add(new Vector2(xPos, yPos + 10));
        }
    }

    private float calculateDistance(Vector2 vec, Vector2 vec2) {
        return (float) (Math.sqrt(Math.pow((vec2.y - vec.y), 2) + Math.pow((vec2.x - vec.x), 2)));
    }

    private float calculateAngle(Vector2 vec, Vector2 vec2) {
        double value = (double) (vec2.y - vec.y) / (double) (vec2.x - vec.x);
        double aTan = Math.atan(value);
        double degrees = Math.toDegrees(aTan);
        return (float) (Math.toRadians(degrees));
    }

    public int getNumberVertices() {
        return numberVertices;
    }

    public void setNumberVertices(int numberVertices) {
        this.numberVertices = numberVertices;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public ArrayList<Vector2> getVertices() {
        return vertices;
    }
}
