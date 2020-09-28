package model;

import java.util.List;

public interface GameListener {
    void playerJoined(Player player);
    void playerLeft(Player player);
    void update(List<Player> players);
}
