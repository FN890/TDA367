import com.backendboys.battlerace.model.gamemodel.GameModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerUpGeneratorTest {

    private GameModel gameModel;

    @Test
    public void testNumberofPowerups() {
        gameModel = new GameModel();
        assertEquals(30, gameModel.getPowerUps().size());
    }

}