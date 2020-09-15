
import com.backendboys.battlerace.model.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    public void PlayerTest(){
        assertNotNull(new Player("name", 0));
    }
}