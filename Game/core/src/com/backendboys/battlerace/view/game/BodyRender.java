package com.backendboys.battlerace.view.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;

public class BodyRender {

    private final OrthographicCamera orthographicCamera;
    private final ShapeRenderer shapeRenderer;

    public BodyRender(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;
        this.shapeRenderer = new ShapeRenderer();
    }

    public void renderBody(Body body) {
    }

}
