package server.protocol;

public class ProtocolException extends Exception {

    ProtocolError error;

    ProtocolException(ProtocolError error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error.message;
    }

    public ProtocolError getError() {
        return error;
    }
}
