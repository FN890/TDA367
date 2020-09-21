package server.protocol;

class Command {

    private final String cmd;
    private final String[] args;

    Command(String cmd, String[] args) {
        this.cmd = cmd;
        this.args = args;
    }

    String getCmd() {
        return cmd;
    }

    String[] getArgs() {
        return args;
    }
}
