package server.protocol;

import java.util.ArrayList;
import java.util.List;

class ServerProtocol {

    private static final String[] VALID_COMMANDS = {"create", "join"};

    static synchronized Command parseTCPMessage(String msg) throws ProtocolException {
        String trimmed = msg.trim().toLowerCase();
        StringBuilder sb = new StringBuilder();

        String command = null;
        List<String> argsList = new ArrayList<>();

        for (char c : trimmed.toCharArray()) {
            if (c == ' ') { continue; }

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

        String[] args = new String[argsList.size()];
        for (int i = 0; i < argsList.size(); i++) {
            args[i] = argsList.get(i);
        }

        if (command == null) {
            throw new ProtocolException(ProtocolError.INVALID_SYNTAX);
        }

        return new Command(command, args);
    }

    static synchronized Command parseTCPCommand(Command cmd) throws ProtocolException {

        for (String s : VALID_COMMANDS) {
            if (cmd.getCmd().equalsIgnoreCase(s)) {
                return cmd;
            }
        }

        throw new ProtocolException(ProtocolError.INVALID_CMD);
    }


}
