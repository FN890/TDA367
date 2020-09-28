package model;

public class GameException extends Exception {

    private final GameError error;

    GameException(GameError error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error.message;
    }

}
