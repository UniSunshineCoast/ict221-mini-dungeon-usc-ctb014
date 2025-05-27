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
    private static int gameStartLocationX = 0;
    private static int gameStartLocationY = mapHeight - 1;

    //Max Steps
    private static final int maxSteps = 100;

    //Number of Levels
    private static final int nLevels = 2;

    //Check for loss state
    private static boolean lost = false;



    //Main Game Code
    public static void main(String[] args) {

        //DECLARATIONS

        //Create Scanner object
        Scanner scanner = new Scanner(System.in);

        //Sets up the map
        Map gameMap = new Map(mapWidth, mapHeight);

        //Creates an instance of each class/object needed
        Player player = new Player();
        Entry entry = new Entry();

        Gold gold1 = new Gold(2);
        Gold gold2 = new Gold(2);
        Gold gold3 = new Gold(2);
        Gold gold4 = new Gold(2);
        Gold gold5 = new Gold(2);

        Trap trap1 = new Trap(2);
        Trap trap2 = new Trap(2);
        Trap trap3 = new Trap(2);
        Trap trap4 = new Trap(2);
        Trap trap5 = new Trap(2);

        Health health1 = new Health(4);
        Health health2 = new Health(4);

        MeleeMutant mutant1 = new MeleeMutant(2,2);
        MeleeMutant mutant2 = new MeleeMutant(2,2);
        MeleeMutant mutant3 = new MeleeMutant(2,2);

        Ladder ladder1 = new Ladder();



     /*____________________________________________________________*/

        //Map Set up

        UniveralGameLoop:
        for (int i = 0; i < nLevels; i++) {
            //Add game Objects to the map
            gameMap.placeObject(entry, gameStartLocationX, gameStartLocationY);
            gameMap.placeObject(player, gameStartLocationX, gameStartLocationY);

            //Gold
            gameMap.randomObjectPlace(gold1);
            gameMap.randomObjectPlace(gold2);
            gameMap.randomObjectPlace(gold3);
            gameMap.randomObjectPlace(gold4);
            gameMap.randomObjectPlace(gold5);

            //Traps
            gameMap.randomObjectPlace(trap1);
            gameMap.randomObjectPlace(trap2);
            gameMap.randomObjectPlace(trap3);
            gameMap.randomObjectPlace(trap4);
            gameMap.randomObjectPlace(trap5);

            //Health Potion
            gameMap.randomObjectPlace(health1);
            gameMap.randomObjectPlace(health2);

            //MeleeMutant
            gameMap.randomObjectPlace(mutant1);
            gameMap.randomObjectPlace(mutant2);
            gameMap.randomObjectPlace(mutant3);

            //Ladder
            gameMap.randomObjectPlace(ladder1);



            /*____________________________________________________________*/


            //Main Game Loop
            while (true) {

                //Get Player info
                int health = player.getPlayerHealth();
                int score = player.getPlayerScore();

                //Draws the map to the Console
                gameMap.printMap();

                //Step Counting
                player.addStep();

                //Player Input Handling
                String movePlayer;
                boolean inputLoop = true;

                while (inputLoop) { /* Loops until valid input */
                    System.out.println("Score: " + score + " | Health: " + health);
                    System.out.println("Move " + (player.getSteps()) + "/100 {'u','d','l','r'}--> ");
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

                /*_____________________________________________________*/
                //Player Stats updaters and logic

                //Gets the objects in the current cell
                List<GameObject> cellObjects = gameMap.cellObjects(player.getX(), player.getY());

                //Gold logic handling
                Gold gold = gameMap.getObjectInCell(player.getX(), player.getY(), Gold.class);
                if (gold != null) {
                    player.setPlayerScore(gold.getValue(), '+');
                    gameMap.removeObject(gold);
                }


                //Trap logic handling
                Trap trap = gameMap.getObjectInCell(player.getX(), player.getY(), Trap.class);
                if (trap != null) {
                    player.setPlayerHealth(trap.getDamage(), '-');
                }


                //Health Potion logic
                Health healthPotion = gameMap.getObjectInCell(player.getX(), player.getY(), Health.class);
                if (healthPotion != null) {
                    player.setPlayerHealth(healthPotion.getHealthValue(), '+');
                    gameMap.removeObject(healthPotion);
                }


                //Melee Mutant Logic
                MeleeMutant mutant = gameMap.getObjectInCell(player.getX(), player.getY(), MeleeMutant.class);
                if (mutant != null) {
                    player.setPlayerHealth(mutant.getDamage(), '-');
                    player.setPlayerScore(mutant.getValue(), '+');
                    gameMap.removeObject(mutant);
                }


                //Ladder Logic
                Ladder ladder = gameMap.getObjectInCell(player.getX(), player.getY(), Ladder.class);
                if (ladder != null) {
                    //Draws the map to the Console
                    gameMap.printMap();


                    gameStartLocationX = ladder1.getX();
                    gameStartLocationY = ladder1.getY();

                    gameMap.clearMap();

                    break;
                }



                /*_____________________________________________________*/

                //Check if player has lost

                if (player.gameOver){
                    lost = true;
                    break UniveralGameLoop;
                }



            }
        }

        //END OF GAME

        //Loose Condition
        if (lost){
            System.out.println("!!YOU LOST!!");
        }


        //Win Condition
        else{
            System.out.println("!!YOU WON!!");
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
