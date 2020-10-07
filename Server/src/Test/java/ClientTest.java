import controller.ServerController;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientTest {

    private static Client client1;
    private static Client client2;
    private static Client client3;

    @BeforeAll
    public static void TestConnectClient() {

        client1 = new Client("localhost", 26000);
        new Thread(client1).start();

        client2 = new Client("localhost", 26000);
        new Thread(client2).start();

        client3 = new Client("localhost", 26000);
        new Thread(client3).start();

    }

    @Test
    @Order(1)
    public void TestInvalidSyntax() {

        String response = client1.sendMessage("test");

        Assertions.assertEquals("error:Invalid syntax.", response);
    }

    @Test
    @Order(2)
    public void TestCreateGame() {

        String response = client1.sendMessage("create:Gustav");

        Assertions.assertEquals("response:1401,true,gustav", response);

        response = client1.sendMessage("get:game");
        Assertions.assertEquals("response:1401,true,gustav", response);
    }

    @Test
    @Order(3)
    public void TestJoinGame() {

        String response1 = client2.sendMessage("create:Client2");
        String sId = response1.substring(9, 13);

        int id = Integer.parseInt(sId);

        String response2 = client3.sendMessage("join:" + id + ",Client3");
        Assertions.assertEquals("response:" + sId + ",true,client2,client3", response2);
    }

    @Test
    @Order(4)
    public void TestLeaveGame() {
        

    }

}
