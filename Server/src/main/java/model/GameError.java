package model;

/**
 * GameError enum contains pre-defined error types with values.
 */
public enum GameError {
    NAME_TAKEN("That name is already taken.");

    String message;

    GameError(String message) {
        this.message = message;
    }

}
