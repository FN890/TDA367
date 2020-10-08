package com.backendboys.battlerace.view.game;

import com.backendboys.battlerace.model.gamemodel.particles.IParticle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class MissileRender {

    private final OrthographicCamera orthographicCamera;
    private final ArrayList<IParticle> missiles;
    private final SpriteBatch batch;
    private final Sprite sprite;

    private final static int WIDTH = 10, HEIGHT = 10;

    public MissileRender(OrthographicCamera orthographicCamera, ArrayList<IParticle> powerUps) {
        this.orthographicCamera = orthographicCamera;
        this.missiles = powerUps;
        batch = new SpriteBatch();
        Texture texture = new Texture(Gdx.files.internal(""));
        sprite = new Sprite(texture);
        sprite.setSize(WIDTH,HEIGHT);
    }

    public void render(){
        for (IParticle missile: missiles){
            if(withinCamera(missile)){
                batch.begin();
                batch.setProjectionMatrix(orthographicCamera.combined);
                sprite.setPosition(missile.getPosition().x,missile.getPosition().y);
                sprite.draw(batch);
                batch.end();
            }
        }
    }

    private boolean withinCamera(IParticle missile){
        if (missile.getPosition().x > orthographicCamera.position.x - orthographicCamera.viewportWidth){
            return missile.getPosition().x < orthographicCamera.position.x + orthographicCamera.viewportWidth;
        }
        return false;
    }
}
