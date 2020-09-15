package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.model.GameModel;
import com.backendboys.battlerace.model.world.GameWorld;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController implements InputProcessor {

    private final GameModel gameModel;

    private final List<Integer> keysDown;

    public GameController() {
        gameModel = new GameModel();

        keysDown = new ArrayList<>();
    }

    public void gameRendered() {
        gameModel.getGameWorld().stepWorld();
        handleKeyPresses();
    }

    private void handleKeyPresses() {
        for (int keycode : keysDown) {
            switch (keycode) {
                case Input.Keys.W:
                    gameModel.gas();
                    break;
                case Input.Keys.A:
                    gameModel.rotateLeft();
                    break;
                case Input.Keys.S:
                    gameModel.brake();
                    break;
                case Input.Keys.D:
                    gameModel.rotateRight();
                    break;
            }
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        keysDown.add(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        keysDown.removeAll(Arrays.asList(keycode));
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        switch (character) {
            case 'w':

                break;
            case 'a':

                break;
            case 's':

                break;
            case 'd':

                break;
        }
        return false;
    }

    public GameWorld getGameWorld() {
        return gameModel.getGameWorld();
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
