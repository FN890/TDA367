
import com.backendboys.battlerace.model.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    public void PlayerCreated(){
        assertNotNull(new Player("name"));
    }

    @Test
    public void PlayerCheckName(){
        Player player = new Player("Player1");
    }

    @Test
    public void PlayerAddPowerUp(){
        Player player = new Player("name");
    }
}