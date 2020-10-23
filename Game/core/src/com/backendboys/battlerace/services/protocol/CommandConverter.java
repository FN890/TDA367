package com.backendboys.battlerace.services.protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * CommandConverter converts messages to commands, and commands to messages.
 */
public class CommandConverter {

    /**
     * Converts a string, message, to a Command containing a number of arguments.
     * Form: command:arg1,arg2,arg3...
     *
     * @param msg The message to convert.
     * @return Returns the message as a Command.
     */
    public Command toCommand(String msg) {
        String trimmed = msg.trim().toLowerCase();
        StringBuilder sb = new StringBuilder();

        String command = null;
        List<String> argsList = new ArrayList<>();

        for (char c : trimmed.toCharArray()) {
            if (c == ' ') {
                continue;
            }

            if (c == ':' && command == null) {
                command = sb.toString();
                sb.setLength(0);
                continue;
            }

            if (c == ',') {
                argsList.add(sb.toString());
                sb.setLength(0);
                continue;
            }

            sb.append(c);
        }

        // If command missing arguments and ":", add the remaining characters as command,
        // else add the remaining characters as an argument.
        if (command == null) {
            command = sb.toString();
        } else {
            argsList.add(sb.toString());
        }

        String[] args = new String[argsList.size()];
        for (int i = 0; i < argsList.size(); i++) {
            args[i] = argsList.get(i);
        }

        return new Command(command, args);
    }

    /**
     * Converts a Command to a string, message.
     *
     * @param cmd The command to convert.
     * @return Returns the command as a string on the form: command:arg1,arg2,arg3...
     */
    public String toMessage(ICommand cmd) {
        String command = cmd.getCmd();
        StringBuilder sb = new StringBuilder();

        for (String arg : cmd.getArgs()) {
            sb.append(arg).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);

        return command + ":" + sb.toString();
    }


}
