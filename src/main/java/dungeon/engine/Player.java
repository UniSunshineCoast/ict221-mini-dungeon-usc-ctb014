package dungeon.engine;

public class Player extends GameObject{

    //Player Constants
    private int maxHealth = 10;
    public boolean gameOver = false;

    // Player Attributes
    private int health;
    private int score;
    private int steps;

    //Constructor
    public Player() {
        //Player Constants
        this.health = maxHealth;
        this.steps = 100;
    }

    public String getSymbol() {return "P";}


    // Changes the score based on an operation
    public void setPlayerScore(int scoreAmount, char operation) {
        if (operation == '+') {score += scoreAmount;}
        if (operation == '-') {score -= scoreAmount;}
        if (operation == '*') {score *= scoreAmount;}
        if (operation == '/') {score /= scoreAmount;}
        if (operation == '!') {score = scoreAmount;}
    }

    // Gets the current player score
    public int getPlayerScore() {return score;}


    // Changes the health based on an operation
    public void setPlayerHealth(int healthAmount, char operation) {
        if (operation == '+') {health += healthAmount;}
        if (operation == '-') {health -= healthAmount;}
        if (operation == '*') {health *= healthAmount;}
        if (operation == '/') {health /= healthAmount;}
        if (operation == '!') {health = healthAmount;}

        //Checks for Game Over
        if (health <= 0) {gameOver = true;}

        //Checks if health is greater than  max health
        if (health > maxHealth) {health = maxHealth;}
    }

    // Gets the current player health
    public int getPlayerHealth() {return health;}


    //Gets the remanding steps
    public int getSteps() {return steps;}



}
