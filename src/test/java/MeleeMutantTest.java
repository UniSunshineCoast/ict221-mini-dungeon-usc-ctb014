import dungeon.engine.MeleeMutant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MeleeMutantTest {

    @Test
    void testConstructorAndFields() {
        MeleeMutant mutant = new MeleeMutant(10, 5);
        assertEquals(10, mutant.getDamage(), "Damage should match constructor input");
        assertEquals(5, mutant.getValue(), "Value should match constructor input");
        assertFalse(mutant.isBlocksMovement(), "MeleeMutant should not block movement");
    }

    @Test
    void testGetSymbol() {
        MeleeMutant mutant = new MeleeMutant(8, 3);
        assertEquals("M", mutant.getSymbol(), "Symbol should be 'M'");
    }

    @Test
    void testGetImagePath() {
        MeleeMutant mutant = new MeleeMutant(8, 3);
        assertEquals("/monster.png", mutant.getImagePath(), "Image path should match");
    }

    @Test
    void testSetAndGetPosition() {
        MeleeMutant mutant = new MeleeMutant(5, 2);
        mutant.setPosition(4, 7);
        assertEquals(4, mutant.getX(), "X position should match");
        assertEquals(7, mutant.getY(), "Y position should match");
    }
}
