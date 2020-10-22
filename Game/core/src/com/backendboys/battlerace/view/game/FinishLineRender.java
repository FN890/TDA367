package com.backendboys.battlerace.view.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

/**
 * Class that handles rendering of the finish line.
 */
public class FinishLineRender extends AbstractRender{

    private final List<Vector2> finishLineVerts;
    private final ShapeRenderer shapeRenderer;

    private final static int WIDTH = 8, HEIGHT = 5;

    /**
     * @param orthographicCamera The camera the game is using.
     * @param finishLineVerts    The positions of the finish line blocks.
     */
    public FinishLineRender(OrthographicCamera orthographicCamera, List<Vector2> finishLineVerts) {
        super(orthographicCamera);
        this.finishLineVerts = finishLineVerts;
        this.shapeRenderer = new ShapeRenderer();
    }

    /**
     * Method that renders the blocks, called everytime the world steps.
     */
    public void render(SpriteBatch batch) {
        renderFinishLine();
    }

    /**
     * Renders the finish line if it is within the view of the camera.
     */
    private void renderFinishLine() {
        for (int i = 0; i < finishLineVerts.size(); i++) {
            if (withinCamera(finishLineVerts.get(i))) {
                shapeRenderer.setProjectionMatrix(getProjectionMatrix());

                if ((int) finishLineVerts.get(i).y % 3 == 0) {
                    shapeRenderer.setColor(Color.BLACK);
                } else {
                    shapeRenderer.setColor(Color.WHITE);
                }

                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.rect(finishLineVerts.get(i).x - 4, finishLineVerts.get(i).y - 4, WIDTH, HEIGHT);
                shapeRenderer.end();
            }
        }
    }

    /**
     * Disposes the shapeRender.
     */
    public void dispose() {
        shapeRenderer.dispose();
    }

    private boolean withinCamera(Vector2 vector2) {
        if (vector2.x > getCameraPosition().x - getViewportWidth()) {
            return vector2.x < getCameraPosition().x + getViewportWidth();
        }
        return false;
    }

}