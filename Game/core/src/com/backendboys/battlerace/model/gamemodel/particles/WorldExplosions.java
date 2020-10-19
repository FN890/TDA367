package com.backendboys.battlerace.model.gamemodel.particles;

import com.backendboys.battlerace.model.gamemodel.GameModel;
import com.backendboys.battlerace.model.gamemodel.world.GameWorldListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * This is the class that creates the explosions in the world
 * and the class that is exposed outside the particles package
 */
public class WorldExplosions implements GameWorldListener {


    /**
     * A list of all the explosions that have been created in the world
     */

    private final ArrayList<Explosion> explosions = new ArrayList<>();
    private final ArrayList<OnImpactMissile> missiles = new ArrayList<>();
    private final ArrayList<IMissileListener> missileListeners = new ArrayList<>();

    public WorldExplosions() {
    }

    //TODO: TEMP PARAMETER!
    private GameModel gameModel;

    public WorldExplosions(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    /**
     * Adds an explosion to the world at a set position
     *
     * @param pos          position for the explosion
     * @param numParticles the number off particles that are set the be generated
     * @param world        The world in which the explosion occurs
     */
    public void addExplosion(Vector2 pos, int numParticles, World world) {
        if (numParticles > 0) {
            explosions.add(new Explosion(pos, numParticles, world));
        }
    }

    public int getNumberOffExplosions() {
        return explosions.size();
    }

    public int getTotalExplosionParticles() {
        int particles = 0;
        for (Explosion explosion : explosions) {
            particles += explosion.getNParticles();
        }
        return particles;
    }

    /**
     * Removes particles and Explodes collided Missiles
     */
    private void removeCollidedMissilesAndParticles() {
        removeCollidedParticles();
        removeAndExplodeMissiles();
    }

    /**
     * Removes ExplosionParticles that have collided from the world
     * Also removes Explosions from the ExplosionsList -
     * if there are no more particles in that particular Explosion Object
     */
    private void removeCollidedParticles() {
        ArrayList<Explosion> removedExplosions = new ArrayList<>();
        for (Explosion explosion : explosions) {
            explosion.removeCollidedParticles();
            if (explosion.explosionIsDead()) {
                removedExplosions.add(explosion);
            }
        }
        for (Explosion removedExplosion : removedExplosions) {
            explosions.remove(removedExplosion);
        }
    }

    /**
     * Explodes Missiles if they have collided
     * creates an explosion at the collision point
     * Also removes Missiles that are under the map
     */
    private void removeAndExplodeMissiles() {
        ArrayList<OnImpactMissile> removedMissiles = new ArrayList<>();
        for (OnImpactMissile missile : missiles) {
            Body body = missile.getBody();
            if (missile.getToBeRemoved()) {
                addExplosion(body.getPosition(), OnImpactMissile.getNumParticles(), body.getWorld());
                removedMissiles.add(missile);
                body.getWorld().destroyBody(body);
            } else if (body.getPosition().x < 0) {
                removedMissiles.add(missile);
                body.getWorld().destroyBody(body);
            }
        }
        for (OnImpactMissile missile : removedMissiles) {
            missiles.remove(missile);
        }
    }

    /**
     * Adds a missile to the world
     *
     * @param world The world where the missile spawns
     * @param pos   the position of the missile
     */
    // TODO: TEMP EDIT HERE!
    public void addMissile(Vector2 pos, World world, float rotation, Vector2 initialVelocity) {
        if (gameModel != null) {
            gameModel.missileShot(pos.x, pos.y, rotation, initialVelocity.x, initialVelocity.y);
        }
        missiles.add(new OnImpactMissile(world, pos, rotation, initialVelocity));
        notifyMissileListeners(pos, initialVelocity, rotation);
    }

    public ArrayList<IParticle> getMissiles() {
        return new ArrayList<IParticle>(missiles);
    }

    public ArrayList<IParticle> getParticles() {
        ArrayList<IParticle> iExplosionParticles = new ArrayList<>();
        for (Explosion explosion : explosions) {
            System.out.println(explosion.getExplosionParticles().size());
            iExplosionParticles.addAll(explosion.getExplosionParticles());
        }
        return iExplosionParticles;
    }

    private void notifyMissileListeners(Vector2 position, Vector2 velocity, float rotation) {
        for (IMissileListener missileListener : missileListeners) {
            missileListener.onMissileShot(position, velocity, rotation);
        }
    }

    public void addMissileListener(IMissileListener missileListener) {
        missileListeners.add(missileListener);
    }

    public void removeMissileListener(IMissileListener missileListener) {
        missileListeners.add(missileListener);
    }

    @Override
    public void onGameWorldStepped() {
        removeCollidedMissilesAndParticles();
    }
}
