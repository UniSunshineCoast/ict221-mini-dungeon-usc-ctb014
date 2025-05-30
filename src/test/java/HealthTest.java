import dungeon.engine.Health;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HealthTest {

    @Test
    void testHealthValueIsStoredCorrectly() {
        Health potion = new Health(25);
        assertEquals(25, potion.getHealthValue(), "Health value should match constructor parameter");
    }

    @Test
    void testBlocksMovementIsFalse() {
        Health potion = new Health(10);
        assertFalse(potion.isBlocksMovement(), "Health should not block movement");
    }

    @Test
    void testGetSymbol() {
        Health potion = new Health(10);
        assertEquals("H", potion.getSymbol(), "Health symbol should be 'H'");
    }

    @Test
    void testGetImagePath() {
        Health potion = new Health(10);
        assertEquals("/Health.png", potion.getImagePath(), "Health image path should match");
    }

    @Test
    void testSetAndGetPosition() {
        Health potion = new Health(15);
        potion.setPosition(4,5);
        assertEquals(4, potion.getX(), "X position should match");
        assertEquals(5, potion.getY(), "Y position should match");
    }
}

