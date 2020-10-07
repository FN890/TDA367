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
    public void TestInvalidCommand() {

        String error = client1.sendMessage("test");

        Assertions.assertEquals("error:Invalid command.", error);
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

        String response2 = client3.sendMessage("join:1402,Client3");
        Assertions.assertEquals("response:1402,true,client2,client3", response2);
    }

    @Test
    @Order(4)
    public void TestLeaveGame() {

        client3.sendMessage("leave");

        String response = client2.sendMessage("get:game");
        Assertions.assertEquals("response:1402,true,client2", response);

        client2.sendMessage("leave");
        String error = client2.sendMessage("join:1402,client2");
        Assertions.assertEquals("error:Game not found.", error);
    }

    @Test
    @Order(5)
    public void TestAlreadyInGame() {

        client2.sendMessage("create:Client2");
        String error1 = client2.sendMessage("create:Client2");
        Assertions.assertEquals("error:Already in game.", error1);

        String error2 = client2.sendMessage("join:1403,Client2");
        Assertions.assertEquals("error:Already in game.", error2);
    }

    @Test
    @Order(6)
    public void TestPositionUpdates() {

        
    }

}
