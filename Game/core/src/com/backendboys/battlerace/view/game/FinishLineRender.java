package com.backendboys.battlerace.view.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Class that handles rendering of the finish line.
 */
public class FinishLineRender {

    private final OrthographicCamera orthographicCamera;
    private final ArrayList<Vector2> finishLineVerts;
    private final ShapeRenderer shapeRenderer;

    private final static int WIDTH = 8, HEIGHT = 5;

    public FinishLineRender(OrthographicCamera orthographicCamera, ArrayList<Vector2> finishLineVerts) {
        this.orthographicCamera = orthographicCamera;
        this.finishLineVerts = finishLineVerts;
        this.shapeRenderer = new ShapeRenderer();
    }

    /**
     * Calls the method that renders the finish, should be called every render.
     */
    public void renderFinishLine() {
        render();
    }

    private void render() {
        for (int i = 0; i < finishLineVerts.size(); i++) {
            if (withinCamera(finishLineVerts.get(i))) {
                shapeRenderer.setProjectionMatrix(orthographicCamera.combined);

                if ((int)finishLineVerts.get(i).y % 3 == 0) {
                    shapeRenderer.setColor(Color.BLACK);
                }
                else {
                    shapeRenderer.setColor(Color.WHITE);
                }

                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.rect(finishLineVerts.get(i).x - 4, finishLineVerts.get(i).y - 4, WIDTH, HEIGHT);
                shapeRenderer.end();
            }
        }
    }

    private boolean withinCamera(Vector2 vector2) {
        if (vector2.x > orthographicCamera.position.x - orthographicCamera.viewportWidth) {
            return vector2.x < orthographicCamera.position.x + orthographicCamera.viewportWidth;
        }
        return false;
    }

}