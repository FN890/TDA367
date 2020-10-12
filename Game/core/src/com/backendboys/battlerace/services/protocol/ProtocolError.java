package com.backendboys.battlerace.services.protocol;

/**
 * ProtocolError contains types of errors with pre-defined messages.
 */
enum ProtocolError {
    INVALID_ARGS("Invalid arguments."),
    INVALID_CMD("Invalid command."),
    INVALID_SYNTAX("Invalid syntax.");

    String message;

    ProtocolError(String message) {
        this.message = message;
    }
}
