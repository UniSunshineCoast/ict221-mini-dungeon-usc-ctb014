import dungeon.engine.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    @Test
    void testMazeGenerationNoException() {
        Map map = new Map(11, 11);
        assertDoesNotThrow(() -> Maze.generateMaze(map, new Wall(), 3, 30));
    }

    @Test
    void testMazeDimensionsMatch() {
        Map map = new Map(11, 11);
        Maze.generateMaze(map, new Wall(), 3, 30);
        assertEquals(11, map.getWidth());
        assertEquals(11, map.getHeight());
    }

    @Test
    void testWallsInEvenEvenPositions() {
        Map map = new Map(11, 11);
        Maze.generateMaze(map, new Wall(), 3, 30);
        for (int x = 0; x < map.getWidth(); x += 2) {
            for (int y = 0; y < map.getHeight(); y += 2) {
                boolean canEnter = map.canEnterCell(x, y);
                assertFalse(canEnter, "Expected wall at even-even cell (" + x + "," + y + ")");
            }
        }
    }

    @Test
    void testPathsExistInOddOddPositions() {
        Map map = new Map(11, 11);
        Maze.generateMaze(map, new Wall(), 3, 30);
        boolean hasPath = false;
        for (int x = 1; x < map.getWidth(); x += 2) {
            for (int y = 1; y < map.getHeight(); y += 2) {
                if (map.canEnterCell(x, y)) {
                    hasPath = true;
                    break;
                }
            }
        }
        assertTrue(hasPath, "Expected at least one walkable path in maze");
    }

    @Test
    void testMazeGenerationForDifferentSizes() {
        int[] sizes = {5, 11, 21};
        for (int size : sizes) {
            Map map = new Map(size, size);
            assertDoesNotThrow(() -> Maze.generateMaze(map, new Wall(), 3, 30), "Maze generation failed for size " + size);
        }
    }
}
