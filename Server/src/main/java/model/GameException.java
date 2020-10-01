package model;

/**
 * GameException stores a GameError.
 */
public class GameException extends Exception {

    private final GameError error;

    /**
     * Creates a GameException.
     * @param error The GameError.
     */
    GameException(GameError error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error.message;
    }

}
