package dungeon.engine;

public class Player {

    // Player Attributes
    private int health = 10;
    private int score = 0;
    private int steps = 100;

    public int x;
    public int y;


    // Changes the score based on an operation
    public void set_player_score(int scoreAmount, char operation) {
        if (operation == '+') {score += scoreAmount;}
        if (operation == '-') {score -= scoreAmount;}
        if (operation == '*') {score *= scoreAmount;}
        if (operation == '/') {score /= scoreAmount;}
        if (operation == '!') {score = scoreAmount;}
    }

    // Gets the current player score
    public int get_player_score() {return score;}


    // Changes the health based on an operation
    public void set_player_health(int healthAmount, char operation) {
        if (operation == '+') {health += healthAmount;}
        if (operation == '-') {health -= healthAmount;}
        if (operation == '*') {health *= healthAmount;}
        if (operation == '/') {health /= healthAmount;}
        if (operation == '!') {health = healthAmount;}
    }

    // Gets the current player health
    public int get_player_health() {return health;}



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
    public int get_steps() {return steps;}


}
