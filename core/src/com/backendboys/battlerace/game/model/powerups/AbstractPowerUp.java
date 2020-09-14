package com.backendboys.battlerace.game.model.powerups;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public abstract class AbstractPowerUp {
    BodyDef bodyDef;
    FixtureDef fixtureDef;

    public AbstractPowerUp(BodyDef bodyDef, FixtureDef fixtureDef) {
        this.bodyDef = bodyDef;
        this.fixtureDef = fixtureDef;
    }
}
