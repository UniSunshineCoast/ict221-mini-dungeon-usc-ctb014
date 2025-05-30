import dungeon.engine.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    private Map map;
    private Player player;
    private Wall wall;
    private Gold gold;

    @BeforeEach
    void setUp() {
        map = new Map(5, 5);
        player = new Player();
        wall = new Wall();
        gold = new Gold(10);
    }

    @Test
    void testPlaceObjectAndIsCellEmpty() {
        assertTrue(map.isCellEmpty(2, 2));
        map.placeObject(player, 2, 2);
        assertFalse(map.isCellEmpty(2, 2));
        assertEquals(2, player.getX());
        assertEquals(2, player.getY());
    }

    @Test
    void testRemoveSpecificObject() {
        map.placeObject(player, 2, 2);
        map.removeObject(player);
        assertTrue(map.isCellEmpty(2, 2));
    }

    @Test
    void testRemoveObjectsAt() {
        map.placeObject(player, 1, 1);
        map.placeObject(gold, 1, 1);
        map.removeObjectsAt(1, 1);
        assertTrue(map.isCellEmpty(1, 1));
    }

    @Test
    void testCanEnterCell() {
        map.placeObject(wall, 3, 3);
        assertFalse(map.canEnterCell(3, 3));
        assertTrue(map.canEnterCell(0, 0)); // Empty cell
    }

    @Test
    void testMovementWithinBounds() {
        map.placeObject(player, 2, 2);
        map.up(player);
        assertEquals(2, player.getX());
        assertEquals(1, player.getY());

        map.down(player);
        assertEquals(2, player.getX());
        assertEquals(2, player.getY());

        map.left(player);
        assertEquals(1, player.getX());
        assertEquals(2, player.getY());

        map.right(player);
        assertEquals(2, player.getX());
        assertEquals(2, player.getY());
    }

    @Test
    void testCannotMoveIntoBlockedCell() {
        map.placeObject(player, 1, 1);
        map.placeObject(wall, 1, 0); // Block above
        map.up(player);
        assertEquals(1, player.getX());
        assertEquals(1, player.getY()); // Should not move
    }

    @Test
    void testAvailableCells() {
        map.placeObject(player, 0, 0);
        List<int[]> emptyCells = map.availableCells();
        assertEquals(24, emptyCells.size());
        for (int[] cell : emptyCells) {
            assertTrue(map.isCellEmpty(cell[0], cell[1]));
        }
    }

    @Test
    void testRandomObjectPlace() {
        Gold g = new Gold(5);
        map.randomObjectPlace(g);
        assertTrue(g.getX() >= 0 && g.getX() < map.getWidth());
        assertTrue(g.getY() >= 0 && g.getY() < map.getHeight());
        assertFalse(map.isCellEmpty(g.getX(), g.getY()));
    }

    @Test
    void testGetObjectInCell() {
        map.placeObject(gold, 2, 2);
        Gold found = map.getObjectInCell(2, 2, Gold.class);
        assertNotNull(found);
        assertEquals(gold, found);
    }

    @Test
    void testClearMap() {
        map.placeObject(player, 1, 1);
        map.placeObject(wall, 2, 2);
        map.clearMap();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                assertTrue(map.isCellEmpty(x, y));
            }
        }
    }
}
