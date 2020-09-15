package com.backendboys.battlerace.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class BackgroundGenerator {

    private final OrthographicCamera orthographicCamera;
    private final ArrayList<Vector2> groundVertices;
    private final ShapeRenderer shapeRenderer;

    public BackgroundGenerator(OrthographicCamera orthographicCamera, ArrayList<Vector2> groundVertices) {
        this.orthographicCamera = orthographicCamera;
        this.groundVertices = groundVertices;
        this.shapeRenderer = new ShapeRenderer();
    }

    public void renderBackground() {
        //generateSky();
        generateGround();
        generateDirt();

    }

    public void dispose() {
        shapeRenderer.dispose();
    }

    private void generateGround() {
        for (int i = 0; i < groundVertices.size(); i++) {
            if (i + i < groundVertices.size()) {
                if (withinCameraView(groundVertices.get(i))) {
                    shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
                    shapeRenderer.setColor(Color.GREEN);
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.rectLine(groundVertices.get(i), groundVertices.get(i + 1), 3);
                    shapeRenderer.end();
                }
            }
        }
    }

    private void generateDirt() {
        for (int i = 0; i < groundVertices.size(); i++) {
            if (i + i < groundVertices.size()) {
                if (withinCameraView(groundVertices.get(i))) {
                    shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
                    shapeRenderer.setColor(Color.BROWN);
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.box(groundVertices.get(i).x, groundVertices.get(i).y, 1, 1, -groundVertices.get(i).y, 1);
                    shapeRenderer.end();
                }
            }
        }
    }

    private void generateSky() {
        for (int i = 0; i < groundVertices.size(); i++) {
            if (i + i < groundVertices.size()) {
                if (withinCameraView(groundVertices.get(i))) {
                    shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
                    shapeRenderer.setColor(Color.SKY);
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.box(groundVertices.get(i).x, groundVertices.get(i).y + 1, 1, 1, orthographicCamera.viewportHeight, 1);
                    shapeRenderer.end();
                }
            }
        }
    }

    private boolean withinCameraView(Vector2 vec2) {
        if (vec2.x > orthographicCamera.position.x - (orthographicCamera.viewportWidth / 2)) {
            return vec2.x < orthographicCamera.position.x + (orthographicCamera.viewportWidth / 2);
        } else {
            return false;
        }
    }
}
