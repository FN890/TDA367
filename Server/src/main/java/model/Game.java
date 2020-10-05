package model;

import controller.ServerController;
import data.Vector2;
import services.protocol.IServerProtocol;
import services.protocol.ServerProtocolFactory;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Game class keeps track of an ongoing game.
 */
public class Game implements Runnable {

    /**
     * How often packets will be sent to clients. Unit: Times/Second
     */
    private static final int UPDATE_RATE = 5;

    private final String id;
    private final List<Player> players = new ArrayList<>();
    private final List<GameListener> listeners = new ArrayList<>();

    private boolean run = false;
    private boolean gameEnded = false;

    /**
     * Adds a GameListener for listening to game updates.
     *
     * @param listener The GameListener to add.
     */
    public void addListener(GameListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a GameListener.
     *
     * @param listener The GameListener to remove.
     */
    public void removeListener(GameListener listener) {
        listeners.remove(listener);
    }

    Game(String id, Player host) {
        this.id = id;
        players.add(host);
    }

    /**
     * Adds a player to the game.
     *
     * @param player The Player to add.
     * @throws GameException When the player name is already taken.
     */
    public synchronized void addPlayer(Player player) throws GameException {
        for (Player p : players) {
            if (player.getName().equalsIgnoreCase(p.getName())) {
                throw new GameException(GameError.NAME_TAKEN);
            }
        }
        players.add(player);
        notifyListenersPlayerJoined(player);
    }

    /**
     * Removes a player from the game, and ends the game if it's the last player.
     *
     * @param address The Players Address which to remove.
     */
    public synchronized void removePlayerByAddress(InetAddress address) {
        for (Player p : players) {
            if (p.getAddress().equals(address)) {
                players.remove(p);
                notifyListenersPlayerLeft(p);
            }
        }

        if (players.isEmpty()) {
            endGame();
        }
    }

    private synchronized void endGame() {
        GamesManager.getInstance().removeGame(id);
        gameEnded = true;
    }

    /**
     * Updates player position and rotation by its InetAddress.
     *
     * @param address  The Player InetAddress.
     * @param x        The x position.
     * @param y        The y position.
     * @param rotation The rotation.
     */
    public synchronized void updatePositionByAddress(InetAddress address, float x, float y, float rotation) {
        for (Player p : players) {
            if (p.getAddress().equals(address)) {
                p.setPosition(new Vector2(x, y));
                p.setRotation(rotation);
            }
        }
    }

    /**
     * Tells the game whether to keep sending packets or not.
     *
     * @param run The boolean specifying if packets should be sent.
     */
    public synchronized void start(boolean run) {
        this.run = run;
    }

    @Override
    public void run() {

        long taskTime = 0;
        long sleepTime = 1000 / UPDATE_RATE;

        while (!gameEnded) {
            if (!run) continue;

            taskTime = System.currentTimeMillis();
            sendPositionPackets();
            taskTime = System.currentTimeMillis() - taskTime;

            if (sleepTime - taskTime > 0) {
                try {
                    Thread.sleep(sleepTime - taskTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Sends position packet of every player in game, to every player in game.
     */
    private void sendPositionPackets() {
        IServerProtocol protocol = ServerProtocolFactory.getServerProtocol();

        for (Player p1 : players) {
            for (Player p2 : players) {

                if (!p1.equals(p2)) {
                    ServerController.getInstance().sendUDPPacket(protocol.writePosition(p1.getName(), p1.getPosition(), p1.getRotation()), p2.getAddress(), p2.getPort());
                }

            }
        }
    }

    /**
     * Get a player by it's InetAddress
     *
     * @param address The players InetAddress.
     * @return The Player.
     */
    public synchronized Player getPlayerByAddress(InetAddress address) {
        for (Player p : players) {
            if (p.getAddress().equals(address)) {
                return p;
            }
        }
        return null;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getId() {
        return id;
    }

    public boolean isRunning() {
        return run;
    }

    private void notifyListenersPlayerJoined(Player player) {
        for (GameListener l : listeners) {
            l.playerJoined(player);
        }
    }

    private void notifyListenersPlayerLeft(Player player) {
        for (GameListener l : listeners) {
            l.playerLeft(player);
        }
    }

}
