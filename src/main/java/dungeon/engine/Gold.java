package dungeon.engine;

public class Gold extends GameObject{

    private final int value; //How much the gold will increase the player's score

    //Constructor
    public Gold(int value){
        this.value = value;
        this.blocksMovement = false;
    }

    //Gets the current coins value
    public int getValue(){
        return value;
    }


    public String getSymbol() {return "G";}

    public String getImagePath() {return "/Gold.png";}
}
