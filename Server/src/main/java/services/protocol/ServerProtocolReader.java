package services.protocol;

import java.util.ArrayList;
import java.util.List;

class ServerProtocolReader {

    private static final String[] VALID_COMMANDS = {"create", "join", "leave", "pos", "get", "start", "port"};

    Command parseMessage(String msg) throws ProtocolException {
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

    Command parseCommand(Command cmd) throws ProtocolException {

        for (int i = 0; i < VALID_COMMANDS.length; i++) {
            if (cmd.getCmd().equalsIgnoreCase(VALID_COMMANDS[i])) {
                return cmd;
            }
        }

        throw new ProtocolException(ProtocolError.INVALID_CMD);
    }


}
