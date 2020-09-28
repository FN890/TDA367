package model;

import java.util.ArrayList;
import java.util.List;

class Game {

    private final Player host;
    private final List<Player> players = new ArrayList<>();

    private final List<GameListener> listeners = new ArrayList<>();

    void addListener(GameListener listener) {
        listeners.add(listener);
    }

    void removeListener(GameListener listener) {
        listeners.remove(listener);
    }

    Game(Player host) {
        this.host = host;
    }

    void addPlayer(Player player) throws GameException {
        for (Player p : players) {
            if (player.getName().equalsIgnoreCase(p.getName()) || player.getName().equalsIgnoreCase(host.getName())) {
                throw new GameException(GameError.NAME_TAKEN);
            }
        }
        players.add(player);
        notifyListenersPlayerJoined(player);
    }

    void removePlayer(String name) {
        players.removeIf(p -> p.getName().equals(name));
        notifyListenersPlayerLeft(name);
    }

    void updatePosition(Player player, float x, float y) {
        for (Player p : players) {
            if (player == p) {
                p.setPosition(x, y);
                notifyListenersPositionUpdated(p);
            }
        }
    }

    private void notifyListenersPlayerJoined(Player player) {
        for (GameListener l : listeners) {
            l.playerJoined(player);
        }
    }

    private void notifyListenersPlayerLeft(String name) {
        for (GameListener l : listeners) {
            l.playerLeft(name);
        }
    }

    private void notifyListenersPositionUpdated(Player player) {
        for (GameListener l : listeners) {
            l.positionUpdated(player);
        }
    }

}
