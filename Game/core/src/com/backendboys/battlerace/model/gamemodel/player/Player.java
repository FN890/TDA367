package com.backendboys.battlerace.model.gamemodel.player;

import com.backendboys.battlerace.model.gamemodel.powerups.AbstractPowerUp;
import com.backendboys.battlerace.model.gamemodel.vehicle.IVehicle;
import com.backendboys.battlerace.model.gamemodel.vehicle.VehicleFactory;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.List;
import java.util.Stack;
import java.util.UUID;

/**
 * Player is a class containing a vehicle and a list of powerups.
 * A Player is a user that will move across the gameworld.
 */
public class Player {

    private final UUID playerId;
    private final String name;
    private final Stack<AbstractPowerUp> powerUpStack = new Stack<>();

    private IVehicle vehicle;

    /**
     * @param name Name of player
     */
    public Player(String name) {
        this.name = name;
        this.playerId = UUID.randomUUID();
    }

    /**
     * @param world is needed for creating the body of a vehicle.
     * @param xPos  spawn position of the car in the world
     * @param yPos  spawn position of the car in the world
     */
    public void addVehicle(World world, float xPos, float yPos) {
        this.vehicle = VehicleFactory.createSportsCar(world, xPos, yPos);
    }

    /**
     * @return Name of player
     */
    public String getName() {
        return name;
    }

    /**
     * @return Unique id of player
     */
    public UUID getPlayerId() {
        return playerId;
    }

    /**
     * @return list of {@link AbstractPowerUp()} cloned
     */
    public List<AbstractPowerUp> getListPowerUp() {
        return (List<AbstractPowerUp>) powerUpStack.clone();
    }

    /**
     * @param powerUp Add a {@link AbstractPowerUp()} to Player
     */
    public void addPowerUp(AbstractPowerUp powerUp) {
        powerUpStack.add(powerUp);
    }

    /**
     * Use a powerup if it exist one.
     */
    public void usePowerUp() {
        if (powerUpStack.size() > 0) {
            powerUpStack.get(0).use(this);
        }
        powerUpStack.pop();
    }

    /**
     * Press on the gas pedal.
     */
    public void gas() {
        vehicle.gas();
    }

    /**
     * Press on the brake.
     */
    public void brake() {
        vehicle.brake();
    }

    /**
     * Tilt the vehicle left
     */
    public void rotateLeft() {
        vehicle.rotateLeft();
    }

    /**
     * Tilt the vehicle right
     */
    public void rotateRight() {
        vehicle.rotateRight();
    }

    /**
     * @return The position of vehicle
     */
    public Vector2 getPosition() {
        return vehicle.getPosition();
    }

    public IVehicle getVehicle() {
        return vehicle;
    }

    public float getRotation() {
        return vehicle.getRotation();
    }
}
