package com.backendboys.battlerace;

import com.backendboys.battlerace.controller.GameController;
import com.backendboys.battlerace.controller.MenuController;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;

public class BattleRace extends Game {

    @Override
    public void create() {
        MenuController menuController = new MenuController(this);
    }
    
    public void startMenu() {
        MenuController menuController = new MenuController(this);
    }

    public void startSinglePlayer(){
        GameController gameController = new GameController(this);
        Gdx.input.setInputProcessor(gameController);
    }

    public void startMultiplayer(){
        //Start multiplayerController
        //Controller got reference to server model?
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }

}
