package dungeon.engine;

public class Trap extends GameObject{

    private final int damage; //How much damage the trap will cause

    //Constructor
    public Trap(int damage){
        this.damage = damage;
        this.blocksMovement = false;
    }

    public int getDamage(){
        return damage;
    }


    public String getSymbol() {return("T");}

    public String getImagePath() {return "/Fire.png";}
}
