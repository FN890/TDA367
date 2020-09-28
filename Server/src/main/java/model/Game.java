package model;

import data.Vector2;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable {

    private final String id;
    private final Player host;
    private final List<Player> players = new ArrayList<>();

    private final List<GameListener> listeners = new ArrayList<>();

    public void addListener(GameListener listener) {
        listeners.add(listener);
    }

    public void removeListener(GameListener listener) {
        listeners.remove(listener);
    }

    Game(String id, Player host) {
        this.id = id;
        this.host = host;
    }

    public synchronized void addPlayer(Player player) throws GameException {
        for (Player p : players) {
            if (player.getName().equalsIgnoreCase(p.getName()) || player.getName().equalsIgnoreCase(host.getName())) {
                throw new GameException(GameError.NAME_TAKEN);
            }
        }
        players.add(player);
        notifyListenersPlayerJoined(player);
    }

    public synchronized void removePlayerByAddress(InetAddress address) {
        for (Player p : players) {
            if (p.getAddress().equals(address)) {
                players.remove(p);
                notifyListenersPlayerLeft(p);
            }
        }
    }

    public synchronized void updatePositionByAddress(InetAddress address, float x, float y) {
        for (Player p : players) {
            if (p.getAddress().equals(address)) {
                p.setPosition(new Vector2(x, y));
            }
        }
    }

    //TODO: Fix so that the game sends out updates.
    @Override
    public void run() {

        while (true) {
            notifyUpdate();
        }

    }

    public synchronized Player getPlayerByAddress(InetAddress address) {
        for (Player p : players) {
            if (p.getAddress().equals(address)) {
                return p;
            }
        }
        return null;
    }

    public String getId() {
        return id;
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

    private void notifyUpdate() {
        for (GameListener l : listeners) {
            l.update(players);
        }
    }

}
