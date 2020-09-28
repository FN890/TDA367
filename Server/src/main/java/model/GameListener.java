package model;

public interface GameListener {
    void playerJoined(Player player);
    void playerLeft(String name);
    void positionUpdated();
}
