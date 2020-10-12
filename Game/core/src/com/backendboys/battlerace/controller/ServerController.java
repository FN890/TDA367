package com.backendboys.battlerace.controller;

import com.backendboys.battlerace.BattleRace;
import com.backendboys.battlerace.model.gamemodel.opponent.OpponentPlayer;
import com.backendboys.battlerace.services.IPacketListener;
import com.backendboys.battlerace.services.ITCPListener;
import com.backendboys.battlerace.services.TCPClient;
import com.backendboys.battlerace.services.UDPClient;
import com.backendboys.battlerace.services.protocol.CommandConverter;
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

    public ServerController(BattleRace game) {
        this.game = game;

        udpClient = new UDPClient(HOSTNAME, PORT);
        udpClient.addListener(this);
        new Thread(udpClient).start();

        tcpClient = new TCPClient(HOSTNAME, PORT);
        tcpClient.addListener(this);
        new Thread(tcpClient).start();
    }

    @Override
    public void gotPacket(String message) {
        ICommand command = commandConverter.toCommand(message);

        if(command.getCmd().equals("pos")) {
            try {
                String playerName = command.getArgs()[0];
                float playerXPos = Float.parseFloat(command.getArgs()[1]);
                float playerYPos = Float.parseFloat(command.getArgs()[2]);
                float playerRotation = Float.parseFloat(command.getArgs()[3]);

                gameController.handleUpdateOpponentPosition(playerName, playerXPos, playerYPos, playerRotation);

            }catch (NumberFormatException e){

            }
        }
    }

    @Override
    public void UDPErrorOccurred(String message) {

    }

    @Override
    public void gotMessage(String message) {
        ICommand command = commandConverter.toCommand(message);

        if(command.getCmd().equals("response")){
            if(command.getArgs().length > 2){
                int id = Integer.parseInt(command.getArgs()[0]);
                boolean isRunning = Boolean.parseBoolean(command.getArgs()[1]);
                gameController = new GameController(game);

                for(int i=2; i<command.getArgs().length; i++){
                    String playerName = command.getArgs()[i];
                    gameController.handleAddOpponent(new OpponentPlayer(playerName, new Vector2(25, 50), 0));
                }
            }
        }
    }

    @Override
    public void lostConnection(String message) {

    }
}
