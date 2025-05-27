package dungeon.engine;

import javafx.scene.text.Text;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class GameEngine {



    public static void main(String[] args) {

        //Create Scanner object
        Scanner scanner = new Scanner(System.in);

        //Sets up the map
        Map gameMap = new Map(10, 10);

        //Creates an instance of each game object
        Player player = new Player();
        Entry entry = new Entry();

        //Testing
        gameMap.placeObject(entry, 2, 2);
        gameMap.placeObject(player, 5, 5);


        //Main Game Loop
        for (int i = 0; i <= 100; i++){

            gameMap.printMap();

            String movePlayer = null;

            System.out.println("Move {'u','d','l','r'}: ");
            movePlayer = scanner.nextLine();
            System.out.println(" ");


            if (Objects.equals(movePlayer, "u")) {gameMap.up(player);}
            if (Objects.equals(movePlayer, "d")) {gameMap.down(player);}
            if (Objects.equals(movePlayer, "l")) {gameMap.left(player);}
            if (Objects.equals(movePlayer, "r")) {gameMap.right(player);}


            //Check what objects are in the same cell as the player
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
