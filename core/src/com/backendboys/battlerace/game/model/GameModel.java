package com.backendboys.battlerace.game.model;

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

    public void removeListener(IModelListener modelListener){
        modelListeners.remove(modelListener);
    }

    private void notifyListeners() {
        for(IModelListener modelListener : modelListeners){
            modelListener.update();
        }
    }

    // Increase vehicle velocity, if touching ground
    public void gas() {
    }

    // Rotate vehicle left/backwards
    public void rotateLeft() {
    }

    // Decrease vehicle velocity, if touching ground
    public void brake() {
    }

    // Rotate vehicle right/forward
    public void rotateRight() {
    }

}
