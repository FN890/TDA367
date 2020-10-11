import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.backendboys.battlerace.model.gamemodel.powerups.MissilePowerUp;
import com.backendboys.battlerace.model.gamemodel.powerups.NitroPowerUp;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private final Player player;
    private final String testName = "Dummy";
    private final World world;
    private final Random random;


    public PlayerTest() {
        player = new Player(testName);
        world = new World(new Vector2(0, -10), true);
        this.random = new Random();
    }

    @Test
    public void PlayerCreated() {
        assertNotNull(player);
    }

    @Test
    public void PlayerCheckName() {
        assertEquals(testName, player.getName());
    }

    @Test
    public void PlayerAddVehicle() {
        player.addVehicle(world, 10, 10);
        assertNotNull(player.getVehicle());
    }


    /**
     * test a random amount of power ups and check if that amount exist in player.
     */
    @Test
    public void PlayerAddPowerUp() {
        int amountPowerUp1 = random.nextInt(100);
        int amountPowerUp2 = random.nextInt(100);

        for (int i = 0; i < amountPowerUp1; i++) {
            player.addPowerUp(new MissilePowerUp(world, 1, 1));
        }
        for (int i = 0; i < amountPowerUp2; i++) {
            player.addPowerUp(new NitroPowerUp(world, 1, 1));
        }

        assertEquals(amountPowerUp1 + amountPowerUp2, player.getListPowerUp().size());
    }


    @Test
    public void PlayerUsePowerUp() {
        player.usePowerUp();
        player.usePowerUp();

        assertTrue(player.getListPowerUp().size() == 0);
    }

}