import dungeon.engine.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {

    @Test
    void testSetAndGetPosition() {
        Gold gold = new Gold(10);
        gold.setPosition(5,6);
        assertEquals(5, gold.getX());
        assertEquals(6, gold.getY());
    }

    @Test
    void testBlocksMovementDefault() {
        Gold gold = new Gold(10);
        assertFalse(gold.isBlocksMovement());

        Wall wall = new Wall();
        assertTrue(wall.isBlocksMovement());
    }

    @Test
    void testGetSymbolImplementation() {
        Gold gold = new Gold(10);
        Trap trap = new Trap(5);
        Ladder ladder = new Ladder();
        assertEquals("G", gold.getSymbol());
        assertEquals("T", trap.getSymbol());
        assertEquals("L", ladder.getSymbol());
    }

    @Test
    void testDefaultImagePathIsNull() {
        Gold gold = new Gold(10);
        assertNull(gold.getImagePath());
    }
}
