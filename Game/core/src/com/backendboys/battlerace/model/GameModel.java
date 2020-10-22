package com.backendboys.battlerace.model;

import com.backendboys.battlerace.model.collisions.CollisionHandler;
import com.backendboys.battlerace.model.opponent.OpponentPlayer;
import com.backendboys.battlerace.model.particles.IParticle;
import com.backendboys.battlerace.model.particles.ParticleHandler;
import com.backendboys.battlerace.model.player.Player;
import com.backendboys.battlerace.model.powerups.IPowerUp;
import com.backendboys.battlerace.model.powerups.PowerUpGenerator;
import com.backendboys.battlerace.model.world.GameWorld;
import com.backendboys.battlerace.model.world.ground.GroundStrategyFactory;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The model class, contains data and logic to the game.
 * Contains player and all other objects that exists in a game.
 */
public class GameModel {

    private static final int SPACE_BETWEEN_POWER_UPS = 300;

    private String winnerName = "";
    private boolean gameWon = false;

    private final GameWorld gameWorld;
    private final Player player;
    private final List<OpponentPlayer> opponentPlayers = Collections.synchronizedList(new ArrayList<OpponentPlayer>());
    private final ParticleHandler particleHandler;
    private final CollisionHandler collisionHandler;
    private List<IPowerUp> powerUps = new ArrayList<>();

    /**
     * Instantiates a GameModel, which creates a world and generates GameObjects, such as,
     * player, ground, powerUps, and finishLine.
     */
    public GameModel() {
        this.gameWorld = new GameWorld(GroundStrategyFactory.getSinCosStrategy(5000, 60, 5), 1);
        particleHandler = new ParticleHandler();

        generateObjects();
        gameWorld.addListener(particleHandler);
        Vector2 startPosition = gameWorld.getGroundVertices().get(50);

        player = new Player("You");
        player.addVehicle(gameWorld.getWorld(), startPosition.x, startPosition.y + 25);

        collisionHandler = new CollisionHandler(this);
        gameWorld.setCollisionListener(collisionHandler);
    }

    private void generateObjects() {
        PowerUpGenerator powerUpGenerator = new PowerUpGenerator(gameWorld.getGroundVertices(), gameWorld.getWorld(), particleHandler);
        powerUps = powerUpGenerator.generatePowerups(amountOfPowerUps());

        for (IPowerUp powerUp : powerUps) {
            gameWorld.addListener(powerUp);
        }
    }

    private int amountOfPowerUps() {
        int numberOfPowerUps = 0;
        int i = gameWorld.getGroundVertices().size();
        while (i > SPACE_BETWEEN_POWER_UPS) {
            i -= SPACE_BETWEEN_POWER_UPS;
            numberOfPowerUps++;
        }
        return numberOfPowerUps;
    }

    /**
     * Adds opponent to list of opponents
     *
     * @param opponent
     */
    public void addOpponent(OpponentPlayer opponent) {
        opponentPlayers.add(opponent);
    }

    /**
     * Removes opponent from list of opponents
     *
     * @param name the name of opponent removed
     */
    public void removeOpponent(String name) {
        synchronized (opponentPlayers) {
            for (OpponentPlayer p : opponentPlayers) {
                if (p.getName().equals(name)) {
                    opponentPlayers.remove(p);
                }
            }
        }
    }

    /**
     * Updates the opponent position
     *
     * @param name     of opponent
     * @param x        x position
     * @param y        y position
     * @param rotation rotation of opponent
     */
    public void updateOpponentPosition(String name, float x, float y, float rotation) {
        for (OpponentPlayer o : opponentPlayers) {
            if (o.getName().equalsIgnoreCase(name)) {
                o.setVectorPosition(new Vector2(x, y), rotation);
            }
        }
    }

    /**
     * Removes the PowerUp from the game and from the world.
     *
     * @param powerUp The PowerUp to remove.
     */
    public void removePowerUp(IPowerUp powerUp) {
        powerUp.remove();

        powerUps.remove(powerUp);
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
        return particleHandler.getMissiles();
    }

    public ArrayList<IParticle> getExplosionParticles() {
        return particleHandler.getParticles();
    }

    public ParticleHandler getWorldExplosions() {
        return particleHandler;
    }

    /**
     * Method that tells the player to use a powerup
     */
    public void usePowerUp() {
        player.usePowerUp();
    }

    /**
     * Spawns a missile in the world at a given position and rotation.
     *
     * @param x            The spawn x position.
     * @param y            The spawn y position.
     * @param rotation     The rotation.
     * @param playerXSpeed The x-axis speed of the player spawning the missile.
     * @param playerYSpeed The y-axis speed of the player spawning the missile.
     */
    public void spawnMissile(float x, float y, float rotation, float playerXSpeed, float playerYSpeed, boolean notifyListeners) {
        particleHandler.addMissile(new Vector2(x, y), gameWorld.getWorld(), rotation, new Vector2(playerXSpeed, playerYSpeed), notifyListeners);
    }

    /**
     * Returns the list of PowerUps.
     *
     * @return The PowerUps list.
     */
    public List<IPowerUp> getPowerUps() {
        return powerUps;
    }

    public List<Vector2> getFinishLineVertices() {
        return gameWorld.getFinishLineVertices();
    }

    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
}
