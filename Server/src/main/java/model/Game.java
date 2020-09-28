package model;

import data.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Game {

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

    public void addPlayer(Player player) throws GameException {
        for (Player p : players) {
            if (player.getName().equalsIgnoreCase(p.getName()) || player.getName().equalsIgnoreCase(host.getName())) {
                throw new GameException(GameError.NAME_TAKEN);
            }
        }
        players.add(player);
        notifyListenersPlayerJoined(player);
    }

    public void removePlayer(String name) {
        players.removeIf(p -> p.getName().equals(name));
        notifyListenersPlayerLeft(name);
    }

    public void updatePosition(Player player, float x, float y) {
        for (Player p : players) {
            if (player == p) {
                p.setPosition(new Vector2(x, y));
                notifyListenersPositionUpdated(p);
            }
        }
    }

    public String getId() {
        return id;
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
