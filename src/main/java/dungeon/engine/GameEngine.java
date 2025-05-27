package dungeon.engine;

import javafx.scene.text.Text;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class GameEngine {


    //Game Constants
    //Map Size
    private static final int mapWidth = 10;
    private static final int mapHeight = 10;

    //Start Location
    private static final int gameStartLocationX = 0;
    private static final int gameStartLocationY = mapHeight - 1;

    //Max Steps
    private static final int maxSteps = 100;



    //Main Game Code
    public static void main(String[] args) {

        //Create Scanner object
        Scanner scanner = new Scanner(System.in);

        //Sets up the map
        Map gameMap = new Map(mapWidth, mapHeight);

        //Creates an instance of each game object
        Player player = new Player();
        Entry entry = new Entry();

        //Game Objects
        gameMap.placeObject(entry, gameStartLocationX, gameStartLocationY);
        gameMap.placeObject(player, gameStartLocationX, gameStartLocationY);















        //Main Game Loop
        for (int i = 0; i <= maxSteps; i++){

            //Get Player info
            int health = player.getPlayerHealth();
            int score = player.getPlayerScore();

            //Draws the map to the Console
            gameMap.printMap();

            //Player Input Handling
            String movePlayer;
            boolean inputLoop = true;

            while (inputLoop) {
                System.out.println("Score: " + score + " | Health: " + health);
                System.out.println("Move:" + (i + 1) + " {'u','d','l','r'}--> ");
                movePlayer = scanner.nextLine();
                System.out.println(" ");

                inputLoop = false;

                if (Objects.equals(movePlayer, "u")) {
                    gameMap.up(player);
                } else if (Objects.equals(movePlayer, "d")) {
                    gameMap.down(player);
                } else if (Objects.equals(movePlayer, "l")) {
                    gameMap.left(player);
                } else if (Objects.equals(movePlayer, "r")) {
                    gameMap.right(player);
                } else {
                    System.out.println("!!!Invalid Move!!!");
                    inputLoop = true;
                }


            }



            //Cell Occupancy Check
            List<GameObject> objectsInCell = gameMap.cellObjects(player.getX(), player.getY());

            for (GameObject obj: objectsInCell){
                if (obj instanceof Entry){System.out.println("Entry");}


            }


        }
    }




    // OLD Code


    /**
     * An example board to store the current game state.

     * Note: depending on your game, you might want to change this from 'int' to String or something?
     */
    private Cell[][] map;


    /**
     * Creates a square game board.
     *
     * @param size the width and height.
     */

    //Visual Map
    public GameEngine(int size) {
        map = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = new Cell();
                Text text = new Text(" ");
                cell.getChildren().add(text);
                map[i][j] = cell;
            }
        }


        map[0][0].setStyle("-fx-background-color: #7baaa4");
        map[size-1][size-1].setStyle("-fx-background-color: #7baaa4");
    }



    /**
     * The size of the current game.
     *
     * @return this is both the width and the height.
     */
    public int getSize() {
        return map.length;
    }

    /**
     * The map of the current game.
     *
     * @return the map, which is a 2d array.
     */
    public Cell[][] getMap() {
        return map;
    }

//    /**
//     * Plays a text-based game
//     */
//    public static void main(String[] args) {
//        GameEngine engine = new GameEngine(10);
//        System.out.printf("The size of map is %d * %d\n", engine.getSize(), engine.getSize());
//    }

}
