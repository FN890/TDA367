import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.protocol.ICommand;
import services.protocol.IServerProtocol;
import services.protocol.ProtocolException;
import services.protocol.ServerProtocolFactory;

import java.util.Arrays;

public class ProtocolTest {

    @Test
    public void TestParseMessage() throws ProtocolException {

        IServerProtocol protocol = ServerProtocolFactory.getServerProtocol();
        String message = "join:123,Gustav";

        ICommand cmd = protocol.parseMessage(message);

        System.out.println(cmd.getCmd());
        System.out.println(Arrays.toString(cmd.getArgs()));

        Assertions.assertEquals("join", cmd.getCmd());
        Assertions.assertArrayEquals(new String[]{"123", "gustav"}, cmd.getArgs());
    }


}
