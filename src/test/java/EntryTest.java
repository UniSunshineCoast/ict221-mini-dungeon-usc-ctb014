import dungeon.engine.Entry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EntryTest {

    @Test
    void testEntryInitialization() {
        Entry entry = new Entry();
        assertFalse(entry.isBlocksMovement(), "Entry should not block movement");
    }

    @Test
    void testGetSymbol() {
        Entry entry = new Entry();
        assertEquals("E", entry.getSymbol(), "Entry symbol should be 'E'");
    }

    @Test
    void testGetImagePath() {
        Entry entry = new Entry();
        assertEquals("/Door.png", entry.getImagePath(), "Entry image path should match");
    }
}
