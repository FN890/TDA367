package com.backendboys.battlerace.services.protocol;

public class CommandFactory {

    private CommandFactory() {
    }

    /**
     * Creates a new command with the given string and arguments.
     *
     * @param cmd  The command.
     * @param args The arguments.
     * @return The new ICommand.
     */
    public static ICommand createCommand(String cmd, String... args) {
        return new Command(cmd, args);
    }

}
