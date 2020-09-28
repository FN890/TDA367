package model;

public class Player {

    private final String name;

    private float x = 0;
    private float y = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
