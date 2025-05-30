package dungeon.engine;



import java.util.List;
import java.util.Scanner;


public class GameEngine {


    //Game Constants

    /*
    Map Size
    Due to how my maze algorithm works it is impossible to create a maze that is not an odd number
     */
    private static final int mapWidth = 11;
    private static final int mapHeight = 11;

    //Start Location
    private static int gameStartLocationX = 1;
    private static int gameStartLocationY = mapHeight - 2;


    //Number of Levels
    private static final int nLevels = 2;

    //Check for loss state
    private static boolean lost = false;

    //Difficulty
    private static int difficulty = 3;


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

        MeleeMutant mutant1 = new MeleeMutant(2, 2);
        MeleeMutant mutant2 = new MeleeMutant(2, 2);
        MeleeMutant mutant3 = new MeleeMutant(2, 2);

        RangedMutant rangedMutant1 = new RangedMutant(2, 2);
        RangedMutant rangedMutant2 = new RangedMutant(2, 2);
        RangedMutant rangedMutant3 = new RangedMutant(2, 2);
        RangedMutant rangedMutant4 = new RangedMutant(2, 2);
        RangedMutant rangedMutant5 = new RangedMutant(2, 2);
        RangedMutant rangedMutant6 = new RangedMutant(2, 2);
        RangedMutant rangedMutant7 = new RangedMutant(2, 2);
        RangedMutant rangedMutant8 = new RangedMutant(2, 2);
        RangedMutant rangedMutant9 = new RangedMutant(2, 2);
        RangedMutant rangedMutant10 = new RangedMutant(2, 2);
        RangedMutant rangedMutant11 = new RangedMutant(2, 2);
        RangedMutant rangedMutant12 = new RangedMutant(2, 2);


        Ladder ladder1 = new Ladder();

        Wall wall = new Wall();




        /*____________________________________________________________*/

        //Difficulty setup

        String difficultyInput;
        boolean difficultyLoop = true;

        while (difficultyLoop) { /* Loops until valid input */
            System.out.println("Enter Game Difficulty {0 - 10}-->");
            difficultyInput = scanner.nextLine();
            System.out.println(" ");

            difficultyLoop = false;

            switch (difficultyInput) {
                case "0" -> difficulty = 0;
                case "1" -> difficulty = 1;
                case "2" -> difficulty = 2;
                case "3" -> difficulty = 3;
                case "4" -> difficulty = 4;
                case "5" -> difficulty = 5;
                case "6" -> difficulty = 6;
                case "7" -> difficulty = 7;
                case "8" -> difficulty = 8;
                case "9" -> difficulty = 9;
                case "10" -> difficulty = 10;
                case "11" -> difficulty = 11;
                case "12" -> difficulty = 12;
                case null, default -> {
                    System.out.println("!!!Invalid Difficulty!!!");
                    difficultyLoop = true;
                }
            }
        }

        System.out.println("Difficulty is set to {" + difficulty + "}");
        sleep(1000);



        /*____________________________________________________________*/
        //Map Set up
        UniversalGameLoop:
        for (int i = 0; i < nLevels; i++) {

            //Walls
            Maze.generateMaze(gameMap, wall, 3, 30);

            //Ladder
            gameMap.randomObjectPlace(ladder1);

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


            //Ranged Mutant
            if (difficulty >= 1) {
                gameMap.randomObjectPlace(rangedMutant1);
            }
            if (difficulty >= 2) {
                gameMap.randomObjectPlace(rangedMutant2);
            }
            if (difficulty >= 3) {
                gameMap.randomObjectPlace(rangedMutant3);
            }
            if (difficulty >= 4) {
                gameMap.randomObjectPlace(rangedMutant4);
            }
            if (difficulty >= 5) {
                gameMap.randomObjectPlace(rangedMutant5);
            }
            if (difficulty >= 6) {
                gameMap.randomObjectPlace(rangedMutant6);
            }
            if (difficulty >= 7) {
                gameMap.randomObjectPlace(rangedMutant7);
            }
            if (difficulty >= 8) {
                gameMap.randomObjectPlace(rangedMutant8);
            }
            if (difficulty >= 9) {
                gameMap.randomObjectPlace(rangedMutant9);
            }
            if (difficulty >= 10) {
                gameMap.randomObjectPlace(rangedMutant10);
            }
            if (difficulty >= 11) {
                gameMap.randomObjectPlace(rangedMutant11);
            }
            if (difficulty >= 12) {
                gameMap.randomObjectPlace(rangedMutant12);
            }







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

                    switch (movePlayer) {
                        case "u" -> gameMap.up(player);
                        case "d" -> gameMap.down(player);
                        case "l" -> gameMap.left(player);
                        case "r" -> gameMap.right(player);
                        case null, default -> {
                            System.out.println("!!!Invalid Move!!!");
                            inputLoop = true;
                        }
                    }
                }

                /*_____________________________________________________*/
                //Player Stats updaters and logic

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


                // Ranged Mutant Logic

                List<int[]> checkOffSet = List.of(new int[]{0, -2}, new int[]{0, 2}, new int[]{-2, 0}, new int[]{2, 0});


                for (int[] checkCell : checkOffSet) {
                    int checkX = player.getX() + checkCell[0];
                    int checkY = player.getY() + checkCell[1];

                    if (checkX >= 0 && checkX < mapWidth && checkY >= 0 && checkY < mapHeight) {
                        RangedMutant rangedMutant = gameMap.getObjectInCell(checkX, checkY, RangedMutant.class);

                        if (rangedMutant != null && rangedMutant.tryAttack()) {
                            player.setPlayerHealth(rangedMutant.getDamage(), '-');
                        }
                    }

                }


                RangedMutant rangedMutant = gameMap.getObjectInCell(player.getX(), player.getY(), RangedMutant.class);
                if (rangedMutant != null) {
                    player.setPlayerScore(rangedMutant.getValue(), '+');
                    gameMap.removeObject(rangedMutant);
                }


                //Ladder Logic
                Ladder ladder = gameMap.getObjectInCell(player.getX(), player.getY(), Ladder.class);
                if (ladder != null) {
                    //Draws the map to the Console
                    gameMap.printMap();

                    gameStartLocationX = ladder1.getX();
                    gameStartLocationY = ladder1.getY();
                    difficulty += 2;

                    //Console Update to show next level is updating
                    System.out.println("!!!NEXT LEVEL!!!");
                    System.out.print("Loading");

                    sleep(400);
                    for (int j = 0; j < 3; j++) {
                        System.out.print(".");
                        sleep(400);
                    }
                    System.out.println(" ");

                    //Clears Game Field
                    gameMap.clearMap();

                    break;
                }



                /*_____________________________________________________*/

                //Check if player has lost

                if (player.gameOver) {
                    lost = true;
                    break UniversalGameLoop;
                }


            }
        }

        //END OF GAME

        //Loose Condition
        if (lost) {
            System.out.println("!!YOU LOST!!");
        }


        //Win Condition
        else {
            System.out.println("!!YOU WON!!");
        }
    }


    //Stops the program for a certain amount of time
    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
