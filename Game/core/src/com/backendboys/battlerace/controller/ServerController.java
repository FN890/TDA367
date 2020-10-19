package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.BattleRace;
import com.backendboys.battlerace.model.gamemodel.opponent.OpponentPlayer;
import com.backendboys.battlerace.model.gamemodel.particles.IMissileListener;
import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.backendboys.battlerace.model.gamemodel.world.GameWorld;
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

    public ServerController(BattleRace game, GameController gameController) {
        this.game = game;

        gameController.getGameModel().getWorldExplosions().addMissileListener(this);

        this.gameController = gameController;
        commandConverter = new CommandConverter();

        udpClient = new UDPClient(HOSTNAME, PORT);
        udpClient.addListener(this);
        new Thread(udpClient).start();

        tcpClient = new TCPClient(HOSTNAME, PORT);
        tcpClient.addListener(this);
        new Thread(tcpClient).start();
    }

    public void sendPositionPacket(Player player) {
        ICommand command = CommandFactory.createCommand("pos",
                String.valueOf(player.getPosition().x),
                String.valueOf(player.getPosition().y), String.valueOf(player.getRotation()));

        udpClient.sendPacket(clientID + "-" + commandConverter.toMessage(command));
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

            } catch (NumberFormatException e) {

            }
        }
    }

    @Override
    public void UDPErrorOccurred(String message) {
        System.out.println(message);
    }

    @Override
    public void gotMessage(String message) {
        ICommand command = commandConverter.toCommand(message);

        if (command.getCmd().equals("connected")) {
            clientID = command.getArgs()[0];
            System.out.println("Got id" + clientID);

        } else if (command.getCmd().equals("response")) {
            if (command.getArgs().length > 2) {
                int id = Integer.parseInt(command.getArgs()[0]);
                System.out.println("Server ID: " + id);

                // FIX THIS Hardocded name removal
                for (int i = 2; i < command.getArgs().length - 1; i++) {
                    String playerName = command.getArgs()[i];
                    gameController.handleAddOpponent(new OpponentPlayer(playerName, new Vector2(50, 100), 0));
                }
            }
        } else if (command.getCmd().equals("missile")) {
            String[] args = command.getArgs();

            float x = Float.parseFloat(args[0]);
            float y = Float.parseFloat(args[1]);
            float rotation = Float.parseFloat(args[2]);
            float playerXSpeed = Float.parseFloat(args[3]);
            float playerYSpeed = Float.parseFloat(args[4]);

            gameController.getGameModel().spawnMissile(x, y, rotation, playerXSpeed, playerYSpeed);

        } else if (command.getCmd().equals("game")) {
            if (command.getArgs().length > 1) {
                if (command.getArgs()[0].equals("joined")) {
                    String playerName = command.getArgs()[1];
                    gameController.handleAddOpponent(new OpponentPlayer(playerName, new Vector2(50, 100), 0));
                    System.out.println("Opponent created");

                } else if (command.getArgs()[0].equals("left")) {

                }
            }
        }

    }

    @Override
    public void lostConnection(String message) {
        isConnected = false;
    }

    public void sendMessage(String command) {
        tcpClient.sendCommand(command);
    }

    @Override
    public void onConnection() {
        isConnected = true;
        gameController.onConnection();
    }

    public void createGame(String name) {
        if (isConnected) {
            sendMessage("create:" + name);
            sendMessage("start");
        }
    }

    public void joinGame(String name, String id) {
        if (isConnected) {
            sendMessage("join:" + id + "," + name);
        }
    }

    public void sendMissile(float x, float y, float rotation, float playerXSpeed, float playerYSpeed) {
        if (isConnected) {
            sendMessage("missile:" + x + "," + y + "," + rotation + "," + playerXSpeed + "," + playerYSpeed);
        }
    }

    @Override
    public void onMissileShot(Vector2 position, Vector2 velocity, float rotation) {
        if (isConnected) {
            sendMessage("missile:" + position.x + "," + position.y + "," + rotation + "," + velocity.x + "," + velocity.y);
        }
    }
}
