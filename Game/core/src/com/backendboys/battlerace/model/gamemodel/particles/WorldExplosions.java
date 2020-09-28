package com.backendboys.battlerace.model.gamemodel.particles;

import java.util.ArrayList;

public class WorldExplosions {
    private ArrayList<Explosion> explosions = new ArrayList<>();

    public void addExplosion(Explosion explosion){
       explosions.add(explosion);
    }
    public void removeExplosions(){
        for (Explosion explosion: explosions){
            explosion.removeSlowParticles();
            if(explosion.explosionIsDead()){
                explosions.remove(explosion);
            }
        }
    }
}
