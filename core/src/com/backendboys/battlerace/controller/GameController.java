package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.model.GameModel;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class GameController implements InputProcessor {

    //Get inputs from views and do changes in model
    //Comment

    private GameModel gameModel;

    public GameController() {
        gameModel = new GameModel();
    }

    @Override
    public boolean keyDown(int keycode) {
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
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:

                break;
            case Input.Keys.A:

                break;
            case Input.Keys.S:

                break;
            case Input.Keys.D:

                break;
        }
        return false;
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
