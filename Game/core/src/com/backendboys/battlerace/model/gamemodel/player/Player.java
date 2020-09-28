package com.backendboys.battlerace.model.gamemodel.player;

import com.backendboys.battlerace.model.gamemodel.powerups.AbstractPowerUp;
import com.backendboys.battlerace.model.gamemodel.vehicle.IVehicle;
import com.backendboys.battlerace.model.gamemodel.vehicle.VehicleFactory;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {

    private UUID playerId;
    private String name;
    private ArrayList<AbstractPowerUp> listPowersUp = new ArrayList<>();

    private IVehicle vehicle;

    public Player(String name){
        this.name = name;
        this.playerId = UUID.randomUUID();
    }

    public void addVehicle(World world, float xPos, float yPos){
        this.vehicle = VehicleFactory.createSportsCar(world, xPos, yPos);
    }

    public String getName(){
        return name;
    }

    public UUID getPlayerId(){
        return playerId;
    }

    public List<AbstractPowerUp> getListPowerUp(){
        return (List<AbstractPowerUp>) listPowersUp.clone();
    }

    public void addPowerUp(AbstractPowerUp powerUp){
        listPowersUp.add(powerUp);
    }

    // Do something with vehicle or shoot a enemy, maybe change sprite
    public void usePowerUp(){
        if (listPowersUp.size() > 0) {
            listPowersUp.get(0).use(this);
        }
    }

    public void gas(){
        vehicle.gas();
    }

    public void brake(){
        vehicle.brake();
    }

    public void rotateLeft(){
        vehicle.rotateLeft();
    }

    public void rotateRight(){
        vehicle.rotateRight();
    }

    public Vector2 getPosition() {
        return vehicle.getPosition();
    }

    public IVehicle getVehicle() {
        return vehicle;
    }
}
