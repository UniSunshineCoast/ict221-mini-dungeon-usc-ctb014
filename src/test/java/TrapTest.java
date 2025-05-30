import dungeon.engine.Trap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrapTest {

    @Test
    void testConstructorAndFields() {
        Trap trap = new Trap(15);
        assertEquals(15, trap.getDamage(), "Trap damage should match constructor input");
        assertEquals("T", trap.getSymbol(), "Trap symbol should be 'T'");
        assertEquals("/Fire.png", trap.getImagePath(), "Trap image path should match");
        assertFalse(trap.isBlocksMovement(), "Trap should not block movement");
    }

    @Test
    void testSetAndGetPosition() {
        Trap trap = new Trap(10);
        trap.setPosition(3, 4);
        assertEquals(3, trap.getX(), "X position should match");
        assertEquals(4, trap.getY(), "Y position should match");
    }
}
