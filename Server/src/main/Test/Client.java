import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {

    private String hostname;
    private int port;

    public PrintWriter writer;
    public BufferedReader reader;

    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(hostname, port)) {

            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;

            while((message = reader.readLine()) != null) {
                System.out.println("Server: " + message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendMessage(String message) {
        writer.println(message);
    }

}
