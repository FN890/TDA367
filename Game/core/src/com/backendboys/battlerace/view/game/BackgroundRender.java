package com.backendboys.battlerace.view.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Class used for rendering the game background.
 */
public class BackgroundRender extends AbstractRender<Object> {

    private final float rectWidth;
    private final ArrayList<Vector2> groundVertices;
    private final ShapeRenderer shapeRenderer;

    /**
     * @param orthographicCamera Used setting the projection matrix.
     * @param groundVertices     The ground vertices of the world that should be rendered.
     */
    public BackgroundRender(OrthographicCamera orthographicCamera, ArrayList<Vector2> groundVertices) {
        super(orthographicCamera);
        this.groundVertices = groundVertices;
        this.shapeRenderer = new ShapeRenderer();

        rectWidth = calculateDX(groundVertices.get(1), groundVertices.get(0));
    }

    @Override
    public void render(SpriteBatch batch, Object object) {
        renderBackground();
    }

    private void renderBackground() {
        generateSky();
        generateDirt();
        generateGround();
    }

    public void dispose() {
        shapeRenderer.dispose();
    }

    private void generateGround() {
        for (int i = 0; i < groundVertices.size(); i++) {
            if (i + 1 < groundVertices.size()) {
                if (withinCameraView(groundVertices.get(i))) {
                    shapeRenderer.setProjectionMatrix(getProjectionMatrix());
                    shapeRenderer.setColor(Color.GREEN);
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.rectLine(groundVertices.get(i), groundVertices.get(i + 1), rectWidth);
                    shapeRenderer.end();
                }
            }
        }
    }

    private void generateDirt() {
        for (int i = 0; i < groundVertices.size(); i++) {
            if (i + 1 < groundVertices.size()) {
                if (withinCameraView(groundVertices.get(i))) {
                    shapeRenderer.setProjectionMatrix(getProjectionMatrix());
                    shapeRenderer.setColor(Color.BROWN);
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.box(groundVertices.get(i).x, groundVertices.get(i).y, 1, rectWidth, -groundVertices.get(i).y * 2, 1);
                    shapeRenderer.end();
                }
            }
        }
    }

    private void generateSky() {
        for (int i = 0; i < groundVertices.size(); i++) {
            if (i + 1 < groundVertices.size()) {
                if (withinCameraView(groundVertices.get(i))) {
                    shapeRenderer.setProjectionMatrix(getProjectionMatrix());
                    shapeRenderer.setColor(Color.SKY);
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.box(groundVertices.get(i).x, groundVertices.get(i).y + rectWidth, 1, rectWidth, getViewportHeight() * 2, 1);
                    shapeRenderer.end();
                }
            }
        }
    }

    private boolean withinCameraView(Vector2 vec2) {
        if (vec2.x > getCameraPosition().x - getViewportWidth()) {
            return vec2.x < getCameraPosition().x + getViewportWidth();
        } else {
            return false;
        }
    }

    private float calculateDX(Vector2 v1, Vector2 v2) {
        return v2.x - v1.x;
    }
}
