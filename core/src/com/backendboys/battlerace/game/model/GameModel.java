package com.backendboys.battlerace.game.model;

import com.backendboys.battlerace.game.model.world.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private final GameWorld gameWorld;
    private final List<IModelListener> modelListeners = new ArrayList<>();

    // TODO: Create Factory for Car.
    //private Car car;

    public GameModel() {
        this.gameWorld = new GameWorld();

    }

    // TODO: We dont need this. Updates can be made to model in the controller gameRendered();
    // TODO: ModelListeners will be used for things like: gameended(); gamestarted();
    public void update() {
        gameWorld.stepWorld();
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

    public void gas() {
        //car.gas();
    }

    public void rotateLeft() {
        //car.rotateLeft();
    }

    public void brake() {
        //car.brake();
    }

    public void rotateRight() {
        //car.rotateRight();
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

}
