package com.backendboys.battlerace.model.powerups;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.awt.*;
import java.awt.geom.RectangularShape;

public abstract class AbstractPowerUp {
    BodyDef bodyDef;
    FixtureDef fixtureDef;

    public AbstractPowerUp(BodyDef bodyDef, FixtureDef fixtureDef) {
        this.bodyDef = bodyDef;
        this.fixtureDef = fixtureDef;

        bodyDef.type = BodyDef.BodyType.StaticBody;
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(0.5f, 0.5f);
        fixtureDef.shape = boxShape;
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }
}
