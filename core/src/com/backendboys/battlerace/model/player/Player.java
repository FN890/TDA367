package com.backendboys.battlerace.model.player;

import com.backendboys.battlerace.model.powerups.AbstractPowerUp;

import java.util.ArrayList;
import java.util.UUID;

public class Player {

    private UUID playerId;
    private String name;
    //private AbstractVehicle vehicle;
    private ArrayList<AbstractPowerUp> listPowersUp = new ArrayList<>();

    public Player(String name, int playerId){
        this.name = name;
        this.playerId = UUID.randomUUID();
    }

    public void addPowerUp(AbstractPowerUp powerUp){
        listPowersUp.add(powerUp);
    }

    // Do something with vehicle or shoot a enemy, maybe change sprite
    public void usePowerUp(){

    }

    public void gas(){
        //vehicle.gas();
    }

    public void brake(){
        //vehicle.brake();
    }

    public void rotateLeft(){
        //vehicle.rotateLeft();
    }

    public void rotateRight(){
        //vehicle.rotateRight();
    }


}
