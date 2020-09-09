package com.backendboys.battlerace.model;

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
}
