package com.backendboys.battlerace.model.powerups;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class NitroPowerUp extends AbstractPowerUp {

    public NitroPowerUp(BodyDef bodyDef, FixtureDef fixtureDef) {
        super(bodyDef, fixtureDef);
    }
}
