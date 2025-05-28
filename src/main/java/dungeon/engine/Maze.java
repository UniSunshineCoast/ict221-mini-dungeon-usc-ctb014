package dungeon.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {
    private static final Random rand = new Random();


    //Maze Generating Algorithm creates a maze of any size, ODD width and length for best result
    public static void generateMaze(Map map, GameObject wallTemplate, int maxWalkLength, int throughPathChance) {
        int width = map.getWidth();
        int height = map.getHeight();

        // Clear the map first
        map.clearMap();

        List<int[]> nodes = new ArrayList<>();

        // Identify valid nodes (odd-odd cells)
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x % 2 != 0 && y % 2 != 0) {
                    nodes.add(new int[]{x, y});
                } else {
                    // Fill even cells with walls using the template
                    try {
                        GameObject wall = wallTemplate.getClass().getDeclaredConstructor().newInstance();
                        map.placeObject(wall, x, y);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error creating wall at " + x + ", " + y);
                    }
                }
            }
        }

        // Convert throughPathChance to probability array
        boolean[] tpc = new boolean[100];
        for (int i = 0; i < throughPathChance; i++) {
            tpc[i] = true;
        }

        // Random walk generation
        List<int[]> visited = new ArrayList<>();
        int[] current = nodes.remove(rand.nextInt(nodes.size()));
        visited.add(current);

        while (!nodes.isEmpty()) {
            current = visited.get(rand.nextInt(visited.size()));

            for (int i = 0; i < maxWalkLength; i++) {
                List<int[]> nearby = new ArrayList<>();

                int[][] directions = {
                        {0, -2}, {0, 2}, {2, 0}, {-2, 0}
                };

                for (int[] d : directions) {
                    int nx = current[0] + d[0];
                    int ny = current[1] + d[1];

                    int[] neighbor = {nx, ny};
                    if (containsNode(nodes, neighbor)) {
                        nearby.add(neighbor);
                    }
                }

                if (nearby.isEmpty()) break;

                int[] next = nearby.get(rand.nextInt(nearby.size()));

                // Carve path between current and next
                int gapX = (current[0] + next[0]) / 2;
                int gapY = (current[1] + next[1]) / 2;
                map.removeObjectsAt(gapX, gapY);  // Clear wall

                current = next;
                visited.add(current);
                removeNode(nodes, current);

                // Optional through-path logic
                if (tpc[rand.nextInt(100)]) {
                    List<int[]> moreNearby = new ArrayList<>();
                    for (int[] d : directions) {
                        int nx = current[0] + d[0];
                        int ny = current[1] + d[1];
                        int[] neighbor = {nx, ny};
                        if (containsNode(nodes, neighbor)) {
                            moreNearby.add(neighbor);
                        }
                    }

                    if (!moreNearby.isEmpty()) {
                        int[] nextNext = moreNearby.get(rand.nextInt(moreNearby.size()));
                        gapX = (current[0] + nextNext[0]) / 2;
                        gapY = (current[1] + nextNext[1]) / 2;
                        map.removeObjectsAt(gapX, gapY);
                    }
                }
            }
        }

        System.out.println("Maze generation complete.");
    }

    private static boolean containsNode(List<int[]> nodes, int[] target) {
        for (int[] n : nodes) {
            if (n[0] == target[0] && n[1] == target[1]) return true;
        }
        return false;
    }

    private static void removeNode(List<int[]> nodes, int[] target) {
        nodes.removeIf(n -> n[0] == target[0] && n[1] == target[1]);
    }
}
