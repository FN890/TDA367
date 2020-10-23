package controller;

import model.*;
import services.PacketListener;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;
import services.protocol.ProtocolException;
import services.protocol.ServerProtocolFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * ClientController Controls one client connected to the TCPServer, each ClientController
 * has it's own thread.
 */
public class ClientController implements Runnable, GameListener, PacketListener {

    private final Socket socket;
    private final String clientID;

    private BufferedReader reader;
    private PrintWriter writer;
    private CommandManager cmdManager;
    private IServerProtocol protocol;

    private Game game = null;
    private Player player = null;

    /**
     * Initializes a ClientController.
     *
     * @param socket The socket which the client is connected to.
     */
    public ClientController(Socket socket, String clientID) {
        this.socket = socket;
        this.clientID = clientID;
    }

    @Override
    public void run() {

        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            this.protocol = ServerProtocolFactory.getServerProtocol();
            this.cmdManager = new CommandManager(this);

            ServerController.getInstance().addPacketListener(clientID, this);

            writer.println("connected:" + clientID);

            String input;
            while ((input = reader.readLine()) != null) {
                // Handle input

                try {
                    // Parse into command
                    ICommand cmd = protocol.parseMessage(input);
                    cmdManager.handleCommand(cmd);

                } catch (ProtocolException e) {
                    sendTCP(protocol.writeError(e.getMessage()));
                }

            }

            disconnect();

        } catch (IOException e) {
            disconnect();
        }


    }

    /**
     * Send a TCP Message to the clients socket.
     *
     * @param message The message to send.
     */
    public void sendTCP(String message) {
        writer.println(message);
    }

    /**
     * Creates a new Game for this client.
     * Sends a response to the client if any error occurs.
     *
     * @param name The host Player name.
     */
    public void createGame(String name) {
        if (game != null) {
            sendTCP(protocol.writeError("Already in game."));
            return;
        }

        this.player = new Player(name);
        Game game = GamesManager.getInstance().createGame(player);

        game.addListener(this);
        this.game = game;

        sendTCP(protocol.writeGameInfo(game));
    }

    /**
     * Joins a game by finding it by it's identifier.
     * Sends a response to the client if any error occurs.
     *
     * @param name The Player name.
     * @param id   The Game id to join.
     */
    public void joinGame(String name, String id) {
        if (game != null) {
            sendTCP(protocol.writeError("Already in game."));
            return;
        }

        Game game = GamesManager.getInstance().findGameByID(id);
        if (game == null) {
            sendTCP(protocol.writeError("Game not found."));
            return;
        }

        this.player = new Player(name);

        try {
            game.addPlayer(player);
            game.addListener(this);
            this.game = game;

            sendTCP(protocol.writeGameInfo(game));
        } catch (GameException e) {
            sendTCP(protocol.writeError(e.getMessage()));
        }

    }

    /**
     * Leaves the game if the client is in any.
     */
    public void leaveGame() {
        if (game == null) return;

        game.removePlayer(player);
        game.removeListener(this);
        game = null;
        player = null;
    }

    /**
     * Starts the game if the client is in any.
     */
    public void startGame() {
        if (game == null) {
            sendTCP(protocol.writeError("Not in a game."));
            return;
        }
        game.start(true);
    }

    /**
     * Updates the clients position and rotation in a game, if there is a game.
     *
     * @param x        The new x position.
     * @param y        The new y position.
     * @param rotation The new rotation.
     */
    public void updateGamePosition(float x, float y, float rotation) {
        if (game == null) return;

        game.updatePlayerPosition(player, x, y, rotation);
    }

    /**
     * Sends a missile to a game using the given parameters.
     *
     * @param x            The spawn x position of the missile.
     * @param y            The spawn y position of the missile.
     * @param rotation     The rotation of the missile.
     * @param playerXSpeed The player's speed in x-axis, sending the missile.
     * @param playerYSpeed The player's speed in y-axis, sending the missile.
     */
    public void sendGameMissile(float x, float y, float rotation, float playerXSpeed, float playerYSpeed) {
        if (game == null) return;
        game.sendMissile(player, x, y, rotation, playerXSpeed, playerYSpeed);
    }

    /**
     * Sets this client's player to the winner in the current game.
     */
    public void sendWin() {
        if (game == null) return;
        game.setWinner(player);
    }

    /**
     * @return The current game.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Disconnects the client, and performs all safety procedures to
     * properly end this clients life on the TCPServer.
     */
    public void disconnect() {
        try {
            System.out.println("Client disconnected from server: " + socket.getInetAddress().getHostAddress());
            ServerController.getInstance().removePacketListener(clientID);
            ServerController.getInstance().removeConnection(socket);
            leaveGame();
            socket.close();
        } catch (Exception ignored) {
        }
    }


    @Override
    public void playerJoined(Player player) {
        sendTCP(protocol.writeGamePlayerUpdate(player.getName(), true));
    }

    @Override
    public void playerLeft(Player player) {
        sendTCP(protocol.writeGamePlayerUpdate(player.getName(), false));
    }

    @Override
    public void playerWon(Player player) {
        sendTCP(protocol.writeGamePlayerWinner(player.getName()));
    }

    @Override
    public void gameStatusUpdated(boolean started) {
        sendTCP(protocol.writeGameStatusUpdated(started));
    }

    @Override
    public void gameEnded() {
        sendTCP(protocol.writeGameEndedUpdate());
    }

    @Override
    public void gotMissile(Player player, float x, float y, float rotation, float playerXSpeed, float playerYSpeed) {
        if (player.getName().equalsIgnoreCase(this.player.getName())) {
            return;
        }

        sendTCP(protocol.writeMissile(x, y, rotation, playerXSpeed, playerYSpeed));
    }

    @Override
    public void gotPacket(InetAddress address, int port, String message) {
        if (game == null) return;

        if (!player.hasUDPAddress()) {
            player.setUDPAddress(address, port);
        }

        try {
            ICommand cmd = protocol.parseMessage(message);
            cmdManager.handleCommand(cmd);
        } catch (ProtocolException ignored) {
        }

    }

}
