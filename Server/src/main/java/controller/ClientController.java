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
import java.net.Socket;

/**
 * ClientController Controls one client connected to the TCPServer, each ClientController
 * has it's own thread.
 */
public class ClientController implements Runnable, GameListener, PacketListener {

    private final Socket socket;

    private BufferedReader reader;
    private PrintWriter writer;
    private CommandHandler cmdHandler;
    private IServerProtocol protocol;

    private Game game = null;

    /**
     * Initializes a ClientController.
     * @param socket The socket which the client is connected to.
     */
    public ClientController(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            this.protocol = ServerProtocolFactory.getServerProtocol();
            this.cmdHandler = new CommandHandler(this);

            ServerController.getInstance().addPacketListener(socket.getInetAddress(), this);


            String input;
            while ((input = reader.readLine()) != null) {
                // Handle input

                try {
                    // Parse into command
                    ICommand cmd = protocol.parseMessage(input);
                    cmdHandler.handleCommand(cmd);

                } catch (ProtocolException e) {
                    sendTCP(protocol.writeError(e.getMessage()));
                }

            }

            disconnect();

        } catch (IOException e) {
            e.printStackTrace();
            disconnect();
        }


    }

    /**
     * Send a TCP Message to the clients socket.
     * @param message The message to send.
     */
    public void sendTCP(String message) {
        writer.println(message);
    }

    /**
     * Creates a new Game for this client.
     * Sends a response to the client if any error occurs.
     * @param name The host Player name.
     */
    public void createGame(String name) {
        if (game != null) {
            sendTCP(protocol.writeError("Already in game."));
            return;
        }

        Player player = new Player(name, socket.getInetAddress(), socket.getPort());
        Game game = GamesManager.getInstance().createGame(player);

        game.addListener(this);

        game.start(true);
        this.game = game;
    }

    /**
     * Joins a game by finding it by it's identifier.
     * Sends a response to the client if any error occurs.
     * @param name The Player name.
     * @param id The Game id to join.
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

        Player player = new Player(name, socket.getInetAddress(), socket.getPort());

        try {
            game.addPlayer(player);
            game.addListener(this);
            this.game = game;
        } catch (GameException e) {
            sendTCP(protocol.writeError(e.getMessage()));
        }

    }

    /**
     * Leaves the game if the client is in any.
     */
    public void leaveGame() {
        if (game == null) return;

        game.removePlayerByAddress(socket.getInetAddress());
        game.removeListener(this);
        game = null;
    }

    /**
     * Updates the clients position and rotation in a game, if there is a game.
     * @param x The new x position.
     * @param y The new y position.
     * @param rotation The new rotation.
     */
    public void updateGamePosition(float x, float y, float rotation) {
        if (game == null) return;

        game.updatePositionByAddress(socket.getInetAddress(), x, y, rotation);
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
    private void disconnect() {
        try {
            System.out.println("Client disconnected from server: " + socket.getInetAddress().getHostAddress());
            ServerController.getInstance().removePacketListener(socket.getInetAddress());
            leaveGame();
            socket.close();
        } catch (Exception ignored) {}
    }


    @Override
    public void playerJoined(Player player) {
        sendTCP("Player " + player.getName() + " left the game.");
    }

    @Override
    public void playerLeft(Player player) {
        sendTCP("Player " + player.getName() + " joined the game.");
    }

    @Override
    public void gotPacket(String message) {
        if (game == null) return;

        try {
            ICommand cmd = protocol.parseMessage(message);
            cmdHandler.handleCommand(cmd);
        } catch (ProtocolException ignored) {}

    }

}
