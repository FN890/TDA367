package services.protocol;

import data.Vector2;
import model.Game;

class ServerProtocolFacade implements IServerProtocol {

    private static ServerProtocolFacade instance = null;

    private final ServerProtocolReader readerProtocol;
    private final ServerProtocolWriter writerProtocol;

    private ServerProtocolFacade() {
        this.readerProtocol = new ServerProtocolReader();
        this.writerProtocol = new ServerProtocolWriter();
    }

    @Override
    public synchronized Command parseMessage(String msg) throws ProtocolException {
        return readerProtocol.parseMessage(msg);
    }

    @Override
    public synchronized String writeResponse(String msg) {
        return writerProtocol.writeResponse(msg);
    }

    @Override
    public synchronized String writeError(String msg) {
        return writerProtocol.writeError(msg);
    }

    @Override
    public synchronized String writePosition(String name, Vector2 pos, float rotation) {
        return writerProtocol.writePosition(name, pos, rotation);
    }

    @Override
    public synchronized String writeMissile(float x, float y, float rotation, float playerXSpeed, float playerYSpeed) {
        return writerProtocol.writeMissile(x, y, rotation, playerXSpeed, playerYSpeed);
    }

    @Override
    public synchronized String writeGameInfo(Game game) {
        return writerProtocol.writeGameInfo(game);
    }

    @Override
    public synchronized String writeGamePlayerUpdate(String name, boolean joined) {
        return writerProtocol.writeGamePlayerUpdate(name, joined);
    }

    @Override
    public synchronized String writeGamePlayerWinner(String name) {
        return writerProtocol.writeGamePlayerWinner(name);
    }

    @Override
    public synchronized String writeGameStatusUpdated(boolean started) {
        return writerProtocol.writeGameStatusUpdate(started);
    }

    @Override
    public synchronized String writeGameEndedUpdate() {
        return writerProtocol.writeGameEndedUpdate();
    }

    static synchronized ServerProtocolFacade getInstance() {
        if (instance == null) {
            instance = new ServerProtocolFacade();
        }
        return instance;
    }

}
