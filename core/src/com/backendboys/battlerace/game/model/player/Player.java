package com.backendboys.battlerace.game.model.player;

import java.util.ArrayList;

public class Player {

    private int playerId;
    private String name;
    private AbstractVehicle vehicle;
    private ArrayList<AbstractPowerUp> listPowersUp = new ArrayList<>();

    public Player(String name, int playerId){
        this.name = name;
        this.playerId = playerId;
    }

    public void addPowerUp(AbstractPowerUp powerUp){
        listPowersUp.add(powerUp);
    }

    // Do something with vehicle or shoot a enemy, maybe change sprite
    public void usePowerUp(){

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


}
