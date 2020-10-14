import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {

    private final String hostname;
    private final int port;

    private Socket socket;

    public PrintWriter writer;
    public BufferedReader reader;

    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;

        try {
            socket = new Socket(hostname, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {

            String message;
            while ((message = reader.readLine()) != null) {

                // Skips sending the response on "updates".
                if (!message.equalsIgnoreCase("connected") && !message.startsWith("Player")) {
                    response = message;
                }
            }

        } catch (IOException ignored) {
        }

    }

    private String response = "";

    public String sendMessage(String message) {
        System.out.println("Sending message...");
        writer.println(message);

        // Skips waiting on commands that does not return a response.
        if (message.startsWith("pos:")) {
            return null;
        }

        // Waits for a response.
        while (response.isEmpty()) {
        }

        String r = response;
        response = "";
        return r;
    }

    public void disconnect() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
