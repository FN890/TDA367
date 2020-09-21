package server.protocol;

class ServerProtocolWriter {

    String writeError(String msg) {
        return "error:" + msg;
    }

}
