package server;

import controller.ClientController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {

    private final int port;
    private final ServerSocket serverSocket;

    private final List<ClientController> connections = new ArrayList<>();

    public TCPServer(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }

    public void listen() throws IOException {

        System.out.println("Server started listening on port: " + port);

        while (true) {
            Socket client = serverSocket.accept();

            System.out.println("Client connected to the Server: " + client.getInetAddress().getHostAddress());
            ClientController clientController = new ClientController(client);
            connections.add(clientController);

            new Thread(clientController).start();
        }

    }

    public List<ClientController> getConnections() {
        return connections;
    }

}
