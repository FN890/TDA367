package com.backendboys.battlerace.model.gamemodel.powerups;

import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Class that handles properties of nitro powerups.
 */
public class NitroPowerUp extends AbstractPowerUp {

    public NitroPowerUp() {
        super(new BodyDef(), new FixtureDef());
    }

    @Override
    public void use(Player player) {

    }
}
