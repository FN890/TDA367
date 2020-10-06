package com.backendboys.battlerace.model.gamemodel.powerups;

import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Class that handles the properties of the missile powerup.
 */
public class MissilePowerUp extends AbstractPowerUp {

    public MissilePowerUp() {
        super(new BodyDef(), new FixtureDef());
    }

    @Override
    public void use(Player player) {

    }

    @Override
    public String toString() {
        return "Missile PowerUp";
    }
}
