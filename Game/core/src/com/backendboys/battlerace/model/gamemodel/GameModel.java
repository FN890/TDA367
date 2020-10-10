package com.backendboys.battlerace.model.gamemodel;

import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.backendboys.battlerace.model.gamemodel.powerups.AbstractPowerUp;
import com.backendboys.battlerace.model.gamemodel.powerups.PowerUpGenerator;
import com.backendboys.battlerace.model.gamemodel.world.GameWorld;
import com.backendboys.battlerace.model.gamemodel.world.GroundGenerator;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * The model class, contains data and logic to change data.
 * Contains player and the methods that move the vehicle
 */
public class GameModel {

    private final GameWorld gameWorld;
    private final Player player;

    private List<AbstractPowerUp> powerUps = new ArrayList<>();
    private FinishLineGenerator finishLineGenerator;

    public GameModel() {
        this.gameWorld = new GameWorld(new GroundGenerator(10000, 5, 1));
        generateObjects();

        Vector2 startPosition = gameWorld.getGroundVertices().get(50);
        player = new Player("Mad Max");
        player.addVehicle(gameWorld.getWorld(), startPosition.x, startPosition.y + 25);

        gameWorld.setCollisionListener(new CollisionListener(this));
    }

    private void generateObjects() {
        PowerUpGenerator powerUpGenerator = new PowerUpGenerator(gameWorld.getGroundVertices(), gameWorld.getWorld());
        powerUps = powerUpGenerator.generatePowerups(30);

        finishLineGenerator = new FinishLineGenerator(gameWorld.getGroundVertices());
        finishLineGenerator.generateFinishLine(gameWorld.getWorld());
    }

    /**
     * Removes the PowerUp from the game and from the world.
     * @param powerUp The PowerUp to remove.
     */
    public void removePowerUp(AbstractPowerUp powerUp) {
        boolean removed = powerUps.remove(powerUp);
        if (removed) {
            gameWorld.destroyBody(powerUp.getBody());
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
        gameWorld.addMissile(new Vector2(player.getPosition().x, player.getPosition().y), player.getRotation());

    }

    public List<AbstractPowerUp> getPowerUps() {
        return powerUps;
    }

    public List<Vector2> getFinishLineVertices() {
        return finishLineGenerator.getFinishLineVerts();
    }

}
