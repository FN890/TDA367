package model;

public enum GameError {
    NAME_TAKEN("That name is already taken.");

    String message;

    GameError(String message) {
        this.message = message;
    }

}
