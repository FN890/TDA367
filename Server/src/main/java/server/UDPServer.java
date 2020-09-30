package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class UDPServer implements Runnable {

    private final int port;
    private final DatagramSocket socket;

    private final Map<InetAddress, PacketListener> listeners = new HashMap<>();

    public void addListener(InetAddress address, PacketListener listener) {
        listeners.put(address, listener);
    }

    public void removeListener(InetAddress address) {
        listeners.remove(address);
    }

    public UDPServer(int port) throws SocketException {
        this.port = port;
        this.socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        byte[] buffer = new byte[548];

        try {
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String message = new String(request.getData(), 0, request.getLength());
                System.out.println("Packet received: " + message);

                sendToListener(request.getAddress(), message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendToListener(InetAddress address, String message) {
        if (listeners.containsKey(address)) {
            listeners.get(address).gotPacket(message);
        }
    }

    public void sendPacket(String message, InetAddress address, int port) throws IOException {
        byte[] byteMsg =  message.getBytes();

        DatagramPacket packet = new DatagramPacket(byteMsg, 0, byteMsg.length, address, port);
        socket.send(packet);
    }

}
