package controller;

import server.protocol.ICommand;
import server.protocol.IServerProtocol;
import server.protocol.ProtocolException;
import server.protocol.ServerProtocolFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController implements Runnable {

    private Socket socket;

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
                    ICommand cmd = protocol.parseTCPMessage(input);


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

    private void disconnect() {
        try {
            System.out.println("Client disconnected from server: " + socket.getInetAddress().getHostAddress());
            socket.close();
        } catch (Exception ignored) {}
    }


}
