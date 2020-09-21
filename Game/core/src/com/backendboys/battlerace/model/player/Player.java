package com.backendboys.battlerace.model.player;

import com.backendboys.battlerace.model.powerups.AbstractPowerUp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {

    private UUID playerId;
    private String name;
    private ArrayList<AbstractPowerUp> listPowersUp = new ArrayList<>();

    public Player(String name){
        this.name = name;
        this.playerId = UUID.randomUUID();
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
