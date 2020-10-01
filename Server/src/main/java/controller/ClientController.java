package controller;

import model.*;
import server.PacketListener;
import server.protocol.ICommand;
import server.protocol.IServerProtocol;
import server.protocol.ProtocolException;
import server.protocol.ServerProtocolFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController implements Runnable, GameListener, PacketListener {

    private final Socket socket;

    private BufferedReader reader;
    private PrintWriter writer;
    private IServerProtocol protocol;

    private Game currentGame = null;

    public ClientController(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            this.protocol = ServerProtocolFactory.getServerProtocol();

            ServerController.getInstance().addPacketListener(socket.getInetAddress(), this);

            String input;
            while ((input = reader.readLine()) != null) {
                // Handle input

                try {
                    // Parse into command
                    ICommand cmd = protocol.parseTCPMessage(input);
                    handleCommand(cmd);

                } catch (ProtocolException e) {
                    writer.println(protocol.writeError(e.getMessage()));
                }

            }

            disconnect();

        } catch (IOException e) {
            e.printStackTrace();
            disconnect();
        }


    }


    // TODO: Separate these into own class. --------------------
    private void handleCommand(ICommand cmd) {

        switch (cmd.getCmd()) {

            case "create":
                handleCreateGame(cmd.getArgs());
                break;

            case "join":
                handleJoinGame(cmd.getArgs());
                break;

            case "leave":
                handleLeaveGame();
                break;

        }

    }

    // create:Name
    private void handleCreateGame(String[] args) {
        if (args.length < 1) {
            writer.println(protocol.writeError("Invalid arguments."));
            return;
        }

        String name = args[0];

        Player player = new Player(name, socket.getInetAddress(), socket.getPort());
        currentGame = GamesManager.getInstance().createGame(player);
        currentGame.addListener(this);
    }

    // join:ID,Name
    private void handleJoinGame(String[] args) {
        if (args.length < 2) {
            writer.println(protocol.writeError("Invalid arguments."));
            return;
        }

        String id = args[0];
        String name = args[1];

        Game game = GamesManager.getInstance().findGameByID(id);
        if (game == null) {
            writer.println(protocol.writeError("Game not found."));
            return;
        }

        Player player = new Player(name, socket.getInetAddress(), socket.getPort());

        try {
            game.addPlayer(player);
            currentGame = game;
            currentGame.addListener(this);
        } catch (GameException e) {
            writer.println(protocol.writeError(e.getMessage()));
        }

    }

    // leave
    private void handleLeaveGame() {
        if (currentGame == null) {
            return;
        }

        currentGame.removePlayerByAddress(socket.getInetAddress());
        currentGame.removeListener(this);
        currentGame = null;

        writer.println("Left the game.");
    }

    // TODO: ------------------------------------------------------------------------

    private void disconnect() {
        try {
            System.out.println("Client disconnected from server: " + socket.getInetAddress().getHostAddress());
            ServerController.getInstance().removePacketListener(socket.getInetAddress());
            socket.close();
        } catch (Exception ignored) {}
    }


    @Override
    public void playerJoined(Player player) {

    }

    @Override
    public void playerLeft(Player player) {

    }


    @Override
    public void gotPacket(String message) {
        
    }


}
