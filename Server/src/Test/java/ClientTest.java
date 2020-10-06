import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ClientTest {

    private static Client client;

    @BeforeAll
    public static void TestConnectClient() {

        client = new Client("localhost", 26000);
        new Thread(client).start();

    }

    @Test
    public void TestInvalidSyntax() {

        String response = client.sendMessage("test");

        Assertions.assertEquals("error:Invalid syntax.", response);
    }

    @Test
    public void TestCreateGame() {

        String response = client.sendMessage("create:Gustav");

        Assertions.assertEquals("response:1401,true,gustav", response);

        response = client.sendMessage("get:game");
        Assertions.assertEquals("response:1401,true,gustav", response);
    }

}
