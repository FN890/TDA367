import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private int port;
    private ServerSocket serverSocket;

    public TCPServer(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }

    public void listen() throws IOException {

        System.out.println("Server started listening on port: " + port);

        while (true) {
            Socket client = serverSocket.accept();

            System.out.println("Client connected to the Server: " + client.getInetAddress().getHostAddress());
            new Thread(new ClientController(client)).start();
        }

    }

}
