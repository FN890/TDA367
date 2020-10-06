import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    public void TestConnectClient() {

        Client client = new Client("localhost", 26000);
        new Thread(client).start();

        Assertions.assertEquals(1, 1);
    }

    @Test
    public void Test() {
        Assertions.assertEquals(1, 1);
    }

}
