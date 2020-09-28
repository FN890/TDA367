package controller;

import model.Game;
import model.GameListener;
import model.GamesManager;
import model.Player;
import server.protocol.ICommand;
import server.protocol.IServerProtocol;
import server.protocol.ProtocolException;
import server.protocol.ServerProtocolFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController implements Runnable, GameListener {

    private final Socket socket;

    private BufferedReader reader;
    private PrintWriter writer;

    public ClientController(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);

            IServerProtocol protocol = ServerProtocolFactory.getServerProtocol();

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


    private void handleCommand(ICommand cmd) {

        switch (cmd.getCmd()) {

            case "create":
                handleCreateGame(cmd.getArgs());
                break;

            case "join":
                handleJoinGame(cmd.getArgs());
                break;


        }

    }

    // create:Name
    private void handleCreateGame(String[] args) {
        if (args.length < 1) {
            writer.println("Invalid arguments.");
            return;
        }

        Player player = new Player(args[0]);
        GamesManager.getInstance().createGame(player);
    }

    // join:ID,Name
    private void handleJoinGame(String[] args) {
        if (args.length < 2) {
            writer.println("Invalid arguments.");
            return;
        }

        String id = args[0];
        String name = args[1];

        Game game = GamesManager.getInstance().findGameByID(id);
    }

    private void disconnect() {
        try {
            System.out.println("Client disconnected from server: " + socket.getInetAddress().getHostAddress());
            socket.close();
        } catch (Exception ignored) {}
    }


    @Override
    public void playerJoined(Player player) {

    }

    @Override
    public void playerLeft(String name) {

    }

    @Override
    public void positionUpdated(Player player) {

    }
}
