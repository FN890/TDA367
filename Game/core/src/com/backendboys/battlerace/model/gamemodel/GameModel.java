package com.backendboys.battlerace.model.gamemodel;

import com.backendboys.battlerace.model.gamemodel.opponent.OpponentPlayer;
import com.backendboys.battlerace.model.gamemodel.particles.IParticle;
import com.backendboys.battlerace.model.gamemodel.particles.WorldExplosions;
import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.backendboys.battlerace.model.gamemodel.powerups.AbstractPowerUp;
import com.backendboys.battlerace.model.gamemodel.powerups.PowerUpGenerator;
import com.backendboys.battlerace.model.gamemodel.world.GameWorld;
import com.backendboys.battlerace.model.gamemodel.world.ground.GroundStrategyFactory;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * The model class, contains data and logic to the game.
 * Contains player and all other objects that exists in a game.
 */
public class GameModel {

    private final GameWorld gameWorld;
    private final Player player;
    private final List<OpponentPlayer> opponentPlayers = new ArrayList<>();

    private final WorldExplosions worldExplosions;
    private List<AbstractPowerUp> powerUps = new ArrayList<>();
    private FinishLineGenerator finishLineGenerator;

    /**
     * Instantiates a GameModel, which creates a world and generates GameObjects, such as,
     * player, ground, powerups, and finishline.
     */
    public GameModel() {
        this.gameWorld = new GameWorld(GroundStrategyFactory.getSinCosStrategy(5000, 60, 5), 1);
        worldExplosions = new WorldExplosions();
        generateObjects();
        gameWorld.addListener(worldExplosions);
        Vector2 startPosition = gameWorld.getGroundVertices().get(50);
        player = new Player("gustav");
        player.addVehicle(gameWorld.getWorld(), startPosition.x, startPosition.y + 25);
        addOpponent(new OpponentPlayer("Xerxz", new Vector2(100, 100), 0));
        gameWorld.setCollisionListener(new CollisionListener(this));
    }

    private void generateObjects() {
        PowerUpGenerator powerUpGenerator = new PowerUpGenerator(gameWorld.getGroundVertices(), gameWorld.getWorld(), worldExplosions);
        powerUps = powerUpGenerator.generatePowerups(30);

        finishLineGenerator = new FinishLineGenerator(gameWorld.getGroundVertices());
        finishLineGenerator.generateFinishLine(gameWorld.getWorld());
    }

    public void addOpponent(OpponentPlayer opponent) {
        opponentPlayers.add(opponent);
    }

    public void removeOpponent(OpponentPlayer opponent) {
        opponentPlayers.remove(opponent);
    }

    public void updateOpponentPosition(String name, float x, float y, float rotation) {
        for (OpponentPlayer o : opponentPlayers) {
            if (o.getPlayerName().equalsIgnoreCase(name)) {
                o.setVectorPosition(new Vector2(x, y), rotation);
            }
        }
    }

    /**
     * Removes the PowerUp from the game and from the world.
     *
     * @param powerUp The PowerUp to remove.
     */
    public void removePowerUp(AbstractPowerUp powerUp) {
        boolean removed = powerUps.remove(powerUp);
        if (removed) {
            gameWorld.destroyBody(powerUp.getBody());
        }
    }

    /**
     * Makes the Player gas.
     */
    public void gas() {
        player.gas();
    }

    /**
     * Makes the Player rotate to the left.
     */
    public void rotateLeft() {
        player.rotateLeft();
    }

    /**
     * Makes the Player brake.
     */
    public void brake() {
        player.brake();
    }

    /**
     * Makes the Player rotate to the right.
     */
    public void rotateRight() {
        player.rotateRight();
    }

    /**
     * Returns the GameWorld.
     *
     * @return The GameWorld object.
     */
    public GameWorld getGameWorld() {
        return gameWorld;
    }

    /**
     * Returns the Player.
     *
     * @return The Player object.
     */
    public Player getPlayer() {
        return player;
    }

    public Vector2 getPlayerPosition() {
        return player.getPosition();
    }

    public float getPlayerRotation() {
        return player.getRotation();
    }

    public List<OpponentPlayer> getOpponents() {
        return opponentPlayers;
    }

    public ArrayList<IParticle> getMissiles() {
        return worldExplosions.getMissiles();
    }

    public ArrayList<IParticle> getExplosionParticles() {
        return worldExplosions.getParticles();
    }

    /**
     * Method that tells the player to use a powerup
     */
    public void usePowerUp() {
        player.usePowerUp();
    }

    /**
     * Temp function for testing missiles
     */
    public void shootMissile() {
        worldExplosions.addMissile(player.getPosition(), gameWorld.getWorld(), player.getRotation(), player.getLinearVelocity());
    }

    /**
     * Returns the list of PowerUps.
     *
     * @return The PowerUps list.
     */
    public List<AbstractPowerUp> getPowerUps() {
        return powerUps;
    }

    public List<Vector2> getFinishLineVertices() {
        return finishLineGenerator.getFinishLineVertices();
    }

}
