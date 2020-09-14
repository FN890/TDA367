package com.backendboys.battlerace.game.model.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite implements InputProcessor {

    //The speed at which the player moves, and the gravity strength
    private final Vector2 velocity = new Vector2();
    private final float speed = 120;
    private final float gravity = 60 * 1.8f;

    public Player(Sprite sprite) {
        super(sprite);
    }

    public void draw(SpriteBatch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    private void update(float delta) {
        //Applies gravity
        //velocity.y -= gravity * delta;

        //Increases fall speed with 'speed' as max velocity
      /*  if (velocity.y > speed) {
            velocity.y = speed;
        }
        else if (velocity.y < speed) {
            velocity.y = -speed;
        }
        */
        //Moves the player
        setX(getX() + velocity.x * delta);
        setY(getY() + velocity.y * delta);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                velocity.y = speed;
                break;
            case Input.Keys.A:
                velocity.x = -speed;
                break;
            case Input.Keys.S:
                velocity.y = -speed;
                break;
            case Input.Keys.D:
                velocity.x = speed;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.D:
                velocity.x = 0;
                break;
            case Input.Keys.W:
            case Input.Keys.S:
                velocity.y = 0;
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
