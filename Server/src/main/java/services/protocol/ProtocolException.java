package services.protocol;

/**
 * ProtocolException containing a ProtocolError.
 */
public class ProtocolException extends Exception {

    private final ProtocolError error;

    ProtocolException(ProtocolError error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error.message;
    }

    /**
     * @return The ProtocolError.
     */
    public ProtocolError getError() {
        return error;
    }

}
