package com.backendboys.battlerace.model.gamemodel;

import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.backendboys.battlerace.model.gamemodel.powerups.AbstractPowerUp;
import com.backendboys.battlerace.model.gamemodel.vehicle.SportsCar;
import com.backendboys.battlerace.model.gamemodel.world.GameWorld;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * The model class, contains data and logic to change data.
 * Contains player and the methods that move the vehicle
 */
public class GameModel {

    private final GameWorld gameWorld;
    private final List<IModelListener> modelListeners = new ArrayList<>();

    // TODO: Create Factory for Car.
    private SportsCar car;
    private final Player player;

    public GameModel() {
        this.gameWorld = new GameWorld();

        Vector2 startPosition = gameWorld.getGroundVertices().get(50);
        player = new Player("Mad Max");
        player.addVehicle(gameWorld.getWorld(), startPosition.x, startPosition.y + 25);
    }

    // TODO: We dont need this. Updates can be made to model in the controller gameRendered();
    // TODO: ModelListeners will be used for things like: gameended(); gamestarted();
    public void update() {
        gameWorld.stepWorld();
        notifyListeners();
    }

    public void addListener(IModelListener modelListener) {
        modelListeners.add(modelListener);
    }

    public void removeListener(IModelListener modelListener) {
        modelListeners.remove(modelListener);
    }

    private void notifyListeners() {
        for (IModelListener modelListener : modelListeners) {
            modelListener.update();
        }
    }

    public void gas() {
        player.gas();
    }

    public void rotateLeft() {
        player.rotateLeft();
    }

    public void brake() {
        player.brake();
    }

    public void rotateRight() {
        player.rotateRight();
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public Player getPlayer() {
        return player;
    }

    public Vector2 getPlayerPosition() {
        return player.getPosition();
    }

    public float getPlayerRotation() {
        return player.getRotation();
    }

    /**
     * Temp function for testing speed
     */
    public void usePowerUp() {
        gameWorld.addExplosion(new Vector2(player.getPosition().x - 20, player.getPosition().y), 30);
    }

    public ArrayList<AbstractPowerUp> getPowerUps() {
        return gameWorld.getPowerUps();
    }

}
