package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer implements Runnable {

    private final int port;
    private final DatagramSocket socket;

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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPacket(String message, InetAddress address, int port) throws IOException {
        byte[] byteMsg =  message.getBytes();

        DatagramPacket packet = new DatagramPacket(byteMsg, 0, byteMsg.length, address, port);
        socket.send(packet);
    }

}
