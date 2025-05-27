package dungeon.engine;

import java.util.ArrayList;
import java.util.List;

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

    // Core move logic (for all directions)
    private void moveObject(GameObject obj, int newX, int newY) {
        int oldX = obj.getX();
        int oldY = obj.getY();

        if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
            grid[oldX][oldY].remove(obj);
            obj.setPosition(newX, newY);
            grid[newX][newY].add(obj);
        } else {
            System.out.println("Move out of bounds!");
        }
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

    // Get all objects at a specific cell (optional helper)
    public List<GameObject> cellObjects(int x, int y) {
        return grid[x][y];
    }
}




//package dungeon.engine;
//
//public class Map {
//    private final int width;
//    private final int height;
//    private final GameObject[][] grid;
//
//    public Map(int width, int height) {
//        this.width = width;
//        this.height = height;
//        grid = new GameObject[width][height];
//    }
//
//
//    //Checks to see if a cell is empty
//    public boolean isCellEmpty(int x, int y) {
//        return grid[x][y] == null;
//    }
//
//
//    //Places a gameObject on to the map
//    public void placeObject(GameObject obj, int x, int y) {
//        if (isCellEmpty(x, y)) {
//            obj.setPosition(x, y);
//            grid[x][y] = obj;
//        } else {
//            System.out.println("Cell is occupied!");
//        }
//    }
//
//
//    //Object Movement
//    public void up(GameObject obj){
//        int oldX = obj.getX();
//        int oldY = obj.getY();
//
//        int newX = oldX;
//        int newY = oldY - 1;
//
//        if (newY >= 0) {
//            grid[oldX][oldY] = null;
//            obj.setPosition(newX, newY);
//            grid[newX][newY] = obj;
//        }
//    }
//
//    public void down(GameObject obj){
//        int oldX = obj.getX();
//        int oldY = obj.getY();
//
//        int newX = oldX;
//        int newY = oldY + 1;
//
//        if (newY < height) {
//            grid[oldX][oldY] = null;
//            obj.setPosition(newX, newY);
//            grid[newX][newY] = obj;
//        }
//    }
//
//    public void left(GameObject obj){
//        int oldX = obj.getX();
//        int oldY = obj.getY();
//
//        int newX = oldX - 1;
//        int newY = oldY;
//
//        if (newX >= 0) {
//            grid[oldX][oldY] = null;
//            obj.setPosition(newX, newY);
//            grid[newX][newY] = obj;
//        }
//    }
//
//    public void right(GameObject obj){
//        int oldX = obj.getX();
//        int oldY = obj.getY();
//
//        int newX = oldX + 1;
//        int newY = oldY;
//
//        if (newX < width) {
//            grid[oldX][oldY] = null;
//            obj.setPosition(newX, newY);
//            grid[newX][newY] = obj;
//        }
//    }
//
//
//
//
//
//
//    //Prints the map to the console
//    public void printMap() {
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                if (grid[x][y] != null) {
//                    System.out.print(grid[x][y].getSymbol() + " ");
//                } else {
//                    System.out.print(". ");
//                }
//            }
//            System.out.println();
//        }
//        System.out.println(" ");
//    }
//}
