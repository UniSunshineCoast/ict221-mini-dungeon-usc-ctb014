package dungeon.engine;

public class Trap extends GameObject{

    private final int damage; //How much damage the trap will cause

    //Constructor
    public Trap(int damage){
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }


    public String getSymbol() {return("T");}
}
