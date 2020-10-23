package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.model.opponent.OpponentPlayer;
import com.backendboys.battlerace.model.player.Player;
import com.backendboys.battlerace.services.IPacketListener;
import com.backendboys.battlerace.services.ITCPListener;
import com.backendboys.battlerace.services.TCPClient;
import com.backendboys.battlerace.services.UDPClient;
import com.backendboys.battlerace.services.protocol.CommandConverter;
import com.backendboys.battlerace.services.protocol.CommandFactory;
import com.backendboys.battlerace.services.protocol.ICommand;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * ServerController handles connection to Server.
 * It creates a TCPClient and a UDPClient
 */
public class ServerController implements ITCPListener, IPacketListener {

    private final static String HOSTNAME = "167.172.34.88";
    private final static int PORT = 26000;

    private final TCPClient tcpClient;
    private final UDPClient udpClient;
    private String clientID;
    private int gameId;

    private final GameController gameController;
    private final CommandConverter commandConverter;

    private boolean isConnected = false;

    public ServerController(GameController gameController) {
        this.gameController = gameController;
        commandConverter = new CommandConverter();

        udpClient = new UDPClient(HOSTNAME, PORT);
        udpClient.addListener(this);

        tcpClient = new TCPClient(HOSTNAME, PORT);
        tcpClient.addListener(this);

        connect();
    }

    /**
     * Starts the UDP and TCP threads
     */
    private void connect() {
        new Thread(udpClient).start();
        new Thread(tcpClient).start();
    }

    /**
     * Disconnects the UDP and TCP clients from server
     */
    public void disconnect() {
        udpClient.close();
        tcpClient.disconnect();
    }

    /**
     * Sends the position of a player to the server via UDP.
     *
     * @param player
     */
    public void sendPositionPacket(Player player) {
        ICommand command = CommandFactory.createCommand("pos",
                String.valueOf(player.getPosition().x),
                String.valueOf(player.getPosition().y), String.valueOf(player.getRotation()));

        udpClient.sendPacket(clientID + "-" + commandConverter.toMessage(command));
    }

    /**
     * Sends message to the server via TCP.
     *
     * @param message Message to be sent.
     */
    void sendMessage(String message) {
        tcpClient.sendMessage(message);
    }

    /**
     * @param message The message received from UDP
     */
    @Override
    public void gotPacket(String message) {
        ICommand command = commandConverter.toCommand(message);

        if (command.getCmd().equals("pos")) {
            try {
                String playerName = command.getArgs()[0];
                float playerXPos = Float.parseFloat(command.getArgs()[1]);
                float playerYPos = Float.parseFloat(command.getArgs()[2]);
                float playerRotation = Float.parseFloat(command.getArgs()[3]);

                gameController.handleUpdateOpponentPosition(playerName, playerXPos, playerYPos, playerRotation);

            } catch (NumberFormatException ignored) {
            }
        }
    }

    /**
     * @param message error if udp failed
     */
    @Override
    public void UDPErrorOccurred(String message) {
        System.out.println(message);
    }

    /**
     * @param message received from TCP
     */
    @Override
    public void gotMessage(String message) {
        ICommand command = commandConverter.toCommand(message);

        switch (command.getCmd()) {
            case "connected":
                clientID = command.getArgs()[0];
                break;

            case "response":
                if (command.getArgs().length > 2) {
                    gameId = Integer.parseInt(command.getArgs()[0]);

                    for (int i = 2; i < command.getArgs().length - 1; i++) {
                        String playerName = command.getArgs()[i];
                        gameController.handleAddOpponent(new OpponentPlayer(playerName, new Vector2(50, 100), 0));
                    }

                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            gameController.showScreen();
                        }
                    });
                }
                break;

            case "missile":
                String[] args = command.getArgs();

                final float x = Float.parseFloat(args[0]);
                final float y = Float.parseFloat(args[1]);
                final float rotation = Float.parseFloat(args[2]);
                final float playerXSpeed = Float.parseFloat(args[3]);
                final float playerYSpeed = Float.parseFloat(args[4]);

                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        gameController.getGameModel().spawnMissile(x, y, rotation, playerXSpeed, playerYSpeed, false);
                    }
                });


                break;

            case "game":
                if (command.getArgs().length > 1) {
                    switch (command.getArgs()[0]) {
                        case "joined": {
                            String playerName = command.getArgs()[1];
                            gameController.handleAddOpponent(new OpponentPlayer(playerName, new Vector2(50, 100), 0));

                            break;
                        }
                        case "left": {
                            String playerName = command.getArgs()[1];
                            gameController.handleRemoveOpponent(playerName);
                            break;
                        }
                        case "winner": {
                            String playerName = command.getArgs()[1];
                            gameController.setWinnerName(playerName);
                            gameController.setGameWon(true);
                            break;
                        }
                    }
                }
                break;
        }
    }

    /**
     * @param message is lost connection to server
     */
    @Override
    public void lostConnection(String message) {
        isConnected = false;
    }

    /**
     * Callback from TCP client telling serverController that it is connect
     */
    @Override
    public void onConnection() {
        isConnected = true;
    }

    /**
     * Sends createGame command to server if connected
     *
     * @param name player name
     */
    public void createGame(String name) {
        if (isConnected) {
            gameController.getGameModel().getPlayer().setName(name);
            sendMessage("create:" + name);
            sendMessage("start");
        }
    }

    /**
     * Sends join command to server if connected
     *
     * @param name player name
     * @param id   game id
     */
    public void joinGame(String name, String id) {
        if (isConnected) {
            gameController.getGameModel().getPlayer().setName(name);
            sendMessage("join:" + id + "," + name);
        }
    }

    /**
     * Send sendMissle command to server
     *
     * @param position missle postion
     * @param velocity missle velocity
     * @param rotation missle rotation
     */
    public void sendMissile(Vector2 position, Vector2 velocity, float rotation) {
        if (isConnected) {
            sendMessage("missile:" + position.x + "," + position.y + "," + rotation + "," + velocity.x + "," + velocity.y);
        }
    }

    /**
     * @return if connected to server
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * @return game id of connected game
     */
    public int getGameId() {
        return gameId;
    }
}
