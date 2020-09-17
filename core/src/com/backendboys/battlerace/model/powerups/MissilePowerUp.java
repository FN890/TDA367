package com.backendboys.battlerace.model.powerups;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class MissilePowerUp extends AbstractPowerUp {

    public MissilePowerUp() {
        super(new BodyDef(), new FixtureDef());
    }
}
