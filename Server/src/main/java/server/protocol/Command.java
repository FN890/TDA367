package server.protocol;

class Command implements ICommand {

    private final String cmd;
    private final String[] args;

    Command(String cmd, String[] args) {
        this.cmd = cmd;
        this.args = args;
    }

    @Override
    public String getCmd() {
        return cmd;
    }

    @Override
    public String[] getArgs() {
        return args;
    }
}
