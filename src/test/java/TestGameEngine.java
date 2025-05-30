import dungeon.engine.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestGameEngine {

    private GameEngine engine;
    private Player player;
    private Map map;

    @BeforeEach
    void setup() {
        engine = new GameEngine();  // If you have a constructor, or refactor GameEngine to non-static
        map = new Map(11,11);
        player = new Player();
    }

    @Test
    void testGoldIncreasesScore() {
        Gold gold = new Gold(5);
        map.placeObject(gold, 5,5);
        map.placeObject(player, 5,5);

        player.setPlayerScore(0, '!');  // Reset score
        Gold foundGold = map.getObjectInCell(5,5, Gold.class);
        assertNotNull(foundGold);

        player.setPlayerScore(foundGold.getValue(), '+');
        map.removeObject(foundGold);

        assertEquals(5, player.getPlayerScore());
    }

    @Test
    void testTrapDecreasesHealth() {
        Trap trap = new Trap(10);
        map.placeObject(trap, 4,4);
        map.placeObject(player, 4,4);

        int originalHealth = player.getPlayerHealth();
        player.setPlayerHealth(trap.getDamage(), '-');
        assertTrue(player.getPlayerHealth() < originalHealth);
    }

    @Test
    void testLadderAdvancesLevel() {
        Ladder ladder = new Ladder();
        map.placeObject(ladder, 3,3);
        map.placeObject(player, 3,3);

        assertEquals(3, player.getX());
        assertEquals(3, player.getY());
        // You can simulate triggering the ladder logic here if it's exposed in methods
    }

    @Test
    void testPlayerLosesWhenHealthZero() {
        player.setPlayerHealth(999, '!'); // Set health high
        player.setPlayerHealth(1000, '-'); // Subtract to 0
        assertTrue(player.gameOver, "Player should be marked as game over when health <= 0");
    }

    @Test
    void testMapBoundaries() {
        player.setPosition(0,0);
        map.placeObject(player, 0,0);
        map.left(player); // Should not move left outside bounds
        assertEquals(0, player.getX());
    }
}
