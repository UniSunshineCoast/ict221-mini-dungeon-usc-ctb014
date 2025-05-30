import dungeon.engine.Wall;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    @Test
    void testConstructorAndFields() {
        Wall wall = new Wall();
        assertTrue(wall.isBlocksMovement(), "Wall should block movement");
        assertEquals("#", wall.getSymbol(), "Wall symbol should be '#'");
        assertEquals("/wall.png", wall.getImagePath(), "Wall image path should match");
    }

    @Test
    void testSetAndGetPosition() {
        Wall wall = new Wall();
        wall.setPosition(2, 3);
        assertEquals(2, wall.getX(), "X position should match");
        assertEquals(3, wall.getY(), "Y position should match");
    }
}
