package com.backendboys.battlerace.view.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Currently not in use but is an idea for future versions
 */
public class BodyRender extends AbstractRender<Body> {

    public BodyRender(OrthographicCamera orthographicCamera) {
        super(orthographicCamera);
    }

    @Override
    public void render(SpriteBatch batch, Body object) {

    }
}
