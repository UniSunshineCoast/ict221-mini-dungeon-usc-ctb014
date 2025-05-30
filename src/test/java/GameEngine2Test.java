import dungeon.engine.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameEngine2Test {

    private GameEngine2 engine;
    private Player player;

    @BeforeEach
    void setUp() {
        engine = new GameEngine2();
        player = engine.getPlayer();
    }

    @Test
    void testInitialPlayerPosition() {
        assertEquals(1, player.getX());
        assertEquals(9, player.getY());
    }

    @Test
    void testPlayerMovementWithinBounds() {
        int initialX = player.getX();
        int initialY = player.getY();
        engine.movePlayer("u");
        assertTrue(player.getY() < initialY || player.getY() == initialY);
    }

    @Test
    void testInvalidMoveDoesNotThrowError() {
        assertDoesNotThrow(() -> engine.movePlayer("invalid"));
    }


    @Test
    void testRangedMutantAttack() {
        player.setPlayerHealth(100, '!');
        RangedMutant ranged = new RangedMutant(10, 5);
        engine.getMap().placeObject(ranged, player.getX(), player.getY() - 2); // Up 2 cells
        for (int i = 0; i < 10; i++) { // Try multiple turns for chance-based attack
            engine.movePlayer("d");
        }
        assertTrue(player.getPlayerHealth() < 100);
    }


    @Test
    void testGameOverWhenHealthZero() {
        player.setPlayerHealth(1, '!');
        player.setPlayerHealth(10, '-');
        engine.movePlayer("u");
        assertTrue(engine.isGameOver());
    }

    @Test
    void testForceLose() {
        engine.forceLoose();
        assertTrue(engine.isLost());
    }


}
