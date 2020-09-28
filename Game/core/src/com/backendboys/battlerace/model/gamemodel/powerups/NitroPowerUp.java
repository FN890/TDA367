package com.backendboys.battlerace.model.gamemodel.powerups;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class NitroPowerUp extends AbstractPowerUp {

    public NitroPowerUp() {
        super(new BodyDef(), new FixtureDef());
    }

}
