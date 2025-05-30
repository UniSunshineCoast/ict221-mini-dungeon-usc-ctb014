package dungeon.engine;

import java.util.List;

public class GameEngine2 {

    private static final int mapWidth = 11;
    private static final int mapHeight = 11;
    private static final int nLevels = 3;

    private int gameStartLocationX = 1;
    private int gameStartLocationY = mapHeight - 2;

    private int difficulty = 3;
    private boolean lost = false;

    private Map gameMap;
    private Player player;
    private Entry entry;
    private Ladder ladder;

    private int currentLevel = 1;

    public GameEngine2() {
        gameMap = new Map(mapWidth, mapHeight);
        setupGame();
    }

    private void setupGame() {

        // Generate Maze
        Wall wall = new Wall();
        Maze.generateMaze(gameMap, wall, 3, 30);

        player = new Player();
        entry = new Entry();
        ladder = new Ladder();

        // Place basic objects
        gameMap.placeObject(entry, gameStartLocationX, gameStartLocationY);
        gameMap.placeObject(player, gameStartLocationX, gameStartLocationY);
        gameMap.randomObjectPlace(ladder);


        // Add Objects
        for (int i = 0; i < 5; i++) gameMap.randomObjectPlace(new Gold(2));
        for (int i = 0; i < 5; i++) gameMap.randomObjectPlace(new Trap(2));
        for (int i = 0; i < 2; i++) gameMap.randomObjectPlace(new Health(4));
        for (int i = 0; i < 3; i++) gameMap.randomObjectPlace(new MeleeMutant(2,2));
        for (int i = 0; i < difficulty; i++) gameMap.randomObjectPlace(new RangedMutant(2,2));
    }

    // Called by Console or GUI for player movement
    public void movePlayer(String direction) {
        switch (direction) {
            case "u" -> gameMap.up(player);
            case "d" -> gameMap.down(player);
            case "l" -> gameMap.left(player);
            case "r" -> gameMap.right(player);
            default -> { /* Ignore invalid moves */ }
        }
        player.addStep();
        processTurn();
    }

    // Processes one turn (mutants, traps, health, etc.)
    private void processTurn() {
        // Gold pickup
        Gold gold = gameMap.getObjectInCell(player.getX(), player.getY(), Gold.class);
        if (gold != null) {
            player.setPlayerScore(gold.getValue(), '+');
            gameMap.removeObject(gold);
        }

        // Trap
        Trap trap = gameMap.getObjectInCell(player.getX(), player.getY(), Trap.class);
        if (trap != null) {
            player.setPlayerHealth(trap.getDamage(), '-');
        }

        // Health potion
        Health healthPotion = gameMap.getObjectInCell(player.getX(), player.getY(), Health.class);
        if (healthPotion != null) {
            player.setPlayerHealth(healthPotion.getHealthValue(), '+');
            gameMap.removeObject(healthPotion);
        }

        // Melee mutant
        MeleeMutant mutant = gameMap.getObjectInCell(player.getX(), player.getY(), MeleeMutant.class);
        if (mutant != null) {
            player.setPlayerHealth(mutant.getDamage(), '-');
            player.setPlayerScore(mutant.getValue(), '+');
            gameMap.removeObject(mutant);
        }

        // Ranged mutant attack check (adjacent cells)
        List<int[]> checkOffsets = List.of(new int[]{0, -2}, new int[]{0, 2}, new int[]{-2, 0}, new int[]{2, 0});
        for (int[] offset : checkOffsets) {
            int checkX = player.getX() + offset[0];
            int checkY = player.getY() + offset[1];
            if (checkX >= 0 && checkX < mapWidth && checkY >= 0 && checkY < mapHeight) {
                RangedMutant rMutant = gameMap.getObjectInCell(checkX, checkY, RangedMutant.class);
                if (rMutant != null && rMutant.tryAttack()) {
                    player.setPlayerHealth(rMutant.getDamage(), '-');
                }
            }
        }

        RangedMutant rMutant = gameMap.getObjectInCell(player.getX(), player.getY(), RangedMutant.class);
        if (rMutant != null) {
            player.setPlayerScore(rMutant.getValue(), '+');
            gameMap.removeObject(rMutant);
        }

        // Ladder check
        Ladder ladder = gameMap.getObjectInCell(player.getX(), player.getY(), Ladder.class);
        if (ladder != null) {
            nextLevel();
        }

        // Check if player has lost
        if (player.gameOver) {
            lost = true;
        }
    }

    public void nextLevel() {
        currentLevel++;
        difficulty += 2;
        gameMap.clearMap();

        //Sets player and entry position to the ladder's position
        gameStartLocationX = ladder.getX();
        gameStartLocationY = ladder.getY();


        //Sets up new level
        setupGame();
    }

    public int getPlayerSteps(){
        return player.getSteps();
    }

    public boolean isGameOver() {
        return lost;
    }

    public void forceLoose(){
        lost = true;
    }

    public boolean isLost() {
        return lost;
    }

    // GUI/Console helper methods
    public Player getPlayer() { return player; }
    public Map getMap() { return gameMap; }
    public int getCurrentLevel() { return currentLevel; }
    public int getDifficulty() { return difficulty; }

    public int getSizeX() { return mapWidth; }
    public int getSizeY() { return mapHeight; }

    // Console-specific helper
    public void printMapToConsole() {
        gameMap.printMap();
    }


}
