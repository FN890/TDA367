package com.backendboys.battlerace.model.gamemodel.powerups;

import com.badlogic.gdx.physics.box2d.*;

public abstract class AbstractPowerUp {
    BodyDef bodyDef;
    FixtureDef fixtureDef;

    public AbstractPowerUp(BodyDef bodyDef, FixtureDef fixtureDef) {
        this.bodyDef = bodyDef;
        this.fixtureDef = fixtureDef;
    }

    public void InstantiateBody(World world, float spawnPosx, float spawnPosy) {
        bodyDef.type = BodyDef.BodyType.StaticBody;
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(2f, 2f);
        fixtureDef.shape = boxShape;

        bodyDef.position.set(spawnPosx, spawnPosy);

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);

        boxShape.dispose();
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }
}
