package com.backendboys.battlerace.services.protocol;

public class CommandFactory {

    public static ICommand createCommand(String cmd, String... args) {
        return new Command(cmd, args);
    }

}
