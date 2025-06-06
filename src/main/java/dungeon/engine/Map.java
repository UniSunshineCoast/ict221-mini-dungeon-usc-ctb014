package dungeon.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    private final int width;
    private final int height;
    private final List<GameObject>[][] grid;

    @SuppressWarnings("unchecked")
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new ArrayList[width][height];

        // Initialize each cell with an empty list
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }
    }

    // Checks if a cell is empty
    public boolean isCellEmpty(int x, int y) {
        return grid[x][y].isEmpty();
    }

    // Places a gameObject on the map (adds to the list)
    public void placeObject(GameObject obj, int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            obj.setPosition(x, y);
            grid[x][y].add(obj);
        } else {
            System.out.println("Invalid position!");
        }
    }

    // Removes a specific object from the map
    public void removeObject(GameObject obj) {
        int x = obj.getX();
        int y = obj.getY();
        grid[x][y].remove(obj);
    }

    //Removes all objects at a particular location
    public void removeObjectsAt(int x, int y) {
        grid[x][y].clear();
    }


    //Checks to see if the cell has an object that blocks movement into that cell
    public boolean canEnterCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false; // Out of bounds
        }

        for (GameObject obj : grid[x][y]) {
            if (obj.isBlocksMovement()) {
                return false; //Object allows movement into Cell
            }
        }
        return true; //Blocks object from being able to move into Cell
    }


    //More logic, used for all directions
    private void moveObject(GameObject obj, int newX, int newY) {
        int oldX = obj.getX();
        int oldY = obj.getY();

        if (canEnterCell(newX,newY)) {
            grid[oldX][oldY].remove(obj);
            obj.setPosition(newX, newY);
            grid[newX][newY].add(obj);
        } else {
            System.out.println("You Can't Go That Way");
        }
    }

    // Moves an object in a specific direction
    public void up(GameObject obj) {
        moveObject(obj, obj.getX(), obj.getY() - 1);
    }

    public void down(GameObject obj) {
        moveObject(obj, obj.getX(), obj.getY() + 1);
    }

    public void left(GameObject obj) {
        moveObject(obj, obj.getX() - 1, obj.getY());
    }

    public void right(GameObject obj) {
        moveObject(obj, obj.getX() + 1, obj.getY());
    }



    // Prints the map: shows only the last object in each cell
    public void printMap() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                List<GameObject> cellObjects = grid[x][y];
                if (!cellObjects.isEmpty()) {
                    GameObject lastObject = cellObjects.get(cellObjects.size() - 1);
                    System.out.print("[" + lastObject.getSymbol() + "] ");
                } else {
                    System.out.print("[ ] ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }



    //Empty Cell Functions


    //Find empty cells on the map
    public List<int[]> availableCells() {
        List<int[]> emptyCells = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (grid[x][y].isEmpty()) {
                    emptyCells.add(new int[]{x, y});
                }
            }
        }

        return emptyCells;
    }

    //Outputs a random empty cell
    public int[] randomCellSelect() {
        List<int[]> emptyCells = availableCells();
        if (emptyCells.isEmpty()) {
            return null; // No empty cells available
        }
        Random rand = new Random();
        return emptyCells.get(rand.nextInt(emptyCells.size()));
    }

    //Randomly places an object on the map
    public void randomObjectPlace(GameObject obj) {
        int[] cords = randomCellSelect();

        if (cords != null) {
            int x = cords[0];
            int y = cords[1];
            placeObject(obj, x, y);
        }
    }


    // Get all objects at a specific cell (optional helper)
    public List<GameObject> cellObjects(int x, int y) {
        return grid[x][y];

    }

    //Checks to see if an object is at a particular location
    public <T extends GameObject> T getObjectInCell(int x, int y, Class<T> objectType) {
        List<GameObject> objectsInCell = cellObjects(x, y);

        for (GameObject obj : objectsInCell) {
            if (objectType.isInstance(obj)) {
                return objectType.cast(obj);
            }
        }
        return null;  // No object of that type found
    }


    //Clears Maps of all objects
    public void clearMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y].clear();  // Remove all objects from the list at each cell
            }
        }
    }

    public int getHeight() {return height;}
    public int getWidth() {return width;}
}




