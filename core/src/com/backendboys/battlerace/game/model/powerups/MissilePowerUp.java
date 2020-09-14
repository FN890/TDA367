package com.backendboys.battlerace.game.model.powerups;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class MissilePowerUp extends AbstractPowerUp {

    public MissilePowerUp(BodyDef bodyDef, FixtureDef fixtureDef) {
        super(bodyDef, fixtureDef);
    }
}
