package model;

/**
 * GameListener used when listening for game updates.
 */
public interface GameListener {

    /**
     * Notifies that a new player joined the game.
     *
     * @param player The new player.
     */
    void playerJoined(Player player);

    /**
     * Notifies that a player left the game.
     *
     * @param player The player that left.
     */
    void playerLeft(Player player);

    /**
     * Called when a player has won the game.
     *
     * @param player The player that won the game.
     */
    void playerWon(Player player);

    /**
     * Called whenever the game state changes. Can be started, or paused.
     *
     * @param started Specifies if the game is running or is paused.
     */
    void gameStatusUpdated(boolean started);

    /**
     * Called when the game has ended.
     */
    void gameEnded();

    /**
     * Called whenever someone sends a missile to the game.
     *
     * @param player       The player sending the missile.
     * @param x            The x spawn position of the missile.
     * @param y            The y spawn position of the missile.
     * @param rotation     The rotation of the missile.
     * @param playerXSpeed The player's speed in x-axis, sending the missile.
     * @param playerYSpeed The player's speed in y-axis, sending the missile.
     */
    void gotMissile(Player player, float x, float y, float rotation, float playerXSpeed, float playerYSpeed);
}
