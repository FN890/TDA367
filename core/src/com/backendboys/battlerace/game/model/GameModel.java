package com.backendboys.battlerace.game.model;

import com.backendboys.battlerace.game.model.vehicle.Car;
import com.backendboys.battlerace.game.model.world.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private final GameWorld gameWorld;
    private final List<IModelListener> modelListeners = new ArrayList<>();

    private Car car;

    public GameModel() {
        this.gameWorld = new GameWorld();
        car = new Car(gameWorld.getWorld(), 10, 100);

        //new Thread(new GameThread()).start();
    }

    private void update() {
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

    // Increase vehicle velocity, if touching ground
    public void gas() {
        car.gas();
    }

    // Rotate vehicle left/backwards
    public void rotateLeft() {
    }

    // Decrease vehicle velocity, if touching ground
    public void brake() {
        car.brake();
    }

    // Rotate vehicle right/forward
    public void rotateRight() {
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    private class GameThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                update();
            }
        }
    }

}
