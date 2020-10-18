package model;

import controller.ServerController;
import data.Vector2;
import services.protocol.IServerProtocol;
import services.protocol.ServerProtocolFactory;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Game class keeps track of an ongoing game.
 */
public class Game implements Runnable {

    /**
     * How often packets will be sent to clients. Unit: Times/Second
     */
    private static final int UPDATE_RATE = 80;

    private final String id;
    private final List<Player> players = Collections.synchronizedList(new ArrayList<>());
    private final List<GameListener> listeners = Collections.synchronizedList(new ArrayList<>());

    private boolean started = false;
    private boolean gameEnded = false;

    /**
     * Adds a GameListener for listening to game updates.
     *
     * @param listener The GameListener to add.
     */
    public synchronized void addListener(GameListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a GameListener.
     *
     * @param listener The GameListener to remove.
     */
    public synchronized void removeListener(GameListener listener) {
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
        synchronized (players) {
            for (Player p : players) {
                if (p.getName().equalsIgnoreCase(player.getName())) {
                    throw new GameException(GameError.NAME_TAKEN);
                }
            }
            players.add(player);
        }

        notifyListenersPlayerJoined(player);
    }

    /**
     * Removes a player from the game, and ends the game if it's the last player.
     *
     * @param player The Player which to remove.
     */
    public synchronized void removePlayer(Player player) {
        synchronized (players) {
            Player toRemove = null;
            for (Player p : players) {
                if (p.equals(player)) {
                    toRemove = p;
                }
            }

            if (toRemove != null) {
                players.remove(toRemove);
                notifyListenersPlayerLeft(toRemove);
            }


            if (players.isEmpty()) {
                endGame();
            }
        }
    }

    private synchronized void endGame() {
        GamesManager.getInstance().removeGame(id);
        gameEnded = true;
        notifyListenersGameEnded();
    }

    /**
     * Updates player position and rotation.
     *
     * @param player  The Player.
     * @param x        The x position.
     * @param y        The y position.
     * @param rotation The rotation.
     */
    public synchronized void updatePlayerPosition(Player player, float x, float y, float rotation) {
        synchronized (players) {
            for (Player p : players) {
                if (p.equals(player)) {
                    p.setPosition(new Vector2(x, y));
                    p.setRotation(rotation);
                }
            }
        }
    }

    /**
     * Sends a missile to the players in this game.
     *
     * @param player The player sending the missile.
     * @param x The spawn x position of the missile.
     * @param y The spawn y position of the missile.
     * @param rotation The rotation of the missile.
     * @param playerXSpeed The player's speed in x-axis, sending the missile.
     * @param playerYSpeed The player's speed in y-axis, sending the missile.
     */
    public synchronized void sendMissile(Player player, float x, float y, float rotation, float playerXSpeed, float playerYSpeed) {
        synchronized (listeners) {
            for (GameListener l : listeners) {
                l.gotMissile(player, x, y, rotation, playerXSpeed, playerYSpeed);
            }
        }
    }

    /**
     * Tells the game whether to keep sending packets or not. With other words, if the game should run.
     *
     * @param send The boolean specifying if packets should be sent.
     */
    public synchronized void start(boolean send) {
        if (started != send) {
            this.started = send;
            notifyListenersStateChanged(started);
        }
    }

    @Override
    public void run() {

        long taskTime = 0;
        long sleepTime = 1000 / UPDATE_RATE;

        while (!gameEnded) {

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
     * Only sending if the player has gotten a position.
     * Only sending if the started flag is set to true.
     */
    private void sendPositionPackets() {
        if (!started) { ;return; }
        IServerProtocol protocol = ServerProtocolFactory.getServerProtocol();

        synchronized (players) {
            for (Player p1 : players) {
                if (p1.getPosition() == null) { continue; }
                for (Player p2 : players) {
                    if (!p2.hasUDPAddress()) { continue; }
                    if (!p1.equals(p2)) {
                        ServerController.getInstance().sendUDPPacket(protocol.writePosition(p1.getName(), p1.getPosition(), p1.getRotation()), p2.getAddress().getAddress(), p2.getAddress().getPort());
                    }
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
        synchronized (players) {
            for (Player p : players) {
                if (p.getAddress().equals(address)) {
                    return p;
                }
            }
        }

        return null;
    }

    /**
     * Returns a list of players in the game.
     *
     * @return The list of players.
     */
    public synchronized List<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the game id.
     * @return The game id.
     */
    public synchronized String getId() {
        return id;
    }

    /**
     * Returns whether the game is running or not.
     * If the game is running, the game sends packets to clients.
     * @return True if the game is running.
     */
    public synchronized boolean isRunning() {
        return started;
    }

    private void notifyListenersPlayerJoined(Player player) {
        synchronized (listeners) {
            for (GameListener l : listeners) {
                l.playerJoined(player);
            }
        }
    }

    private void notifyListenersPlayerLeft(Player player) {
        synchronized (listeners) {
            for (GameListener l : listeners) {
                l.playerLeft(player);
            }
        }
    }

    private void notifyListenersStateChanged(boolean started) {
        synchronized (listeners) {
            for (GameListener l : listeners) {
                l.gameStatusUpdated(started);
            }
        }
    }

    private void notifyListenersGameEnded() {
        synchronized (listeners) {
            for (GameListener l : listeners) {
                l.gameEnded();
            }
        }
    }

}
