import dungeon.engine.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @Test
    void testInitialHealth() {
        assertEquals(10, player.getPlayerHealth(), "Initial health should be 10");
    }

    @Test
    void testInitialScoreIsZero() {
        assertEquals(0, player.getPlayerScore(), "Initial score should be 0");
    }

    @Test
    void testAddScore() {
        player.setPlayerScore(5, '+');
        assertEquals(5, player.getPlayerScore());
    }

    @Test
    void testSubtractScore() {
        player.setPlayerScore(10, '+');
        player.setPlayerScore(3, '-');
        assertEquals(7, player.getPlayerScore());
    }

    @Test
    void testMultiplyScore() {
        player.setPlayerScore(4, '+'); // 4
        player.setPlayerScore(2, '*'); // 8
        assertEquals(8, player.getPlayerScore());
    }

    @Test
    void testDivideScore() {
        player.setPlayerScore(20, '+');
        player.setPlayerScore(2, '/');
        assertEquals(10, player.getPlayerScore());
    }

    @Test
    void testSetExactScore() {
        player.setPlayerScore(42, '!');
        assertEquals(42, player.getPlayerScore());
    }

    @Test
    void testAddHealth() {
        player.setPlayerHealth(-5, '-'); // Reduce health first
        player.setPlayerHealth(3, '+');  // Then heal
        assertEquals(8, player.getPlayerHealth());
    }

    @Test
    void testHealthDoesNotExceedMax() {
        player.setPlayerHealth(5, '+');
        assertEquals(10, player.getPlayerHealth());
    }

    @Test
    void testHealthGoesToZeroAndGameOver() {
        player.setPlayerHealth(20, '-');
        assertEquals(0, player.getPlayerHealth());
        assertTrue(player.gameOver, "GameOver should be true when health <= 0");
    }

    @Test
    void testMultiplyHealth() {
        player.setPlayerHealth(2, '*');
        assertEquals(10, player.getPlayerHealth(), "Should cap at max health 10");
    }

    @Test
    void testDivideHealth() {
        player.setPlayerHealth(2, '/');
        assertEquals(5, player.getPlayerHealth());
    }

    @Test
    void testExactSetHealth() {
        player.setPlayerHealth(7, '!');
        assertEquals(7, player.getPlayerHealth());
    }

    @Test
    void testAddSteps() {
        player.addStep();
        player.addStep();
        assertEquals(2, player.getSteps());
    }

    @Test
    void testStepCountTriggersGameOver() {
        for (int i = 0; i <= 100; i++) {
            player.addStep();
        }
        assertTrue(player.gameOver, "GameOver should be true after exceeding max steps");
    }

    @Test
    void testGetSymbol() {
        assertEquals("P", player.getSymbol());
    }

    @Test
    void testGetImagePath() {
        assertEquals("/player.png", player.getImagePath());
    }

    @Test
    void testSetAndGetPosition() {
        player.setPosition(3, 4);
        assertEquals(3, player.getX());
        assertEquals(4, player.getY());
    }
}
