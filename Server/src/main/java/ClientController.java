import java.net.Socket;

public class ClientController implements Runnable {

    private Socket socket;

    public ClientController(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }


}
