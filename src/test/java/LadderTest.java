import dungeon.engine.Ladder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LadderTest {

    @Test
    void testLadderBlocksMovementIsFalse() {
        Ladder ladder = new Ladder();
        assertFalse(ladder.isBlocksMovement(), "Ladder should not block movement");
    }

    @Test
    void testGetSymbol() {
        Ladder ladder = new Ladder();
        assertEquals("L", ladder.getSymbol(), "Ladder symbol should be 'L'");
    }

    @Test
    void testGetImagePath() {
        Ladder ladder = new Ladder();
        assertEquals("/Ladder.png", ladder.getImagePath(), "Ladder image path should match");
    }

    @Test
    void testSetAndGetPosition() {
        Ladder ladder = new Ladder();
        ladder.setPosition(7, 8);
        assertEquals(7, ladder.getX(), "X position should match");
        assertEquals(8, ladder.getY(), "Y position should match");
    }
}
