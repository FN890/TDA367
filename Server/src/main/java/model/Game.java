package model;

import controller.ServerController;
import data.Vector2;
import server.protocol.IServerProtocol;
import server.protocol.ServerProtocolFactory;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable {

    private static final int UPDATE_RATE = 5;

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

    @Override
    public void run() {

        long taskTime = 0;
        long sleepTime = 1000/UPDATE_RATE;

        while (true) {
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

}
