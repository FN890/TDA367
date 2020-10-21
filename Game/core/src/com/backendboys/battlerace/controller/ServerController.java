package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.BattleRace;
import com.backendboys.battlerace.model.gamemodel.opponent.OpponentPlayer;
import com.backendboys.battlerace.model.gamemodel.particles.IMissileListener;
import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.backendboys.battlerace.services.IPacketListener;
import com.backendboys.battlerace.services.ITCPListener;
import com.backendboys.battlerace.services.TCPClient;
import com.backendboys.battlerace.services.UDPClient;
import com.backendboys.battlerace.services.protocol.CommandConverter;
import com.backendboys.battlerace.services.protocol.CommandFactory;
import com.backendboys.battlerace.services.protocol.ICommand;
import com.badlogic.gdx.math.Vector2;

public class ServerController implements ITCPListener, IPacketListener, IMissileListener {

    private final static String HOSTNAME = "167.172.34.88";
    private final static int PORT = 26000;

    private final TCPClient tcpClient;
    private final UDPClient udpClient;
    private String clientID;

    private final BattleRace game;

    private final GameController gameController;
    private final CommandConverter commandConverter;

    private boolean isConnected = false;

    private String name;
    private String id;

    public ServerController(BattleRace game, GameController gameController) {
        this.game = game;

        gameController.getGameModel().getWorldExplosions().addMissileListener(this);

        this.gameController = gameController;
        commandConverter = new CommandConverter();

        udpClient = new UDPClient(HOSTNAME, PORT);
        udpClient.addListener(this);

        tcpClient = new TCPClient(HOSTNAME, PORT);
        tcpClient.addListener(this);
    }

    public void connect() {
        new Thread(udpClient).start();
        new Thread(tcpClient).start();
    }

    public void setNameAndId(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void sendPositionPacket(Player player) {
        ICommand command = CommandFactory.createCommand("pos",
                String.valueOf(player.getPosition().x),
                String.valueOf(player.getPosition().y), String.valueOf(player.getRotation()));

        udpClient.sendPacket(clientID + "-" + commandConverter.toMessage(command));
    }

    private void sendMessage(String message) {
        tcpClient.sendMessage(message);
    }

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

            } catch (NumberFormatException ignored) { }
        }
    }

    @Override
    public void UDPErrorOccurred(String message) {
        System.out.println(message);
    }

    @Override
    public void gotMessage(String message) {
        ICommand command = commandConverter.toCommand(message);

        switch (command.getCmd()) {
            case "connected":
                clientID = command.getArgs()[0];
                System.out.println("Got id" + clientID);
                break;
            case "response":
                if (command.getArgs().length > 2) {
                    int id = Integer.parseInt(command.getArgs()[0]);
                    System.out.println("Server ID: " + id);

                    // TODO: FIX THIS Hardcoded name removal
                    for (int i = 2; i < command.getArgs().length - 1; i++) {
                        String playerName = command.getArgs()[i];
                        gameController.handleAddOpponent(new OpponentPlayer(playerName, new Vector2(50, 100), 0));
                    }
                }
                break;
            case "missile":
                String[] args = command.getArgs();

                float x = Float.parseFloat(args[0]);
                float y = Float.parseFloat(args[1]);
                float rotation = Float.parseFloat(args[2]);
                float playerXSpeed = Float.parseFloat(args[3]);
                float playerYSpeed = Float.parseFloat(args[4]);

                gameController.getGameModel().spawnMissile(x, y, rotation, playerXSpeed, playerYSpeed, false);

                break;
            case "game":
                if (command.getArgs().length > 1) {
                    if (command.getArgs()[0].equals("joined")) {
                        String playerName = command.getArgs()[1];
                        gameController.handleAddOpponent(new OpponentPlayer(playerName, new Vector2(50, 100), 0));
                        System.out.println("Opponent created");

                    } else if (command.getArgs()[0].equals("left")) {
                        //TODO: Remove Opponent
                    }
                }
                break;
        }
    }

    @Override
    public void lostConnection(String message) {
        isConnected = false;
    }

    @Override
    public void onConnection() {
        isConnected = true;

        if (id.isEmpty()) {
            createGame(name);
        } else {
            joinGame(name, id);
        }
    }

    public void createGame(String name) {
        if (isConnected) {
            sendMessage("create:" + name);
            sendMessage("start");
            System.out.println("Game created");
        }
    }

    public void joinGame(String name, String id) {
        if (isConnected) {
            sendMessage("join:" + id + "," + name);
            System.out.println("Game joined");
        }
    }

    @Override
    public void onMissileShot(Vector2 position, Vector2 velocity, float rotation) {
        if (isConnected) {
            sendMessage("missile:" + position.x + "," + position.y + "," + rotation + "," + velocity.x + "," + velocity.y);
        }
    }

    /**
     *
     * @return
     */
    public boolean isConnected(){
        return isConnected;
    }
}
