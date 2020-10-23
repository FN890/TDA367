package com.backendboys.battlerace.model.player;

import com.backendboys.battlerace.model.powerups.IPowerUp;
import com.backendboys.battlerace.model.vehicle.IVehicle;
import com.backendboys.battlerace.model.vehicle.VehicleFactory;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.List;
import java.util.Stack;

/**
 * Player is a class containing a vehicle and a list of powerups.
 * A Player is a user that will move across the gameworld.
 */
public class Player {

    private String name;
    private final Stack<IPowerUp> powerUpStack = new Stack<>();

    private IVehicle vehicle;

    /**
     * @param name Name of player
     */
    public Player(String name) {
        this.name = name;
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
     * Use a powerup if one exists.
     */
    public void usePowerUp() {
        if (powerUpStack.size() > 0) {
            powerUpStack.pop().use(this);
        }
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
     * @return Name of player
     */
    public String getName() {
        return name;
    }

    /**
     * @return list of {@link IPowerUp()} cloned
     */
    public List<IPowerUp> getListPowerUp() {
        return (List<IPowerUp>) powerUpStack.clone();
    }

    public IPowerUp getNextPowerUp() {
        return powerUpStack.peek();
    }

    /**
     * @param powerUp Add a {@link IPowerUp()} to Player
     */
    public void addPowerUp(IPowerUp powerUp) {
        powerUpStack.add(powerUp);
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

    public World getWorld() {
        return vehicle.getWorld();
    }

    public Vector2 getLinearVelocity() {
        return vehicle.getLinearVelocity();
    }

    public void setName(String name) {
        this.name = name;
    }
}
