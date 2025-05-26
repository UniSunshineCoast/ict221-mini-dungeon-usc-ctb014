package dungeon.engine;

public class Player {

    //Player Constants
    int maxHealth = 10;

    // Player Attributes
    private int health;
    private int score;
    private int steps;

    private int x;
    private int y;


    //Constructor
    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        //Player Constants
        this.health = maxHealth;
        this.steps = 100;
    }


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
    }

    // Gets the current player health
    public int getPlayerHealth() {return health;}



    //Player Movement
    public void up(){
        y++;
        steps--;
    }

    public void down(){
        y--;
        steps--;
    }

    public void left(){
        x--;
        steps--;
    }

    public void right(){
        x++;
        steps--;
    }

    //Gets the remanding steps
    public int getSteps() {return steps;}

    //Get Current Player Position
    public int getX() {return x;}
    public int getY() {return y;}


    //Set Player Position
    public void setX(int newX){x = newX;}
    public void setY(int newY){y = newY;}

}
