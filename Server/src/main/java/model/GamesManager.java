package model;

import java.util.ArrayList;
import java.util.List;

public class GamesManager {

    private static GamesManager instance = null;

    private final List<Game> games = new ArrayList<>();
    private int currentGameID = 1400;

    private GamesManager() {}

    public synchronized Game createGame(Player host) {
        Game game = new Game(generateGameID(), host);
        games.add(game);
        return game;
    }

    public synchronized void removeGame(String id) {
        games.removeIf(g -> g.getId().equalsIgnoreCase(id));
    }

    public synchronized Game findGameByID(String id) {
        for (Game g : games) {
            if (id.equalsIgnoreCase(g.getId())) {
                return g;
            }
        }
        return null;
    }

    private synchronized String generateGameID() {
        currentGameID += 1;
        return Integer.toString(currentGameID);
    }


    public static synchronized GamesManager getInstance() {
        if (instance == null) {
            instance = new GamesManager();
        }
        return instance;
    }


}
