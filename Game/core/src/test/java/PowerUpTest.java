import com.backendboys.battlerace.model.gamemodel.GameModel;
import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.backendboys.battlerace.model.gamemodel.world.GameWorld;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerUpTest {

    private GameModel gameModel;
    private Player player;

    public PowerUpTest() {
        gameModel = new GameModel();
        player = new Player("player");
    }

    @Test
    public void testPickUpPowerUp() {
        player.addPowerUp(gameModel.getPowerUps().get(0));
        assertEquals(1, player.getListPowerUp().size());
    }

}