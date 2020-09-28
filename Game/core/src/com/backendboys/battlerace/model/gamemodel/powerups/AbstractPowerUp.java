package com.backendboys.battlerace.model.gamemodel.powerups;

import com.backendboys.battlerace.model.gamemodel.player.Player;
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
        boxShape.setAsBox(4f, 4f);
        fixtureDef.shape = boxShape;

        bodyDef.position.set(spawnPosx, spawnPosy);

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);

        boxShape.dispose();
    }

    protected abstract void onPickup(Player player);

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }
}
