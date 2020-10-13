import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UDPClient implements Runnable {

    private final String hostname;
    private final int port;

    private InetAddress address;
    private DatagramSocket socket;

    private final List<String> messages = Collections.synchronizedList(new ArrayList<>());

    public UDPClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;

        try {

            address = InetAddress.getByName(hostname);
            socket = new DatagramSocket(port);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        try {

            while (true) {
                byte[] buffer = new byte[548];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);

                String message = new String(buffer, 0, response.getLength());
                messages.add(message);

            }

        } catch (Exception ignored) {
        }


    }

    public String getClientMessage(String name) {
        synchronized (messages) {
            for (String m : messages) {
                if (m.startsWith("pos:" + name)) {
                    return m;
                }
            }
        }

        return null;
    }

    public void clearMessages() {
        messages.clear();
    }

}
