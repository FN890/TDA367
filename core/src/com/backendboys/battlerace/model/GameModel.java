package com.backendboys.battlerace.model;

import com.badlogic.gdx.Input;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    List<IModelListener> modelListeners = new ArrayList<>();

    //Update every 1/60 sec, and notify listeners

    private void update(){

        //Do logic changes
        notifyListeners();
    }

    public void addListener(IModelListener modelListener){
        modelListeners.add(modelListener);
    }

    public void removeListerner(IModelListener modelListener){
        modelListeners.remove(modelListener);
    }

    private void notifyListeners() {
        for(IModelListener modelListener : modelListeners){
            modelListener.update();
        }
    }

    public void keyDown(int keycode){
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
    }

    public void keyUp(int keycode){
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
    }

    public void keyTyped(int keycode){
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
    }
}
