import com.backendboys.battlerace.model.gamemodel.player.Player;
import com.backendboys.battlerace.model.gamemodel.powerups.MissilePowerUp;
import com.backendboys.battlerace.model.gamemodel.powerups.NitroPowerUp;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player;
    private final String testName = "Dummy";
    World world;


    public PlayerTest() {
        player = new Player(testName);
        world = new World(new Vector2(0, -10), true);
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
        player.addPowerUp(new MissilePowerUp());
        player.addPowerUp(new NitroPowerUp());

        assertTrue(player.getListPowerUp().size() == 2);
    }


    @Test
    public void PlayerUsePowerUp() {
        player.usePowerUp();
        player.usePowerUp();

        assertTrue(player.getListPowerUp().size() == 0);
    }

}