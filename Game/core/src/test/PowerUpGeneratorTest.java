import com.backendboys.battlerace.model.GameModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PowerUpGeneratorTest {

    private GameModel gameModel;

    @Test
    public void testPowerUpsGeneration() {
        gameModel = new GameModel();

        assertNotEquals(0, gameModel.getPowerUps().size());
    }

}