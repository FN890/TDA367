package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.BattleRace;
import com.backendboys.battlerace.model.gamemodel.opponent.OpponentPlayer;
import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.backendboys.battlerace.services.*;
import com.backendboys.battlerace.services.protocol.CommandConverter;
import com.backendboys.battlerace.services.protocol.CommandFactory;
import com.backendboys.battlerace.services.protocol.ICommand;
import com.badlogic.gdx.math.Vector2;

public class ServerController implements ITCPListener, IPacketListener {

    private final static String HOSTNAME = "167.172.34.88";
    private final static int PORT = 26000;

    private TCPClient tcpClient;
    private UDPClient udpClient;

    private final BattleRace game;

    private GameController gameController;
    private CommandConverter commandConverter;

    private boolean isConnected = false;

    public ServerController(BattleRace game, GameController gameController) {
        this.game = game;

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

        udpClient.sendPacket(commandConverter.toMessage(command));
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

        System.out.println(message);
        if (command.getCmd().equals("response")) {
            if (command.getArgs().length > 2) {
                int id = Integer.parseInt(command.getArgs()[0]);
                System.out.println("Server ID: " + id);

                // FIX THIS Hardocded name removal
                for(int i=2; i<command.getArgs().length-1; i++){
                    String playerName = command.getArgs()[i];
                    gameController.handleAddOpponent(new OpponentPlayer(playerName, new Vector2(50, 100), 0));
                }
            }
        }else if (command.getCmd().equals("game")) {
            if (command.getArgs().length > 1) {
                if(command.getArgs()[0].equals("joined")) {
                    String playerName = command.getArgs()[1];
                    gameController.handleAddOpponent(new OpponentPlayer(playerName, new Vector2(50, 100), 0));
                    System.out.println("Opponent created");

                }else if(command.getArgs()[0].equals("left")) {

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

    public void startServer(String name) {
        if(isConnected){
            sendMessage("create:" + name);
            sendMessage("start");
        }
    }

    public void joinServer(String name, String id) {
        if(isConnected){
            sendMessage("join:" + id + "," + name);
        }
    }
}
